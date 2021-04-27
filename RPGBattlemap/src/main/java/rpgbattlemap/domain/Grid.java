package rpgbattlemap.domain;

import java.util.ArrayList;

public class Grid {
    private Square[][] grid;
    private int width;
    private int height;
    
    public Grid(int width, int height, int squareSize) {
        grid = new Square[height][width];        
        this.width = width;
        this.height = height;
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                addSquare(i, j, new Square(i, j, squareSize));               
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
    
    public Square getSquareFromMousePosition(double x, double y, int squareSize) {
        int colIndex = 0;
        if (x >= 100) {
            colIndex = (int) x / squareSize;
        }

        int rowIndex = 0;
        if (y >= 100) {
            rowIndex = (int) y / squareSize;
        }
        
        return getSquare(colIndex, rowIndex);
    }
    
}
