import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button that takes you to the Canada map.
 * 
 * @author Michael Zou, Chengze Cai
 * @version June 11, 2018
 */
public class CanadaMapButton extends Button
{
    private int playerNum;
    
    /**
     * Constructor of objects of classes CanadaMapButton, initializes the number of players;
     */
    public CanadaMapButton(){
        // Initialize and set the image.
        ready = new GreenfootImage("canadamapReady.png");
        clicked = new GreenfootImage("canadamapClicked.png");
        setImage(ready);
        
        // Initializes the animation variables.
        isClicked = false;
        animationCounter = 0;
    }
    
    /**
     * Method that overrides abstract the method in the parent class.
     */
    public void whenClicked(){
        Greenfoot.setWorld(new CanadaMap());
    }
}
