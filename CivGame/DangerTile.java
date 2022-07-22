import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A stationary tile that tells the player what areas an unit can attack.
 * 
 * @author Michael Zou, Chengze Cai
 * @version May 24, 2018
 */
public class DangerTile extends DisplayTiles
{
    /**
     * Constructor of objects of class DangerTile, initializes the image.
     */
    public DangerTile(int x, int y){
        super(x, y);

        // Set the image to be the AttackTile image.
        GreenfootImage sprite = new GreenfootImage("TargetTile.png");
        setImage(sprite);

        // Lower the transparency.
        getImage().setTransparency(100);
    }
}
