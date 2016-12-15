package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by troels on 24/11/2016.
 */
public interface BattleStrategy {

    public boolean outcomeOfBattle(Game game, Position attackPos, Position defensePos);

}
