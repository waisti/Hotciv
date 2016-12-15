package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created by troels on 01/12/2016.
 */
class StubUnit implements Unit {
    private String type; private Player owner;
    public StubUnit(String type, Player owner) {
        this.type = type; this.owner = owner;
    }
    public String getTypeString() { return type; }
    public Player getOwner() { return owner; }
    public int getMoveCount() { return 0; }
    public int getDefensiveStrength() { return 0; }
    public int getAttackingStrength() { return 0; }
}
