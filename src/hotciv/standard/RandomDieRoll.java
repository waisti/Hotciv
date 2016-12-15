package hotciv.standard;

/**
 * Created by Sine on 25-11-2016.
 */
public class RandomDieRoll implements DieRollDecisionStrategy {
    @Override
    public int dieRoll() {
        return (int) Math.round((Math.random()* 6)+1); //integer value in range 1-6
    }
}
