package hotciv.standard;

import hotciv.framework.Game;

/**
 * Created by troels on 25/11/2016.
 */
public class BetaFactory implements GameFactory {
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new BetaWinnerStrategy();
    }

    @Override
    public WorldStrategy createWorldStrategy() {
        return new BetaWorldStrategy();
    }

    @Override
    public UnitStrategy createUnitStrategy() {
        return new AlphaUnitStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new AlphaWorldLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new AlphaBattleStrategy();
    }
}
