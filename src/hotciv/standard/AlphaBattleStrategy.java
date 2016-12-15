package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by Sine on 24-11-2016.
 */
public class AlphaBattleStrategy implements BattleStrategy {
    @Override
    public boolean outcomeOfBattle(Game game, Position attackPos, Position defensePos) {
        return true;
    }

}
