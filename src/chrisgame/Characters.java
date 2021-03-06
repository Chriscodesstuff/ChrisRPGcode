/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chrisgame;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Chris
 */
public class Characters {
    //resource type
    private int resourceType = 0; //1 is mana, 2 is rage, 3 is shielding
    public int getResourceType (){
        return(resourceType);
    }
    
    //current resource
    private double resourceBar = 0;
    public double getResourceBar (){
        return(resourceBar);
    }
    public void setResourceBar (int newResourceBar){
        resourceBar = newResourceBar;
    }
    
    //max health
    private double maxHealth = 100;
    public double getMaxHealth (){
        return(maxHealth);
    }
    public void setMaxHealth (double newMaxHealth) {
        maxHealth = newMaxHealth;
    }
    
    //current health
    double currentHealth = 100;
    public double getCurrentHealth (){
        return(currentHealth);
    }
    public void setCurrentHealth (double newCurrentHealth){
        currentHealth = newCurrentHealth;
    }
    
    
    //position values
    
    
    //animation functions
        //Arraysn
    double[] dest = {0,0};
    double[] change = {0,0};
    double[] pos = {1000,500};
    double[] temp = {0,0};
    double[] home = {0,0};
    

    
    //Methods/functions
    public void setDest(double x, double y){
        dest[0] = x;
        dest[1] = y;
        change[0] = Math.cos(Math.atan2(y - pos[1] , x - pos[0]))*2;
        change[1] = Math.sin(Math.atan2(y - pos[1] , x - pos[0]))*2;
    }

    
    //image collection
    Image character1 = new Image(getClass().getResourceAsStream("/character.png"));
    ImageView imageView = new ImageView();
    Rectangle2D standing1 = new Rectangle2D(0,0,16,16);
    Rectangle2D standing2 = new Rectangle2D(16,0,16,16);
    Rectangle2D standing3 = new Rectangle2D(0,16,16,16);
    Rectangle2D standing4 = new Rectangle2D(16,16,16,16);
    Rectangle2D moving1 = new Rectangle2D(32,0,16,16);
    Rectangle2D moving2 = new Rectangle2D(48,0,16,16);
    Rectangle2D moving3 = new Rectangle2D(32,16,16,16);
    Rectangle2D moving4 = new Rectangle2D(48,16,16,16);
    
    public ImageView update (int animationTimer){
        //update position
        if(pos[0]>dest[0]+1||pos[0]<dest[0]-1||pos[1]>dest[1]+1||pos[1]<dest[1]-1){
            pos[0]+=this.change[0];
            pos[1]+=this.change[1];
            if((animationTimer*2)%100==0){
                if(change[0]>=0){
                    imageView.setViewport(moving1);
                }else{
                    imageView.setViewport(moving3);
                }
            }else if((animationTimer*2)%50==0){
                if(change[0]>=0){
                    imageView.setViewport(moving2);
                }else{
                    imageView.setViewport(moving4);
                }
            }
        }else{
            if((animationTimer)%100==0){
                if(change[0]>=0){
                    imageView.setViewport(standing1);
                }else{
                    imageView.setViewport(standing3);
                }
            }else if((animationTimer)%50==0){
                if(change[0]>=0){
                    imageView.setViewport(standing2);
                }else{
                    imageView.setViewport(standing4);
                }
            }
        }
        
        //update animation
        imageView.setImage(character1);
        
        imageView.setX(pos[0]);
        imageView.setY(pos[1]);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        return(imageView);
    }
    
    public Characters (int startResourceType, double startResourceBar, double startMaxHealth, int homeNum){
        resourceType = startResourceType;
        resourceBar = startResourceBar;
        maxHealth = startMaxHealth;
        switch(homeNum){
            case 1:
                home[0] = 500; home[1] = 200;
                break;
            case 2:
                home[0] = 500; home[1] = 450;
                break;
            case 3:
                home[0] = 500; home[1] = 700;
                break;
            case 4:
                home[0] = 1500; home[1] = 200;
                break;
            case 5:
                home[0] = 1500; home[1] = 450;
                break;
            case 6:
                home[0] = 1500; home[1] = 700;
                break;    
            default:
                break;
        }
        this.setDest(home[0],home[1]);
    }
}
