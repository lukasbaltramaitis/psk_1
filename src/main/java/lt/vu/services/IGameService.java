package lt.vu.services;

import java.io.Serializable;

public interface IGameService extends Serializable {
    int registerPlayerForRoundStart();
    void registerPlayerForRoundEnd();
    boolean canEndRound();
    boolean canStartRound();
    int getRoundNr(boolean change);
    void changeRoundNr();
}
