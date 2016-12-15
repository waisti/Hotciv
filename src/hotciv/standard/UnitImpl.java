package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created by troels on 11/11/2016.
 */
public class UnitImpl implements Unit {

    private String type;
    private Player owner;
    private int moveCount;

    public UnitImpl(String type, Player owner){
        this.type = type;
        this.owner = owner;
        moveCount = 1;
    }

    @Override
    public String getTypeString() {
        return type;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {

        return moveCount;
    }
    public void setMoveCount(int moveSpent){
        moveCount = moveSpent;
    }

    @Override
    public int getDefensiveStrength() {
        int strength = 0;
        if(type == GameConstants.ARCHER){
           strength = 3;
        }
        else if(type == GameConstants.LEGION){
            strength = 2;
        }
        else if(type == GameConstants.SETTLER){
            strength = 3;
        }
        else if(type == NewConstants.BOMB){
            strength = 1;
        }
        return strength;
    }

    @Override
    public int getAttackingStrength() {
        int strength = 0;
        if(type == GameConstants.ARCHER){
            strength = 2;
        }
        else if(type == GameConstants.LEGION){
            strength = 4;
        }
        else if(type == GameConstants.SETTLER){
            strength = 0;
        }
        else if(type == NewConstants.BOMB){
            strength =0;
        }
        return strength;

    }
}
