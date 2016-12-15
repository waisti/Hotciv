package hotciv.standard;
import hotciv.framework.*;

import thirdparty.ThirdPartyFractalGenerator;

import java.util.HashMap;

public class FractalAdapter implements WorldLayoutStrategy {


    private HashMap tiles = new HashMap();
    private HashMap cities = new HashMap();
    private HashMap units = new HashMap();

    private ThirdPartyFractalGenerator mapCreator;

    public FractalAdapter(){
     mapCreator = new ThirdPartyFractalGenerator();
    }


    @Override
    public HashMap createCities() {
        return null;
    }

    @Override
    public HashMap createUnits() {
        return null;
    }

    @Override
    public HashMap createTiles() {

        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = mapCreator.getLandscapeAt(r,c);
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
