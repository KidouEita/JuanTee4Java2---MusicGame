package musicgame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.text.*;
import javafx.scene.media.*;
import javafx.stage.Stage;

import java.util.LinkedList;

public class MusicGame {

    BorderPane pane = new BorderPane();
    Scene scene = new Scene(pane, 1200, 550);
    Text ScoreBoard = new Text();
    Text ComboBoard = new Text();
    KeyCode[] keyCode = new KeyCode[7];
    MusicPaneA ballPaneA;
    Stage main;
    Scene mScene;

    public MusicGame() {
    }

    public MusicGame(Stage stage, Scene yee) {
        main = stage;
        mScene = yee;

        // Import the StyleSheet css file
        scene.getStylesheets().add(getClass().getResource("Game.css").toExternalForm());

        // Initialize every Buttons with the default Buttons
        keyCode[0] = KeyCode.S;
        keyCode[1] = KeyCode.D;
        keyCode[2] = KeyCode.F;
        keyCode[3] = KeyCode.SPACE;
        keyCode[4] = KeyCode.J;
        keyCode[5] = KeyCode.K;
        keyCode[6] = KeyCode.L;

        ScoreBoard.setLayoutX(1100);
        ScoreBoard.setLayoutY(30);
        ComboBoard.setLayoutX(900);
        ComboBoard.setLayoutY(150);
        pane.setId("gamePane");
        pane.getChildren().addAll(ScoreBoard, ComboBoard);
    }

    public void playGame() {
        ballPaneA = new MusicPaneA(scene, ScoreBoard, ComboBoard);
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        // Add or remove a ball
        // Pause and resume animation
        ballPaneA.setOnMousePressed(e -> ballPaneA.pause());
        ballPaneA.setOnMouseReleased(e -> ballPaneA.play());
        // Use a scroll bar to control animation speed
        pane.setCenter(ballPaneA);
        pane.setBottom(hBox);
    }

    public Scene getScene() {
        return scene;
    }

    public void setButton(int index, KeyCode btn) {
        keyCode[index] = btn;
    }

    private class MusicPaneA extends Pane {
        private boolean isSPressed, isDPressed, isFPressed, isSpacePressed,
                isJPressed, isKPressed, isLPressed;
        private Timeline animation;
        private Timeline MusicDuration;
        private Timeline animationA;
        private Timeline DynamicScore;
        private Timeline DynamicCombo;

        Media Anima;
        MediaPlayer player;

        LinkedList<Node> board = new LinkedList<Node>();
        LinkedList<Node> boarderlight = new LinkedList<Node>();
        LinkedList<Node> boarderGood = new LinkedList<Node>();
        LinkedList<Node> boarderOK = new LinkedList<Node>();
        LinkedList<Timeline> perfectAnimatedA = new LinkedList<Timeline>();
        LinkedList<Timeline> GoodAnimatedA = new LinkedList<Timeline>();
        LinkedList<Timeline> OKAnimatedA = new LinkedList<Timeline>();
        private int Score = 0;
        private int Combo = 0;

        public MusicPaneA(Scene scene, Text text, Text text1) {
            // Create an animation for moving the ball
            Anima = new Media(getClass().getResource("MusicSrc/Deemo 2.0 - Xi - Anima.mp3").toString());
            player = new MediaPlayer(Anima);
            MusicDuration = new Timeline(
                    new KeyFrame(Duration.seconds(2.325), e -> player.play()));
            MusicDuration.play();
            animation = new Timeline(
                    new KeyFrame(Duration.millis(25), e -> moveBall(scene)));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.setRate(5);
            animation.play();
            animationA = new Timeline(
                    new KeyFrame(Duration.seconds(1.5), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(1.5), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(2.8), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(2.8), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(4), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(4), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(5.57), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(5.92), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(6.18), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(6.55), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(6.55), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(7.86), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(7.86), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(8.55), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(8.55), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(9.25), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(9.25), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(10.9), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(11.25), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(11.51), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(11.8), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(12.8), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(12.9), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(13.0), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(13.2), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(14.6), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(14.6), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(15.2), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(15.2), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(15.85), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(15.85), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(16.5), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(16.5), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(17.07), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(17.07), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(18.9), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(19.2), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(19.2), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(19.4), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(19.5), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(19.6), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(19.7), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(19.9), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(20.5), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(21.0), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(19.9), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(20.05), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(20.15), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(20.25), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(20.35), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(20.45), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(20.55), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(20.65), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(20.75), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(20.85), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(20.95), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(21.05), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(22.50), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(23.00), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(23.40), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(23.40), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(23.75), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(23.90), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(24.30), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(24.50), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(24.50), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(24.70), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(24.70), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(24.80), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(24.90), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(25.1), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(25.2), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(25.3), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(25.7), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(25.7), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(25.9), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(25.9), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(26.4), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(26.8), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(27.3), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(27.6), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(27.8), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(28.0), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(28.35), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(28.35), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(28.7), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(28.7), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(29.0), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(29.0), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(29.4), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(29.9), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(29.9), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(30.4), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(30.73), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(30.73), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(30.95), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(30.95), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(31.3), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(31.6), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(31.9), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(32.1), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(32.5), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(32.65), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(33.1), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(33.1), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(33.2), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(33.2), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(33.6), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(33.85), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(34.03), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(34.25), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(34.5), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(34.63), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(34.85), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(35.8), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(35.85), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(35.90), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(36.19), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(36.19), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(36.35), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(36.45), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(36.55), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(36.65), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(36.78), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(36.78), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(37.10), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(37.20), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(37.30), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(37.40), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(37.80), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(37.80), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(38.10), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(38.10), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(38.50), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(38.50), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(38.60), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(38.65), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(38.70), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(38.85), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(39.10), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(39.25), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(39.45), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(39.45), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(39.85), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(39.85), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(40.10), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(40.10), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(40.30), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(40.40), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(40.50), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(40.80), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(40.80), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(41.10), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(41.10), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(41.38), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(41.48), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(41.58), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(42.10), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(42.10), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(42.10), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(42.10), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(42.79), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(42.79), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(43.00), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(43.00), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(43.30), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(43.30), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(45.10), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(45.10), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(45.20), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(45.20), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(45.30), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(45.30), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(45.40), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(45.40), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(45.50), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(45.50), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(45.60), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(45.60), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(45.95), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(46.30), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(46.40), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(46.60), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(46.92), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(47.25), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(47.25), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(47.58), e -> add(500, board.size())),
                    new KeyFrame(Duration.seconds(47.68), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(47.78), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(47.88), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(47.98), e -> add(500, board.size())),
                    new KeyFrame(Duration.seconds(48.08), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(48.18), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(48.28), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(48.38), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(48.48), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(48.58), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(48.75), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(49.00), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(49.25), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(49.90), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(49.90), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(50.20), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(50.20), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(50.30), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(50.30), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(50.40), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(50.40), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(50.50), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(50.50), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(50.60), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(50.60), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(50.70), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(50.70), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(51.05), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(51.40), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(51.50), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(52.15), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(52.15), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(52.50), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(52.50), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(52.80), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(52.97), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(53.20), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(53.20), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(53.45), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(53.55), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(53.70), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(53.80), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(53.90), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(54.23), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(54.33), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(54.43), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(54.80), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(54.80), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(55.20), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(55.20), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(56.40), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(56.50), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(56.65), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(56.75), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(56.90), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(57.00), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(57.05), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(57.40), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(57.70), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(57.70), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(58.40), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(58.40), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(58.85), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(58.95), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(59.10), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(59.20), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(59.35), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(59.45), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(59.60), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(59.95), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(60.30), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(60.30), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(60.70), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(60.70), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(61.75), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(61.75), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(62.00), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(62.00), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(62.35), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(62.35), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(62.70), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(62.70), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(63.00), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(63.00), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(63.30), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(63.30), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(63.55), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(63.65), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(63.65), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(63.95), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(63.95), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(64.25), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(64.25), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(64.50), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(64.50), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(64.95), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(64.95), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(64.95), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(64.95), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(65.60), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(65.60), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(65.90), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(65.90), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(66.20), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(66.20), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(66.50), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(66.50), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(66.80), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(66.80), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(67.10), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(67.10), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(67.40), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(67.40), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(67.70), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(67.70), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(68.00), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(68.00), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(68.30), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(68.30), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(68.60), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(68.60), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(68.90), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(68.90), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(69.20), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(69.20), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(69.50), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(69.50), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(69.65), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(69.85), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(69.85), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(70.10), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(70.10), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(70.50), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(70.50), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(70.80), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(70.80), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(71.40), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(71.50), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(71.60), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(71.80), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(72.10), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(72.40), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(72.70), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(73.10), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(73.40), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(73.40), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(73.85), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(74.05), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(74.35), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(74.70), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(75.00), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(75.35), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(75.70), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(76.00), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(76.10), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(76.20), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(76.30), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(76.40), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(76.50), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(76.60), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(76.70), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(76.70), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(77.33), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(77.43), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(77.53), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(77.63), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(77.73), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(77.83), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(77.93), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(78.03), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(78.03), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(78.30), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(78.60), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(78.60), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(79.00), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(79.00), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(79.30), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(79.30), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(79.60), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(79.60), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(80.00), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(80.00), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(80.30), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(80.30), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(80.60), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(80.60), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(80.95), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(80.95), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(81.30), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(81.30), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(81.30), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(81.30), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(81.55), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(81.75), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(82.00), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(82.25), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(82.60), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(82.75), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(82.90), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(82.05), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(83.20), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(83.40), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(83.55), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(83.70), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(83.90), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(83.90), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(84.35), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(84.50), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(84.65), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(84.85), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(85.00), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(85.15), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(85.35), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(85.50), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(85.65), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(85.85), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(86.00), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(86.20), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(86.50), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(86.65), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(86.80), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(86.95), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(87.18), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(87.18), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(87.80), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(87.95), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(88.10), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(88.25), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(88.50), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(88.50), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(89.10), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(89.25), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(89.40), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(89.55), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(89.80), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(89.80), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(90.00), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(90.00), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(90.20), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(90.20), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(90.45), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(90.45), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(90.65), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(90.65), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(91.10), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(91.10), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(91.40), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(91.40), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(91.40), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(91.40), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(91.70), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(91.70), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(91.90), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(91.90), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(91.40), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(91.40), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(92.10), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(92.20), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(92.30), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(92.40), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(92.40), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(92.60), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(92.60), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(92.70), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(92.80), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(92.90), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(93.00), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(93.00), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(93.20), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(93.20), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(93.40), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(93.50), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(93.60), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(93.70), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(93.70), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(94.00), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(94.00), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(94.35), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(94.35), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(94.50), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(94.50), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(94.70), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(94.80), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(94.90), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(95.00), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(95.00), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(95.20), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(95.20), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(95.40), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(95.50), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(95.60), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(95.70), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(95.70), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(95.90), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(95.90), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(96.00), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(96.10), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(96.20), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(96.30), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(96.30), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(96.65), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(96.65), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(97.30), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(97.40), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(97.50), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(97.60), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(97.70), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(97.80), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(97.90), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(98.10), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(98.20), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(98.25), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(98.35), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(98.45), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(98.55), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(98.65), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(98.75), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(98.85), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(99.00), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(99.10), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(99.20), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(99.30), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(99.40), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(99.50), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(99.60), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(99.70), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(99.90), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(100.05), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(100.15), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(100.25), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(100.35), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(101.30), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(101.40), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(101.50), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(101.90), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(102.00), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(102.10), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(102.35), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(102.50), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(102.65), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(102.90), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(102.90), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(103.10), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(103.10), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(103.20), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(103.20), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(103.60), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(103.60), e -> add(8500, board.size())),
                    new KeyFrame(Duration.seconds(103.60), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(103.60), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(104.20), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(104.20), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(104.50), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(104.50), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(104.80), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(104.80), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(106.10), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(106.50), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(106.50), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(106.80), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(106.80), e -> add(2500, board.size())),
                    new KeyFrame(Duration.seconds(107.10), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(107.10), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(107.50), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(107.50), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(107.50), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(107.50), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(108.20), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(108.20), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(108.50), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(108.50), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(108.80), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(108.90), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(109.10), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(109.50), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(109.50), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(109.75), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(109.75), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(110.40), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(110.50), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(110.60), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(110.70), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(110.80), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(111.00), e -> add(1000, board.size())),
                    new KeyFrame(Duration.seconds(111.10), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(111.40), e -> add(100, board.size())),
                    new KeyFrame(Duration.seconds(111.40), e -> add(250, board.size())),
                    new KeyFrame(Duration.seconds(111.40), e -> add(400, board.size())),
                    new KeyFrame(Duration.seconds(111.40), e -> add(550, board.size())),
                    new KeyFrame(Duration.seconds(111.40), e -> add(700, board.size())),
                    new KeyFrame(Duration.seconds(111.40), e -> add(850, board.size())),
                    new KeyFrame(Duration.seconds(111.40), e -> add(1000, board.size())));
            animationA.play();
            DynamicScore = new Timeline(
                    new KeyFrame(Duration.millis(25), e -> printScore(text)));
            DynamicScore.setCycleCount(Timeline.INDEFINITE);
            DynamicScore.play();
            DynamicCombo = new Timeline(
                    new KeyFrame(Duration.millis(25), e -> printCombo(text1)));
            DynamicCombo.setCycleCount(Timeline.INDEFINITE);
            DynamicCombo.play();
            for (int i = 0; i < 8; i++) {
                boarderlight.add(new BoarderLightView(0, 387));
                boarderGood.add(new BoarderGoodView(0, 387));
                boarderOK.add(new BoarderOKView(0, 387));
                final int inneri = i;
                perfectAnimatedA.add(new Timeline(
                        new KeyFrame(Duration.millis(1), e -> perfectadd(boarderlight, inneri * 150 + 100, inneri)),
                        new KeyFrame(Duration.millis(302), e -> perfectremove(boarderlight, inneri))));
                GoodAnimatedA.add(new Timeline(
                        new KeyFrame(Duration.millis(1), e -> perfectadd(boarderGood, inneri * 150 + 100, inneri)),
                        new KeyFrame(Duration.millis(302), e -> perfectremove(boarderGood, inneri))));
                OKAnimatedA.add(new Timeline(
                        new KeyFrame(Duration.millis(1), e -> perfectadd(boarderOK, inneri * 150 + 100, inneri)),
                        new KeyFrame(Duration.millis(302), e -> perfectremove(boarderOK, inneri))));
            }
        }

        public void perfectAnimationadd(LinkedList<Timeline> perfectAnimatedA, LinkedList<Node> boarderlight, double x, int y) {
            boarderlight.get(y).setLayoutY(x);
            perfectAnimatedA.get(y).play();
        }

        public void perfectadd(LinkedList<Node> boarderlight, double x, int l) {
            boarderlight.get(l).setLayoutX(x);
            getChildren().add(boarderlight.get(l));
        }

        public void perfectremove(LinkedList<Node> boarderlight, int y) {
            getChildren().remove(boarderlight.get(y));
        }

        public void add(double x, int y) {
            board.add(y, new BoarderView(0, 0));
            board.get(y).setLayoutX(x);
            getChildren().add(board.get(y));
        }

        public void remove(Node balla) {
            board.remove(balla);
            getChildren().remove(balla);
        }

        public void play() {
            animation.play();
            animationA.play();
            player.play();
        }

        public void pause() {
            animation.pause();
            animationA.pause();
            player.pause();
        }

        public void Pressed(Scene scene) {
            scene.setOnKeyPressed(e -> {
                if (e.getCode().equals(keyCode[0])) {
                    isSPressed = true;
                } else if (e.getCode().equals(keyCode[1]))
                    isDPressed = true;
                else if (e.getCode().equals(keyCode[2]))
                    isFPressed = true;
                else if (e.getCode().equals(keyCode[3]))
                    isSpacePressed = true;
                else if (e.getCode().equals(keyCode[4]))
                    isJPressed = true;
                else if (e.getCode().equals(keyCode[5]))
                    isKPressed = true;
                else if (e.getCode().equals(keyCode[6]))
                    isLPressed = true;
                else if (e.getCode().equals(KeyCode.ESCAPE)) {
                    player.stop();
                    animation.stop();
                    animationA.stop();

                    main.setScene(mScene);
                }
            });
        }

        public void Released(Scene scene) {
            scene.setOnKeyReleased(e -> {
                if (e.getCode().equals(keyCode[0]))
                    isSPressed = false;
                else if (e.getCode().equals(keyCode[1]))
                    isDPressed = false;
                else if (e.getCode().equals(keyCode[2]))
                    isFPressed = false;
                else if (e.getCode().equals(keyCode[3]))
                    isSpacePressed = false;
                else if (e.getCode().equals(keyCode[4]))
                    isJPressed = false;
                else if (e.getCode().equals(keyCode[5]))
                    isKPressed = false;
                else if (e.getCode().equals(keyCode[6]))
                    isLPressed = false;
            });
        }

        protected void moveBall(Scene scene) {
            Pressed(scene);
            Released(scene);
            for (Node balla : board) {
                balla.setLayoutY(1 + balla.getLayoutY());
                if (balla.getLayoutY() >= getHeight()) {
                    Combo = 0;
                    remove(balla);
                }
                if (balla.getLayoutX() == 100) {
                    if (balla.getLayoutY() <= 300 && balla.getLayoutY() >= 250) {
                        if (isSPressed) {
                            remove(balla);
                            Combo = 0;
                            isSPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 330 && balla.getLayoutY() >= 300) {
                        if (isSPressed) {
                            remove(balla);
                            Score += 250;
                            Combo += 1;
                            perfectAnimationadd(OKAnimatedA, boarderOK, balla.getLayoutY(), 0);
                            isSPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 350 && balla.getLayoutY() >= 330) {
                        if (isSPressed) {
                            remove(balla);
                            Score += 450;
                            Combo += 1;
                            perfectAnimationadd(GoodAnimatedA, boarderGood, balla.getLayoutY(), 0);
                            isSPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 380 && balla.getLayoutY() >= 350) {
                        if (isSPressed) {
                            remove(balla);
                            Score += 650;
                            perfectAnimationadd(perfectAnimatedA, boarderlight, balla.getLayoutY(), 0);
                            Combo += 1;
                            isSPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 400 && balla.getLayoutY() >= 380) {
                        if (isSPressed) {
                            remove(balla);
                            Score += 450;
                            Combo += 1;
                            perfectAnimationadd(GoodAnimatedA, boarderGood, balla.getLayoutY(), 0);
                            isSPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 450 && balla.getLayoutY() >= 400) {
                        if (isSPressed) {
                            remove(balla);
                            Score += 250;
                            perfectAnimationadd(OKAnimatedA, boarderOK, balla.getLayoutY(), 0);
                            Combo += 1;
                            isSPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 500 && balla.getLayoutY() >= 450) {
                        if (isSPressed) {
                            remove(balla);
                            Combo = 0;
                            isSPressed = false;
                        }
                    }
                    if (balla.getLayoutY() >= 510) {
                        Combo = 0;
                    }
                }
                if (balla.getLayoutX() == 250) {
                    if (balla.getLayoutY() <= 300 && balla.getLayoutY() >= 250) {
                        if (isDPressed) {
                            remove(balla);
                            Combo = 0;
                            isDPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 330 && balla.getLayoutY() >= 330) {
                        if (isDPressed) {
                            remove(balla);
                            Score += 250;
                            perfectAnimationadd(OKAnimatedA, boarderOK, balla.getLayoutY(), 1);
                            Combo += 1;
                            isDPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 350 && balla.getLayoutY() >= 330) {
                        if (isDPressed) {
                            remove(balla);
                            Score += 450;
                            perfectAnimationadd(GoodAnimatedA, boarderGood, balla.getLayoutY(), 1);
                            Combo += 1;
                            isDPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 380 && balla.getLayoutY() >= 350) {
                        if (isDPressed) {
                            remove(balla);
                            Score += 650;
                            perfectAnimationadd(perfectAnimatedA, boarderlight, balla.getLayoutY(), 1);
                            Combo += 1;
                            isDPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 400 && balla.getLayoutY() >= 380) {
                        if (isDPressed) {
                            remove(balla);
                            Score += 450;
                            perfectAnimationadd(GoodAnimatedA, boarderGood, balla.getLayoutY(), 1);
                            Combo += 1;
                            isDPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 450 && balla.getLayoutY() >= 400) {
                        if (isDPressed) {
                            remove(balla);
                            Score += 250;
                            perfectAnimationadd(OKAnimatedA, boarderOK, balla.getLayoutY(), 1);
                            Combo += 1;
                            isDPressed = false;
                        }
                    }
                    if (balla.getLayoutY() >= 510) {
                        Combo = 0;
                    }
                }
                if (balla.getLayoutX() == 400) {
                    if (balla.getLayoutY() <= 300 && balla.getLayoutY() >= 250) {
                        if (isFPressed) {
                            remove(balla);
                            Combo = 0;
                            isFPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 330 && balla.getLayoutY() >= 300) {
                        if (isFPressed) {
                            remove(balla);
                            ;
                            Score += 250;
                            perfectAnimationadd(OKAnimatedA, boarderOK, balla.getLayoutY(), 2);
                            Combo += 1;
                            isFPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 350 && balla.getLayoutY() >= 330) {
                        if (isFPressed) {
                            remove(balla);
                            ;
                            Score += 450;
                            perfectAnimationadd(GoodAnimatedA, boarderGood, balla.getLayoutY(), 2);
                            Combo += 1;
                            isFPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 380 && balla.getLayoutY() >= 350) {
                        if (isFPressed) {
                            remove(balla);
                            Score += 650;
                            perfectAnimationadd(perfectAnimatedA, boarderlight, balla.getLayoutY(), 2);
                            Combo += 1;
                            isFPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 400 && balla.getLayoutY() >= 380) {
                        if (isFPressed) {
                            remove(balla);
                            Score += 450;
                            perfectAnimationadd(GoodAnimatedA, boarderGood, balla.getLayoutY(), 2);
                            Combo += 1;
                            isFPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 450 && balla.getLayoutY() >= 400) {
                        if (isFPressed) {
                            remove(balla);
                            Score += 250;
                            perfectAnimationadd(OKAnimatedA, boarderOK, balla.getLayoutY(), 2);
                            Combo += 1;
                            isFPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 500 && balla.getLayoutY() >= 450) {
                        if (isFPressed) {
                            remove(balla);
                            Combo = 0;
                            isFPressed = false;
                        }
                    }
                    if (balla.getLayoutY() >= 510) {
                        Combo = 0;
                    }
                }
                if (balla.getLayoutX() == 550) {
                    if (balla.getLayoutY() <= 300 && balla.getLayoutY() >= 250) {
                        if (isSpacePressed) {
                            remove(balla);
                            Combo = 0;
                            isSpacePressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 330 && balla.getLayoutY() >= 300) {
                        if (isSpacePressed) {
                            remove(balla);
                            Score += 250;
                            perfectAnimationadd(OKAnimatedA, boarderOK, balla.getLayoutY(), 3);
                            Combo += 1;
                            isSpacePressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 350 && balla.getLayoutY() >= 330) {
                        if (isSpacePressed) {
                            remove(balla);
                            Score += 450;
                            perfectAnimationadd(GoodAnimatedA, boarderGood, balla.getLayoutY(), 3);
                            Combo += 1;
                            isSpacePressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 380 && balla.getLayoutY() >= 350) {
                        if (isSpacePressed) {
                            remove(balla);
                            Score += 650;
                            perfectAnimationadd(perfectAnimatedA, boarderlight, balla.getLayoutY(), 3);
                            Combo += 1;
                            isSpacePressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 400 && balla.getLayoutY() >= 380) {
                        if (isSpacePressed) {
                            remove(balla);
                            Score += 450;
                            perfectAnimationadd(GoodAnimatedA, boarderGood, balla.getLayoutY(), 3);
                            Combo += 1;
                            isSpacePressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 450 && balla.getLayoutY() >= 400) {
                        if (isSpacePressed) {
                            remove(balla);
                            Score += 250;
                            perfectAnimationadd(OKAnimatedA, boarderOK, balla.getLayoutY(), 3);
                            Combo += 1;
                            isSpacePressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 500 && balla.getLayoutY() >= 450) {
                        if (isSpacePressed) {
                            remove(balla);
                            Combo = 0;
                            isSpacePressed = false;
                        }
                    }
                    if (balla.getLayoutY() >= 510) {
                        Combo = 0;
                    }
                }
                if (balla.getLayoutX() == 700) {
                    if (balla.getLayoutY() <= 300 && balla.getLayoutY() >= 250) {
                        if (isJPressed) {
                            remove(balla);
                            Combo = 0;
                            isJPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 330 && balla.getLayoutY() >= 300) {
                        if (isJPressed) {
                            remove(balla);
                            Score += 250;
                            perfectAnimationadd(OKAnimatedA, boarderOK, balla.getLayoutY(), 4);
                            Combo += 1;
                            isJPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 350 && balla.getLayoutY() >= 330) {
                        if (isJPressed) {
                            remove(balla);
                            Score += 450;
                            perfectAnimationadd(GoodAnimatedA, boarderGood, balla.getLayoutY(), 4);
                            Combo += 1;
                            isJPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 380 && balla.getLayoutY() >= 350) {
                        if (isJPressed) {
                            remove(balla);
                            Score += 650;
                            perfectAnimationadd(perfectAnimatedA, boarderlight, balla.getLayoutY(), 4);
                            Combo += 1;
                            isJPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 400 && balla.getLayoutY() >= 380) {
                        if (isJPressed) {
                            remove(balla);
                            Score += 450;
                            perfectAnimationadd(GoodAnimatedA, boarderGood, balla.getLayoutY(), 4);
                            Combo += 1;
                            isJPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 450 && balla.getLayoutY() >= 400) {
                        if (isJPressed) {
                            remove(balla);
                            Score += 250;
                            perfectAnimationadd(OKAnimatedA, boarderOK, balla.getLayoutY(), 4);
                            Combo += 1;
                            isJPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 500 && balla.getLayoutY() >= 450) {
                        if (isJPressed) {
                            remove(balla);
                            Combo = 0;
                            isJPressed = false;
                        }
                    }
                    if (balla.getLayoutY() >= 510) {
                        Combo = 0;
                    }
                }
                if (balla.getLayoutX() == 850) {
                    if (balla.getLayoutY() <= 300 && balla.getLayoutY() >= 250) {
                        if (isKPressed) {
                            remove(balla);
                            Combo = 0;
                            isKPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 330 && balla.getLayoutY() >= 300) {
                        if (isKPressed) {
                            remove(balla);
                            Score += 250;
                            perfectAnimationadd(OKAnimatedA, boarderOK, balla.getLayoutY(), 5);
                            Combo += 1;
                            isKPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 350 && balla.getLayoutY() >= 330) {
                        if (isKPressed) {
                            remove(balla);
                            Score += 450;
                            perfectAnimationadd(GoodAnimatedA, boarderGood, balla.getLayoutY(), 5);
                            Combo += 1;
                            isKPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 380 && balla.getLayoutY() >= 350) {
                        if (isKPressed) {
                            remove(balla);
                            Score += 650;
                            perfectAnimationadd(perfectAnimatedA, boarderlight, balla.getLayoutY(), 5);
                            Combo += 1;
                            isKPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 400 && balla.getLayoutY() >= 380) {
                        if (isKPressed) {
                            remove(balla);
                            Score += 450;
                            perfectAnimationadd(GoodAnimatedA, boarderGood, balla.getLayoutY(), 5);
                            Combo += 1;
                            isKPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 450 && balla.getLayoutY() >= 400) {
                        if (isKPressed) {
                            remove(balla);
                            Score += 250;
                            perfectAnimationadd(OKAnimatedA, boarderOK, balla.getLayoutY(), 5);
                            Combo += 1;
                            isKPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 500 && balla.getLayoutY() >= 450) {
                        if (isKPressed) {
                            remove(balla);
                            Combo = 0;
                            isKPressed = false;
                        }
                    }
                    if (balla.getLayoutY() >= 510) {
                        Combo = 0;
                    }
                }
                if (balla.getLayoutX() == 1000) {
                    if (balla.getLayoutY() <= 300 && balla.getLayoutY() >= 250) {
                        if (isLPressed) {
                            remove(balla);
                            Combo = 0;
                            isLPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 330 && balla.getLayoutY() >= 300) {
                        if (isLPressed) {
                            remove(balla);
                            ;
                            Score += 250;
                            perfectAnimationadd(OKAnimatedA, boarderOK, balla.getLayoutY(), 6);
                            Combo += 1;
                            isLPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 350 && balla.getLayoutY() >= 330) {
                        if (isLPressed) {
                            remove(balla);
                            Score += 450;
                            perfectAnimationadd(GoodAnimatedA, boarderGood, balla.getLayoutY(), 6);
                            Combo += 1;
                            isLPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 380 && balla.getLayoutY() >= 350) {
                        if (isLPressed) {
                            remove(balla);
                            Score += 650;
                            perfectAnimationadd(perfectAnimatedA, boarderlight, balla.getLayoutY(), 6);
                            Combo += 1;
                            isLPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 400 && balla.getLayoutY() >= 380) {
                        if (isLPressed) {
                            remove(balla);
                            Score += 450;
                            perfectAnimationadd(GoodAnimatedA, boarderGood, balla.getLayoutY(), 6);
                            Combo += 1;
                            isLPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 450 && balla.getLayoutY() >= 400) {
                        if (isLPressed) {
                            remove(balla);
                            Score += 250;
                            perfectAnimationadd(OKAnimatedA, boarderOK, balla.getLayoutY(), 6);
                            Combo += 1;
                            isLPressed = false;
                        }
                    }
                    if (balla.getLayoutY() <= 500 && balla.getLayoutY() >= 450) {
                        if (isLPressed) {
                            remove(balla);
                            Combo = 0;
                            isLPressed = false;
                        }
                    }
                    if (balla.getLayoutY() >= 510) {
                        Combo = 0;
                    }
                }
            }
        }

        public void printScore(Text text) {
            text.setText(String.valueOf(Score));
        }

        public void printCombo(Text text) {
            if (Combo >= 5) {
                text.setText(String.valueOf(Combo));
            } else {
                text.setText(" ");
            }
        }
    }


    class BoarderView extends ImageView {
        BoarderView(double x, double y) {
            super(MusicGame.class.getResource("MusicInterFace/Boarder.png").toString());
            setLayoutX(x);
            setLayoutY(y);
            setFitHeight(15);
            setFitWidth(100);

        }
    }

    class BoarderLightView extends ImageView {
        BoarderLightView(double x, double y) {
            super();
            setLayoutX(x);
            setLayoutY(y);
            setFitHeight(23);
            setFitWidth(100);

        }
    }

    class BoarderGoodView extends ImageView {
        BoarderGoodView(double x, double y) {
            super(MusicGame.class.getResource("MusicInterFace/BoardLight3.gif").toString());
            setLayoutX(x);
            setLayoutY(y);
            setFitHeight(23);
            setFitWidth(100);
        }
    }

    class BoarderOKView extends ImageView {
        BoarderOKView(double x, double y) {
            super(MusicGame.class.getResource("MusicInterFace/BoardOK.gif").toString());
            setLayoutX(x);
            setLayoutY(y);
            setFitHeight(23);
            setFitWidth(100);
        }
    }
}
