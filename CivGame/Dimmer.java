import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A object that is created when a unit can no longer move. 
 * Tells the user that this unit cannot move anymore by darkening that tile.
 * 
 * @author Michael Zou
 * @version May 15, 2018
 */
public class Dimmer extends ScrollActor
{
    /**
     * Constructor of objects of class Dimmer. 
     * Initalize the image and the transparency.
     */
    public Dimmer(){
        GreenfootImage dimmer = new GreenfootImage("Dimmer.png");
        dimmer.setTransparency(100);
        setImage(dimmer);        
    }
}
