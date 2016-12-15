package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

/**
 * Created by Sine on 09-12-2016.
 */
public class LogDecorator implements Game {
    private Game game;

    public LogDecorator (Game g){
        game = g;
    }

    @Override
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        return game.getWinner();
    }

    @Override
    public int getAge() {
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        Player oldUnitOwner = game.getUnitAt(from).getOwner();
        String oldUnit = game.getUnitAt(from).getTypeString();
        boolean result = game.moveUnit(from,to);
        if (result) {
            System.out.println(oldUnitOwner + " moves " + oldUnit + " from " + from + " to " + to + ".");
        }
        return result;
    }

    @Override
    public void endOfTurn() {
        System.out.println(game.getPlayerInTurn() + " ends turn.");
        game.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        game.changeWorkForceFocusInCityAt(p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        System.out.println(game.getPlayerInTurn() + " changes production in city at " + p + " to " + unitType + ".");
        game.changeProductionInCityAt(p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        game.performUnitActionAt(p);
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }

    @Override
    public void endTurn(int turns) {
        game.endTurn(turns);
    }
}
