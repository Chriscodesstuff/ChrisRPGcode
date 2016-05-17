/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chrisgame;

import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 *
 * @author Chris
 */


public class ChrisGame extends Application {
    
    

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Chris's totally radical game");
        final Group root = new Group();
        Scene scene1 = new Scene(root);
        theStage.setScene(scene1);
        theStage.setWidth(2000);
        theStage.setHeight(1000);
        
        
        new AnimationTimer(){
            //add colors
            final Color grey = Color.GREY;
            
            //reset animation
            Rectangle background = new Rectangle(0,0,2000,1000);
            
            //characters
            ArrayList<Characters> character = new ArrayList ();
            
            boolean doOnce = true;
            int selectedCharacter = 0;
            int animationTimer = 0;
            int step = 1;
            double[] mousePos = {0,0};
            boolean mouseIsPressed = false;
            boolean hasClickedThisTurn = false;
            Tools tool = new Tools();
            
            //messages
            Text text = new Text(900,50, "Select an Ally");
            
            public void handle (long currentTime){
                //reset canvas
                root.getChildren().clear();
                background.setFill(grey);
                root.getChildren().add(background);
                
                //do once
                if(doOnce){
                    character.add(new Characters(0,0,0,1));
                    character.add(new Characters(0,0,0,2));
                    character.add(new Characters(0,0,0,3));
                    character.add(new Characters(0,0,0,4));
                    character.add(new Characters(0,0,0,5));
                    character.add(new Characters(0,0,0,6));
                    
                    doOnce = false;
                }
                root.setOnTouchPressed(new EventHandler<TouchEvent>(){
                    public void handle(TouchEvent me){
                        mousePos[0]=me.getTouchPoint().getSceneX();
                        mousePos[1]=me.getTouchPoint().getSceneY();
                        mouseIsPressed = true;
                    }
                });            
                root.setOnMousePressed(new EventHandler<MouseEvent>(){
                    public void handle(MouseEvent me){
                        mousePos[0]=me.getX();
                        mousePos[1]=me.getY();
                        mouseIsPressed = true;
                    }
                });
                
                
                
                switch(step){
                    case 1: //choosing ally to 
                        if(mouseIsPressed){
                            hasClickedThisTurn = true;
                        }else if(hasClickedThisTurn){
                            if(tool.isWithin(mousePos[0],mousePos[1],0,0,1000,1000/3)){
                                selectedCharacter = 0;
                            }else if(tool.isWithin(mousePos[0],mousePos[1],0,1000/3,1000,1000/3)){
                                selectedCharacter = 1;
                            }else if(tool.isWithin(mousePos[0],mousePos[1],0,2000/3,1000,1000/3)){
                                selectedCharacter = 2;
                            }else if(tool.isWithin(mousePos[0],mousePos[1],1001,0,1000,1000/3)){
                                selectedCharacter = 3;
                            }else if(tool.isWithin(mousePos[0],mousePos[1],1001,1000/3,1000,1000/3)){
                                selectedCharacter = 4;
                            }else if(tool.isWithin(mousePos[0],mousePos[1],1001,2000/3,1000,1000/3)){
                                selectedCharacter = 5;
                            }
                            hasClickedThisTurn = false;
                            step = 2;
                        }
                        text.setFont(new Font(20));
                        root.getChildren().add(text);
                        break;
                    default:
                        if(mouseIsPressed){
                            character.get(selectedCharacter).setDest(mousePos[0],mousePos[1]);
                        }
                        break;
                }
                
                
                //last stuff
                
                root.getChildren().add(character.get(1).update(animationTimer));
                root.getChildren().add(character.get(2).update(animationTimer));
                root.getChildren().add(character.get(3).update(animationTimer));
                root.getChildren().add(character.get(4).update(animationTimer));
                root.getChildren().add(character.get(5).update(animationTimer));
                root.getChildren().add(character.get(0).update(animationTimer));
                mouseIsPressed = false;
                animationTimer++;
            }
        }.start();
        
        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
