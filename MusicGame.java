package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import javafx.scene.text.*;
import javafx.scene.media.*;
import java.io.File;
import java.util.LinkedList;


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
	Scene scene = new Scene(pane, 1200, 550);
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
    private Timeline perfectAnimation;
    private Timeline DynamicScore;
    private Timeline DynamicCombo;
    Media Anima;
    MediaPlayer player;
    LinkedList<Node> board= new LinkedList<Node>(); 
	Line line1 = new Line();
	private int Score = 0;
	private int Combo = 0;;
	private BoarderLightView boarderlight;
	
	
    public MusicPaneA(Scene scene,Text text,Text text1) {
      // Create an animation for moving the ball
  	  String path = "C:/Users/Hank/workspace/MusicGame/src/application/Deemo 2.0 - Xi - Anima.mp3";
  	  Anima = new Media(new File(path).toURI().toString());
  	  player = new MediaPlayer(Anima);
  	  player.play();
	  line1.setStroke(Color.YELLOWGREEN);
	  line1.setStrokeWidth(10);
	  line1.setStartX(0);
	  line1.setEndX(1200);
	  line1.setStartY(400);
	  line1.setEndY(400);
	  getChildren().add(line1);
      animation = new Timeline(
      new KeyFrame(Duration.millis(25), e -> moveBall(scene)));
      animation.setCycleCount(Timeline.INDEFINITE);
      animation.setRate(5);
      animation.play(); 
      animationA = new Timeline(
    	        new KeyFrame(Duration.millis(450), e -> add(150,0)),
    	        new KeyFrame(Duration.millis(450), e -> add(300,1)),
    	        new KeyFrame(Duration.millis(450), e -> add(450,2)),
    	        new KeyFrame(Duration.millis(650), e -> add(150,3)),
    	        new KeyFrame(Duration.millis(850), e -> add(600,4)),
    	        new KeyFrame(Duration.millis(1000), e -> add(450,5)),
    	        new KeyFrame(Duration.millis(1150), e -> add(600,6)));
  	  animationA.play();
  	  perfectAnimation = new Timeline(
  			new KeyFrame(Duration.millis(1), e -> perfect(150)),
  			new KeyFrame(Duration.millis(1000), e -> perfectremove()));
      DynamicScore = new Timeline(
    		  new KeyFrame( Duration.millis(25), e -> printScore(text)));
      DynamicScore.setCycleCount(Timeline.INDEFINITE);
      DynamicScore.play();
      DynamicCombo = new Timeline(
    		  new KeyFrame( Duration.millis(25), e -> printCombo(text1)));
      DynamicCombo.setCycleCount(Timeline.INDEFINITE);
      DynamicCombo.play();
    }
    public void perfect(double x){
    	boarderlight = new BoarderLightView(x,395);
    	getChildren().add(boarderlight);
    }
    public void perfectremove(){
    	getChildren().remove(boarderlight);
    }
    public void add(double x,int y) {
    	board.add(new BoarderView(x,0));
    	board.get(y).setLayoutX(x);
    	getChildren().add(board.get(y));
    }
    public void remove(Node balla){
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
    protected void moveBall(Scene scene) {
    	Pressed(scene);
    	Released(scene);
    	for(Node balla : board){	
		balla.setLayoutY(1 + balla.getLayoutY());
    	if(balla.getLayoutY() >= getHeight()){
    		Combo = 0;
    		remove(balla);
    	} 
	      if(balla.getLayoutX() == 150){
	    	  if(balla.getLayoutY()<=350 && balla.getLayoutY()>=250){
	    		if(isSPressed==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isSPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=380 && balla.getLayoutY()>=350){
	    		if(isSPressed==true){
	    			remove(balla);;
	    			Score += 250;
	    			Combo +=1;
	    			isSPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=400 && balla.getLayoutY()>=380){
	    		if(isSPressed==true){
	    			remove(balla);
	    			Score += 650;
	    			Combo +=1;	
	    			isSPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=450 && balla.getLayoutY()>=400){
	    		if(isSPressed==true){
	    			remove(balla);
	    			Score += 450;
	    			Combo +=1;
	    			isSPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY() <=500 && balla.getLayoutY()>=450){
	    		if(isSPressed==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isSPressed = false;
	    		}
	    	  }
		      if(balla.getLayoutY() >=510){
		    	  Combo = 0;
		      }
	      }
	      if(balla.getLayoutX() == 300 ){
	    	  if(balla.getLayoutY() <=350 && balla.getLayoutY()>=250){
	    		if(isDPressed==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isDPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY() <=380 && balla.getLayoutY() >=350){
	    		if(isDPressed==true){
	    			remove(balla);
	    			Score += 250;
	    			Combo +=1;
	    			isDPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY() <=400 && balla.getLayoutY() >=380){
	    		if(isDPressed==true){
	    			remove(balla);
	    			Score += 650;
	    			Combo +=1;
	    			isDPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=450 && balla.getLayoutY() >=400){
	    		if(isDPressed==true){
	    			remove(balla);
	    			Score += 450;
	    			Combo +=1;
	    			isDPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY() <=500 && balla.getLayoutY() >=450){
	    		if(isDPressed==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isDPressed = false;
	    		}
	    	  }
		      if(balla.getLayoutY() >=510){
		    	  Combo = 0;
		      }
	      }
	      if(balla.getLayoutX() == 450){
	    	  if(balla.getLayoutY() <=350 && balla.getLayoutY() >=250){
	    		if(isFPressed==true){
	    			remove(balla);;
	    			Combo = 0 ;
	    			isFPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY() <=380 && balla.getLayoutY() >=350){
	    		if(isFPressed==true){
	    			remove(balla);
	    			Score += 250;
	    			Combo +=1;
	    			isFPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY() <=400 && balla.getLayoutY() >=380){
	    		if(isFPressed==true){
	    			remove(balla);
	    			Score += 650;
	    			Combo +=1;
	    			perfectAnimation.play();
	    			isFPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY() <=450 && balla.getLayoutY() >=400){
	    		if(isFPressed==true){
	    			remove(balla);
	    			Score += 450;
	    			Combo +=1;
	    			isFPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY() <=500 && balla.getLayoutY() >=450){
	    		if(isFPressed==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isFPressed = false;
	    		}
	    	  }
		      if(balla.getLayoutY()>=510){
		    	  Combo = 0;
		      }
	      }
	      if(balla.getLayoutX() == 600){
	    	  if(balla.getLayoutY() <=350 && balla.getLayoutY() >=250){
	    		if(isSpacePressed==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isSpacePressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY() <=380 && balla.getLayoutY() >=350){
	    		if(isJPressed==true){
	    			remove(balla);
	    			Score += 250;
	    			Combo +=1;
	    			isSpacePressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY() <=400 && balla.getLayoutY() >=380){
	    		if(isSpacePressed==true){
	    			remove(balla);
	    			Score += 650;
	    			Combo +=1;
	    			isSpacePressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY() <=450 && balla.getLayoutY() >=400){
	    		if(isSpacePressed==true){
	    			remove(balla);
	    			Score += 450;
	    			Combo +=1;
	    			isSpacePressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY() <=500 && balla.getLayoutY() >=450){
	    		if(isSpacePressed==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isSpacePressed = false;
	    		}
	    	  }
		      if(balla.getLayoutY()>=510){
		    	  Combo = 0;
		      }
	      }
    	}
    	
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


  class BoarderView extends ImageView{
	  BoarderView(double x,double y){
		  super("/application/Boarder.png");
		  setLayoutX(x);
		  setLayoutY(y);
		  setFitHeight(15);
		  setFitWidth(80);
		  	
	  }
  }
  class BoarderLightView extends ImageView{
	  BoarderLightView(double x,double y){
		  super("/application/oie_755244M8Ae18ry.gif");
		  setLayoutX(x);
		  setLayoutY(y);
		  setFitHeight(23);
		  setFitWidth(85);
		  	
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
