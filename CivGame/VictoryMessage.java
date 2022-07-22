import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The message that pops up when a player has dominated the map.
 * 
 * @author Michael Zou
 * @version June 11, 2018
 */
public class VictoryMessage extends Actor
{
    /**
     * Constructor of objects of class VictoryMessage, initializes the image and transparency.
     */
    public VictoryMessage(int team){
        GreenfootImage message = new GreenfootImage("player" + team + "wins.png");    
        setImage(message);
    }
}
