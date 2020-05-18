package lt.vu.services;

public class SimpleGameService implements IGameService {
    private int registerPlayersNumber = 0;
    private static int roundNr = 0;
    @Override
    public void registerPlayerForRoundStart() {
        registerPlayersNumber++;
    }

    @Override
    public void registerPlayerForRoundEnd() {
        registerPlayersNumber--;
    }

    @Override
    public boolean canEndRound() {
        return registerPlayersNumber == 0;
    }

    @Override
    public boolean canStartRound() {
        return registerPlayersNumber > 0;
    }

    @Override
    public int getRoundNr(boolean change) {
        return roundNr;
    }

    @Override
    public void changeRoundNr() {
        roundNr++;
    }
}
