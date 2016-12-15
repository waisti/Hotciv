package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by troels on 18/11/2016.
 */
public interface UnitStrategy {

    public void  performUnitActionAt( Game game , Position p);

    public int getUnitCost(Game game, City city, Position p);
}
