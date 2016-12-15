package hotciv.standard;

import hotciv.framework.Game;

/**
 * Created by troels on 25/11/2016.
 */
public class GammaFactory implements GameFactory {
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new AlphaWinnerStrategy();
    }

    @Override
    public WorldStrategy createWorldStrategy() {
        return new AlphaWorldStrategy();
    }

    @Override
    public UnitStrategy createUnitStrategy() {
        return new GammaUnitStrategy();
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
