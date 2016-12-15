package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;

/**
 * Created by Sine on 25-11-2016.
 */
public class ZetaWinnerStrategy implements WinnerStrategy {
    private WinnerStrategy betaWinnerStrat, epsilonWinnerStrat, currentState;


    public ZetaWinnerStrategy(WinnerStrategy beta, WinnerStrategy epsilon){
        betaWinnerStrat = beta;
        epsilonWinnerStrat = epsilon;
        this.currentState = null;
    }

    public WinnerStrategy getCurrentState(Game game){
        if (lessThan20(game)){
            currentState = betaWinnerStrat;
        }else{
            currentState = epsilonWinnerStrat;
        }
        return currentState;
    }

    @Override
    public Player getWinner(Game game) {
        getCurrentState(game);
        return currentState.getWinner(game);
    }

    @Override
    public void attacksWon(Game game, Player player) {
        getCurrentState(game);
        currentState.attacksWon(game, player);
    }

    public boolean lessThan20(Game game){
        if(game.getAge()<=-2000) {
            return true;
        }
        else{
            return false;
        }
   }
}
