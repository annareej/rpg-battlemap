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
        grid = new Grid(5, 5, 100);
        medium = new Token(grid.getSquare(2, 2), 1, new Circle(), Color.ALICEBLUE);
        large = new Token(grid.getSquare(0, 0), 2, new Circle(), Color.ANTIQUEWHITE);
        huge = new Token(grid.getSquare(0, 0), 3, new Circle(), Color.AQUA);
        gargantuan = new Token(grid.getSquare(0, 0), 4, new Circle(), Color.AQUAMARINE);
    }
    
    @Test
    public void tokenMovesUp() {
        medium.moveUp(grid);
        Square square = medium.getPosition();
        assertEquals(1, square.getY());
    }
    
    @Test
    public void tokenDoesNotMoveUpOverBorder() {
        medium.moveUp(grid);
        medium.moveUp(grid);
        medium.moveUp(grid);
        Square square = medium.getPosition();
        assertEquals(0, square.getY());
    }
    
    @Test
    public void tokenMovesDown() {
        medium.moveDown(grid);
        Square square = medium.getPosition();
        assertEquals(3, square.getY());
    }
    
    @Test
    public void tokenDoesNotMoveDownOverBorder() {
        medium.moveDown(grid);
        medium.moveDown(grid);
        medium.moveDown(grid);
        Square square = medium.getPosition();
        assertEquals(4, square.getY());
    }
    
    @Test
    public void tokenMovesRight() {
        medium.moveRight(grid);
        Square square = medium.getPosition();
        assertEquals(3, square.getX());
    }
    
    @Test
    public void tokenDoesNotMoveRightOverBorder() {
        medium.moveRight(grid);
        medium.moveRight(grid);
        medium.moveRight(grid);
        Square square = medium.getPosition();
        assertEquals(4, square.getX());
    }
    
    @Test
    public void tokenMovesLeft() {
        medium.moveLeft(grid);
        Square square = medium.getPosition();
        assertEquals(1, square.getX());
    }
    
    @Test
    public void tokenDoesNotMoveLeftOverBorder() {
        medium.moveLeft(grid);
        medium.moveLeft(grid);
        medium.moveLeft(grid);
        Square square = medium.getPosition();
        assertEquals(0, square.getX());
    }
}
