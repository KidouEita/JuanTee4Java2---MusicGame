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
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
//import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author KidouEita
 */
public class MusicGameLauncher extends Application {
    
    boolean optIn = false; // to check whether user is in the Option Interface
    
    @Override
    public void start(Stage primaryStage) {
        
        /* Create Main Pane */
        BorderPane mainPane = new BorderPane();
        Scene mainScene = new Scene(mainPane, 1200, 550);
        mainScene.getStylesheets().add(getClass().getResource("Menu.css").toExternalForm());
        
        /* Set the BackGround of the Main Pane */
        mainPane.setId("mainPane");
        
        /* Create two pane and add them to the Main Pane */
        Pane menuPane = new Pane();
        menuPane.setPrefSize(250, 550);
        menuPane.setId("lightWhiteBG");
        Pane sidePane = new Pane();
        sidePane.setPrefSize(950, 550);
        mainPane.setLeft(menuPane);
        mainPane.setRight(sidePane);
        
        //sidePane.setStyle("-fx-background-image: url(\"background_image.jpg\");"); // Unknown
        //sidePane.setStyle("-fx-background-color: red;"); //Effective
        
        /** Nodes on the menuPane */
        /* All About Button "Start" */
        Label startBtn = new Label();
        startBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/Start.png"))));
        startBtn.setLayoutX(40);
        startBtn.setLayoutY(250);
        /* All About Button "Option" */
        Label optBtn = new Label();
        optBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/Option.png"))));
        optBtn.setLayoutX(50);
        optBtn.setLayoutY(320);
        /* All About Button "Help" */
        Label helpBtn = new Label();
        helpBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/Help.png"))));
        helpBtn.setLayoutX(50);
        helpBtn.setLayoutY(390);
        /* All About Button "Exit" */
        Label exitBtn = new Label();
        exitBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/Exit.png"))));
        exitBtn.setLayoutX(50);
        exitBtn.setLayoutY(460);
        /* All About Button "Back" */
        Label backBtn = new Label();
        backBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/Back.png"))));
        backBtn.setLayoutX(50);
        backBtn.setLayoutY(400);
        /* Alert For the "Exit" */
        Alert exitAlert = new Alert(AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit Game?");
        exitAlert.setHeaderText("");
        exitAlert.setResizable(false);
        exitAlert.setContentText("Exit Game?");
        exitAlert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/Help.png"))));
        /* The List of Songs */
        ListView<Label> songList = new ListView<>();
        songList.setEditable(false);
        songList.getStylesheets().add(getClass().getResource("songList.css").toExternalForm());
        //songList.setStyle("-fx-background-color: transparent;");
        songList.setStyle("-fx-background-color: rgba(255, 255,255, 0.2);");
        songList.setLayoutY(50);
        songList.setPrefSize(250, 300);
        songList.getItems().addAll(new Label("Deemo 2.0 - Xi - Anima.mp3"),
                                                new Label("Song2"),
                                                new Label("Song3"),
                                                new Label("Song4"));
        for(int i = 0;i < songList.getItems().size();i++){
            songList.getItems().get(i).setPrefSize(234, 20);
        }
        songList.getItems().get(0).setOnMouseClicked(e -> System.out.println("Deemo 2.0 - Xi - Anima.mp3 Clicked")); // Test Log
        songList.getItems().get(1).setOnMouseClicked(e -> System.out.println("2 is Clicked")); // Test Log
        songList.getItems().get(2).setOnMouseClicked(e -> System.out.println("3 is Clicked")); // Test Log
        songList.getItems().get(3).setOnMouseClicked(e -> System.out.println("4 is Clicked")); // Test Log
        /* Make two Groups to contain Buttons(Labels)in the menuPane */
        Group fstBtns = new Group();
        fstBtns.getChildren().addAll(startBtn,helpBtn,optBtn,exitBtn); // Contain "Start","Option","Help",and "Exit"
        Group lstBtns = new Group();
        lstBtns.getChildren().addAll(backBtn,songList); // Contain "Back" and List of Songs
        lstBtns.setVisible(false); // Set it's unvisible in the beginning
        
        /** Nodes on the sidePane */
        /* Buttons to setting */
        Label[] hintSetBtns = new Label[7];
        String[] btnContext = {"Set 1\ndefault S",
                                            "Set 2\ndefault D",
                                            "Set 3\ndefault F",
                                            "Set 4\ndefault Space",
                                            "Set 5\ndefault J",
                                            "Set 6\ndefault K",
                                            "Set 7\ndefault L"};
        KeyCode[] setBtns = new KeyCode[7];
        Group btnsInOpt = new Group(); // Group containing Buttons in Option interface
        for(int i = 0;i<7;i++) {
            hintSetBtns[i] = new Label(btnContext[i]);
            btnsInOpt.getChildren().add(hintSetBtns[i]);
        }
        
        
        
        

        
        /* Buttons' Fuctions */
        exitBtn.setOnMouseClicked(e -> {
            Optional<ButtonType> resultAlert = exitAlert.showAndWait();
            if(resultAlert.get() == ButtonType.OK){ // if "OK" is chosen
                System.exit(0); // End the Program
            }
        });
        startBtn.setOnMouseClicked(e -> {
            optIn = false;
            fstBtns.setVisible(false);
            lstBtns.setVisible(true);
        });
        optBtn.setOnMouseClicked(e -> {
            if(optIn){
                optIn = false;
            }
            else{
                optIn = true;
            }
        });
        helpBtn.setOnMouseClicked(e -> {
            optIn = false;
        });
        backBtn.setOnMouseClicked(e -> {
            optIn = false;
            fstBtns.setVisible(true);
            lstBtns.setVisible(false);
        });
        
        menuPane.getChildren().addAll(fstBtns,lstBtns);
        primaryStage.setTitle("MusicGame");
        primaryStage.setResizable(false);
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