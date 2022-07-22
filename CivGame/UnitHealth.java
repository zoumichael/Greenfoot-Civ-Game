import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The object assigned to each unit that displays the amount of health the unit has.
 * 
 * @author Michael Zou, Chengze Cai
 * @version May 8, 2018
 */
public class UnitHealth extends ScrollActor
{
    private Unit unitReference;
    
    private GreenfootImage healthAmount;
    
    private final int xDisplacement = 15;
    private final int yDisplacement = 15;
    
    /**
     * Constructor of objects of class UnitHealth, initializes the unit that this would reference.
     */
    public UnitHealth(Unit u){
        unitReference = u;
    }   
    
    /**
     * Act - do whatever the UnitHealth wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Check if the unit this references is still in this world, if it isn't remove this unit. 
        if(unitReference == null){
            getWorld().removeObject(this);
            return;
        }        
    }    
    
    /**
     * A method that constantly updates the location of the health to the lower right 
     * hand corner of the unit its following.
     */
    public void updateLocation(){
        int tempX = unitReference.getX() + xDisplacement;
        int tempY = unitReference.getY() + yDisplacement; 
        setLocation(tempX, tempY);
    }
    
    /**
     * A method that constantly updates the health based on the player. 
     */
    public void updateHealth(){
        int health = unitReference.getHealth();
        healthAmount = new GreenfootImage("Health" + health + ".png");
        setImage(healthAmount);
    }
    
    /**
     * A method that updates everything. We do not call it every act to save memory.
     */
    public void updateAll(){
        updateLocation();
        updateHealth();
    }
}
