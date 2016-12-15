package hotciv.standard;

import hotciv.framework.*;
import hotciv.stub.StubGame2;
import hotciv.view.CivDrawing;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.StandardDrawing;
import org.junit.*;

/**
 * Created by Sine on 15-12-2016.
 */
public class TestObserver {
    private Game game;
    private City city;
    private Position position;
    private Tile tile;
    private Unit unit;
    DrawingEditor editor;

    @Before
    public void setUp() {
        game = new StubGame2();

    }

    @Test
    public void gameShouldAddObserver(){

    }


}

