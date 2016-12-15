package hotciv.standard;

/**
 * Created by troels on 25/11/2016.
 */
public class EpsilonFactory implements GameFactory {
    DieRollDecisionStrategy dieRollDesStrat;

    public EpsilonFactory(DieRollDecisionStrategy dieRollDesStrat){
        this.dieRollDesStrat = dieRollDesStrat;
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new EpsilonWinnerStrategy();
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
        return new AlphaWorldLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new EpsilonBattleStrategy(dieRollDesStrat);
    }
}
