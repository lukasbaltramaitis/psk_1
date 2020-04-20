package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Action;
import lt.vu.entities.Player;
import lt.vu.mybatis.dao.TerritoryMapper;
import lt.vu.mybatis.model.Territory;
import lt.vu.persistence.ActionsDAO;
import lt.vu.persistence.PlayersDAO;
import lt.vu.services.GameService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class ActionsForPlayer implements Serializable {
    @Getter @Setter
    private Player player;
    @Getter @Setter
    private int territoryBId;
    @Inject
    private PlayersDAO playersDAO;
    @Inject
    private ActionsDAO actionsDAO;
    @Inject
    private TerritoryMapper territoryMapper;
    @Getter
    private List<Territory> playerTerritories;
    @Getter @Setter
    private Action action = new Action();
    @Inject
    private GameService gameService;
    @Getter @Setter
    private List<Territory>territoriesForB;
    @Getter @Setter
    private int priority;

    @PostConstruct
    public void init() {
        int playerId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().get("userId").toString());
        loadPlayer(playerId);
        loadPlayerTerritories(playerId);
    }

    public String startRound(){
        gameService.registerPlayerForRoundStart();
        long startTimestamp = System.currentTimeMillis();
        while(true){
            if(gameService.canStartRound()){
                break;
            }
            else{
                long currentTimeStamp = System.currentTimeMillis();
                if(startTimestamp + 60000 > currentTimeStamp){
                    break;
                }
            }
        }
        return "actions.xhtml?faces-redirect=true";
    }

    public void selectStringValueChanged(int territoryId)
    {
        switch (action.getAction()){
            case "attack":
                territoriesForB = territoryMapper.selectNeighbours(territoryId, player.getId());
                break;
            case "move":
                territoriesForB = territoryMapper.selectByPlayer(player.getId());
                break;
            case "defend":
            case "collect":
                territoriesForB = new ArrayList<>();
                break;
            default:
                action.setAction("");
                break;
        }
    }
    @Transactional
    public String registerAction(int territoryAId){
        territoryMapper.updateByState(1, territoryAId);
        action.setTerritoryAId(territoryAId);
        action.setCreationDate(new Timestamp(System.currentTimeMillis()));
        action.setRoundNr(gameService.getRoundNr());
        action.setPlayerId(player.getId());
        int actionPriority = priority;
        int money = player.getMoney();
        if(actionPriority > money){
            action.setPriority(money);
            money = 0;
        }
        else{
            money = money - actionPriority;
        }
        player.setMoney(money);
        playersDAO.update(player);
        actionsDAO.persist(action);
        return "actions.xhtml?faces-redirect=true";
    }

    private void loadPlayer(int playerId){
        player = playersDAO.findOne(playerId);
    }
    private void loadPlayerTerritories(int playerId){
        playerTerritories = territoryMapper.selectByPlayerAndState(playerId);
    }
}
