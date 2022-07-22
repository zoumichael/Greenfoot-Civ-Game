import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The superclass of all tiles that are created by units. 
 * These tiles are completely different from the terrain tiles.
 * Are used as overlay tiles to showcase different things
 * 
 * @author Michael Zou
 * @version May 18, 2018
 */
public class DisplayTiles extends ScrollActor
{
    // The coordinates of the Tile based on the new pixel coordinates.
    protected int coorX;
    protected int coorY;
    
    /**
     * Constructors of objects of class DisplayTiles, initializes the coordinates.
     */
    public DisplayTiles(int x, int y){
        // Update and store the coordinates.
        coorX = x;
        coorY = y;
    }
}
