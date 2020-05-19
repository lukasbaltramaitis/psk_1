package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Action;
import lt.vu.entities.Player;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.TerritoryMapper;
import lt.vu.mybatis.model.Territory;
import lt.vu.persistence.ActionsDAO;
import lt.vu.persistence.PlayersDAO;
import lt.vu.services.GameService;
import lt.vu.services.IGameService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@LoggedInvocation
@Named
@SessionScoped
public class ActionsForPlayer implements Serializable {
    private CompletableFuture<Integer> playerStartRoundTask = null;
    @Getter @Setter
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
    private Action action = new Action();
    @Inject
    private IGameService gameService;
    @Getter @Setter
    private List<Territory>territoriesForB;
    @Getter @Setter
    private int priority;
    @Getter @Setter
    private Integer territoryBId;
    @Getter @Setter
    private Territory territoryA;

    @PostConstruct
    public void init() {
        int playerId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().get("userId").toString());
        loadPlayer(playerId);
        loadPlayerTerritories(playerId);
    }

    public String startRound(){
        playerStartRoundTask = CompletableFuture.supplyAsync(() -> gameService.registerPlayerForRoundStart());
        return "waitingRoom.xhtml?faces-redirect=true";
    }

    private boolean isPlayerStartRoundTaskRunning() {
        return playerStartRoundTask != null && !playerStartRoundTask.isDone();
    }

    public void getPlayerStartRoundTaskStatus() throws IOException {
        if(!isPlayerStartRoundTaskRunning()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("actions.xhtml?faces-redirect=true");
        }
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
            default:
                territoriesForB = new ArrayList<>();
                break;

        }
        if(territoriesForB.size() > 0){
            territoryBId = territoriesForB.get(0).getId();
        }
    }
    @Transactional
    public String registerAction(int territoryAId){
        territoryMapper.updateByState(1, territoryAId);
        action.setTerritoryAId(territoryAId);
        action.setTerritoryBId(territoryBId);
        action.setCreationDate(new Timestamp(System.currentTimeMillis()));
        action.setRoundNr(gameService.getRoundNr(false));
        action.setPlayerId(player.getId());
        int actionPriority = priority;
        int money = player.getMoney();
        if(actionPriority > money){
            actionPriority = money;
            money = 0;
        }
        else{
            money = money - actionPriority;
        }
        String actionString = action.getAction();
        if(actionString == null){
            action.setAction("collect");
        }
        action.setPriority(actionPriority);
        player.setMoney(money);
        playersDAO.update(player);
        actionsDAO.persist(action);
        return "actions.xhtml";
    }

    private void loadPlayer(int playerId){
        player = playersDAO.findOne(playerId);
    }
    private void loadPlayerTerritories(int playerId){
        playerTerritories = territoryMapper.selectByPlayerAndState(playerId);
    }
}
