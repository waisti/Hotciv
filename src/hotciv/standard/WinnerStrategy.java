package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by troels on 17/11/2016.
 */
public interface WinnerStrategy {
    /**
     * return the player that has won the game.
     *
     * @return the player that has won. If the game is still
     * not finished then return null.
     */
    public Player getWinner(Game game);

    public void attacksWon(Game game, Player player);


}
