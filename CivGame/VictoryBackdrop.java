import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The backdrop that pops up when a player dominated the map.
 * 
 * @author Michael Zou
 * @version June 11, 2018
 */
public class VictoryBackdrop extends Actor
{
    /**
     * Constructor of objects of class VictoryBackdrop, initializes the image and transparency.
     */
    public VictoryBackdrop(){
        GreenfootImage cover = new GreenfootImage("BlackCover.png");        
        cover.setTransparency(200);
        setImage(cover);
    }
}
