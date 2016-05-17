/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chrisgame;

/**
 *
 * @author Chris
 */
public class Tools {
    public boolean isWithin (double pointX,double pointY, double cornerX, double cornerY, double width, double height){
        if(pointX>=cornerX&&pointX<=cornerX+width&&pointY>=cornerY&&pointY<=cornerY+height){
            return(true);
        }else{
            return(false);
        }
    };
}
