package hotciv.standard;

/**
 * Created by Sine on 25-11-2016.
 */
public class FixedDieRoll implements DieRollDecisionStrategy {
    private boolean AoD = true; // attacker wins if we don't tell it otherwise, because the first dieroll is 6 and the other is 1
    public FixedDieRoll(boolean AoD){
        this.AoD = AoD;
    }

    public FixedDieRoll(){

    }

    public int dieRoll() {
        if(AoD == true){
            AoD = false;
            return 6;
        }
        else{
            AoD = true;
            return 1;
        }
    }
}
