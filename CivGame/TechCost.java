import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The object that displays the cost of the tech.
 * 
 * @author Michael Zou
 * @version June 11, 2018
 */
public class TechCost extends Actor
{
    private int cost;
    private TechBubble assignedTech;

    /**
     * Constructor for objects of TechCost, initializes the cost and the image.
     */
    public TechCost(int c, TechBubble t){
        assignedTech = t;
        setImage(new GreenfootImage("techcost" + c + ".png"));
    }

    /**
     * Act - do whatever the TechCost wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            assignedTech.whenClicked();
        }
        int turn = ((GameWorld) getWorld()).getTurn();
        String techsUnlocked = ((GameWorld) getWorld()).getUnlockedTechs(turn - 1);
        if(techsUnlocked.indexOf((assignedTech.getName())) != -1){
            getWorld().removeObject(this);
        }
    }    
}
