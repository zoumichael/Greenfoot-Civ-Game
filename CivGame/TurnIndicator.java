import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The object at the top of the screen that changes based on whose turn it is.
 * 
 * @author Chengze Cai, Michael Zou
 * @version May 9, 2018
 */
public class TurnIndicator extends Actor
{    
    private GreenfootImage turnIcon; // The variable that stores the most updated image of the TurnIndicator
    
    /**
     * Constructor of Objects of TurnIndicator, initializes it to the first player.
     */
    public TurnIndicator(){
        turnIcon = new GreenfootImage("Background1Icon.png");
        setImage(turnIcon);
    }
    
    /**
     * Method that updates the image. 
     */
    public void updateImage(){
        setImage(turnIcon);
    }
    
    /**
     * Mutator method for the 'turnIcon' variable.
     * 
     * @param  i    the new value of turnIcon
     */
    public void setTurnIcon(GreenfootImage i){
        turnIcon = i;
    }    
}
