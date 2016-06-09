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
import java.net.URL;

public class MusicGame extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
	BorderPane pane = new BorderPane();
    Text ScoreBoard =new Text();
    ScoreBoard.setLayoutX(1100);
    ScoreBoard.setLayoutY(30);
    Text ComboBoard =new Text();
    ComboBoard.setLayoutX(900);
    ComboBoard.setLayoutY(150);
    pane.setStyle("-fx-background-image: url('/application/Pane.png')");
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
    private Timeline MusicDuration;
    private Timeline animationA;
    private Timeline DynamicScore;
    private Timeline DynamicCombo;
    Media Anima;
    MediaPlayer player;
    LinkedList<Node> board= new LinkedList<Node>(); 
    LinkedList<Node> boarderlight = new LinkedList<Node>();
    LinkedList<Node> boarderGood = new LinkedList<Node>();
    LinkedList<Node> boarderOK = new LinkedList<Node>();
    LinkedList<Timeline> perfectAnimatedA = new LinkedList<Timeline>();
    LinkedList<Timeline> GoodAnimatedA = new LinkedList<Timeline>();
    LinkedList<Timeline> OKAnimatedA = new LinkedList<Timeline>();
	private int Score = 0;
	private int Combo = 0;
	
	
    public MusicPaneA(Scene scene,Text text,Text text1) {
      // Create an animation for moving the ball
  	  URL resource = getClass().getResource("/application/Deemo 2.0 - Xi - Anima.mp3");
  	  Anima = new Media(resource.toString());
  	  player = new MediaPlayer(Anima);
	  MusicDuration = new Timeline(
			  new KeyFrame(Duration.seconds(2), e->player.play()));
	  MusicDuration.play();
      animation = new Timeline(
      new KeyFrame(Duration.millis(25), e -> moveBall(scene)));
      animation.setCycleCount(Timeline.INDEFINITE);
      animation.setRate(5);
      animation.play();
      animationA = new Timeline(
    	        new KeyFrame(Duration.seconds(1.5), e -> add(100,board.size())),
    	        new KeyFrame(Duration.seconds(1.5), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(2.8), e -> add(250,board.size())),
    	        new KeyFrame(Duration.seconds(2.8), e -> add(850,board.size())),
    	        new KeyFrame(Duration.seconds(4), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(4), e -> add(1000,board.size())),
    	        new KeyFrame(Duration.seconds(5.57), e -> add(100,board.size())),
    	        new KeyFrame(Duration.seconds(5.92), e -> add(250,board.size())),
    	        new KeyFrame(Duration.seconds(6.18), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(6.55), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(6.55), e -> add(1000,board.size())),
    	        new KeyFrame(Duration.seconds(7.86), e -> add(100,board.size())),
    	        new KeyFrame(Duration.seconds(7.86), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(8.55), e -> add(250,board.size())),
    	        new KeyFrame(Duration.seconds(8.55), e -> add(850,board.size())),
    	        new KeyFrame(Duration.seconds(9.25), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(9.25), e -> add(1000,board.size())),
    	        new KeyFrame(Duration.seconds(10.9), e -> add(100,board.size())),
    	        new KeyFrame(Duration.seconds(11.25), e -> add(250,board.size())),
    	        new KeyFrame(Duration.seconds(11.51), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(11.8), e -> add(550,board.size())),
    	        new KeyFrame(Duration.seconds(12.8), e -> add(250,board.size())),
    	        new KeyFrame(Duration.seconds(12.9), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(13.0), e -> add(550,board.size())),
    	        new KeyFrame(Duration.seconds(13.2), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(14.6), e -> add(100,board.size())),
    	        new KeyFrame(Duration.seconds(14.6), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(15.2), e -> add(250,board.size())),
    	        new KeyFrame(Duration.seconds(15.2), e -> add(850,board.size())),
    	        new KeyFrame(Duration.seconds(15.85), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(15.85), e -> add(1000,board.size())),
    	        new KeyFrame(Duration.seconds(16.5), e -> add(250,board.size())),
    	        new KeyFrame(Duration.seconds(16.5), e -> add(850,board.size())),
    	        new KeyFrame(Duration.seconds(17.07), e -> add(100,board.size())),
    	        new KeyFrame(Duration.seconds(17.07), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(18.9), e -> add(100,board.size())),
    	        new KeyFrame(Duration.seconds(19.2), e -> add(250,board.size())),
    	        new KeyFrame(Duration.seconds(19.2), e -> add(850,board.size())),
    	        new KeyFrame(Duration.seconds(19.4), e -> add(550,board.size())),
    	        new KeyFrame(Duration.seconds(19.5), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(19.6), e -> add(850,board.size())),
    	        new KeyFrame(Duration.seconds(19.7), e -> add(1000,board.size())),
    	        new KeyFrame(Duration.seconds(19.9), e -> add(1000,board.size())),
    	        new KeyFrame(Duration.seconds(20.5), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(21.0), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(19.9), e -> add(1000,board.size())),
    	        new KeyFrame(Duration.seconds(20.05), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(20.15), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(20.25), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(20.35), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(20.45), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(20.55), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(20.65), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(20.75), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(20.85), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(20.95), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(21.05), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(22.50), e -> add(550,board.size())),
    	        new KeyFrame(Duration.seconds(23.00), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(23.40), e -> add(250,board.size())),
    	        new KeyFrame(Duration.seconds(23.40), e -> add(850,board.size())),
    	        new KeyFrame(Duration.seconds(23.75), e -> add(550,board.size())),
    	        new KeyFrame(Duration.seconds(23.90), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(24.30), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(24.50), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(24.50), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(24.70), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(24.70), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(24.80), e -> add(550,board.size())),
    	        new KeyFrame(Duration.seconds(24.90), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(25.1), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(25.2), e -> add(400,board.size())),
    	        new KeyFrame(Duration.seconds(25.3), e -> add(700,board.size())),
    	        new KeyFrame(Duration.seconds(25.7), e -> add(250,board.size())),
    	        new KeyFrame(Duration.seconds(25.7), e -> add(850,board.size())),
    	        new KeyFrame(Duration.seconds(25.9), e -> add(100,board.size())),
    	        new KeyFrame(Duration.seconds(25.9), e -> add(1000,board.size())),
    	        new KeyFrame(Duration.seconds(26.4), e -> add(500,board.size())),
    	        new KeyFrame(Duration.seconds(26.8), e -> add(700,board.size())));
  	  animationA.play();
      DynamicScore = new Timeline(
    		  new KeyFrame( Duration.millis(25), e -> printScore(text)));
      DynamicScore.setCycleCount(Timeline.INDEFINITE);
      DynamicScore.play();
      DynamicCombo = new Timeline(
    		  new KeyFrame( Duration.millis(25), e -> printCombo(text1)));
      DynamicCombo.setCycleCount(Timeline.INDEFINITE);
      DynamicCombo.play();
      for(int i = 0 ;i<8;i++){
    	  boarderlight.add(new BoarderLightView(0,387));
    	  boarderGood.add(new BoarderGoodView(0,387));
    	  boarderOK.add(new BoarderOKView(0,387));
    	  final int inneri = i ;
          perfectAnimatedA.add(new Timeline(
        	  	new KeyFrame(Duration.millis(1), e -> perfectadd(boarderlight,inneri*150+100,inneri)),
        	  	new KeyFrame(Duration.millis(302), e -> perfectremove(boarderlight,inneri))));
          GoodAnimatedA.add(new Timeline(
          	  	new KeyFrame(Duration.millis(1), e -> perfectadd(boarderGood,inneri*150+100,inneri)),
          	  	new KeyFrame(Duration.millis(302), e -> perfectremove(boarderGood,inneri))));
          OKAnimatedA.add(new Timeline(
            	  	new KeyFrame(Duration.millis(1), e -> perfectadd(boarderOK,inneri*150+100,inneri)),
            	  	new KeyFrame(Duration.millis(302), e -> perfectremove(boarderOK,inneri))));
      }
    }

    public void perfectAnimationadd(LinkedList<Timeline> perfectAnimatedA,LinkedList<Node> boarderlight,double x,int y){
    	boarderlight.get(y).setLayoutY(x);
    	perfectAnimatedA.get(y).play();
    }
    public void perfectadd(LinkedList<Node> boarderlight,double x,int l){
    	boarderlight.get(l).setLayoutX(x);
    	getChildren().add(boarderlight.get(l));
    }
    public void perfectremove(LinkedList<Node> boarderlight,int y){
    	getChildren().remove(boarderlight.get(y));
    }
    public void add(double x,int y) {
    	board.add(y,new BoarderView(0,0));
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
      MusicDuration.play();
    }

    public void pause() {
      animation.pause();
      animationA.pause();
      MusicDuration.pause();
      
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
	      if(balla.getLayoutX() == 100){
	    	  if(balla.getLayoutY()<=300 && balla.getLayoutY()>=250){
	    		if(isSPressed==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isSPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=350 && balla.getLayoutY()>=300){
	    		if(isSPressed==true){
	    			remove(balla);;
	    			Score += 250;
	    			Combo +=1;
	    			perfectAnimationadd(OKAnimatedA,boarderOK,balla.getLayoutY(),0);
	    			isSPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=370 && balla.getLayoutY()>=350){
	    		if(isSPressed==true){
	    			remove(balla);;
	    			Score += 450;
	    			Combo +=1;
	    			perfectAnimationadd(GoodAnimatedA,boarderGood,balla.getLayoutY(),0);
	    			isSPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=400 && balla.getLayoutY()>=370){
	    		if(isSPressed==true){
	    			remove(balla);
	    			Score += 650;
	    			perfectAnimationadd(perfectAnimatedA,boarderlight,balla.getLayoutY(),0);
	    			Combo +=1;	
	    			isSPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=420 && balla.getLayoutY()>=400){
	    		if(isSPressed==true){
	    			remove(balla);
	    			Score += 450;
	    			Combo +=1;
	    			perfectAnimationadd(GoodAnimatedA,boarderGood,balla.getLayoutY(),0);
	    			isSPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=450 && balla.getLayoutY()>=420){
	    		if(isSPressed==true){
	    			remove(balla);
	    			Score += 250;
	    			perfectAnimationadd(OKAnimatedA,boarderOK,balla.getLayoutY(),0);
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
	      if(balla.getLayoutX() == 250 ){
	    	  if(balla.getLayoutY() <=300 && balla.getLayoutY()>=250){
	    		if(isDPressed==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isDPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=350 && balla.getLayoutY()>=300){
	    		if(isDPressed==true){
	    			remove(balla);;
	    			Score += 250;
	    			perfectAnimationadd(OKAnimatedA,boarderOK,balla.getLayoutY(),1);
	    			Combo +=1;
	    			isDPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=370 && balla.getLayoutY()>=350){
	    		if(isDPressed==true){
	    			remove(balla);;
	    			Score += 450;
	    			perfectAnimationadd(GoodAnimatedA,boarderGood,balla.getLayoutY(),1);
	    			Combo +=1;
	    			isDPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=400 && balla.getLayoutY()>=370){
	    		if(isDPressed==true){
	    			remove(balla);
	    			Score += 650;
	    			perfectAnimationadd(perfectAnimatedA,boarderlight,balla.getLayoutY(),1);
	    			Combo +=1;	
	    			isDPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=420 && balla.getLayoutY()>=400){
	    		if(isDPressed==true){
	    			remove(balla);
	    			Score += 450;
	    			perfectAnimationadd(GoodAnimatedA,boarderGood,balla.getLayoutY(),1);
	    			Combo +=1;
	    			isDPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=450 && balla.getLayoutY()>=420){
	    		if(isDPressed==true){
	    			remove(balla);
	    			Score += 250;
	    			perfectAnimationadd(OKAnimatedA,boarderOK,balla.getLayoutY(),1);
	    			Combo +=1;
	    			isDPressed = false;
	    		}
	    	  }
		      if(balla.getLayoutY() >=510){
		    	  Combo = 0;
		      }
	      }
	      if(balla.getLayoutX() == 400){
	    	  if(balla.getLayoutY() <=300 && balla.getLayoutY() >=250){
	    		if(isFPressed==true){
	    			remove(balla);;
	    			Combo = 0 ;
	    			isFPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=350 && balla.getLayoutY()>=300){
		    		if(isFPressed==true){
		    			remove(balla);;
		    			Score += 250;
		    			perfectAnimationadd(OKAnimatedA,boarderOK,balla.getLayoutY(),2);
		    			Combo +=1;
		    			isFPressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=370 && balla.getLayoutY()>=350){
		    		if(isFPressed==true){
		    			remove(balla);;
		    			Score += 450;
		    			perfectAnimationadd(GoodAnimatedA,boarderGood,balla.getLayoutY(),2);
		    			Combo +=1;
		    			isFPressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=400 && balla.getLayoutY()>=370){
		    		if(isFPressed==true){
		    			remove(balla);
		    			Score += 650;
		    			perfectAnimationadd(perfectAnimatedA,boarderlight,balla.getLayoutY(),2);
		    			Combo +=1;	
		    			isFPressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=420 && balla.getLayoutY()>=400){
		    		if(isFPressed==true){
		    			remove(balla);
		    			Score += 450;
		    			perfectAnimationadd(GoodAnimatedA,boarderGood,balla.getLayoutY(),2);
		    			Combo +=1;
		    			isFPressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=450 && balla.getLayoutY()>=420){
		    		if(isFPressed==true){
		    			remove(balla);
		    			Score += 250;
		    			perfectAnimationadd(OKAnimatedA,boarderOK,balla.getLayoutY(),2);
		    			Combo +=1;
		    			isFPressed= false;
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
	      if(balla.getLayoutX() == 550){
	    	  if(balla.getLayoutY() <=300 && balla.getLayoutY() >=250){
	    		if(isSpacePressed==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isSpacePressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=350 && balla.getLayoutY()>=300){
		    		if(isSpacePressed==true){
		    			remove(balla);;
		    			Score += 250;
		    			perfectAnimationadd(OKAnimatedA,boarderOK,balla.getLayoutY(),3);
		    			Combo +=1;
		    			isSpacePressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=370 && balla.getLayoutY()>=350){
		    		if(isSpacePressed==true){
		    			remove(balla);;
		    			Score += 450;
		    			perfectAnimationadd(GoodAnimatedA,boarderGood,balla.getLayoutY(),3);
		    			Combo +=1;
		    			isSpacePressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=400 && balla.getLayoutY()>=370){
		    		if(isSpacePressed==true){
		    			remove(balla);
		    			Score += 650;
		    			perfectAnimationadd(perfectAnimatedA,boarderlight,balla.getLayoutY(),3);
		    			Combo +=1;	
		    			isSpacePressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=420 && balla.getLayoutY()>=400){
		    		if(isSpacePressed==true){
		    			remove(balla);
		    			Score += 450;
		    			perfectAnimationadd(GoodAnimatedA,boarderGood,balla.getLayoutY(),3);
		    			Combo +=1;
		    			isSpacePressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=450 && balla.getLayoutY()>=420){
		    		if(isSpacePressed==true){
		    			remove(balla);
		    			Score += 250;
		    			perfectAnimationadd(OKAnimatedA,boarderOK,balla.getLayoutY(),3);
		    			Combo +=1;
		    			isSpacePressed= false;
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
	      if(balla.getLayoutX() == 700){
	    	  if(balla.getLayoutY() <=300 && balla.getLayoutY() >=250){
	    		if(isJPressed==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isJPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=350 && balla.getLayoutY()>=300){
		    		if(isJPressed==true){
		    			remove(balla);;
		    			Score += 250;
		    			perfectAnimationadd(OKAnimatedA,boarderOK,balla.getLayoutY(),4);
		    			Combo +=1;
		    			isJPressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=370 && balla.getLayoutY()>=350){
		    		if(isJPressed==true){
		    			remove(balla);;
		    			Score += 450;
		    			perfectAnimationadd(GoodAnimatedA,boarderGood,balla.getLayoutY(),4);
		    			Combo +=1;
		    			isJPressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=400 && balla.getLayoutY()>=370){
		    		if(isJPressed==true){
		    			remove(balla);
		    			Score += 650;
		    			perfectAnimationadd(perfectAnimatedA,boarderlight,balla.getLayoutY(),4);
		    			Combo +=1;	
		    			isJPressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=420 && balla.getLayoutY()>=400){
		    		if(isJPressed==true){
		    			remove(balla);
		    			Score += 450;
		    			perfectAnimationadd(GoodAnimatedA,boarderGood,balla.getLayoutY(),4);
		    			Combo +=1;
		    			isJPressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=450 && balla.getLayoutY()>=420){
		    		if(isJPressed==true){
		    			remove(balla);
		    			Score += 250;
		    			perfectAnimationadd(OKAnimatedA,boarderOK,balla.getLayoutY(),4);
		    			Combo +=1;
		    			isJPressed = false;
		    		}
		    	  }
	    	  if(balla.getLayoutY() <=500 && balla.getLayoutY() >=450){
	    		if(isJPressed ==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isJPressed= false;
	    		}
	    	  }
		      if(balla.getLayoutY()>=510){
		    	  Combo = 0;
		      }
	      }
	      if(balla.getLayoutX() == 850){
	    	  if(balla.getLayoutY() <=300 && balla.getLayoutY() >=250){
	    		if(isKPressed==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isKPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=350 && balla.getLayoutY()>=300){
		    		if(isKPressed==true){
		    			remove(balla);;
		    			Score += 250;
		    			perfectAnimationadd(OKAnimatedA,boarderOK,balla.getLayoutY(),5);
		    			Combo +=1;
		    			isKPressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=370 && balla.getLayoutY()>=350){
		    		if(isKPressed ==true){
		    			remove(balla);;
		    			Score += 450;
		    			perfectAnimationadd(GoodAnimatedA,boarderGood,balla.getLayoutY(),5);
		    			Combo +=1;
		    			isKPressed  = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=400 && balla.getLayoutY()>=370){
		    		if(isKPressed ==true){
		    			remove(balla);
		    			Score += 650;
		    			perfectAnimationadd(perfectAnimatedA,boarderlight,balla.getLayoutY(),5);
		    			Combo +=1;	
		    			isKPressed  = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=420 && balla.getLayoutY()>=400){
		    		if(isKPressed ==true){
		    			remove(balla);
		    			Score += 450;
		    			perfectAnimationadd(GoodAnimatedA,boarderGood,balla.getLayoutY(),5);
		    			Combo +=1;
		    			isKPressed  = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=450 && balla.getLayoutY()>=420){
		    		if(isKPressed ==true){
		    			remove(balla);
		    			Score += 250;
		    			perfectAnimationadd(OKAnimatedA,boarderOK,balla.getLayoutY(),5);
		    			Combo +=1;
		    			isKPressed = false;
		    		}
		    	  }
	    	  if(balla.getLayoutY() <=500 && balla.getLayoutY() >=450){
	    		if(isKPressed ==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isKPressed  = false;
	    		}
	    	  }
		      if(balla.getLayoutY()>=510){
		    	  Combo = 0;
		      }
	      }
	      if(balla.getLayoutX() == 1000){
	    	  if(balla.getLayoutY() <=300 && balla.getLayoutY() >=250){
	    		if(isLPressed ==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isLPressed = false;
	    		}
	    	  }
	    	  if(balla.getLayoutY()<=350 && balla.getLayoutY()>=300){
		    		if(isLPressed==true){
		    			remove(balla);;
		    			Score += 250;
		    			perfectAnimationadd(OKAnimatedA,boarderOK,balla.getLayoutY(),6);
		    			Combo +=1;
		    			isLPressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=370 && balla.getLayoutY()>=350){
		    		if(isLPressed==true){
		    			remove(balla);;
		    			Score += 450;
		    			perfectAnimationadd(GoodAnimatedA,boarderGood,balla.getLayoutY(),6);
		    			Combo +=1;
		    			isLPressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=400 && balla.getLayoutY()>=370){
		    		if(isLPressed==true){
		    			remove(balla);
		    			Score += 650;
		    			perfectAnimationadd(perfectAnimatedA,boarderlight,balla.getLayoutY(),6);
		    			Combo +=1;	
		    			isLPressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=420 && balla.getLayoutY()>=400){
		    		if(isLPressed==true){
		    			remove(balla);
		    			Score += 450;
		    			perfectAnimationadd(GoodAnimatedA,boarderGood,balla.getLayoutY(),6);
		    			Combo +=1;
		    			isLPressed = false;
		    		}
		    	  }
		    	  if(balla.getLayoutY()<=450 && balla.getLayoutY()>=420){
		    		if(isLPressed==true){
		    			remove(balla);
		    			Score += 250;
		    			perfectAnimationadd(OKAnimatedA,boarderOK,balla.getLayoutY(),6);
		    			Combo +=1;
		    			isLPressed= false;
		    		}
		    	  }
	    	  if(balla.getLayoutY() <=500 && balla.getLayoutY() >=450){
	    		if(isLPressed==true){
	    			remove(balla);
	    			Combo = 0 ;
	    			isLPressed = false;
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
		  setFitWidth(100);
		  	
	  }
  }
  class BoarderLightView extends ImageView{
	  BoarderLightView(double x,double y){
		  super("/application/BoardLight3.gif");
		  setLayoutX(x);
		  setLayoutY(y);
		  setFitHeight(23);
		  setFitWidth(100);
		  	
	  }
  }
  class BoarderGoodView extends ImageView{
		  BoarderGoodView(double x,double y){
			  super("/application/BoardGood.gif");
			  setLayoutX(x);
			  setLayoutY(y);
			  setFitHeight(23);
			  setFitWidth(100);
			  	
	  }
  }
  class BoarderOKView extends ImageView{
	  BoarderOKView(double x,double y){
		  super("/application/BoardOK.gif");
		  setLayoutX(x);
		  setLayoutY(y);
		  setFitHeight(23);
		  setFitWidth(100);
		  	
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
