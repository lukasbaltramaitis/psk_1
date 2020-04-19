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
    private boolean allPlayersRegistered = false;
    private int playersRegForRndStart = 0;
    @Inject
    private PlayersDAO playersDAO;
    @Getter @Setter
    private int roundNr = 1;

    public void registerPlayerForRoundStart(){
        playersRegForRndStart++;
    }
    public boolean canStartRound(){
        int playersNr = playersDAO.getCount();
        return playersNr == playersRegForRndStart;
    }

}
