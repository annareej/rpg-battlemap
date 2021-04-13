package rpgbattlemap.ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import rpgbattlemap.domain.Grid;

public class RPGBattlemapUI extends Application{
    
    private Stage stage;
    
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
        
        Label widthLabel = new Label("width");
        TextField widthInput = new TextField();
        
        Label heightLabel = new Label("height");
        TextField heightInput = new TextField();
        
        widthInputPane.getChildren().addAll(widthLabel ,widthInput);
        heigthInputPane.getChildren().addAll(heightLabel, heightInput);
        
        Button createGridButton = new Button("Create");
        createGridButton.setOnAction(e -> {
            int width = Integer.parseInt(widthInput.getText());
            int height = Integer.parseInt(heightInput.getText());
            
            resizeStage(height, width);
            Scene gridScene = new Scene(createGrid(height, width));
            gridScene.setFill(Color.WHITE);
            stage.setScene(gridScene);
        });
        
        sizePane.getChildren().addAll(widthInputPane, heigthInputPane, 
                createGridButton);
        Scene sizeScene = new Scene(sizePane);
        
        return sizeScene;
    }
    
    public void resizeStage(int heigth, int width) {
        this.stage.setHeight(heigth * 100);
        this.stage.setWidth(width * 100);
    }
    
    public static Pane createGrid(int width, int height){
        Pane gridPane = new Pane();        
        Rectangle[][] grid = new Rectangle[height][width];
        for(int i=0; i < height; i++){
            for(int j = 0; j < width; j++) {
                grid[i][j] = new Rectangle(i*100, j*100, 100, 100);
                grid[i][j].setFill(Color.WHITE);
                grid[i][j].setStroke(Color.BLACK);
                gridPane.getChildren().add(grid[i][j]);
            }
        }
        
        return gridPane;
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
