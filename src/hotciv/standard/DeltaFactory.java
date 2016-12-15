package hotciv.standard;

/**
 * Created by troels on 25/11/2016.
 */
public class DeltaFactory implements GameFactory {
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
        return new AlphaUnitStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new DeltaWorldLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new AlphaBattleStrategy();
    }
}
