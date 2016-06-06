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
    ScrollBar sbSpeed = new ScrollBar();
    sbSpeed.setMax(20);
    sbSpeed.setValue(10);
    ballPaneA.rateProperty().bind(sbSpeed.valueProperty());
    pane.setCenter(ballPaneA);
    pane.setTop(sbSpeed);
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
	Ball[] ball = new Ball[1000];
	Line line1 = new Line();
	private int Score = 0;
	private int Combo = 0;
	
	
    public MusicPaneA(Scene scene,Text text,Text text1) {
      // Create an animation for moving the ball
	  for(int i = 0 ; i<ball.length ; i++){
		  ball[i] = new Ball(i, 30, 20, Color.BLACK);
	  }
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
	  getChildren().add(line1);
        animation = new Timeline(
        new KeyFrame(Duration.millis(25), e -> moveBall(scene)));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation
        animationA = new Timeline(
    	        new KeyFrame(Duration.millis(500), e -> add(50,0)),
    	        new KeyFrame(Duration.millis(500), e -> add(150,1)),
    	        new KeyFrame(Duration.millis(500), e -> add(250,2)),
    	        new KeyFrame(Duration.millis(750), e -> add(150,3)),
    	        new KeyFrame(Duration.millis(1000), e -> add(50,7)),
    	        new KeyFrame(Duration.millis(1250), e -> add(150,8)),
    	        new KeyFrame(Duration.millis(1500), e -> add(50,14)),
    	        new KeyFrame(Duration.millis(1750), e -> add(150,15)),
    	        new KeyFrame(Duration.millis(2000), e -> add(50,21)),
    	        new KeyFrame(Duration.millis(2250), e -> add(150,22)),
    	        new KeyFrame(Duration.millis(2500), e -> add(50,28)),
    	        new KeyFrame(Duration.millis(2750), e -> add(150,29)),
    	        new KeyFrame(Duration.millis(3250), e -> add(250,9)));
      animationA.setCycleCount(Timeline.INDEFINITE);
      animationA.play();
      DynamicScore = new Timeline(
    		  new KeyFrame( Duration.millis(25), e -> printScore(text)));
      DynamicScore.setCycleCount(Timeline.INDEFINITE);
      DynamicScore.play();
      DynamicCombo = new Timeline(
    		  new KeyFrame( Duration.millis(25), e -> printCombo(text1)));
      DynamicCombo.setCycleCount(Timeline.INDEFINITE);
      DynamicCombo.play();
    }

    public void add(double x,int i) {
    	ball[i] = new Ball(x, 30, 20, Color.BLACK);
    	getChildren().add(ball[i]); 
    }
    public void remover(int i){
    	getChildren().remove(ball[i]);
    	ball[i] = new Ball(0, 0, 0, Color.BLACK);
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
    
    

    public DoubleProperty rateProperty() {
      return animation.rateProperty();
    }
	protected void moveBall(Scene scene) {
		Pressed(scene);
		Released(scene);
        // Check boundaries
        // Adjust ball position
		for(int i = 0 ; i <ball.length ; i = i+1){        
		      if (ball[i].getCenterY() < ball[i].getRadius() || ball[i].getCenterY() > getHeight() - ball[i].getRadius()) {
		    	  remover(i);
		      }
		      if(ball[i].getCenterX() == 50){
		    	  if(ball[i].getCenterY()<=350 && ball[i].getCenterY()>=250){
		    		if(isSPressed==true){
		    			remover(i);
		    			Combo = 0 ;
		    			isSPressed = false;
		    		}
		    	  }
		    	  if(ball[i].getCenterY()<=380 && ball[i].getCenterY()>=350){
		    		if(isSPressed==true){
		    			remover(i);
		    			Score += 250;
		    			Combo +=1;
		    			isSPressed = false;
		    		}
		    	  }
		    	  if(ball[i].getCenterY()<=400 && ball[i].getCenterY()>=380){
		    		if(isSPressed==true){
		    			remover(i);
		    			Score += 650;
		    			Combo +=1;
		    			isSPressed = false;
		    		}
		    	  }
		    	  if(ball[i].getCenterY()<=450 && ball[i].getCenterY()>=400){
		    		if(isSPressed==true){
		    			remover(i);
		    			Score += 450;
		    			Combo +=1;
		    			isSPressed = false;
		    		}
		    	  }
		    	  if(ball[i].getCenterY()<=500 && ball[i].getCenterY()>=450){
		    		if(isSPressed==true){
		    			remover(i);
		    			Combo = 0 ;
		    			isSPressed = false;
		    		}
		    	  }
			      if(ball[i].getCenterY()>=510){
			    	  Combo = 0;
			      }
		      }
		      if(ball[i].getCenterX() == 150 ){
		    	  if(ball[i].getCenterY()<=350 && ball[i].getCenterY()>=250){
		    		if(isDPressed==true){
		    			remover(i);
		    			Combo = 0 ;
		    			isDPressed = false;
		    		}
		    	  }
		    	  if(ball[i].getCenterY()<=380 && ball[i].getCenterY()>=350){
		    		if(isDPressed==true){
		    			remover(i);
		    			Score += 250;
		    			Combo +=1;
		    			isDPressed = false;
		    		}
		    	  }
		    	  if(ball[i].getCenterY()<=400 && ball[i].getCenterY()>=380){
		    		if(isDPressed==true){
		    			remover(i);
		    			Score += 650;
		    			Combo +=1;
		    			isDPressed = false;
		    		}
		    	  }
		    	  if(ball[i].getCenterY()<=450 && ball[i].getCenterY()>=400){
		    		if(isDPressed==true){
		    			remover(i);
		    			Score += 450;
		    			Combo +=1;
		    			isDPressed = false;
		    		}
		    	  }
		    	  if(ball[i].getCenterY()<=500 && ball[i].getCenterY()>=450){
		    		if(isDPressed==true){
		    			remover(i);
		    			Combo = 0 ;
		    			isDPressed = false;
		    		}
		    	  }
			      if(ball[i].getCenterY()>=510){
			    	  Combo = 0;
			      }
		      }
		      if(ball[i].getCenterX() == 250){
		    	  if(ball[i].getCenterY()<=350 && ball[i].getCenterY()>=250){
		    		if(isFPressed==true){
		    			remover(i);
		    			Combo = 0 ;
		    			isFPressed = false;
		    		}
		    	  }
		    	  if(ball[i].getCenterY()<=390 && ball[i].getCenterY()>=350){
		    		if(isFPressed==true){
		    			remover(i);
		    			Score += 250;
		    			Combo +=1;
		    			isFPressed = false;
		    		}
		    	  }
		    	  if(ball[i].getCenterY()<=400 && ball[i].getCenterY()>=380){
		    		if(isFPressed==true){
		    			remover(i);
		    			Score += 650;
		    			Combo +=1;
		    			isFPressed = false;
		    		}
		    	  }
		    	  if(ball[i].getCenterY()<=450 && ball[i].getCenterY()>=400){
		    		if(isFPressed==true){
		    			remover(i);
		    			Score += 450;
		    			Combo +=1;
		    			isFPressed = false;
		    		}
		    	  }
		    	  if(ball[i].getCenterY()<=500 && ball[i].getCenterY()>=450){
		    		if(isFPressed==true){
		    			remover(i);
		    			Combo = 0 ;
		    			isFPressed = false;
		    		}
		    	  }
			      if(ball[i].getCenterY()>=510){
			    	  Combo = 0;
			      }
		      }

			ball[i].setCenterY(ball[i].dy + ball[i].getCenterY());
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
