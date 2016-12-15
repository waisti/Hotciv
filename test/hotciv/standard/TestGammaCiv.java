package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;

import static hotciv.framework.GameConstants.WORLDSIZE;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;


public class TestGammaCiv {
    private GameImpl game;


    /** Fixture for gammaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new GammaFactory());
    }

    @Test
    public void createCitiesAtPosition8_8(){
        game.createCity(new Position(8,8), Player.RED);
        assertNotNull(game.getCityAt(new Position(8,8)));

    }
    @Test
    public void canRemoveUnitAtPosition4_3(){
        game.removeUnit(new Position(4,3));
        assertNull(game.getUnitAt(new Position(4,3)));

    }

    @Test
    public void settlerShouldMakeCityAndDissapear(){
        assertNotNull(game.getUnitAt(new Position(4,3)));
        assertNull(game.getCityAt(new Position(4,3)));
        game.performUnitActionAt(new Position(4,3));
        assertNull(game.getUnitAt(new Position(4,3)));
        assertNotNull(game.getCityAt(new Position(4,3)));
    }
}

