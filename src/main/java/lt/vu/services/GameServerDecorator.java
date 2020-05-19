package lt.vu.services;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class GameServerDecorator implements IGameService {
    @Inject
    @Delegate
    @Any
    private IGameService iGameService;
    public int getRoundNr(boolean change){
        if(change){
            iGameService.changeRoundNr();
        }
        return iGameService.getRoundNr(change);
    }
}
