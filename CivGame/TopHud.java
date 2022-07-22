import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Actor that displays the TopHud at the top of the screen.
 * 
 * @author Chengze Cai 
 * @version May 8, 2018
 */
public class TopHud extends Actor
{
    // Variable that stores the image.
    GreenfootImage hud;
    
    /**
     * Constructor for objects of class TopHud, initializes the image to "topHud.png".
     */
    public TopHud(){
        hud = new GreenfootImage("topHud.png");
        setImage(hud);
    }
}
