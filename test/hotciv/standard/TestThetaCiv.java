package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;

import static hotciv.framework.GameConstants.WORLDSIZE;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;


/**
 * Created by Sine on 01-12-2016.
 */
public class TestThetaCiv {

    private GameImpl game;
    private Position position;

    @Before
    public void setUp() {
        game = new GameImpl(new ThetaFactory());
    }

    @Test
    public void citiesCanProduceBombsfor60Production(){
        position = new Position(1,1);
        assertNull(game.getUnitAt(position));
        game.changeProductionInCityAt(position, NewConstants.BOMB);
        game.endTurn(20); //60 production
        assertThat(game.getUnitAt(position).getTypeString(),is(NewConstants.BOMB));
        assertNotNull(game.getUnitAt(position));
    }

    @Test
    public void bombsDestroyUnitsAroundIt(){
        position = new Position(4,1);
        game.changeProductionInCityAt(position, NewConstants.BOMB);
        game.endTurn(21); //60 production
        game.moveUnit(position, new Position(4,2));

        assertNotNull(game.getUnitAt(new Position(3,2))); //there's a Legion here
        assertNotNull(game.getUnitAt(new Position(4,3))); //there's a happy innocent family of settlers here
        game.performUnitActionAt(new Position(4,2)); // the bomb uses explode
        assertNull(game.getUnitAt(new Position(3,2))); //it's gone
        assertNull(game.getUnitAt(new Position(4,3))); //it's all gone
        assertNull(game.getUnitAt(new Position(4,2))); //what have we done (this was the bomb)


    }


}
