package hotciv.standard;

import hotciv.framework.*;
import java.util.*;
/**
 * Created by Sine on 24-11-2016.
 */
public class EpsilonBattleStrategy implements BattleStrategy {
    private DieRollDecisionStrategy dieRollDesStrat;

    public EpsilonBattleStrategy(DieRollDecisionStrategy dieRoll){
        dieRollDesStrat = dieRoll;
    }

    public boolean outcomeOfBattle(Game game, Position attackPos, Position defensePos) {
        int A = combinedAttackStrength(game, attackPos);
        int D = combinedDefensiveStrength(game, defensePos);
        int ARandom = A * dieRoll();
        int DRandom = D * dieRoll();
        if (ARandom > DRandom){
            return true;
        }else {
            return false;
        }
    }

    public static int combinedAttackStrength(Game game, Position attackPos){

        Unit attacker = game.getUnitAt(attackPos);
        int strength = attacker.getAttackingStrength();
        int friendlySupport = Utility.getFriendlySupport(game, attackPos, attacker.getOwner());
        int terrainFactor = Utility.getTerrainFactor(game, attackPos);

        return (strength + friendlySupport) * terrainFactor;
    }

    public static int combinedDefensiveStrength(Game game, Position defensePos) {
        Unit defender = game.getUnitAt(defensePos);
        int strength = defender.getDefensiveStrength();
        int friendlySupport = Utility.getFriendlySupport(game, defensePos, defender.getOwner());
        int terrainFactor = Utility.getTerrainFactor(game, defensePos);

        return (strength + friendlySupport) * terrainFactor;
    }

    public int dieRoll(){
        return dieRollDesStrat.dieRoll();
    }



}
