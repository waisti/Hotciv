package hotciv.standard;

import hotciv.framework.Game;

/**
 * Created by Sine on 01-12-2016.
 */
public class ThetaFactory implements GameFactory{
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
        return new ThetaUnitStrategy();
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
