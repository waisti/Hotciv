package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;

import static hotciv.framework.GameConstants.PLAINS;
import static hotciv.framework.GameConstants.WORLDSIZE;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/**
 * Created by troels on 01/12/2016.
 */
public class TestSemiCiv {
    private Game game;
    private City city;
    private Position position;
    private Tile tile;
    private Unit unit;



    //hd
    @Before
    public void setUp() {
        game = new GameImpl(new SemiFactory());
    }

    @Test
    public void year50PassBetween50and1750ad(){
        game.endTurn(84);
        int oldAge = game.getAge();
        game.endTurn(2);
        int newAge = game.getAge();
        assertThat((newAge - oldAge), is(50));
    }

    @Test
    public void year25PassBetween1750and1900ad(){
        game.endTurn(152);
        int oldAge = game.getAge();
        game.endTurn(2);
        int newAge = game.getAge();
        assertThat((newAge - oldAge), is(25));
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
    public void loneLegionOnAForestHas4DS(){
        assertThat(EpsilonBattleStrategy.combinedDefensiveStrength(game, new Position(4,4)), is(4));
    }}
