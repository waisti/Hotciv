package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

/**
 * Created by Jon on 18-11-2016.
 */
public interface WorldLayoutStrategy {

    public HashMap createCities();

    public HashMap createUnits();

    public HashMap createTiles();

}
