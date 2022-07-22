import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The tile that is created that when a unit can attack a nearby unit.
 * Clicking it calls the fight method in the world.
 * 
 * @author Michael Zou, Chengze Cai
 * @version May 9, 2018
 */
public class AttackTile extends DisplayTiles
{
    /**
     * Constructor of objects of class AttackTile, initializes the image.
     */
    public AttackTile(int x, int y){
        super(x, y);

        // Set the image to be the AttackTile image.
        GreenfootImage sprite = new GreenfootImage("TargetTile.png");
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
            ((GameWorld) getWorld()).fight(coorX, coorY);
    }    
}
