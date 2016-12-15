package hotciv.standard;

import hotciv.framework.*;

import java.util.Iterator;

/**
 * Created by Sine on 01-12-2016.
 */
public class ThetaUnitStrategy implements UnitStrategy{
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
        else if(unit.getTypeString() == NewConstants.BOMB){
            Iterator<Position> neighborhood = Utility.get8NeighborhoodIterator(p);
            Position position;
            GameImpl gameImpl = (GameImpl) game;
            while ( neighborhood.hasNext() ) {
                position = neighborhood.next();
                if ( game.getUnitAt(position) != null) {
                    gameImpl.removeUnit(position);
                }
            }
            gameImpl.removeUnit(p);
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
        else if (city.getProduction() == NewConstants.BOMB){
            unitCost = 60;
        }
        return unitCost;
    }
}
