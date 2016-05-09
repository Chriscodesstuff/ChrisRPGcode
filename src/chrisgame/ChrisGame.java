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
            int animationTimer = 0;
            
            public void handle (long currentTime){
                //reset canvas
                root.getChildren().clear();
                background.setFill(grey);
                root.getChildren().add(background);
                
                //do once
                if(doOnce){
                    character.add(new Characters(0,0,0));
                    character.add(new Characters(0,0,0));
                    character.add(new Characters(0,0,0));
                    character.add(new Characters(0,0,0));
                    character.add(new Characters(0,0,0));
                    character.add(new Characters(0,0,0));
                    doOnce = false;
                }
                root.setOnTouchPressed(new EventHandler<TouchEvent>(){
                    public void handle(TouchEvent me){
                        character.get(1).setDest(me.getTouchPoint().getSceneX(),me.getTouchPoint().getSceneY());
                    }
                });            
                root.setOnMousePressed(new EventHandler<MouseEvent>(){
                    public void handle(MouseEvent me){
                        character.get(1).setDest(me.getX(),me.getY());
                    }
                });
                
                root.getChildren().add(character.get(1).update(animationTimer));
                
                animationTimer++;
            }
        }.start();
        
        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
