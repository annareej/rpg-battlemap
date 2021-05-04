package rpgbattlemap.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Token {
    private Square position;
    private int size;
    private Circle shape;
    Color colour;

    public Token(Square position, int size, Circle shape, Color colour) {
        this.position = position;
        this.size = size;
        this.shape = shape;
        this.colour = colour;
    }
    
    public Square getPosition() {
        return position;
    }

    public void setPosition(Square position) {
        this.position = position;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Circle getShape() {
        return shape;
    }

    public void setShape(Circle shape) {
        this.shape = shape;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    /**
     * Move token up a square on given grid.
     * 
     * @param grid Grid to get the square above of current position.
     */
    public void moveUp(Grid grid) {
        Square old = this.position;
        if (this.position.getY() - 1 >= 0) {
            this.position = grid.getSquare(old.getY() - 1, old.getX());
        }
    }

    /**
     * Move token down a square on given grid.
     * 
     * @param grid Grid to get the square below of current position.
     */
    public void moveDown(Grid grid) {
        Square old = this.position;
        if (this.position.getY() + 1 < grid.getHeight()) {
            this.position = grid.getSquare(old.getY() + 1, old.getX());
        }
    }

    /**
     * Move token right on given grid.
     * 
     * @param grid Grid to get the square left of current position.
     */
    public void moveRight(Grid grid) {
        Square old = this.position;
        if (this.position.getX() + 1 < grid.getWidth()) {
            this.position = grid.getSquare(old.getY(), old.getX() + 1);
        }
    }
    
    /**
     * Move token left a square on given grid.
     * 
     * @param grid Grid to get the square above of current position.
     */
    public void moveLeft(Grid grid) {
        Square old = this.position;
        if (this.position.getX() - 1 >= 0) {
            this.position = grid.getSquare(old.getY(), old.getX() - 1);
        }
    }
}
