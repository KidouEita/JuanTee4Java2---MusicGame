/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicgame;

import java.util.Optional;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author KidouEita
 */
public class MusicGameLauncher extends Application {

    private boolean optIn = false; // to check whether user is in the Option Interface
    private boolean listItemclicked = false; // to fix a small bug about fadetransition
    private int gameLevel;
    private Label[] hintSetBtns = new Label[7];
    private KeyCode[] setBtns = {KeyCode.S, KeyCode.D, KeyCode.F, KeyCode.SPACE, KeyCode.J, KeyCode.K, KeyCode.L};
    private String[] btnContext = new String[7];

    @Override
    public void start(Stage primaryStage) {

        /** Pane Management */
        /* Create Main Pane */
        HBox mainPane = new HBox();
        Scene mainScene = new Scene(mainPane, 1200, 550);
        mainScene.getStylesheets().add(getClass().getResource("Menu.css").toExternalForm());
        /* Set the BackGround of the Main Pane */
        mainPane.setId("mainPane");
        /* Create two pane and add them to the Main Pane */
        Pane menuPane = new Pane();
        menuPane.setPrefSize(250, 550);
        menuPane.setId("lightWhiteBG");
        mainPane.getChildren().add(menuPane);

        /** Two side Panes named optPane and artPane */
        /* Settings of optPane */
        Pane optPane = new Pane();
        optPane.setPrefSize(950, 550);
        optPane.setId("optPane");
        /* Settings of artPane */
        Pane artPane = new Pane();
        artPane.setPrefSize(950, 550);
        artPane.setId("artPane");
        mainPane.getChildren().add(artPane); // Default setting the side is artPane

        /** Nodes on the menuPane */
        /* All About Button "Start" */
        Label startBtn = new Label();
        startBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/Start.png"))));
        startBtn.setLayoutX(50);
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
        /* All About Button "Play" */
        Label playBtn = new Label();
        playBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/Play.png"))));
        playBtn.setLayoutX(70);
        playBtn.setLayoutY(470);
        /* All About Button "Back" */
        Label backBtn = new Label();
        backBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/Back.png"))));
        backBtn.setLayoutX(30);
        backBtn.setLayoutY(380);
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
        songList.getStylesheets().add(getClass().getResource("SongList.css").toExternalForm());
        songList.setStyle("-fx-background-color: rgba(255, 255,255, 0.2);");
        songList.setLayoutY(50);
        songList.setPrefSize(250, 300);
        songList.getItems().addAll(new Label("Deemo 2.0 - Xi - Anima.mp3"),
                new Label("Song2"),
                new Label("Song3"),
                new Label("Song4"));
        for (int i = 0; i < songList.getItems().size(); i++) {
            songList.getItems().get(i).setPrefSize(234, 20);
        }

        /* Make two Groups to contain Buttons(Labels)in the menuPane */
        Group fstBtns = new Group();
        fstBtns.getChildren().addAll(startBtn, helpBtn, optBtn, exitBtn); // Contain "Start","Option","Help",and "Exit"
        Group lstBtns = new Group();
        lstBtns.getChildren().addAll(backBtn, playBtn, songList); // Contain "Back" and List of Songs
        lstBtns.setVisible(false); // Set it's unvisible in the beginning
        menuPane.getChildren().addAll(fstBtns, lstBtns);

        /** Reactions of the Labels in the SongList */
        FadeTransition songFt = new FadeTransition();
        songFt.setAutoReverse(true);
        ImageView songPic = new ImageView();
        artPane.getChildren().add(songPic);
        songPic.setId("song1");
        songPic.setOpacity(0);
        songList.getItems().get(0).setOnMouseClicked(e -> {
            gameLevel = 1;
            listItemclicked = true;
            songFt.setNode(songPic);
            songFt.setDuration(new Duration(1000));
            songFt.setRate(1);
            songPic.setVisible(true);
            songFt.setFromValue(0);
            songFt.setToValue(1);
            songFt.play();
        });
        songList.getItems().get(1).setOnMouseClicked(e -> gameLevel = 2); // Test Log
        songList.getItems().get(2).setOnMouseClicked(e -> gameLevel = 3); // Test Log
        songList.getItems().get(3).setOnMouseClicked(e -> gameLevel = 4); // Test Log

        /** Nodes on the optPane */
        /* Buttons to setting */
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    btnContext[i] = String.format("Set %d\ndefault S\nNow %s", (i + 1), setBtns[0].getName());
                    break;
                case 1:
                    btnContext[i] = String.format("Set %d\ndefault D\nNow %s", (i + 1), setBtns[0].getName());
                    break;
                case 2:
                    btnContext[i] = String.format("Set %d\ndefault F\nNow %s", (i + 1), setBtns[0].getName());
                    break;
                case 3:
                    btnContext[i] = String.format("Set %d\ndefault Space\nNow %s", (i + 1), setBtns[0].getName());
                    break;
                case 4:
                    btnContext[i] = String.format("Set %d\ndefault J\nNow %s", (i + 1), setBtns[0].getName());
                    break;
                case 5:
                    btnContext[i] = String.format("Set %d\ndefault K\nNow %s", (i + 1), setBtns[0].getName());
                    break;
                case 6:
                    btnContext[i] = String.format("Set %d\ndefault L\nNow %s", (i + 1), setBtns[0].getName());
                    break;
            }
        }

        Group btnsInOpt = new Group(); // Group containing Buttons in Option interface
        for (int i = 0; i < 7; i++) {
            hintSetBtns[i] = new Label(btnContext[i]);
            if (i == 4) {
                hintSetBtns[i].setLayoutX(540);
            } else if (i > 4) {
                hintSetBtns[i].setLayoutX(540 + (i - 4) * 100);
            } else {
                hintSetBtns[i].setLayoutX(110 + i * 100);
            }
            final int innerI = i; // for setting each Labels' reaction
            hintSetBtns[i].setLayoutY(300);
            btnsInOpt.getChildren().add(hintSetBtns[i]);
            hintSetBtns[i].setStyle("-fx-background-color: rgba(255,255,255,0.5);" +
                    "-fx-text-fill: black;" +
                    "-fx-text-alignment: center;");
            hintSetBtns[i].setOnMouseMoved(e -> hintSetBtns[innerI].setStyle("-fx-background-color: rgba(0,0,0,0.5);" +
                    "-fx-text-fill: white;" +
                    "-fx-text-alignment: center;"));
            hintSetBtns[i].setOnMouseExited(e -> hintSetBtns[innerI].setStyle("-fx-background-color: rgba(255,255,255,0.5);" +
                    "-fx-text-fill: black;" +
                    "-fx-text-alignment: center;"));
        }

        Pane setKey = new Pane();
        setKey.setPrefSize(950, 550);
        setKey.setId("optPane");
        Label reminder = new Label();
        setKey.getChildren().add(reminder);
        reminder.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Menu/Start.png"))));

        hintSetBtns[0].setOnMouseClicked(e -> {
            mainScene.setOnKeyTyped(a -> {
                if (!a.getCode().equals(KeyCode.ESCAPE)) {
                    setBtns[0] = a.getCode();
                    btnContext[0] = String.format("Set %d\ndefault S\nNow %s", 1, setBtns[0].getName());
                    hintSetBtns[0].setText(btnContext[0]);
                }
            });
        });
        /*
        hintSetBtns[1].setOnMouseClicked();
        hintSetBtns[2].setOnMouseClicked();
        hintSetBtns[3].setOnMouseClicked();
        hintSetBtns[4].setOnMouseClicked();
        hintSetBtns[5].setOnMouseClicked();
        hintSetBtns[6].setOnMouseClicked();
        */

        optPane.getChildren().add(btnsInOpt);
        primaryStage.setScene(mainScene);

        // Create Songs Object
        MusicGame game = new MusicGame(primaryStage, mainScene);

        /** Buttons' Fuctions */
        startBtn.setOnMouseClicked(e -> {
            mainPane.getChildren().set(1, artPane);
            optIn = false;
            fstBtns.setVisible(false);
            lstBtns.setVisible(true);
            listItemclicked = false;
        });
        optBtn.setOnMouseClicked(e -> {
            if (optIn) {
                optIn = false;
                mainPane.getChildren().set(1, artPane);
            } else {
                optIn = true;
                mainPane.getChildren().set(1, optPane);
            }
        });
        helpBtn.setOnMouseClicked(e -> {
            optIn = false;
        });
        exitBtn.setOnMouseClicked(e -> {
            Optional<ButtonType> resultAlert = exitAlert.showAndWait();
            if (resultAlert.get() == ButtonType.OK) { // if "OK" is chosen
                System.exit(0); // End the Program
            }
        });
        playBtn.setOnMouseClicked(e -> {
            if (gameLevel == 1) {
                game.getScene().setOnKeyTyped(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode().equals(KeyCode.ESCAPE)) {
                            primaryStage.setScene(mainScene);
                        }
                    }
                });
                primaryStage.setScene(game.getScene());
                game.setButton(0, setBtns[0]);
                game.playGame();
            }
        });
        backBtn.setOnMouseClicked(e -> {
            optIn = false;
            fstBtns.setVisible(true);
            lstBtns.setVisible(false);
            if (listItemclicked) {
                songFt.stop();
                songFt.setRate(-1);
                songFt.play();
            }
        });
        primaryStage.setTitle("MusicGame");
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}