package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;

/**
 * Created by troels on 24/11/2016.
 */
public class EpsilonWinnerStrategy implements WinnerStrategy {
    int redAttacksWon = 0;
    int blueAttacksWon = 0;

    public Player getWinner(Game game) {
        Player winner = null;
        if (redAttacksWon == 3){
            winner = Player.RED;
        }
        else if (blueAttacksWon == 3){
            winner = Player.BLUE;
        }
        return winner;
    }

    public void attacksWon(Game game, Player player){
        if (player == Player.RED){
            redAttacksWon++;

        }
        else if (player == Player.BLUE){
            blueAttacksWon++;
        }
    }
}
