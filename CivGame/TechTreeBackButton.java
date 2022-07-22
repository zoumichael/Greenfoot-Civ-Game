import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * The button that takes the player back to the game from the tech tree.
 * 
 * @author Michael Zou
 * @version May 30, 2018
 */
public class TechTreeBackButton extends Actor
{
    /**
     * Constructors of objects of class BackButton, initializes the image.
     */
    public TechTreeBackButton(){
        setImage(new GreenfootImage("TechTreeBackButton.png"));
    }

    /**
     * Act - do whatever the BackButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            closeTechTree();
        }
    }    

    /**
     * Method that closes the tech tree and all related conponents.
     */
    public void closeTechTree(){
        getWorld().removeObject(getWorld().getObjects(TechTreeBackground.class).get(0));
        for(TechBubble t : getWorld().getObjects(TechBubble.class))
            getWorld().removeObject(t);
        for(TechCost t : getWorld().getObjects(TechCost.class))
            getWorld().removeObject(t);
        getWorld().removeObject(this);
    }
}
