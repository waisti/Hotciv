package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by troels on 17/11/2016.
 */
public class TestBetaCiv {
    private GameImpl game;


    /** Fixture for betaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new BetaFactory());
    }

    @Test
    public void winnerIsConqueror(){
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
    public void year100PassBetween4000and100bc(){
        int oldAge = game.getAge();
        game.endTurn(2);
        int newAge = game.getAge();
        assertThat((newAge - oldAge), is(100));
    }

    @Test
    public void yearPassBetween100bcand50ad(){
        game.endTurn(78);
        assertThat(game.getAge(), is(-100));
        game.endTurn(2);
        assertThat(game.getAge(), is(-1));
        game.endTurn(2);
        assertThat(game.getAge(), is(1));
        game.endTurn(2);
        assertThat(game.getAge(), is(50));
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
    public void year5PassBetween1900and1970ad(){
        game.endTurn(164);
        int oldAge = game.getAge();
        game.endTurn(2);
        int newAge = game.getAge();
        assertThat((newAge - oldAge), is(5));
    }

    @Test
    public void year1PassFrom1970(){
        game.endTurn(192);
        int oldAge = game.getAge();
        game.endTurn(2);
        int newAge = game.getAge();
        assertThat((newAge - oldAge), is(1));
    }

}
