package domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import rpgbattlemap.domain.Grid;
import rpgbattlemap.domain.Square;
import rpgbattlemap.domain.Token;

public class TokenTest {
    Grid grid;
    Token medium;
    Token large;
    Token huge;
    Token gargantuan;
    
    @Before
    public void setUp() {
        grid = new Grid(5,5);
        medium = new Token(grid.getSquare(2, 2), 1, new Circle());
        large = new Token(grid.getSquare(0, 0), 2, new Circle());
        huge = new Token(grid.getSquare(0, 0), 3, new Circle());
        gargantuan = new Token(grid.getSquare(0, 0), 4, new Circle());
    }
    
    @Test
    public void tokenMovesUp() {
        medium.moveUp(grid);
        Square square = medium.getPosition();
        assertEquals(1, square.getY());
    }
    
    @Test
    public void tokenMovesDown() {
        medium.moveDown(grid);
        Square square = medium.getPosition();
        assertEquals(3, square.getY());
    }
    
    @Test
    public void tokenMovesRight() {
        medium.moveRight(grid);
        Square square = medium.getPosition();
        assertEquals(3, square.getX());
    }
    
    @Test
    public void tokenMovesLeft() {
        medium.moveLeft(grid);
        Square square = medium.getPosition();
        assertEquals(1, square.getX());
    }
}
