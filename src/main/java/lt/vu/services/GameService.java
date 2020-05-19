package lt.vu.services;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.PlayersDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
@ApplicationScoped
@Model
@Alternative
public class GameService implements IGameService {
    private int playersRegForRndStart = 0;
    private int playersRegForRndEnd = 0;
    @Inject
    private PlayersDAO playersDAO;
    public static int roundNr = 1;

    private int newRoundNr = 1;

    public int registerPlayerForRoundStart(){
        try{
            Thread.sleep(5000);
        }
        catch (InterruptedException ignored){
        }
        return playersRegForRndStart++;
    }
    public void registerPlayerForRoundEnd(){
        playersRegForRndEnd++;
    }
    public boolean canEndRound(){
        newRoundNr = roundNr + 1;
        int playersNr = playersDAO.getCount();
        return playersNr == playersRegForRndEnd;
    }
    public boolean canStartRound(){
        changeRoundNr();
        int playersNr = playersDAO.getCount();
        return playersNr == playersRegForRndStart;
    }

    @Override
    public int getRoundNr(boolean change) {
        return roundNr;
    }

    public void changeRoundNr(){
        if(newRoundNr != roundNr){
            roundNr = newRoundNr;
        }
    }
}
