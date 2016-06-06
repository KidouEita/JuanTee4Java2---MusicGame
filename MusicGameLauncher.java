/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicgame;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author KidouEita
 */
public class MusicGameLauncher extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Label startBtn = new Label();
        startBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("MenuIcon/Start.png"))));
        startBtn.setLayoutX(50);
        startBtn.setLayoutY(250);
        Label helpBtn = new Label();
        helpBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("MenuIcon/Help.png"))));
        helpBtn.setLayoutX(50);
        helpBtn.setLayoutY(320);
        Label exitBtn = new Label();
        exitBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("MenuIcon/Exit.png"))));
        exitBtn.setLayoutX(50);
        exitBtn.setLayoutY(390);
        exitBtn.setOnMouseClicked(e -> System.exit(0));
        
        pane.getChildren().addAll(startBtn,helpBtn,exitBtn);
        Scene mainScene = new Scene(pane, 1000, 550);
        primaryStage.setTitle("MusicGame");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
