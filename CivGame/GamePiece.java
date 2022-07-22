import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The superclass of all gamepieces, contains accessor and mutators for coorX and coorY as well as teams.
 *
 * @author Michael Zou
 * @version May 8, 2018
 */
public class GamePiece extends ScrollActor
{
    // Variable that stores the "coordinates" of the object based on the new coordinate system.
    protected int coorX;
    protected int coorY;

    // Variable that stores the "team" that the object is from.
    protected int team;
    
    /**
     * The construtor for objects of class GamePiece, initalizes the coordinates as well as team.
     */
    public GamePiece(int x, int y, int t){
        super();
        
        // Initialize the coordinates.
        coorX = x;
        coorY = y;
        
        // Initialize the team.
        team = t;
    }
    
    //------------------------------------------------------
    //EVERYTHING BELOW THIS ARE ACCESSOR AND MUTATOR METHODS
    //------------------------------------------------------
    
    /**
     * Mutator method for the 'coorX' variable.
     * 
     * @param  x    the new value of coorX
     */
    public void setPixelX(int x){
        coorX = x;
    }

    /**
     * Accessor method for the 'coorX' variable.
     * 
     * @return     the coorX variable
     */
    public int getPixelX(){
        return coorX;
    }

    /**
     * Mutator method for the 'coorY' variable.
     * 
     * @param  y    the new value of coorY
     */
    public void setPixelY(int y){
        coorY = y;
    }

    /**
     * Accessor method for the 'coorY' variable.
     * 
     * @return     the coorY variable
     */
    public int getPixelY(){
        return coorY;
    }

    /**
     * Mutator method for the 'team' variable.
     * 
     * @param  t    the new value of team
     */
    public void setTeam(int t){
        team = t;
    }

    /**
     * Accessor method for the 'team' variable.
     * 
     * @return     the team variable
     */
    public int getTeam(){
        return team;
    }
}

