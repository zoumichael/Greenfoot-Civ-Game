import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The button that changes the instruction page.
 * 
 * @author Michael Zou, Chengze Cai
 * @version June 11, 2018
 */
public class BackButton extends Button
{
    /**
     * Constructors of objects of class BackButton, initializes the images and sets.
     */
    public BackButton(){
        // Initialize and set the image.
        ready = new GreenfootImage("backReady.png");
        clicked = new GreenfootImage("backClicked.png");
        setImage(ready);
        
        // Initializes the animation variables.
        isClicked = false;
        animationCounter = 0;
    }

    /**
     * Method that overrides abstract the method in the parent class.
     */
    public void whenClicked(){
        ((Instruction) getWorld()).previousPage();
    }
}
