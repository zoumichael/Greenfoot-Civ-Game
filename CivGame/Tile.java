import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The superclass of all tiles, contains parameters that allows it to change from one
 * form of tile to another.
 * 
 * @author Michael Zou
 * @version April 25, 2018
 */
public class Tile extends ScrollActor
{
    // The variables that stores the number of moves a unit needs to spend to move onto this tile.
    private int numMoves;

    // The image that changes based one the numMoves variable. 
    GreenfootImage baseImage;

    // Variable that stores the "coordinates" of the object based on the new coordinate system.
    private int coorX;
    private int coorY;

    // Variable that stores whether this object has something on it or not.
    // -1: Impassible. 0: Nothing is on it. N: A unit from team N is on it.
    private int occupation;
    
    private boolean passable;
    // Variable that stores the current unit that's on this tile.
    private Unit currentUnit;

    // Variable that stores the current building that's on this tile.
    private Building currentBuilding;
    
    //Variable that stores whether or not the tile is road
    private boolean isRoad;
    
    /**
     * Constructor for objects of class Tile, initializes the numMoves variable, 
     * and sets the image based on the numMoves variable.
     */
    public Tile(int m, int x, int y, boolean open){
        super();
        // Initialize numMoves and the image based on numMoves.
        numMoves = m;
        if(m == 2)
            baseImage = new GreenfootImage("FastTile.png");
        else if(m == 4)
            baseImage = new GreenfootImage("MediumTile.png");
        else if(m == 6)
            baseImage = new GreenfootImage("SlowTile.png");
        else if(m == 7)
            baseImage = new GreenfootImage("WaterTile.png");
        setImage(baseImage);
        
        //If it is a water tile, make the tile unpassable
        if(m == 7){
            passable = false;
            numMoves = 1;
        }else{
            passable = true;
        }
        
        // Initializes the coordinates.
        coorX = x;
        coorY = y;
        
        // Initialize the isRoad variable.
        isRoad = false;
        
        // Initialize the occupation and the currentUnit, if the tile is open occupation is 0, otherwise -1.
        if(open)
            occupation = 0;
        else
            occupation = -1;
        currentUnit = null;

        currentBuilding = null;
    }

    /**
     * Method that checks if this tile already has a SelectTile on it.
     * 
     * @return     true if there is already a selectTile on it, false otherwise.
     */
    public boolean checkSelectTile(){
        Actor a = getOneIntersectingObject(SelectTile.class);
        if(a != null)
            return true;
        return false;
    }

    /**
     * Method that checks if this tile already has a AttackTile on it.
     * 
     * @return     true if there is already a AttackTile on it, false otherwise.
     */
    public boolean checkAttackTile(){
        Actor a = getOneIntersectingObject(AttackTile.class);
        if(a != null)
            return true;
        return false;
    }    
    
    //------------------------------------------------------
    //EVERYTHING BELOW THIS ARE ACCESSOR AND MUTATOR METHODS
    //------------------------------------------------------
    
    /**
     * Mutator method for the 'isRoad' variable.
     * 
     * @param  b if there is a road or not 
     */
    public void setRoad(boolean b){
        isRoad = b;
    }
    
    /**
     * Accessor method for the 'currentBuilding' variable.
     * 
     * @return     the value of isRoad
     */
    public boolean getRoad(){
        return isRoad;
    }
    
    /**
     * Mutator method for the 'numMoves' variable.
     * 
     * @param  m    the new value of numMoves 
     */
    public void setMoves(int m){
        numMoves = m;	
    }

    /**
     * Accessor method for the 'numMoves' variable.
     * 
     * @return     the numMoves variable
     */
    public int getMoves(){
        return numMoves;
    }

    /**
     * Mutator method for the 'occupation' variable.
     * 
     * @param  o    the new value of occupation
     */
    public void setOccupation(int o){
        occupation = o;
    }

    /**
     * Accessor method for the 'occupation' variable.
     * 
     * @return     the occupation variable
     */
    public int getOccupation(){
        return occupation;
    }

    /**
     * Mutator method for the 'currentUnit' variable.
     * 
     * @param  u    the reference to the new Unit
     */
    public void setUnit(Unit u){
        currentUnit = u;
    }

    /**
     * Accessor method for the 'currentUnit' variable.
     * 
     * @return     the Unit being referenced by currentUnit
     */
    public Unit getUnit(){
        return currentUnit;
    }

    /**
     * Mutator method for the 'currentBuilding' variable.
     * 
     * @param  b    the reference to the new Building
     */
    public void setBuilding(Building b){
        currentBuilding = b;
    }
    
    /**
     * Accessor method for the 'currentBuilding' variable.
     * 
     * @return     the Building being referenced by currentBuilding
     */
    public Building getBuilding(){
        return currentBuilding;
    }
    
    public boolean isPassable(){
        return passable;
    }
}
