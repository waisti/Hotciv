package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by troels on 17/11/2016.
 */
public class BetaWinnerStrategy implements WinnerStrategy {

    public Player getWinner(Game game) {
        Player winner = null;
        GameImpl gameImpl = (GameImpl) game;
        Iterator cityIt = gameImpl.getCities().entrySet().iterator();
        while (cityIt.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)cityIt.next();
            CityImpl city = (CityImpl) pair.getValue();
            if (winner == null) {
                winner = city.getOwner();
            }else {
                if (winner.equals(city.getOwner())){
                    winner = city.getOwner();
                } else{
                    return null;
                }
            }
        }
        return winner;
    }

    
    public void attacksWon(Game game, Player player) {

    }

}
