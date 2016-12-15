package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by troels on 18/11/2016.123
 */


public class GammaUnitStrategy implements UnitStrategy{

    @Override
    public void performUnitActionAt(Game game, Position p) {
      UnitImpl unit = (UnitImpl) game.getUnitAt(p);
       if(unit.getTypeString() == GameConstants.SETTLER) {
           if (game.getCityAt(p) != null) {
               return;
           } else {
               GameImpl gameImpl = (GameImpl) game;
               gameImpl.createCity(p, unit.getOwner());
               gameImpl.removeUnit(p);

           }
       }
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
