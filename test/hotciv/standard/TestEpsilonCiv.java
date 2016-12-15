package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestEpsilonCiv {
    private Game gameTrue;
    private Game gameFalse;
    private City city;
    private Position position;
    private Tile tile;
    private Unit unit;

    @Before
    public void setUp() {
        gameTrue = new GameImpl(new EpsilonFactory(new FixedDieRoll(true)));
        gameFalse = new GameImpl(new EpsilonFactory(new FixedDieRoll(false)));
    }


    @Test
    public void winnerIsTheFirstToWin3Attacks(){
        City city = gameTrue.getCityAt(new Position(4,1)); // blå by
        GameImpl gameimpl = (GameImpl) gameTrue;
        CityImpl cityimpl = (CityImpl) city;
        cityimpl.setProduction(GameConstants.ARCHER);
        gameTrue.moveUnit(new Position(2,0), new Position(3,0));
        gameTrue.endTurn(2);
        gameTrue.moveUnit(new Position(3,0), new Position(4,0));
        gameTrue.endTurn(6);
        gameTrue.moveUnit(new Position(4,0), new Position(3,1));
        gameTrue.endTurn(2);
        gameTrue.moveUnit(new Position(3,1), new Position(4,0));
        gameTrue.endTurn(2);
        gameTrue.moveUnit(new Position(4,0), new Position(3,1));
        gameTrue.endTurn(2);
        gameTrue.moveUnit(new Position(3,1), new Position(4,1));
        gameTrue.endTurn(2);
        assertThat(gameTrue.getWinner(), is(Player.RED));

    }

    @Test
    public void loneLegionOnAPlainHas4AS(){
        assertThat(EpsilonBattleStrategy.combinedAttackStrength(gameTrue, new Position(3,2)), is(4));
    }

    @Test
    public void legionWithAFriendOnAPlainHas5AS(){
        City city = gameTrue.getCityAt(new Position(4,1)); // blå by
        GameImpl gameimpl = (GameImpl) gameTrue;
        CityImpl cityimpl = (CityImpl) city;
        gameimpl.createUnit(cityimpl, new Position(4,1)); //lav nogen blå units
        assertThat(EpsilonBattleStrategy.combinedAttackStrength(gameTrue, new Position(3,2)), is(5));
    }

    @Test
    public void legionWithAFriendInACityHas15AS(){
        City city = gameTrue.getCityAt(new Position(4,1)); // blå by
        GameImpl gameimpl = (GameImpl) gameTrue;
        CityImpl cityimpl = (CityImpl) city;
        cityimpl.setProduction(GameConstants.LEGION);
        gameTrue.endTurn(6); //lav nogen blå units
        assertThat(EpsilonBattleStrategy.combinedAttackStrength(gameTrue, new Position(4,1)), is(15));
    }

    @Test
    public void loneLegionOnAPlainHas2DS(){
        assertThat(EpsilonBattleStrategy.combinedDefensiveStrength(gameTrue, new Position(3,2)), is(2));
    }

    @Test
    public void legionWithAFriendOnAPlainHas3DS(){
        City city = gameTrue.getCityAt(new Position(4,1)); // blå by
        GameImpl gameimpl = (GameImpl) gameTrue;
        CityImpl cityimpl = (CityImpl) city;
        gameimpl.createUnit(cityimpl, new Position(4,1)); //lav nogen blå units
        assertThat(EpsilonBattleStrategy.combinedDefensiveStrength(gameTrue, new Position(3,2)), is(3));
    }

    @Test
    public void legionWithAFriendInACityHas9DS(){
        City city = gameTrue.getCityAt(new Position(4,1)); // blå by
        GameImpl gameimpl = (GameImpl) gameTrue;
        CityImpl cityimpl = (CityImpl) city;
        cityimpl.setProduction(GameConstants.LEGION);
        gameTrue.endTurn(6); //lav nogen blå units
        assertThat(EpsilonBattleStrategy.combinedDefensiveStrength(gameTrue, new Position(4,1)), is(9));
    }

    @Test
    public void OIsTrueIfASxd1LargerDSxd2(){
        BattleStrategy bs = new EpsilonBattleStrategy(new FixedDieRoll(true)); //attacker rolls 6 and defender rolls 1
        assertTrue(bs.outcomeOfBattle(gameTrue, new Position(3,2), new Position(4,3)));

    }
    @Test
    public void OIsFalseIfASxd1SmallerDSxd2(){
        BattleStrategy bs = new EpsilonBattleStrategy(new FixedDieRoll(false)); //attacker rolls 1 and defender rolls 6
        assertFalse(bs.outcomeOfBattle(gameTrue, new Position(3,2), new Position(4,3)));

    }
    @Test
    public void ifAttackerLosesHeDissapears(){
        gameFalse.endTurn(1);
        gameFalse.moveUnit(new Position(3,2), new Position(4,3)); //attacker rolls 1 and defender rolls 6
        assertNull(gameFalse.getUnitAt(new Position(3,2)));
    }
}