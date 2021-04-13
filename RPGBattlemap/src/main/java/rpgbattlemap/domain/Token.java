package rpgbattlemap.domain;

public class Token {
    private Square position;
    private int size;

    public Token(Square position, int size) {
        this.position = position;
        this.size = size;
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
    
    public void moveUp(Grid grid){
        Square old = this.position;
        if(this.position.getY() - 1 > 0) {
            this.position = grid.getSquare(old.getY() - 1, old.getX());
        }
    }

    public void moveDown(Grid grid) {
        Square old = this.position;
        if(this.position.getY() + 1 < grid.getHeight()) {
            this.position = grid.getSquare(old.getY() + 1, old.getX());
        }
    }

    public void moveRight(Grid grid) {
        Square old = this.position;
        if(this.position.getX() + 1 < grid.getWidth()) {
            this.position = grid.getSquare(old.getY(), old.getX() + 1);
        }
    }
    
    public void moveLeft(Grid grid) {
        Square old = this.position;
        if(this.position.getX() - 1 > 0) {
            this.position = grid.getSquare(old.getY(), old.getX() - 1);
        }
    }
}
