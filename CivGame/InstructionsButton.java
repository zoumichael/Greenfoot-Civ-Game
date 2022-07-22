import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button that takes the users to the instructions world.
 * 
 * @author Michael Zou, Jonathan Hai
 * @version June 11, 2018
 */
public class InstructionsButton extends Button
{
    /**
     * Constructors of objects of class InstructionsButton, initializes the images and sets.
     */
    public InstructionsButton(){
        // Initialize and set the image.
        ready = new GreenfootImage("instructionsReady.png");
        clicked = new GreenfootImage("instructionsClicked.png");
        setImage(ready);
        
        // Initializes the animation variables.
        isClicked = false;
        animationCounter = 0;
    }

    /**
     * Method that overrides abstract the method in the parent class.
     */
    public void whenClicked(){
        Greenfoot.setWorld(new Instruction());
    }
}
