package domain;

import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import rpgbattlemap.domain.Grid;
import rpgbattlemap.domain.Square;

public class SquareTest {
    Grid grid;
    Square square;
    
    @Before
    public void setUp() {
        grid = new Grid(5, 5, 100);
        square = grid.getSquare(1, 2);
    }
    
    @Test
    public void getCorrectYPixelCoordinate() {
        double y = square.getPixelCoordinateY();
        assertEquals(100.0, y);
    }
    
    @Test
    public void getCorrectXPixelCoordinate() {
        double x = square.getPixelCoordinateX();
        assertEquals(200.0, x);
    }
    
}
