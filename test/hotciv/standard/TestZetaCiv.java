package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Sine on 25-11-2016.
 */
//hej
public class TestZetaCiv {
    private Game game;
    private City city;
    private Position position;
    private Tile tile;
    private Unit unit;



    /** Fixture for alphaciv testing. */
    //hd
    @Before
    public void setUp() {
        game = new GameImpl(new ZetaFactory());
    }

    @Test
    public void before20RoundsPlayerThatTakesAllCitiesWins(){
        Position oldPosition = new Position(2,0);
        Position newPosition = new Position(4,1);
        assertThat(game.getCityAt(newPosition).getOwner(), is(Player.BLUE));
        game.moveUnit(oldPosition, new Position(2,1));
        game.endTurn(2);
        game.moveUnit(new Position(2,1), new Position(3,1));
        game.endTurn(2);
        game.moveUnit(new Position(3,1), newPosition);
        assertThat(game.getCityAt(newPosition).getOwner(), is(Player.RED));
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void before20RoundsPlayerThatWins3AttacksDoesNotWin(){

        City city = game.getCityAt(new Position(4,1)); // blå by
        GameImpl gameimpl = (GameImpl) game;
        CityImpl cityimpl = (CityImpl) city;
        cityimpl.setProduction(GameConstants.ARCHER);

        game.moveUnit(new Position(2,0), new Position(3,0));
        game.endTurn(2);
        game.moveUnit(new Position(3,0), new Position(4,0));
        game.endTurn(6);
        game.moveUnit(new Position(4,0), new Position(3,1));
        game.endTurn(2);
        game.moveUnit(new Position(3,1), new Position(4,0));
        game.endTurn(2);
        game.moveUnit(new Position(4,0), new Position(3,1));
        game.endTurn(4);
        game.moveUnit(new Position(3,1), new Position(4,0));
        game.endTurn(2);
        assertNull(game.getWinner());
    }
    @Test
    public void after20RoundsPlayerThatWins3AttacksWins(){

        City city = game.getCityAt(new Position(4,1)); // blå by
        GameImpl gameimpl = (GameImpl) game;
        CityImpl cityimpl = (CityImpl) city;
        cityimpl.setProduction(GameConstants.ARCHER);
        game.endTurn(42);
        game.moveUnit(new Position(2,0), new Position(3,0));
        game.endTurn(2);
        game.moveUnit(new Position(3,0), new Position(3,1)); //red Wins

        game.endTurn(6);
        game.moveUnit(new Position(3,1), new Position(3,0));
        game.endTurn(4);
        game.moveUnit(new Position(3,0), new Position(3,1)); //red wins

        game.endTurn(2);
        game.moveUnit(new Position(3,1), new Position(3,0));
        game.endTurn(4);
        game.moveUnit(new Position(3,0), new Position(3,1)); //red wins
        game.endTurn(2);

        assertThat(game.getWinner(), is(Player.RED));
    }
}
