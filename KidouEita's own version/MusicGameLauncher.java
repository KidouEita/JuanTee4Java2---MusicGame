/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicgame;

import java.util.Optional;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author KidouEita
 */
public class MusicGameLauncher extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        /* Create Main Pane */
        BorderPane mainPane = new BorderPane();
        /* Set the BackGround of the Main Pane */
        mainPane.setStyle("-fx-background-color: yellow;");
        /* Create two pane and add them to the Main Pane */
        Pane menuPane = new Pane();
        menuPane.setPrefSize(250, 550);
        Pane sidePane = new Pane();
        mainPane.setLeft(menuPane);
        mainPane.setRight(sidePane);
        
        Label yee = new Label();
        yee.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/test.png"))));
        sidePane.getChildren().add(yee);
        
        /** Create all Buttons with Label */
        /* All About Button "Start" */
        Label startBtn = new Label();
        startBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/Start.png"))));
        startBtn.setLayoutX(40);
        startBtn.setLayoutY(250);
        /* All About Button "Help" */
        Label helpBtn = new Label();
        helpBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/Help.png"))));
        helpBtn.setLayoutX(70);
        helpBtn.setLayoutY(320);
        /* All About Button "Exit" */
        Label exitBtn = new Label();
        exitBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/Exit.png"))));
        exitBtn.setLayoutX(50);
        exitBtn.setLayoutY(390);
        /* All About Button "Back" */
        Label backBtn = new Label();
        backBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/Back.png"))));
        backBtn.setLayoutX(50);
        backBtn.setLayoutY(400);
        backBtn.setVisible(false);
        
        /* Alert For the "Exit" */
        Alert exitAlert = new Alert(AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit Game?");
        exitAlert.setHeaderText("");
        exitAlert.setResizable(false);
        exitAlert.setContentText("Exit Game?");
        exitAlert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/Help.png"))));
        
        /* The List of Songs */
        ListView<Label> songList = new ListView<>();
        songList.setOpacity(0.7); // set its semi-transparent
        songList.setLayoutY(50);
        songList.setPrefSize(250, 300);
        songList.setVisible(false);
        songList.getItems().addAll(new Label("Song1"),
                                                new Label("Song2"));
        songList.getItems().get(0).setOnMouseClicked(e -> System.out.println("1 is Clicked"));// Test Statement
        songList.getItems().get(1).setOnMouseClicked(e -> System.out.println("2 is Clicked"));// Test Statement
        
        
        /* Buttons' Fuctions */
        exitBtn.setOnMouseClicked(e -> {
            //exitAlert.showAndWait();
            Optional<ButtonType> resultAlert = exitAlert.showAndWait();
            if(resultAlert.get() == ButtonType.OK){
                System.exit(0);
            }
        });  //********************need Improvement
        startBtn.setOnMouseClicked(e -> {
            startBtn.setVisible(false);
            helpBtn.setVisible(false);
            exitBtn.setVisible(false);
            backBtn.setVisible(true);
            songList.setVisible(true);
        });
        backBtn.setOnMouseClicked(e -> {
            startBtn.setVisible(true);
            helpBtn.setVisible(true);
            exitBtn.setVisible(true);
            backBtn.setVisible(false);
            songList.setVisible(false);
        });
        
        
        
        menuPane.getChildren().addAll(startBtn,helpBtn,exitBtn,backBtn,songList);
        Scene mainScene = new Scene(mainPane, 1200, 550);
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
