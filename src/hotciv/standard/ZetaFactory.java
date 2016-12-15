package hotciv.standard;

/**
 * Created by troels on 25/11/2016.
 */
public class ZetaFactory implements GameFactory {
    public WinnerStrategy createWinnerStrategy() {
        return new ZetaWinnerStrategy(new BetaWinnerStrategy(), new EpsilonWinnerStrategy());
    }

    public WorldStrategy createWorldStrategy() {
        return new AlphaWorldStrategy();
    }

    public UnitStrategy createUnitStrategy() { return new AlphaUnitStrategy(); }

    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new AlphaWorldLayoutStrategy();
    }

    public BattleStrategy createBattleStrategy() {
        return new AlphaBattleStrategy();
    }
}
