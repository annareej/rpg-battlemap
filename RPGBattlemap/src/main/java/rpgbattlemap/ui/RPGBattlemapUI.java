package rpgbattlemap.ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import rpgbattlemap.domain.Grid;
import rpgbattlemap.domain.Square;
import rpgbattlemap.domain.Token;

public class RPGBattlemapUI extends Application {

    private Stage stage;
    private Grid grid;
    private final int squareSize = 100;
    private boolean createTokenMode = false;
    private Token selected = null;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("RPG battlemap");

        stage.setScene(createSizeScene());

        stage.show();
    }

    public Scene createSizeScene() {
        GridPane pane = new GridPane();
        Text sizeHeader = new Text("Grid");
        sizeHeader.setFont(Font.font(15));
        pane.add(sizeHeader, 0, 0);

        Label widthLabel = new Label("width");
        TextField widthInput = new TextField();
        pane.add(widthLabel, 0, 1);
        pane.add(widthInput, 1, 1);

        Label heightLabel = new Label("height");
        TextField heightInput = new TextField();
        pane.add(heightLabel, 0, 2);
        pane.add(heightInput, 1, 2);

        Label backgroundColourLabel = new Label("Background colour");
        ColorPicker backgroundColour = new ColorPicker();

        Label gridColourLabel = new Label("Grid colour");
        ColorPicker gridColour = new ColorPicker();
        gridColour.setValue(Color.BLACK);

        pane.add(backgroundColourLabel, 0, 3);
        pane.add(backgroundColour, 1, 3);

        pane.add(gridColourLabel, 0, 4);
        pane.add(gridColour, 1, 4);

        Button createGridButton = new Button("Create");
        createGridButton.setOnAction(e -> {
            if (isStringInt(widthInput.getText()) && isStringInt(heightInput.getText())) {
                int width = Integer.parseInt(widthInput.getText());
                int height = Integer.parseInt(heightInput.getText());
                if(width > 0 && height > 0)
                    stage.setScene(createGridScene(width, height, backgroundColour.getValue(),
                            gridColour.getValue()));

            }
        });

        pane.add(createGridButton, 0, 5);

        pane.setMinSize(400, 200);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(5);
        pane.setHgap(5);
        Scene sizeScene = new Scene(pane);

        return sizeScene;
    }
    
    public boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public Scene createGridScene(int width, int height, Color backgroundColour, Color gridColour) {
        ComboBox sizeDdl = createSizeDropdown();
        sizeDdl.getSelectionModel().selectFirst();

        Text tokenHeader = new Text("Token");
        tokenHeader.setFont(Font.font(15));

        ColorPicker tokenColourPicker = new ColorPicker();
        tokenColourPicker.setValue(Color.RED);
        Button tokenButton = new Button("Create");
        Text createHelpText = new Text("Click on grid to create token.");
        createHelpText.setVisible(createTokenMode);

        Text selectedTokenHelpText = new Text("Token selected. Click grid with right mouse button to unselect. Move with mouse or arrow keys. Press DEL to remove.");
        selectedTokenHelpText.setVisible(false);
        
        VBox tokenControls = new VBox(tokenHeader, tokenColourPicker, sizeDdl,
                tokenButton, createHelpText, selectedTokenHelpText);
        tokenControls.setPadding(new Insets(10, 10, 10, 10));
        tokenControls.setSpacing(5);

        ScrollPane scrollPane = new ScrollPane();
        Pane gridPane = createGrid(height, width, backgroundColour, gridColour);

        scrollPane.setContent(gridPane);

        tokenButton.setOnAction(e -> {
            createTokenMode = true;
            createHelpText.setVisible(createTokenMode);
        });

        gridPane.setOnMouseClicked((MouseEvent event) -> {
            if (createTokenMode) {
                Square square = grid.getSquareFromMousePosition(event.getY(),
                        event.getX(), squareSize);
                Size size = (Size) sizeDdl.getValue();
                drawToken(gridPane, square, size.getSize(),
                        tokenColourPicker.getValue(), selectedTokenHelpText);
                createTokenMode = false;
                createHelpText.setVisible(createTokenMode);
            } else if (event.getButton() == MouseButton.SECONDARY) {
                unSelectToken();
                selectedTokenHelpText.setVisible(false);
            }
            
            //Enable key event listener
            gridPane.requestFocus();
        });

        gridPane.setOnKeyPressed(e -> {
            if (this.selected != null) {
                if (e.getCode() == KeyCode.DELETE) {
                    deleteToken(gridPane, this.selected, selectedTokenHelpText);
                } else {
                    UITokenControls.bindArrowKeyEvents(this.selected, this.grid, e.getCode(), this.squareSize);
                }
                //So event doesn't scroll pane with arrow keys
                e.consume();
            }
        });

        HBox box = new HBox(scrollPane, tokenControls);
        resizeStage(height, width);
        Scene gridScene = new Scene(box);
        stage.setScene(gridScene);

        return gridScene;
    }

    public void resizeStage(int heigth, int width) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        this.stage.setX(bounds.getMinX());
        this.stage.setY(bounds.getMinY());
        this.stage.setWidth(bounds.getWidth());
        this.stage.setHeight(bounds.getHeight());
    }

    public ComboBox createSizeDropdown() {
        ComboBox sizeDdl = new ComboBox();
        sizeDdl.getItems().add(new Size(1, "Medium"));
        sizeDdl.getItems().add(new Size(2, "Large"));
        sizeDdl.getItems().add(new Size(3, "Huge"));
        sizeDdl.getItems().add(new Size(4, "Gargantuan"));
        return sizeDdl;
    }

    public Pane createGrid(int width, int height, Color backgroundColour, Color gridColour) {
        Pane gridPane = new Pane();
        this.grid = new Grid(width, height, this.squareSize);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Rectangle r = this.grid.getSquare(i, j).getRectangle();
                r.setFill(backgroundColour);
                r.setStroke(gridColour);
                gridPane.getChildren().add(r);
            }
        }
        return gridPane;
    }

    public void tokenClicked(MouseEvent event, Token token, Text text) {
        unSelectToken();
        this.selected = token;
        UITokenControls.selectToken(token);
        text.setVisible(true);
    }

    public void unSelectToken() {
        if (selected != null) {
            UITokenControls.unSelectToken(selected);
            selected = null;    
        }
    }

    public void drawToken(Pane gridPane, Square square, int size, Color colour, Text text) {
        Circle circle = new Circle();
        Token token = new Token(square, size, circle, colour);
        UITokenControls.createToken(token, colour, squareSize);

        circle.setOnMouseClicked(e -> tokenClicked(e, token, text));
        circle.setOnMousePressed(e -> UITokenControls.tokenPressed(e, token));
        circle.setOnMouseDragged(e -> UITokenControls.tokenDragged(e, token));
        circle.setOnMouseReleased(e -> UITokenControls.tokenReleased(e, token,
                this.grid, this.squareSize));

        gridPane.getChildren().add(token.getShape());
    }

    public void deleteToken(Pane gridPane, Token token, Text text) {
        gridPane.getChildren().remove(token.getShape());
        this.selected = null;
        text.setVisible(false);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static class Size {

        private int size;
        private String name;

        public Size(int size, String name) {
            this.size = size;
            this.name = name;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

}