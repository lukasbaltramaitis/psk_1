package lt.vu.services;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.PlayersDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
@ApplicationScoped
@Model
public class GameService implements Serializable {
    private int playersRegForRndStart = 0;
    private int playersRegForRndEnd = 0;
    @Inject
    private PlayersDAO playersDAO;
    public static int roundNr = 1;

    private int newRoundNr = 1;

    public void registerPlayerForRoundStart(){
        playersRegForRndStart++;
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
        if(newRoundNr != roundNr){
            roundNr = newRoundNr;
        }
        int playersNr = playersDAO.getCount();
        return playersNr == playersRegForRndStart;
    }

    public int getRoundNr() {
        return roundNr;
    }
}
