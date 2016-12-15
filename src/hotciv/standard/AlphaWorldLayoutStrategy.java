package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

/**
 * Created by Jon on 18-11-2016.
 */
public class AlphaWorldLayoutStrategy implements WorldLayoutStrategy {

    private HashMap tiles = new HashMap();
    private HashMap cities = new HashMap();
    private HashMap units = new HashMap();

    @Override
    public HashMap createCities() {
        cities.put(new Position(1,1), new CityImpl(Player.RED));
        cities.put(new Position(4,1), new CityImpl(Player.BLUE));
        return cities;
    }

    @Override
    public HashMap createUnits() {
        units.put(new Position(2,0), new UnitImpl(GameConstants.ARCHER, Player.RED));
        units.put(new Position(3,2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
        units.put(new Position(4,3), new UnitImpl(GameConstants.SETTLER, Player.RED));
        return units;
    }

    @Override
    public HashMap createTiles() {
        for (int r = GameConstants.WORLDSIZE-1;r>=0;r--){
            for (int c = GameConstants.WORLDSIZE-1;c>=0;c--){
                tiles.put(new Position(r,c), new TileImpl(GameConstants.PLAINS));
            }
        }
        tiles.put(new Position(1,0), new TileImpl(GameConstants.OCEANS));
        tiles.put(new Position(0,1), new TileImpl(GameConstants.HILLS));
        tiles.put(new Position(2,2), new TileImpl(GameConstants.MOUNTAINS));
        return tiles;
    }
}
