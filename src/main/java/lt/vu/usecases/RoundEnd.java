package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;

import lt.vu.entities.Action;
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
    private GameService gameService;
    @Inject
    private ActionsDAO actionsDAO;
    @Getter @Setter
    private List<Action> actions;
    @PostConstruct
    public void init() {
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
        return "roundEnd.xhtml?faces-redirect=true";
    }

    private void loadActions(int roundNr){
        actions = actionsDAO.selectByRound(roundNr);
    }
}
