import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The tile that is created that when a unit can attack a nearby unit.
 * Clicking it calls the fight method in the world.
 * 
 * @author Michael Zou
 * @version May 9, 2018
 */
public class SelectTile extends DisplayTiles
{
    /**
     * Constructor of objects of class SelectTile, initializes the image.
     */
    public SelectTile(int x, int y){
        super(x, y);

        // Set the image to be the AttackTile image.
        GreenfootImage sprite = new GreenfootImage("SelectTile.png");
        setImage(sprite);

        // Lower the transparency.
        getImage().setTransparency(100);
    }

    /**
     * Act - do whatever the AttackTile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
            ((GameWorld) getWorld()).moveSelectedObject(coorX, coorY);
    }    
}
