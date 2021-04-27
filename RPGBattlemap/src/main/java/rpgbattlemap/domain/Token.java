package rpgbattlemap.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Token {
    private Square position;
    private int size;
    private Circle shape;

    public Token(Square position, int size, Circle shape) {
        this.position = position;
        this.size = size;
        this.shape = shape;
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

    public void createToken(Color colour, int squareSize) {
        double radius = squareSize/2 * this.size;
        
        drawToken(squareSize);
        this.shape.setRadius(radius);
        this.shape.setFill(colour);
    }
    
    public void drawToken(int squareSize) {
        double x = getSquarePixelCoordinate(this.position.getPixelCoordinateX(), squareSize);
        double y = getSquarePixelCoordinate(this.position.getPixelCoordinateY(), squareSize);
        
        draw(x, y);
    }
    
    private double getSquarePixelCoordinate(double coordinate, int squareSize) {
        //Sizes medium (1) and huge (3) are positioned in center of square
        if(this.size % 2 != 0) {
            coordinate += squareSize / 2;            
        }
        return coordinate;
    }
    
    public void draw(double x, double y) {
        this.shape.setTranslateX(x);
        this.shape.setTranslateY(y);
    }
    
    public void moveUp(Grid grid) {
        Square old = this.position;
        if (this.position.getY() - 1 > 0) {
            this.position = grid.getSquare(old.getY() - 1, old.getX());
        }
    }

    public void moveDown(Grid grid) {
        Square old = this.position;
        if (this.position.getY() + 1 < grid.getHeight()) {
            this.position = grid.getSquare(old.getY() + 1, old.getX());
        }
    }

    public void moveRight(Grid grid) {
        Square old = this.position;
        if (this.position.getX() + 1 < grid.getWidth()) {
            this.position = grid.getSquare(old.getY(), old.getX() + 1);
        }
    }
    
    public void moveLeft(Grid grid) {
        Square old = this.position;
        if (this.position.getX() - 1 > 0) {
            this.position = grid.getSquare(old.getY(), old.getX() - 1);
        }
    }
}
