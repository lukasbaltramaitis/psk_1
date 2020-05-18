package lt.vu.services;

public interface IGameService {
    void registerPlayerForRoundStart();
    void registerPlayerForRoundEnd();
    boolean canEndRound();
    boolean canStartRound();
    int getRoundNr(boolean change);
    void changeRoundNr();
}
