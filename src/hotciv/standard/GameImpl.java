package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

/** Skeleton implementation of HotCiv.
 
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
 
   Unless required by applicable law or agreed,0 to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

public class GameImpl implements Game {


  private int age = -4000;

  private HashMap tiles = new HashMap();
  private HashMap cities = new HashMap();
  private HashMap units = new HashMap();
  private Player currentPlayer = Player.RED;
  private GameFactory factory;

  private WinnerStrategy winnerStrat;
  private BattleStrategy battleStrat;
  private WorldStrategy worldStrat;
  private UnitStrategy unitStrat;
  private WorldLayoutStrategy worldLayoutStrat;

    private GameObserver observer;


  public GameImpl(GameFactory factory){
    this.factory = factory;
    this.winnerStrat = factory.createWinnerStrategy();
    this.battleStrat = factory.createBattleStrategy();
    this.worldLayoutStrat = factory.createWorldLayoutStrategy();
    this.unitStrat = factory.createUnitStrategy();
    this.worldStrat = factory.createWorldStrategy();

    tiles = createTiles();
    cities = createCities();
    units = createUnits();
  }

  public Tile getTileAt( Position p ) {
     Tile tile = (TileImpl) tiles.get(p);
    return tile;
  }

  public Unit getUnitAt( Position p ) {
      Unit unit = (UnitImpl) units.get(p);
      return unit;
  }

  public City getCityAt( Position p ) {
      City city = (CityImpl) cities.get(p);
      return city;
  }


  public Player getPlayerInTurn() {
    return currentPlayer;
  }

  public Player getWinner() {
      return winnerStrat.getWinner(this);
  }

  public int getAge() {
    return age;
  }

  public boolean moveUnit( Position from, Position to ) {
      UnitImpl unit = (UnitImpl) units.get(from);

      if(Math.abs(to.getRow() - from.getRow()) > 1 || Math.abs(to.getColumn() - from.getColumn()) > 1 ) {
          System.out.println("Can not move outside the map");
          return false;
      }
      if(getUnitAt(from).getOwner() != getPlayerInTurn()) {
          System.out.println("Not that players turn");
          return false;
      }
      if (Objects.equals(getTileAt(to).getTypeString(), GameConstants.OCEANS)
              || Objects.equals(getTileAt(to).getTypeString(), GameConstants.MOUNTAINS)) {
          System.out.println("Can not move to an ocean or mountain");
          return false;
      }
      if(unit.getMoveCount() < 1){
          System.out.println("Can not move more than one 1 tile");
          return false;
      }
      else {
          unit.setMoveCount(0);
          if(getUnitAt(to) != null){
            if(battleStrat.outcomeOfBattle(this, to, from)){
                units.put(to, unit);
                units.remove(from);
                takeCity(to, from);
                winnerStrat.attacksWon(this, getUnitAt(to).getOwner()); //winner's attack count 1 up
            }else{
                units.remove(from);
            }
          }
          else{
            units.put(to, unit);
            units.remove(from);
            takeCity(to, from);
          }
          observer.worldChangedAt(from);
          observer.worldChangedAt(to);
          return true;

      }
  }

  public void takeCity (Position to, Position from){
      if (getCityAt(to) != null) {
          CityImpl city = (CityImpl) cities.get(to);
          city.setOwner(getUnitAt(to).getOwner());
      } else{
          return;
      }
  }

  public void endOfTurn() {
    if (getPlayerInTurn() == Player.RED){
      currentPlayer = Player.BLUE;
        observer.turnEnds(currentPlayer, age);
    }
    else {
        currentPlayer = Player.RED;

        age = worldStrat.worldAging(this);

        Iterator cityIt = cities.entrySet().iterator();
        while (cityIt.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)cityIt.next();
            CityImpl city = (CityImpl) pair.getValue();
            Position position = (Position) pair.getKey();
            city.setStorage(6);
            createUnit(city,position);
        }

        Iterator unitIt = units.entrySet().iterator();
        while (unitIt.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)unitIt.next();
            UnitImpl unit = (UnitImpl) pair.getValue();
            unit.setMoveCount(1);
        }
        observer.turnEnds(currentPlayer, age);

    }
  }



    public void endTurn(int turns){
     for(int i=turns-1; i>=0; i--) {
         endOfTurn();
     }
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}

  public void changeProductionInCityAt( Position p, String unitType ) {
      CityImpl city = (CityImpl) cities.get(p);
      city.setProduction(unitType);
  }
  public void performUnitActionAt( Position p ) {

      unitStrat.performUnitActionAt(this, p);
  }

    @Override
    public void addObserver(GameObserver observer) {
        this.observer = observer;
    }

    @Override
    public void setTileFocus(Position position) {

    }

    public void createUnit(CityImpl city, Position position) {
      int unitCost = unitStrat.getUnitCost(this, city, position);
      if(getUnitAt(position)!=null) {
          position = new Position(position.getRow() - 1, position.getColumn());

          if (getUnitAt(position) != null) {
              return;
          }
      }
        if(city.getStorageSize() >= unitCost){
            city.setStorage(-unitCost);
            units.put(position, new UnitImpl(city.getProduction(), city.getOwner()));
        }
    }

  public void createCity(Position p, Player owner){
      // tjek evt om der er en by først
      cities.put(p, new CityImpl(owner));
  }


    //create world
    public HashMap createTiles() {
        tiles = worldLayoutStrat.createTiles();
        return tiles;
    }

    public HashMap createCities() {
        cities = worldLayoutStrat.createCities();
        return cities;
    }

    public HashMap getCities(){
        return cities;
    }

    public HashMap createUnits() {
        units = worldLayoutStrat.createUnits();
        return units;
    }

    public void removeUnit(Position p) {
        units.remove(p);
    }
}

/*
    public Position turnClockwise (Position position, int n){
        Position endPosition = position;

        if (n == 0) {

        }

        new Position(endPosition.getRow()-1, endPosition.getColumn();
        new Position(endPosition.getRow(), endPosition.getColumn()+1);
        new Position(endPosition.getRow()+1, endPosition.getColumn());
        new Position(endPosition.getRow(), endPosition.getColumn()-1);
        new Position(endPosition.getRow()-1, endPosition.getColumn());

        if (getUnitAt(endPosition)== null){
            return endPosition;
        }


    }
    */