  
package rpgbattlemap.ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.binding.Bindings;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import rpgbattlemap.domain.Grid;
import rpgbattlemap.domain.Square;
import rpgbattlemap.domain.Token;

public class RPGBattlemapUI extends Application{
    
    private Stage stage;
    private Grid grid;
    private final int squareSize = 100;
    private boolean createTokenMode = false;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("RPG battlemap");
        
        stage.setScene(createSizeScene());
        
        stage.show();
    }
    
    public Scene createSizeScene() {
        Label label = new Label("Size");
        VBox sizePane = new VBox(label);
        
        HBox widthInputPane = new HBox(10);
        HBox heigthInputPane = new HBox(10);
        VBox colourInputPane = new VBox(10);
        
        Label widthLabel = new Label("width");
        TextField widthInput = new TextField();
        
        Label heightLabel = new Label("height");
        TextField heightInput = new TextField();
        
        Label backgroundColourLabel = new Label("Background colour");
        ColorPicker backgroundColour = new ColorPicker();
        
        Label gridColourLabel = new Label("Grid colour");
        ColorPicker gridColour = new ColorPicker();
        gridColour.setValue(Color.BLACK);
        
        widthInputPane.getChildren().addAll(widthLabel ,widthInput);
        heigthInputPane.getChildren().addAll(heightLabel, heightInput);
        colourInputPane.getChildren().addAll(backgroundColourLabel, backgroundColour, gridColourLabel, gridColour);
        
        Button createGridButton = new Button("Create");
        createGridButton.setOnAction(e -> {
            int width = Integer.parseInt(widthInput.getText());
            int height = Integer.parseInt(heightInput.getText());
            
            stage.setScene(createGridScene(width, height, backgroundColour.getValue(), gridColour.getValue()));
        });
        
        sizePane.getChildren().addAll(widthInputPane, heigthInputPane, colourInputPane, 
                createGridButton);
        Scene sizeScene = new Scene(sizePane);
        
        return sizeScene;
    }
    
    public Scene createGridScene(int width, int height, Color backgroundColour, Color gridColour) {
        ComboBox sizeDdl = createSizeDropdown();
        sizeDdl.getSelectionModel().selectFirst();
        
        ColorPicker tokenColourPicker = new ColorPicker();
        Button tokenButton = new Button("Token");
        Label helpLabel = new Label("Click on grid to create token.");
        helpLabel.setVisible(createTokenMode);
        VBox tokenControls = new VBox(tokenColourPicker, sizeDdl, tokenButton, 
            helpLabel);
        
        ScrollPane scrollPane = new ScrollPane();
        Pane gridPane = createGrid(height, width, backgroundColour, gridColour);
        
        scrollPane.setContent(gridPane);
        
        tokenButton.setOnAction(e -> {
            createTokenMode = true;
            helpLabel.setVisible(createTokenMode);
        });
        
        gridPane.setOnMouseClicked((MouseEvent event) -> {
            if (createTokenMode) {
                int colIndex = 0;
                if(event.getSceneX() >= 100)
                    colIndex = (int)event.getSceneX()/squareSize;
                
                int rowIndex = 0;
                if(event.getSceneY() >= 100)
                    rowIndex = (int)event.getSceneY()/squareSize;   
                
                Square square = grid.getSquareFromMousePosition(event.getSceneY(), event.getSceneX(), squareSize);
                Size size = (Size) sizeDdl.getValue();
                drawToken(gridPane, square, size.getSize(), tokenColourPicker.getValue());
                createTokenMode = false;
                helpLabel.setVisible(createTokenMode);
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
    
    public ComboBox createSizeDropdown(){
        ComboBox sizeDdl = new ComboBox();
        sizeDdl.getItems().add(new Size(1, "Medium"));
        sizeDdl.getItems().add(new Size(2, "Large"));
        sizeDdl.getItems().add(new Size(3, "Huge"));
        sizeDdl.getItems().add(new Size(4, "Gargantuan"));
        return sizeDdl;
    }
    
    public Pane createGrid(int width, int height, Color backgroundColour, Color gridColour){
        Pane gridPane = new Pane();
        this.grid = new Grid(width, height, this.squareSize);        
        
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++) {
                Rectangle r = this.grid.getSquare(i, j).getRectangle();
                r.setFill(backgroundColour);
                r.setStroke(gridColour);
                gridPane.getChildren().add(r);
            }
        }
        return gridPane;
    }
    
    public void drawToken(Pane gridPane, Square square, int size, Color colour) {
        Circle circle = new Circle();
        Token token = new Token(square, size, circle);
        token.createToken(colour, squareSize);
        
        circle.setOnMousePressed(e -> tokenPressed(e, token));
        circle.setOnMouseDragged(e -> tokenDragged(e, token));
        circle.setOnMouseReleased(e -> tokenReleased(e, token));
        
        gridPane.getChildren().add(token.getShape());
    }
    
    public void tokenPressed(MouseEvent event, Token token){
        token.getShape().setOpacity(0.8);
    }

    private void tokenDragged(MouseEvent e, Token token) {
        System.out.println(e.getSceneX() + " " + e.getSceneY());
        
        token.draw(e.getSceneX()-token.getShape().getCenterX(), e.getSceneY()-token.getShape().getCenterY());
    }
    
    public void tokenReleased(MouseEvent event, Token token) {
        Square s = grid.getSquareFromMousePosition(event.getSceneY(), event.getSceneX(), squareSize);
        token.setPosition(s);
        
        token.drawToken(squareSize);
        token.getShape().setOpacity(1);
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
        public String toString(){
            return this.name;
        }
    }

}