import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The button that appears on the bottom HUD to build units. 
 * 
 * @author Michael Zou
 * @version June 12, 2018
 */
public class BuildButton extends Actor
{
    private boolean canBuild;
    private String unitType;

    private GreenfootImage image;

    /**
     * Constructors of objects of class BuildButton, initialize the canBuild and the unitType.
     */
    public BuildButton(boolean c, String unit){
        canBuild = c;
        unitType = unit;

        // If you can build it, change the image appropriately.
        if(c)
            image = new GreenfootImage(unit + "CANBUILD.png");
        else
            image = new GreenfootImage(unit + "CANTBUILD.png");

        setImage(image);
    }

    /**
     * Act - do whatever the BuildButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // If the unit can be built.
        if(canBuild){
            // And this button is clicked.
            if(Greenfoot.mouseClicked(this)){
                ((GameWorld) getWorld()).build(unitType);
                getWorld().getObjects(BotHud.class).get(0).closeBuild();
            }
        }
    }    
}
