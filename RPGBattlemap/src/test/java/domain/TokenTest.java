package domain;

import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import rpgbattlemap.domain.Grid;
import rpgbattlemap.domain.Square;
import rpgbattlemap.domain.Token;

public class TokenTest {
    Grid grid;
    Token token;
    
    @Before
    public void setUp() {
        grid = new Grid(5,5);
        token = new Token(grid.getSquare(2, 2), 1); 
    }
    
    @Test
    public void tokenMovesUp(){
        token.moveUp(grid);
        Square square = token.getPosition();
        assertEquals(1, square.getY());
    }
    
    @Test
    public void tokenMovesDown(){
        token.moveDown(grid);
        Square square = token.getPosition();
        assertEquals(3, square.getY());
    }
    
    @Test
    public void tokenMovesRight(){
        token.moveRight(grid);
        Square square = token.getPosition();
        assertEquals(3, square.getX());
    }
    
    @Test
    public void tokenMovesLeft(){
        token.moveLeft(grid);
        Square square = token.getPosition();
        assertEquals(1, square.getX());
    }
}
