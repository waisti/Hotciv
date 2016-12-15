package hotciv.standard;

/**
 * Created by troels on 01/12/2016.
 */
public class SemiFactory implements GameFactory {
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new EpsilonWinnerStrategy();
    }

    @Override
    public WorldStrategy createWorldStrategy() {
        return new BetaWorldStrategy();
    }

    @Override
    public UnitStrategy createUnitStrategy() {
        return new GammaUnitStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new DeltaWorldLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new EpsilonBattleStrategy(new FixedDieRoll());
    }


}
