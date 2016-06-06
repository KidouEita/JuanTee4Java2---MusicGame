package application;

import javafx.animation.AnimationBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import javafx.scene.text.*;
import javafx.scene.media.*;
import java.io.File;
import java.util.ArrayList;


public class MusicGame extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
	BorderPane pane = new BorderPane();
    Text ScoreBoard =new Text();
    ScoreBoard.setLayoutX(900);
    ScoreBoard.setLayoutY(100);
    Text ComboBoard =new Text();
    ComboBoard.setLayoutX(900);
    ComboBoard.setLayoutY(150);
    pane.getChildren().add(ScoreBoard);
    pane.getChildren().add(ComboBoard);
	Scene scene = new Scene(pane, 1000, 550);
    MusicPaneA ballPaneA = new MusicPaneA(scene,ScoreBoard,ComboBoard);
    HBox hBox = new HBox(10);
    hBox.setAlignment(Pos.CENTER);

    // Add or remove a ball
    // Pause and resume animation
    ballPaneA.setOnMousePressed(e -> ballPaneA.pause());
    ballPaneA.setOnMouseReleased(e -> ballPaneA.play());
    // Use a scroll bar to control animation speed
    pane.setCenter(ballPaneA);
    pane.setBottom(hBox);
    // Create a scene and place the pane in the stage
    primaryStage.setTitle("MusicGame"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  private class MusicPaneA extends Pane {
    private boolean isSPressed, isDPressed, isFPressed,isSpacePressed,
                   isJPressed,isKPressed,isLPressed;
    private Timeline animation;
    private Timeline animationA;
    private Timeline DynamicScore;
    private Timeline DynamicCombo;
    Media Anima;
    MediaPlayer player;
    ArrayList<Node> ball= new ArrayList<Node>(); 
	Line line1 = new Line();
	private int Score = 0;
	private int Combo = 0;
	
	
    public MusicPaneA(Scene scene,Text text,Text text1) {
      // Create an animation for moving the ball
  	  String path = "C:/Users/Hank/workspace/Music/src/application/Deemo 2.0 - Xi - Anima.mp3";
  	  Anima = new Media(new File(path).toURI().toString());
  	  player = new MediaPlayer(Anima);
  	  player.play();
	  line1.setStroke(Color.YELLOWGREEN);
	  line1.setStrokeWidth(10);
	  line1.setStartX(0);
	  line1.setEndX(1000);
	  line1.setStartY(400);
	  line1.setEndY(400);
	  add(150,0);
	  getChildren().add(line1);
    }

    public void add(double x,int y) {
    	ball.add(new Ball(x, 30, 20, Color.BLACK));
    	getChildren().add(ball.get(y)); 
    }
    
    public void remove(int y){
    	getChildren().remove(ball.get(y));
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
    public void increaseSpeed() {
      animation.setRate(animation.getRate() + 0.1);
    }

    public void decreaseSpeed() {
      animation.setRate(
        animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }
    
    public void Pressed(Scene scene){
        scene.setOnKeyPressed(e->{
            if(e.getCode().equals(KeyCode.S)) {
                isSPressed = true;
            }
            else if(e.getCode().equals(KeyCode.D))
                isDPressed = true ;
            else if(e.getCode().equals(KeyCode.F))
                isFPressed = true;
            else if(e.getCode().equals(KeyCode.SPACE))
                isSpacePressed = true ;
            else if(e.getCode().equals(KeyCode.J))
                isJPressed = true;
            else if(e.getCode().equals(KeyCode.K))
                isKPressed = true ;
            else if(e.getCode().equals(KeyCode.L))
                isLPressed = true;
       });
    }
    public void Released(Scene scene){
        scene.setOnKeyReleased(e->{
            if(e.getCode().equals(KeyCode.S)) 
                isSPressed = false;
            else if(e.getCode().equals(KeyCode.D))
                isDPressed = false ;
            else if(e.getCode().equals(KeyCode.F))
                isFPressed = false;
            else if(e.getCode().equals(KeyCode.SPACE))
                isSpacePressed = false ;
            else if(e.getCode().equals(KeyCode.J))
                isJPressed = false;
            else if(e.getCode().equals(KeyCode.K))
                isKPressed = false ;
            else if(e.getCode().equals(KeyCode.L))
                isLPressed = false;
       });
    }
    
    public void printScore(Text text){
    	text.setText(String.valueOf(Score));
    }
    public void printCombo(Text text){
    	if(Combo >= 5){
    	text.setText(String.valueOf(Combo));
    	}
    	else{
    		text.setText(" ");
    	}
    }
  }


  class Ball extends Circle {
    private double dx = 1, dy = 1;

    Ball(double x, double y, double radius, Color color) {
      super(x, y, radius);
      setFill(color); // Set ball color
    }
  }
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
