package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by Sine on 10-11-2016.
 */
public class TileImpl implements Tile {

private String type;

    public TileImpl(String type){
        this.type = type;
    }
    public String getTypeString() {
        return type;
    }
}
