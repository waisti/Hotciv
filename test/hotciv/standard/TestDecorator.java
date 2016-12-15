package hotciv.standard;

import hotciv.framework.*;
import thirdparty.ThirdPartyFractalGenerator;
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
public class TestDecorator {
    private LogDecorator logDecorator;
    private City city;
    private Position position;
    private Tile tile;
    private Unit unit;



    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        logDecorator = new LogDecorator(new GameImpl (new AlphaFactory()));
    }

    // FRS p. 455 states that 'Red is the first player to take a turn'.
    @Test
    public void shouldBeRedAsStartingPlayer() {
        assertThat(logDecorator.getPlayerInTurn(), is(Player.RED));
    }

    @Test
    public void blueComesAfterRed(){
        assertThat(logDecorator.getPlayerInTurn(), is(Player.RED));
        logDecorator.endOfTurn();
        assertThat(logDecorator.getPlayerInTurn(), is(Player.BLUE));
    }

    @Test
    public void gameShouldAge(){
        int oldAge = logDecorator.getAge();
        logDecorator.endTurn(2);
        int newAge = logDecorator.getAge();
        assertThat((newAge - oldAge), is(100));
    }

    @Test
    public void redShouldWinAt3000() {
        logDecorator.endTurn(20);
        assertThat(logDecorator.getWinner(), is(Player.RED));
    }
    @Test

    public void thereShouldBePlainsAt0_0and3_7and15_15(){
        position = new Position(0,0);
        tile = logDecorator.getTileAt(position);
        assertThat(tile.getTypeString(), is(GameConstants.PLAINS));

        position = new Position(3,7);
        tile = logDecorator.getTileAt(position);
        assertThat(tile.getTypeString(), is(GameConstants.PLAINS));

        position = new Position(WORLDSIZE-1,WORLDSIZE-1);
        tile = logDecorator.getTileAt(position);
        assertThat(tile.getTypeString(), is(GameConstants.PLAINS));
    }
    @Test
    public void thereShouldBeOceanAt1_0(){
        position = new Position(1,0);
        tile = logDecorator.getTileAt(position);
        assertThat(tile.getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void thereShouldBeHillsAt0_1(){
        position = new Position(0,1);
        tile = logDecorator.getTileAt(position);
        assertThat(tile.getTypeString(), is(GameConstants.HILLS));
    }

    @Test
    public void thereShouldBeMountainsAt2_2(){
        position = new Position(2,2);
        tile = logDecorator.getTileAt(position);
        assertThat(tile.getTypeString(), is(GameConstants.MOUNTAINS));
    }



    @Test
    public void thereShouldBeARedCityAt1_1(){
        position = new Position(1,1);
        city = logDecorator.getCityAt(position);
        assertThat(city.getOwner(), is(Player.RED));
    }
    @Test
    public void thereShouldBeABlueCityAt4_1(){
        position = new Position(4,1);
        city = logDecorator.getCityAt(position);
        assertThat(city.getOwner(), is(Player.BLUE));
    }

    @Test
    public void citiesAccumulateProduction(){
        position = new Position(1,1);
        city = logDecorator.getCityAt(position);
        int oldProduction = city.getStorageSize();
        logDecorator.endTurn(2);
        int newProduction = city.getStorageSize();
        assertThat((newProduction - oldProduction), is(6));
    }
    @Test
    public void cityPopShouldBe1(){
        position = new Position(1,1);
        city = logDecorator.getCityAt(position);
        assertThat(city.getSize(), is(1));
    }


    @Test
    public void thereShouldBeARedArcherAt2_0(){
        position = new Position(2,0);
        unit = logDecorator.getUnitAt(position);
        assertThat(unit.getOwner(), is(Player.RED));
        assertThat(unit.getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void thereShouldBeABlueLegionAt3_2(){
        position = new Position(3,2);
        unit = logDecorator.getUnitAt(position);
        assertThat(unit.getOwner(), is(Player.BLUE));
        assertThat(unit.getTypeString(), is(GameConstants.LEGION));
    }

    @Test
    public void thereShouldBeARedSettlerAt4_3(){
        position = new Position(4,3);
        unit = logDecorator.getUnitAt(position);
        assertThat(unit.getOwner(), is(Player.RED));
        assertThat(unit.getTypeString(), is(GameConstants.SETTLER));
    }

    @Test
    public void citiesCanChangeProduction(){
        position = new Position(1,1);
        logDecorator.changeProductionInCityAt(position, GameConstants.ARCHER);
        assertThat(logDecorator.getCityAt(position).getProduction(), is(GameConstants.ARCHER));
    }

    @Test
    public void citiesCanProduceArchers(){
        position = new Position(1,1);
        assertNull(logDecorator.getUnitAt(position));
        logDecorator.changeProductionInCityAt(position, GameConstants.ARCHER);
        logDecorator.endTurn(4);
        assertNotNull(logDecorator.getUnitAt(position));
        System.out.println(logDecorator.getUnitAt(position).getTypeString());
    }
    @Test
    public void legionsCost15(){
        position = new Position(1,1);
        assertNull(logDecorator.getUnitAt(position));
        logDecorator.changeProductionInCityAt(position, GameConstants.LEGION);
        logDecorator.endTurn(6);
        assertNotNull(logDecorator.getUnitAt(position));
        assertThat(logDecorator.getUnitAt(position).getTypeString(), is(GameConstants.LEGION));
    }
    @Test
    public void settlersCost30(){
        position = new Position(1,1);
        System.out.println(logDecorator.getCityAt(position));
        assertNull(logDecorator.getUnitAt(position));
        logDecorator.changeProductionInCityAt(position, GameConstants.SETTLER);
        logDecorator.endTurn(10);
        assertNotNull(logDecorator.getUnitAt(position));
        assertThat(logDecorator.getUnitAt(position).getTypeString(), is(GameConstants.SETTLER));

    }
    @Test
    public void unitsHaveCorrectAttackingStrength() {
        assertThat(logDecorator.getUnitAt(new Position(3, 2)).getAttackingStrength(), is(4)); // legion
        assertThat(logDecorator.getUnitAt(new Position(2, 0)).getAttackingStrength(), is(2)); // archer
        assertThat(logDecorator.getUnitAt(new Position(4, 3)).getAttackingStrength(), is(0)); // settler
    }
    @Test
    public void unitsHaveCorrectDefensiveStrength() {
        assertThat(logDecorator.getUnitAt(new Position(3,2)).getDefensiveStrength(), is(2));
        assertThat(logDecorator.getUnitAt(new Position(2,0)).getDefensiveStrength(), is(3));
        assertThat(logDecorator.getUnitAt(new Position(4,3)).getDefensiveStrength(), is(3));
    }
    @Test
    public void producedUnitsSpawnNorth(){
        position = new Position(1,1);
        logDecorator.changeProductionInCityAt(position, GameConstants.ARCHER);
        logDecorator.endTurn(4);
        assertNotNull(logDecorator.getUnitAt(position));
        logDecorator.endTurn(4);
        position = new Position(0,1);
        assertNotNull(logDecorator.getUnitAt(position));

    }
    @Test
    public void unitsCanMove(){
        Position oldPosition = new Position(2,0);
        Position newPosition = new Position(3,0);
        System.out.println(logDecorator.getUnitAt(oldPosition).getTypeString() + "for helvede");
        System.out.println(logDecorator.getUnitAt(newPosition) + "for søren");

        logDecorator.moveUnit(oldPosition, newPosition);
        assertNull(logDecorator.getUnitAt(oldPosition));
        assertNotNull(logDecorator.getUnitAt(newPosition));

    }
    @Test
    public void unitsCantMoveMoreThanOne(){
        Position oldPosition = new Position(2,0);
        Position newPosition = new Position(4,0);
        assertFalse(logDecorator.moveUnit(oldPosition, newPosition));

    }
    @Test
    public void unitsCantMoveOnOcean() {
        Position oldPosition = new Position(2, 0);
        Position newPosition = new Position(1, 0);
        assertFalse(logDecorator.moveUnit(oldPosition, newPosition));
    }
    @Test
    public void UnitsCantMoveWithDepletedMovement() {
        Position oldPosition = new Position(2, 0);
        Position newPosition = new Position(3, 0);
        logDecorator.moveUnit(oldPosition, newPosition);
        assertThat(logDecorator.getUnitAt(newPosition).getMoveCount(), is(0));
        assertFalse(logDecorator.moveUnit(newPosition, oldPosition));
    }
    @Test
    public void redCantMoveBlueUnits(){
        logDecorator.endTurn(1);
        assertThat(logDecorator.getPlayerInTurn(), is(Player.BLUE));
        Position oldPosition = new Position(2,0);
        Position newPosition = new Position(2,1);
        assertThat(logDecorator.moveUnit(oldPosition, newPosition),is(false));
    }

    @Test
    public void attackingUnitDestroysDefender(){
        Position oldPosition = new Position(2,0);
        Position newPosition = new Position(3,2);
        assertThat(logDecorator.getUnitAt(newPosition).getOwner(), is(Player.BLUE));
        logDecorator.moveUnit(oldPosition, new Position(3,0));
        logDecorator.endTurn(2);
        logDecorator.moveUnit(new Position(3,0), new Position(3,1));
        logDecorator.endTurn(2);
        logDecorator.moveUnit(new Position(3,1), newPosition);
        assertThat(logDecorator.getUnitAt(newPosition).getOwner(), is(Player.RED));
    }
    @Test
    public void unitsCanTakeCities(){
        Position oldPosition = new Position(2,0);
        Position newPosition = new Position(4,1);
        assertThat(logDecorator.getCityAt(newPosition).getOwner(), is(Player.BLUE));
        logDecorator.moveUnit(oldPosition, new Position(2,1));
        logDecorator.endTurn(2);
        logDecorator.moveUnit(new Position(2,1), new Position(3,1));
        logDecorator.endTurn(2);
        logDecorator.moveUnit(new Position(3,1), newPosition);
        assertThat(logDecorator.getCityAt(newPosition).getOwner(), is(Player.RED));
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
}