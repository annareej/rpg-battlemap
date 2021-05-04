package rpgbattlemap.ui;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import rpgbattlemap.domain.Grid;
import rpgbattlemap.domain.Square;
import rpgbattlemap.domain.Token;

public class UITokenControls {

    public static void tokenPressed(MouseEvent event, Token token) {
        token.getShape().setOpacity(0.8);
    }

    public static void tokenDragged(MouseEvent e, Token token) {
        if (e.isPrimaryButtonDown()) {
            double x = e.getSceneX() - token.getShape().getCenterX();
            double y = e.getSceneY() - token.getShape().getCenterY();

            draw(token, x, y);

        }
    }

    public static void tokenReleased(MouseEvent event, Token token, Grid grid, int squareSize) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Square s = grid.getSquareFromMousePosition(event.getSceneY(),
                    event.getSceneX(), squareSize);
            token.setPosition(s);

            drawToken(token, squareSize);
            token.getShape().setOpacity(1);

        }
    }

    public static void createToken(Token token, Color colour, int squareSize) {
        double radius = squareSize / 2 * token.getSize();

        drawToken(token, squareSize);
        token.getShape().setRadius(radius);
        token.getShape().setFill(colour);
    }

    /**
     * Draw a token.
     * 
     * @param token Token that is being drawn.
     * @param squareSize Size of a grid square. 
     */
    public static void drawToken(Token token, int squareSize) {
        double x = centerToken(token,
                token.getPosition().getPixelCoordinateX(), squareSize);

        double y = centerToken(token,
                token.getPosition().getPixelCoordinateY(), squareSize);

        draw(token, x, y);
    }

    /**
     * Calculates center coordinate of token. 
     * Sizes medium (1) and huge (3) have center coordinates in center of square.
     * 
     * @param  token Token that is being centered.
     * @param coordinate Coordinate of token.
     * @param squareSize squareSize of the grid. Is used to calculate the center of the square.
     */
    private static double centerToken(Token token, double coordinate, int squareSize) {
        //Sizes medium (1) and huge (3) have center coordinates in center of square
        if (token.getSize() % 2 != 0) {
            coordinate += squareSize / 2;
        }
        return coordinate;
    }
 
    /**
     * Set the coordinates on screen of the shape of given token.
     * 
     * @param token Token that is being drawn.
     * @param x x coordinate for the shape.
     * @param y y coordinate for the shape.
     */
    public static void draw(Token token, double x, double y) {
        token.getShape().setTranslateX(x);
        token.getShape().setTranslateY(y);
    }

    public static void selectToken(Token token) {
        Color colour = token.getColour();
        token.getShape().setStroke(colour.darker());
        token.getShape().setStrokeWidth(5);
    }

    public static void unSelectToken(Token token) {
        token.getShape().setStroke(null);
    }

    public static void bindArrowKeyEvents(Token token, Grid grid, KeyCode keyCode, int squareSize) {
        switch (keyCode) {
            case RIGHT:
                token.moveRight(grid);
                break;
            case LEFT:
                token.moveLeft(grid);
                break;
            case UP:
                token.moveUp(grid);
                break;
            case DOWN:
                token.moveDown(grid);
                break;
        }
        drawToken(token, squareSize);
    }
}
