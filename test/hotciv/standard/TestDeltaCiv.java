package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;

import static hotciv.framework.GameConstants.WORLDSIZE;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * Created by Sine on 18-11-2016.
 */
public class TestDeltaCiv {
    private GameImpl game;
    private City city;
    private Position position;
    private Tile tile;
    private Unit unit;

    /** Fixture for betaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new DeltaFactory());
    }

    @Test
    public void thereShouldBeRedCityAt8_12(){
        city = game.getCityAt(new Position(8,12));
        assertThat(city.getOwner(), is(Player.RED));
    }

    @Test
    public void thereShouldBeBlueCityAt4_5(){
        city = game.getCityAt(new Position(4,5));
        assertThat(city.getOwner(), is(Player.BLUE));
    }

    @Test
    public void theWorldShouldHaveCorrectTiles(){
        position = new Position(0,5);
        tile = game.getTileAt(position);
        assertThat(tile.getTypeString(), is(GameConstants.MOUNTAINS));

        position = new Position(5,1);
        tile = game.getTileAt(position);
        assertThat(tile.getTypeString(), is(GameConstants.PLAINS));

        position = new Position(WORLDSIZE-1,WORLDSIZE-1);
        tile = game.getTileAt(position);
        assertThat(tile.getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void thereShouldBeARedArcherAt3_8(){
        position = new Position(3,8);
        unit = game.getUnitAt(position);
        assertThat(unit.getOwner(), is(Player.RED));
        assertThat(unit.getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void thereShouldBeABlueLegionAt4_4(){
        position = new Position(4,4);
        unit = game.getUnitAt(position);
        assertThat(unit.getOwner(), is(Player.BLUE));
        assertThat(unit.getTypeString(), is(GameConstants.LEGION));
    }

    @Test
    public void thereShouldBeARedSettlerAt5_5(){
        position = new Position(5,5);
        unit = game.getUnitAt(position);
        assertThat(unit.getOwner(), is(Player.RED));
        assertThat(unit.getTypeString(), is(GameConstants.SETTLER));
    }
}
