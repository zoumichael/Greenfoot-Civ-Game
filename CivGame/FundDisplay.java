import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The class that displays the funds that each player has, updated when the next turn button is pressed.
 * 
 * @author Michael Zou
 * @version May 11, 2018
 */
public class FundDisplay extends Actor
{
    // The transparent color we define so there is no background.
    private final Color transparent = new Color(0, 0, 0, 0);
    
    /**
     * Constructor for Objects of class FundDisplay. Since the game just started, the funds is 0.
     */
    public FundDisplay(){
        setImage(new GreenfootImage("Funds: 0", 34, Color.YELLOW, transparent));
    }
    
    /**
     * Method that is used to update the display on the upper right hand corner of the screen.
     */
    public void updateImage(int funds){       
        setImage(new GreenfootImage("Funds: " + funds, 34, Color.YELLOW, transparent));
    }
}
