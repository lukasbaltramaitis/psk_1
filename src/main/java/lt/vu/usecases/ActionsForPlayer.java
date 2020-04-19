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
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Model
public class ActionsForPlayer implements Serializable {
    private Player player;
    @Inject
    private PlayersDAO playersDAO;
    @Inject
    private ActionsDAO actionsDAO;
    @Inject
    private TerritoryMapper territoryMapper;
    @Getter
    private List<Territory> playerTerritories;
    @Getter @Setter
    private int priority = 10;
    @Getter @Setter
    private Action action = new Action();
    @Inject
    private GameService gameService;
    @Getter @Setter
    private List<Territory>territoriesForB = territoryMapper.selectAll();

    @PostConstruct
    public void init() {
        int playerId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().get("playerId").toString());
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

    public void selectStringValueChanged(ValueChangeEvent vce)
    {
        String actionValueParams = vce.getNewValue().toString();
        String[] params = actionValueParams.split("_");
        if(params.length > 1){
            int territoryId = Integer.parseInt(params[1]);
            switch (params[0]){
                case "attack":
                    territoriesForB = territoryMapper.selectNeighbours(territoryId);
                    break;
                case "move":
                    territoriesForB = territoryMapper.selectByPlayer(player.getId());
                    break;
                case "defend":
                case "collect":
                    territoriesForB = new ArrayList<>();
                    break;
            }
        }
    }
    @Transactional
    public String registerAction(int territoryAId){
        String actionStr = action.getAction();
        String[] actionStrParams = actionStr.split("_");
        if(actionStrParams.length > 0){
            action.setAction(actionStrParams[0]);
        }
        action.setTerritoryAId(territoryAId);
        action.setCreationDate(new Timestamp(System.currentTimeMillis()));
        action.setRoundNr(gameService.getRoundNr());
        action.setPlayerId(player.getId());
        if(priority > 0) {
            priority -= action.getPriority();
            if(priority < 0){
                priority = 0;
            }
        }
        actionsDAO.persist(action);
        return "actions.xhtml?faces-redirect=true";
    }

    private void loadPlayer(int playerId){
        player = playersDAO.findOne(playerId);
    }
    private void loadPlayerTerritories(int playerId){
        playerTerritories = territoryMapper.selectByPlayer(playerId);
    }
}
