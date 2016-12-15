package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;
/**
 * Created by Sine on 18-11-2016.
 */
public class DeltaWorldLayoutStrategy implements WorldLayoutStrategy {

    private HashMap tiles = new HashMap();
    private HashMap cities = new HashMap();
    private HashMap units = new HashMap();

    @Override
    public HashMap createCities() {
        cities.put(new Position(8,12), new CityImpl(Player.RED));
        cities.put(new Position(4,5), new CityImpl(Player.BLUE));
        return cities;
    }

    @Override
    public HashMap createUnits() {
        units.put(new Position(3,8), new UnitImpl(GameConstants.ARCHER, Player.RED));
        units.put(new Position(4,4), new UnitImpl(GameConstants.LEGION, Player.BLUE));
        units.put(new Position(5,5), new UnitImpl(GameConstants.SETTLER, Player.RED));
        return units;
    }

    @Override
    public HashMap createTiles() {
        String[] layout =
                new String[] {
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
                };
        // Conversion...
        String line;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(r,c);
                tiles.put( p, new TileImpl(type));
            }
        }
        return tiles;
    }

}
