package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;

import lt.vu.entities.Action;
import lt.vu.mybatis.dao.TerritoryMapper;
import lt.vu.persistence.ActionsDAO;
import lt.vu.services.GameService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

@Model
public class RoundEnd {
    @Inject
    private TerritoryMapper territoryMapper;
    @Inject
    private GameService gameService;
    @Inject
    private ActionsDAO actionsDAO;
    @Getter @Setter
    private List<Action> actions;
    private int playerId;
    @PostConstruct
    public void init() {
        playerId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().get("userId").toString());
        loadActions(gameService.getRoundNr());
    }

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
        territoryMapper.updateStateByPlayer(0, playerId);
        return "roundEnd.xhtml?faces-redirect=true";
    }

    private void loadActions(int roundNr){
        actions = actionsDAO.selectByRound(roundNr);
    }
}
