import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Superclass of all Buttons, contains the flicker method and an abstract whenClicked method 
 * thqt will be overwritten in child classes.
 * 
 * @author Michael Zou
 * @version June 11, 2018
 */
public abstract class Button extends Actor
{
    protected GreenfootImage ready;        // Variable that handles the animation
    protected GreenfootImage clicked; // Variable that handles the animation
    protected int animationCounter;           // Counter to change picture for button feedback
    protected boolean isClicked;                // Boolean to confirm button was clicked

    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            whenClicked();
            
            // Change variables for the flashing animation of the button.
            isClicked = true;
            animationCounter = 0;
        }
        
        // Animate the Flickering portion of the Button.
        if(isClicked && animationCounter <= 7){
            setImage(clicked);
            animationCounter++;
        }
        else{
            setImage(ready);
            isClicked = false;
        }
    }    

    /**
     * Abstract class that you need to override. 
     */
    public abstract void whenClicked();   
}
