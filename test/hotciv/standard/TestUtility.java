package hotciv.standard;

import hotciv.framework.*;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

/** Test the utility library's methods to calculate neighborhoods
 * and battle factors.
 */
public class TestUtility {
    private Iterator<Position> iter;
    private ArrayList<Position> neighborhood;
    private Position center, p;

    Game game;
    @Before public void setUp() {
        game = new GameStubForBattleTesting();
    }

    /** helper method to insert elements in an iterator into a list. */
    private ArrayList<Position> convertIteration2List(Iterator<Position> iter) {
        neighborhood = new ArrayList<Position>();
        while ( iter.hasNext() ) {
            p = iter.next();
            neighborhood.add(p);
        }
        return neighborhood;
    }

    @Test public void shouldGive8PositionsForP8_8() {
        center = new Position(8,8);
        iter = Utility.get8NeighborhoodIterator(center);
        neighborhood = convertIteration2List( iter );

        assertTrue( "Must contain (7,7)",
                neighborhood.contains( new Position(7,7)));
        assertTrue( "Must contain (9,9)",
                neighborhood.contains( new Position(9,9)));
        assertTrue( "Must contain (7,9)",
                neighborhood.contains( new Position(7,9)));
        assertTrue( "Must contain (8,7)",
                neighborhood.contains( new Position(8,7)));

        assertFalse( "Must not contain center position",
                neighborhood.contains( center ));

        assertFalse( "Must not contain (5,5) position",
                neighborhood.contains( new Position(5,5) ));

        assertEquals( "Should be 8 positions in the iterator",
                8, neighborhood.size());
    }

    @Test public void shouldGive3PositionsForP0_0() {
        center = new Position(0,0);
        iter = Utility.get8NeighborhoodIterator(center);
        neighborhood = convertIteration2List( iter );

        assertTrue( "Must contain (1,0)",
                neighborhood.contains( new Position(1,0)));
        assertTrue( "Must contain (1,1)",
                neighborhood.contains( new Position(1,1)));
        assertTrue( "Must contain (0,1)",
                neighborhood.contains( new Position(0,1)));

        assertEquals( "Should be 3 positions in the iterator",
                3, neighborhood.size());

    }
    @Test public void shouldGive3PositionsForP15_15() {
        center = new Position(15,15);
        iter = Utility.get8NeighborhoodIterator(center);
        neighborhood = convertIteration2List( iter );

        assertTrue( "Must contain (14,15)",
                neighborhood.contains( new Position(14,15)));
        assertTrue( "Must contain (14,14)",
                neighborhood.contains( new Position(14,14)));
        assertTrue( "Must contain (15,14)",
                neighborhood.contains( new Position(15,14)));

        assertEquals( "Should be 3 positions in the iterator",
                3, neighborhood.size());

    }

    @Test public void shouldGiveCorrectTerrainFactors() {
        // plains have multiplier 1
        assertEquals( 1, Utility.getTerrainFactor( game, new Position(0,1)));
        // hills have multiplier 2
        assertEquals( 2, Utility.getTerrainFactor( game, new Position(1,0)));
        // forest have multiplier 2
        assertEquals( 2, Utility.getTerrainFactor( game, new Position(0,0)));
        // cities have multiplier 3
        assertEquals( 3, Utility.getTerrainFactor( game, new Position(1,1)));
    }

    @Test public void shouldGiveSum1ForBlueAtP5_5() {
        assertEquals("Blue unit at (5,5) should get +1 support",
                +1, Utility.getFriendlySupport( game, new Position(5,5), Player.BLUE));
    }

    @Test public void shouldGiveSum0ForBlueAtP2_4() {
        assertEquals("Blue unit at (2,4) should get +0 support",
                +0, Utility.getFriendlySupport( game, new Position(2,4), Player.BLUE));
    }
    @Test public void shouldGiveSum2ForRedAtP2_4() {
        assertEquals("Red unit at (2,4) should get +2 support",
                +2, Utility.getFriendlySupport( game, new Position(2,4), Player.RED));
    }
    @Test public void shouldGiveSum3ForRedAtP2_2() {
        assertEquals("Red unit at (2,2) should get +3 support",
                +3, Utility.getFriendlySupport( game, new Position(2,2), Player.RED));
    }
}