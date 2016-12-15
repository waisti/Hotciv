package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by troels on 17/11/2016.
 */
public class AlphaWinnerStrategy implements WinnerStrategy {


    public Player getWinner(Game game) {
        if(game.getAge() < -3000) return null;
        else return Player.RED;
    }

    @Override
    public void attacksWon(Game game, Player player) {

    }


}
