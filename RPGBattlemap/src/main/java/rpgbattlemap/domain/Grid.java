package rpgbattlemap.domain;

public class Grid {
    private Square[][] grid;
    private int width;
    private int height;
    
    public Grid(int width, int height) {
        grid = new Square[height][width];        
        this.width = width;
        this.height = height;
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                addSquare(i, j, new Square(i, j));               
            }
        }
    }

    public Square[][] getGrid() {
        return grid;
    }

    public void setGrid(Square[][] grid) {
        this.grid = grid;
    }
    
    public Square getSquare(int y, int x) {
        return this.grid[y][x];
    }
    
    public void addSquare(int y, int x, Square square) {
        this.grid[y][x] = square;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
}
