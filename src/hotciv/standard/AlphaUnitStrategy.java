package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;

/**
 * Created by Sine on 01-12-2016.
 */
public class AlphaUnitStrategy implements UnitStrategy {
    @Override
    public void performUnitActionAt(Game game, Position p) {

    }

    @Override
    public int getUnitCost(Game game, City city, Position p) {
        int unitCost = 0;
        if(city.getProduction() == GameConstants.ARCHER) {
            unitCost = 10;
        }
        else if(city.getProduction() == GameConstants.LEGION){
            unitCost = 15;
        }
        else if(city.getProduction() == GameConstants.SETTLER){
            unitCost = 30;
        }
        return unitCost;
    }
}
