
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
import javafx.scene.input.KeyCodeCombination;

public class MusicGame extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
	BorderPane pane = new BorderPane();
	Scene scene = new Scene(pane, 1000, 500);
    MusicPaneA ballPaneA = new MusicPaneA(scene);

    Button btAdd = new Button("+");
    Button btSubtract = new Button("-");
    HBox hBox = new HBox(10);
    hBox.getChildren().addAll(btAdd, btSubtract);
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
    private Timeline animation;
    private Timeline animationA;
	Ball[] ball = new Ball[1000];
	Line line1 = new Line();
	
	
    public MusicPaneA(Scene scene) {
      // Create an animation for moving the ball
	  for(int i = 0 ; i<ball.length ; i++){
		  ball[i] = new Ball(i, 30, 20, Color.BLACK);
	  }
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
    	        new KeyFrame(Duration.millis(750), e -> add(150,1)),
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
    }

    public void add(double x,int i) {
    	ball[i] = new Ball(x, 30, 20, Color.BLACK);
      getChildren().add(ball[i]); 
    }
    public void remover(Ball ball){
    	getChildren().remove(ball);
    }


    public void play() {
      animation.play();
    }

    public void pause() {
      animation.pause();
    }
    public void increaseSpeed() {
      animation.setRate(animation.getRate() + 0.1);
    }

    public void decreaseSpeed() {
      animation.setRate(
        animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }

    public DoubleProperty rateProperty() {
      return animation.rateProperty();
    }
	protected void moveBall(Scene scene) {
        // Check boundaries
        // Adjust ball position
		for(int i = 0 ; i <ball.length ; i = i+1){        
		      if (ball[i].getCenterY() < ball[i].getRadius() || 
		                ball[i].getCenterY() > getHeight() - ball[i].getRadius()) {
		            	remover(ball[i]);
		              // Change ball move direction
		            }
		      final Integer inneri = new Integer(i);
		      if(ball[i].getCenterX() == 50 && ball[i].getCenterY()<=400 && ball[i].getCenterY()>=350){
		    	  	scene.setOnKeyPressed(e->{
		    			if(e.getCode().equals(KeyCode.S)){
		    				remover(ball[inneri]);
		    			}
		    		  });
		      }
		      if(ball[i].getCenterX() == 150 && ball[i].getCenterY()<=400 && ball[i].getCenterY() >=350){
		    		scene.setOnKeyPressed(e->{
		    			if(e.getCode().equals(KeyCode.D)){
		    				remover(ball[inneri]);
		    			}
		    		  });
		      }
		      if(ball[i].getCenterX() == 250 && ball[i].getCenterY()<=400 && ball[i].getCenterY() >=350){
		    		scene.setOnKeyPressed(e->{
		    			if(e.getCode().equals(KeyCode.F)){
		    				remover(ball[inneri]);
		    			}
		    		  });
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
