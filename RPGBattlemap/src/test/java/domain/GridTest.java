package domain;

import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import rpgbattlemap.domain.Grid;
import rpgbattlemap.domain.Square;

public class GridTest {
    Grid grid;
    int squareSize = 100;
    
    @Before
    public void setUp() {
        grid = new Grid(5, 5, squareSize);
    }
    
    @Test
    public void getCorrectSquareYFromMousePosition() {
        Square s = grid.getSquareFromMousePosition(153.5, 350.2, squareSize);
        assertEquals(1, s.getY());
    }
    
    @Test
    public void getCorrectSquareXFromMousePosition() {
        Square s = grid.getSquareFromMousePosition(153.5, 350.2, squareSize);
        assertEquals(3, s.getX());
    }
    
    @Test
    public void getCorrectSquareYFromMousePositionIfFirstColumn() {
        Square s = grid.getSquareFromMousePosition(44.2, 350.2, squareSize);
        assertEquals(0, s.getY());
    }
    
    @Test
    public void getCorrectSquareXFromMousePositionIfTopRow() {
        Square s = grid.getSquareFromMousePosition(153.5, 50.2, squareSize);
        assertEquals(0, s.getX());
    }
}
