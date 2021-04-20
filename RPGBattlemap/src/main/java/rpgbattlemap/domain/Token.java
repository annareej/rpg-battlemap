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
    
    public void setCircleInPosition(Color colour) {
        double radius = 50 * this.size;
        
        setCircleCenter();
        this.shape.setRadius(radius);
        this.shape.setFill(colour);
    }
    
    private void setCircleCenter() {
        int x = this.position.getX() * 100;
        int y = this.position.getY() * 100;
        if (this.size % 2 != 0) {
            x += 50;
            y += 50;
        }
        this.shape.setCenterX(x);
        this.shape.setCenterY(y);
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
