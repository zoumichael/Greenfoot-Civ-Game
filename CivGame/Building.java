import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Superclass of all buildings.
 * Adds funds to the team the controls it.
 * Changes images of the buildings based on team.
 * 
 * @author Michael Zou
 * @version May 8, 2018
 */
public class Building extends GamePiece
{
    protected int income; // Variable that stores how much resources you get from this building each turn.
    protected String imagePrefix = "Building";
    
    protected GreenfootImage image;
    
    /**
     * Constructors of objects of class Factory
     */
    public Building(int x, int y, int t, int i){
        super(x, y, t);
       
        // Initialize the income variable.
        income = i;
        setImage(new GreenfootImage(imagePrefix + team + ".png"));
    }
    
    public void updateImage(){
        image = new GreenfootImage(imagePrefix + team + ".png");
        setImage(image);
    }
    
    public void capture(int t){
        team = t;
        updateImage();
    }
    
    //------------------------------------------------------
    //EVERYTHING BELOW THIS ARE ACCESSOR AND MUTATOR METHODS
    //------------------------------------------------------
    
    /**
     * Mutator method for the 'income' variable.
     * 
     * @param  i    the new value of income
     */
    public void setIncome(int i){
        income = i;
    }

    /**
     * Accessor method for the 'income' variable.
     * 
     * @return     the income variable
     */
    public int getIncome(){
        return income;
    }
}
