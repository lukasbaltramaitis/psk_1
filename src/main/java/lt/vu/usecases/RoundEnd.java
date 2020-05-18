package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;

import lt.vu.entities.Action;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.PlayerMapper;
import lt.vu.mybatis.dao.TerritoryMapper;
import lt.vu.persistence.ActionsDAO;
import lt.vu.persistence.PlayersDAO;
import lt.vu.services.GameService;
import lt.vu.services.IGameService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
@LoggedInvocation
@Model
public class RoundEnd {
    @Inject
    private PlayerMapper playerMapper;
    @Inject
    private TerritoryMapper territoryMapper;
    @Inject
    @Getter
    private IGameService gameService;
    @Inject
    private ActionsDAO actionsDAO;
    @Getter @Setter
    private List<Action> actions;
    private int playerId;
    @PostConstruct
    public void init() {
        playerId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().get("userId").toString());
        loadActions(gameService.getRoundNr(false));
    }
    @Transactional
    public String endRound(){
        gameService.registerPlayerForRoundEnd();
        long startTimestamp = System.currentTimeMillis();
        while(true){
            if(gameService.canEndRound()){
                break;
            }
            else{
                long currentTimeStamp = System.currentTimeMillis();
                if(startTimestamp + 60000 > currentTimeStamp){
                    break;
                }
            }
        }
        playerMapper.setMoneyBack();
        territoryMapper.updateStateByPlayer(0, playerId);
        return "roundEnd.xhtml?faces-redirect=true";
    }

    private void loadActions(int roundNr){
        actions = actionsDAO.selectByRound(roundNr);
    }
}
