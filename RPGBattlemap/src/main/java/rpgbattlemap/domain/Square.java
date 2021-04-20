package rpgbattlemap.domain;

import javafx.scene.shape.Rectangle;

public class Square {
    private int x;
    private int y;
    private Rectangle rectangle;
    
    public Square(int y, int x) {
        this.x = x;
        this.y = y;
        this.rectangle = new Rectangle(y * 100, x * 100, 100, 100);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
