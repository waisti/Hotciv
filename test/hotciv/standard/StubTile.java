package hotciv.standard;

import hotciv.framework.Tile;

/**
 * Created by troels on 01/12/2016.
 */
public class StubTile implements Tile{
    private String type;
    public StubTile(String type, int r, int c) { this.type = type; }
    public String getTypeString() { return type; }
}
