import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button that takes the user to a world with randomly generated biomes.
 * 
 * @author Michael Zou, Chengze Cai
 * @version June 11, 2018
 */
public class RandomMapButton extends Button
{
    private int playerNum;
    
    /**
     * Constructor of objects of classes RandomMapButton, initializes playerNum;
     */
    public RandomMapButton(int n){
        playerNum = n;
        // Initialize and set the image.
        ready = new GreenfootImage(n + "playerReady.png");
        clicked = new GreenfootImage(n + "playerClicked.png");
        setImage(ready);
        
        // Initializes the animation variables.
        isClicked = false;
        animationCounter = 0;
    }
    
    /**
     * Method that overrides abstract the method in the parent class.
     */
    public void whenClicked(){
        Greenfoot.setWorld(new RandomWorld(playerNum));
    }
}
