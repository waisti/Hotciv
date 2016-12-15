package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;

import static hotciv.framework.GameConstants.PLAINS;
import static hotciv.framework.GameConstants.WORLDSIZE;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 This is an easter egg

*/
public class TestAlphaCiv {
    private Game game;
    private City city;
    private Position position;
    private Tile tile;
    private Unit unit;



    /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
      game = new GameImpl(new AlphaFactory());


  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
      assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void blueComesAfterRed(){
      assertThat(game.getPlayerInTurn(), is(Player.RED));
      game.endOfTurn();
      assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }

  @Test
  public void gameShouldAge(){
      int oldAge = game.getAge();
      game.endTurn(2);
      int newAge = game.getAge();
      assertThat((newAge - oldAge), is(100));
  }

  @Test
  public void redShouldWinAt3000() {
    game.endTurn(20);

    assertThat(game.getWinner(), is(Player.RED));
  }
  @Test

  public void thereShouldBePlainsAt0_0and3_7and15_15(){
        position = new Position(0,0);
        tile = game.getTileAt(position);
        assertThat(tile.getTypeString(), is(GameConstants.PLAINS));

        position = new Position(3,7);
        tile = game.getTileAt(position);
        assertThat(tile.getTypeString(), is(GameConstants.PLAINS));

        position = new Position(WORLDSIZE-1,WORLDSIZE-1);
        tile = game.getTileAt(position);
        assertThat(tile.getTypeString(), is(GameConstants.PLAINS));
    }
  @Test
    public void thereShouldBeOceanAt1_0(){
        position = new Position(1,0);
        tile = game.getTileAt(position);
        assertThat(tile.getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void thereShouldBeHillsAt0_1(){
        position = new Position(0,1);
        tile = game.getTileAt(position);
        assertThat(tile.getTypeString(), is(GameConstants.HILLS));
    }

    @Test
    public void thereShouldBeMountainsAt2_2(){
        position = new Position(2,2);
        tile = game.getTileAt(position);
        assertThat(tile.getTypeString(), is(GameConstants.MOUNTAINS));
    }



    @Test
    public void thereShouldBeARedCityAt1_1(){
        position = new Position(1,1);
        city = game.getCityAt(position);
        assertThat(city.getOwner(), is(Player.RED));
    }
    @Test
    public void thereShouldBeABlueCityAt4_1(){
        position = new Position(4,1);
        city = game.getCityAt(position);
        assertThat(city.getOwner(), is(Player.BLUE));
    }

    @Test
    public void citiesAccumulateProduction(){
        position = new Position(1,1);
        city = game.getCityAt(position);
        int oldProduction = city.getStorageSize();
        game.endTurn(2);
        int newProduction = city.getStorageSize();
        assertThat((newProduction - oldProduction), is(6));
    }
    @Test
    public void cityPopShouldBe1(){
        position = new Position(1,1);
        city = game.getCityAt(position);
        assertThat(city.getSize(), is(1));
    }


    @Test
    public void thereShouldBeARedArcherAt2_0(){
        position = new Position(2,0);
        unit = game.getUnitAt(position);
        assertThat(unit.getOwner(), is(Player.RED));
        assertThat(unit.getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void thereShouldBeABlueLegionAt3_2(){
        position = new Position(3,2);
        unit = game.getUnitAt(position);
        assertThat(unit.getOwner(), is(Player.BLUE));
        assertThat(unit.getTypeString(), is(GameConstants.LEGION));
    }

    @Test
    public void thereShouldBeARedSettlerAt4_3(){
        position = new Position(4,3);
        unit = game.getUnitAt(position);
        assertThat(unit.getOwner(), is(Player.RED));
        assertThat(unit.getTypeString(), is(GameConstants.SETTLER));
    }

    @Test
    public void citiesCanChangeProduction(){
        position = new Position(1,1);
        game.changeProductionInCityAt(position, GameConstants.ARCHER);
        assertThat(game.getCityAt(position).getProduction(), is(GameConstants.ARCHER));
    }

    @Test
    public void citiesCanProduceArchers(){
        position = new Position(1,1);
        assertNull(game.getUnitAt(position));
        game.changeProductionInCityAt(position, GameConstants.ARCHER);
        game.endTurn(4);
        assertNotNull(game.getUnitAt(position));
}
    @Test
    public void legionsCost15(){
        position = new Position(1,1);
        assertNull(game.getUnitAt(position));
        game.changeProductionInCityAt(position, GameConstants.LEGION);
        game.endTurn(6);
        assertNotNull(game.getUnitAt(position));
        assertThat(game.getUnitAt(position).getTypeString(), is(GameConstants.LEGION));
    }
    @Test
    public void settlersCost30(){
        position = new Position(1,1);
        assertNull(game.getUnitAt(position));
        game.changeProductionInCityAt(position, GameConstants.SETTLER);
        game.endTurn(10);
        assertNotNull(game.getUnitAt(position));
        assertThat(game.getUnitAt(position).getTypeString(), is(GameConstants.SETTLER));

    }
    @Test
    public void unitsHaveCorrectAttackingStrength() {
        assertThat(game.getUnitAt(new Position(3, 2)).getAttackingStrength(), is(4)); // legion
        assertThat(game.getUnitAt(new Position(2, 0)).getAttackingStrength(), is(2)); // archer
        assertThat(game.getUnitAt(new Position(4, 3)).getAttackingStrength(), is(0)); // settler
    }
    @Test
    public void unitsHaveCorrectDefensiveStrength() {
        assertThat(game.getUnitAt(new Position(3,2)).getDefensiveStrength(), is(2));
        assertThat(game.getUnitAt(new Position(2,0)).getDefensiveStrength(), is(3));
        assertThat(game.getUnitAt(new Position(4,3)).getDefensiveStrength(), is(3));
    }
    @Test
    public void producedUnitsSpawnNorth(){
        position = new Position(1,1);
        game.changeProductionInCityAt(position, GameConstants.ARCHER);
        game.endTurn(4);
        assertNotNull(game.getUnitAt(position));
        game.endTurn(4);
        position = new Position(0,1);
        assertNotNull(game.getUnitAt(position));

    }
    @Test
    public void unitsCanMove(){
       Position oldPosition = new Position(2,0);
       Position newPosition = new Position(3,0);
       game.moveUnit(oldPosition, newPosition);
        assertNull(game.getUnitAt(oldPosition));
        assertNotNull(game.getUnitAt(newPosition));

    }
    @Test
    public void unitsCantMoveMoreThanOne(){
        Position oldPosition = new Position(2,0);
        Position newPosition = new Position(4,0);
        assertFalse(game.moveUnit(oldPosition, newPosition));

    }
    @Test
    public void unitsCantMoveOnOcean() {
        Position oldPosition = new Position(2, 0);
        Position newPosition = new Position(1, 0);
        assertFalse(game.moveUnit(oldPosition, newPosition));
    }
    @Test
    public void UnitsCantMoveWithDepletedMovement() {
        Position oldPosition = new Position(2, 0);
        Position newPosition = new Position(3, 0);
        game.moveUnit(oldPosition, newPosition);
        assertThat(game.getUnitAt(newPosition).getMoveCount(), is(0));
        assertFalse(game.moveUnit(newPosition, oldPosition));
    }
        @Test
    public void redCantMoveBlueUnits(){
        game.endTurn(1);
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
        Position oldPosition = new Position(2,0);
        Position newPosition = new Position(2,1);
        assertThat(game.moveUnit(oldPosition, newPosition),is(false));
    }

    @Test
    public void attackingUnitDestroysDefender(){
        Position oldPosition = new Position(2,0);
        Position newPosition = new Position(3,2);
        assertThat(game.getUnitAt(newPosition).getOwner(), is(Player.BLUE));
        game.moveUnit(oldPosition, new Position(3,0));
        game.endTurn(2);
        game.moveUnit(new Position(3,0), new Position(3,1));
        game.endTurn(2);
        game.moveUnit(new Position(3,1), newPosition);
        assertThat(game.getUnitAt(newPosition).getOwner(), is(Player.RED));
    }
    @Test
    public void unitsCanTakeCities(){
        Position oldPosition = new Position(2,0);
        Position newPosition = new Position(4,1);
        assertThat(game.getCityAt(newPosition).getOwner(), is(Player.BLUE));
        game.moveUnit(oldPosition, new Position(2,1));
        game.endTurn(2);
        game.moveUnit(new Position(2,1), new Position(3,1));
        game.endTurn(2);
        game.moveUnit(new Position(3,1), newPosition);
        assertThat(game.getCityAt(newPosition).getOwner(), is(Player.RED));
    }
   /* @Test
    public void producedUnitsSpawnClockWise(){
        position = new Position(1,1);
        game.changeProductionInCityAt(position, GameConstants.ARCHER);
        game.endTurn(16); //a lot in production + producing
        position = new Position(3,3);
        assertNotNull(game.getUnitAt(position));
        game.endTurn(16);
        position = new Position(0,0);
        assertNotNull(game.getUnitAt(position));
    }
    */
   // INTEGRATIONTESTING

    @Test
    public void shouldIntegrateBetaWinnerStrategy(){
        game = new GameImpl(new BetaFactory());
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

    //ETC. ETC. ETC.
}