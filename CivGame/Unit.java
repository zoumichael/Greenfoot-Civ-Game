import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Superclass of all units. Contains the method and parameters need to move this
 * unit around the map, and attack.
 * 
 * @author Michael Zou
 * @version April 25, 2018
 */
public class Unit extends GamePiece
{
    // Variable that stores the places that this unit can move to.
    protected boolean[][] avaliableMoves;
    protected int[][] remainingMoves;
    protected boolean movesDetermined;

    // Variables 
    //boolean[][] avaliableStaticAttacks;
    //boolean[][] avaliableGlobalAttacks;

    // Variable that determines if the unit is selected or not.
    protected boolean isSelected;

    // Variable that determines the number of moves this unit can make.
    protected int numMoves;

    // Variable that stores the health of the unit (for obvious reasons). As well as other game stats.
    protected int health;
    protected int attack;
    protected int armor;
    protected int range;
    protected int healthRegen;

    // Variable that stores what type of unit it is.
    protected String name;

    // Variable that stores whether the object can perform an action this turn.
    protected boolean canAct;
    protected boolean canMove;
    protected boolean canAttack;
    protected boolean canAttackAfterMove;
    protected boolean canMoveAfterAttack;
    protected boolean isRanged;
    protected boolean flying;

    // Variable that stores the UnitHealth object associated with this unit.
    protected UnitHealth healthIndicator;

    // Variable that stores the class of unit.
    protected String unitClass;

    // Variable that handles initialization after the object is created.
    protected boolean init = false; 

    // Variable that stores whether a dimmer was added or not.
    protected boolean dimmer;

    // Variable that stores what percent of damage you would do at 0 health.
    protected final double damageReductioFromHealth = 0.3;

    /**
     * The construtor for objects of class Unit, initalizes the coordinates as
     * well as the number of moves this unit can make.
     */
    public Unit(int x, int y, int t){
        super(x, y, t);

        // Initialize the avaliableMoves matrix.
        movesDetermined = false;

        // Initialize the stats.       
        numMoves = 4;
        health = 10;
        attack = 10;
        armor = 4;
        range = 1;
        healthRegen = 2;

        // Initialize the objects avaliablity to perform actions.
        canAct = true;
        canMove = true;
        canAttack = true;
        canAttackAfterMove = true;
        canMoveAfterAttack = false;

        // Set image based on team.
        String name = "Background" + t + ".png";
        GreenfootImage sprite = new GreenfootImage(name);
        setImage(sprite);

        // Create the UnitHealth object asscociated with this unit.
        healthIndicator = new UnitHealth(this);
    }

    /**
     * Act - do whatever the Unit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        // Call the initialization method.
        if(!init)
            initialization();

        // If the object cannot move or attack, then the object cannot act.
        if(!canAttack && !canMove)
            canAct = false;

        // When this or its health indicator is clicked, execute the method.
        if(Greenfoot.mouseClicked(this) || Greenfoot.mouseClicked(healthIndicator))
            whenClicked();            
    }    

    /**
     * Method that is called when the object is initialized.
     */
    public void initialization(){
        getWorld().addObject(healthIndicator, 0, 0);
        healthIndicator.updateAll();

        avaliableMoves = new boolean[((GameWorld) getWorld()).getPixelWidth()][((GameWorld) getWorld()).getPixelHeight()];
        remainingMoves = new int[((GameWorld) getWorld()).getPixelWidth()][((GameWorld) getWorld()).getPixelHeight()];

        // Set init to true so this isn't called again.
        init = true;
    }

    //-------------------------------------------------------
    //BELOW ARE ALL THE METHODS THAT HANDLE USER INTERATIONS.
    //-------------------------------------------------------

    /**
     * Method that handles what happens after the unit is clicked. 
     */
    public void whenClicked(){
        if(canAct && ((GameWorld) getWorld()).getTurn() == team){
            // If its not selected...
            if(!isSelected){
                // Deselects everything in this world.
                ((GameWorld) getWorld()).deselectAllUnits();
                ((GameWorld) getWorld()).deselectAllFactories();
                ((GameWorld) getWorld()).removeAttackTiles();
                ((GameWorld) getWorld()).removeSelectTiles();

                // Select this unit.
                ((GameWorld) getWorld()).setSelectedUnit(this);
                isSelected = true;

                // Close existing Unit Stat display.
                (getWorld().getObjects(BotHud.class).get(0)).closeUnitStats();

                // Open current Unit Stat display.
                (getWorld().getObjects(BotHud.class).get(0)).openUnitStats();

                // Close the Build Menu.
                (getWorld().getObjects(BotHud.class).get(0)).closeBuild();

                // Display all tiles.
                displayAllTiles();
            }
            // If it is selected, deselect this object.
            else{
                ((GameWorld) getWorld()).deselectAllUnits();
                ((GameWorld) getWorld()).removeAttackTiles();
                ((GameWorld) getWorld()).removeSelectTiles();
                (getWorld().getObjects(BotHud.class).get(0)).closeUnitStats();
            }
        }
    }

    //-----------------------------------------------------------------------------------------------
    //BELOW ARE ALL THE METHODS THAT ARE USED TO DETERMINE THE POSSIBILY ACTIONS THAT THE UNIT CAN DO.
    //-----------------------------------------------------------------------------------------------

    public void determinePossibilities(){
        // If the moves have not been determined, determine the tiles that this unit can move onto.
        if(!movesDetermined){
            getMoveTiles(coorX + 1, coorY, numMoves);
            getMoveTiles(coorX - 1, coorY, numMoves);
            getMoveTiles(coorX, coorY + 1, numMoves);
            getMoveTiles(coorX, coorY - 1, numMoves);
            movesDetermined = true;
        }
    }   

    /**
     * Method that is called when the object's possibilties (moving, attacking, etc.) are reset.
     */
    public void clearPossibilities(){
        if(avaliableMoves != null){
            for(int row = 0; row < avaliableMoves.length; row++){
                for(int col = 0; col < avaliableMoves[row].length; col++){
                    avaliableMoves[row][col] = false;               
                    remainingMoves[row][col] = 0;
                }
            }
        }	
        movesDetermined = false;
    }

    /**
     * The recursive method that is used to find all the tiles that the unit can 
     * move onto and hightlight them.
     * 
     * @param  x    the x coordinate
     * @param  y    the y coordinate
     * @param  movesLeft    the number of moves avaliable
     */
    public void getMoveTiles(int x, int y, int movesLeft){
        // If coordinates are outside the world, exit this method immediately.
        if(x < 0 || y < 1 || x >= ((GameWorld) getWorld()).getPixelWidth() || y >= ((GameWorld) getWorld()).getPixelHeight()-3)
            return;

        // If the tile has already been visited and the amount of moves on it is more than movesLeft, exit this method immediately.
        if(avaliableMoves[x][y] && remainingMoves[x][y] > movesLeft)
            return;

        // If coordinates are the same as current coordinates, exit this method immediately.
        if(x == coorX && y == coorY)
            return;

        // If the tile is not open - unit already on it/unpassable tile - exit this method immediately.
        if(((GameWorld) getWorld()).getMapAt(x, y).getOccupation() != 0 && ((GameWorld) getWorld()).getMapAt(x, y).getOccupation() != team)
            return;

        if(!((GameWorld) getWorld()).getMapAt(x, y).isPassable() && !flying){
            return;
        }
        // Get the move cost from moving onto this tile and subtract movesLeft by it.
        int moveCost = ((GameWorld) getWorld()).getMapAt(x, y).getMoves();
        if(!flying){
            movesLeft -= moveCost;
        }
        else{
            if(moveCost <= 2)
                movesLeft -= moveCost;
            else 
                movesLeft -= 2;
        }

        // Set the tile to be Selectable only if its empty.
        if(((GameWorld) getWorld()).getMapAt(x, y).getOccupation() == 0){
            avaliableMoves[x][y] = true;
            remainingMoves[x][y] = movesLeft;
        }

        // If moves is exhuasted, exit this method.
        if(movesLeft <= 0)
            return;

        // Perform selectTile on all the four tiles around it
        getMoveTiles(x + 1, y, movesLeft);
        getMoveTiles(x - 1, y, movesLeft);
        getMoveTiles(x, y + 1, movesLeft);
        getMoveTiles(x, y - 1, movesLeft);
    }

    //-----------------------------------------------
    //BELOW ARE ALL THE METHODS THAT DISPLAYS THINGS.
    //-----------------------------------------------

    /**
     * Method that displays all tiles of interest.
     */
    public void displayAllTiles(){
        if(canMove)
            displayTiles("MOVE");
    }

    /**
     * Create Select Tiles based on the the tiles that this unit can move to.
     */
    public void displayTiles(String tileType){
        determinePossibilities();

        for(int row = 0; row < avaliableMoves.length; row++){
            for(int col = 0; col < avaliableMoves[row].length; col++){
                if(tileType.indexOf("MOVE") != -1 && (avaliableMoves[row][col]))
                    ((GameWorld) getWorld()).addTile(new SelectTile(row, col), row, col);    
            }
        }
    }

    //----------------------------------------------------
    //BELOW ARE THE MISC. METHODS THAT ARE ALSO IMPORTANT.
    //----------------------------------------------------

    /**
     * Method that causes a unit to take damage.
     * 
     * @param   u   the unit that's dealing damage to this unit.
     * @param   dmgType  the type of damage that is being inflicted.
     */
    public void takeDamage(Unit u, String dmgType){
        double baseDamage = u.getAttack() - armor;
        double flatDamage = baseDamage * damageReductioFromHealth;
        double scalingDamage = baseDamage * (1.0-damageReductioFromHealth) * u.getHealth() / 10.0;
        int damageTaken = (int) (flatDamage + scalingDamage);

        if(damageTaken < 0)
            damageTaken = 0;
        health -= damageTaken;
    }

    //------------------------------------------------------
    //EVERYTHING BELOW THIS ARE ACCESSOR AND MUTATOR METHODS
    //------------------------------------------------------

    /**
     * Mutator method for the 'isSelected' variable.
     * 
     * @param  temp    the new value of isSelected
     */
    public void setSelected(boolean temp){
        isSelected = temp;
    }

    /**
     * Accessor method for the 'isSelected' variable.
     * 
     * @return     the isSelected variable
     */
    public boolean getSelected(){
        return isSelected;
    }

    /**
     * Mutator method for the 'canAct' variable.
     * 
     * @param  a    the new value of canAct
     */
    public void setCanAct(boolean a){
        canAct = a;
    }

    /**
     * Accessor method for the 'canAct' variable.
     * 
     * @return     the canAct variable
     */
    public boolean getCanAct(){
        return canAct;
    }

    /**
     * Mutator method for the 'canMove' variable.
     * 
     * @param  m    the new value of canMove
     */
    public void setCanMove(boolean m){
        canMove = m;
    }

    /**
     * Accessor method for the 'canMove' variable.
     * 
     * @return     the canMove variable
     */
    public boolean getCanMove(){
        return canMove;
    }

    /**
     * Mutator method for the 'canAttack' variable.
     * 
     * @param  a    the new value of canAttack
     */
    public void setCanAttack(boolean a){
        canAttack = a;
    }

    /**
     * Accessor method for the 'canAttack' variable.
     * 
     * @return     the canAttack variable
     */
    public boolean getCanAttack(){
        return canAttack;
    }

    /**
     * Mutator method for the 'canAttackAfterMove' variable.
     * 
     * @param  mta    the new value of canAttackAfterMove
     */
    public void setAttackAfterMove(boolean mta){
        canAttackAfterMove = mta;
    }

    /**
     * Accessor method for the 'canAttackAfterMove' variable.
     * 
     * @return     the canAttackAfterMove variable
     */
    public boolean getAttackAfterMove(){
        return canAttackAfterMove;
    }

    /**
     * Mutator method for the 'canMoveAfterAttack' variable.
     * 
     * @param  atm    the new value of canMoveAfterAttack
     */
    public void setMoveAfterAttack(boolean atm){
        canMoveAfterAttack = atm;
    }

    /**
     * Accessor method for the 'canMoveAfterAttack' variable.
     * 
     * @return     the canMoveAfterAttack variable
     */
    public boolean getMoveAfterAttack(){
        return canMoveAfterAttack;
    }

    /**
     * Accessor method for the 'dimmer' variable.
     * 
     * @return     the dimmer variable
     */
    public boolean getDimmerAdded(){
        return dimmer;
    }

    /**
     * Mutator method for the 'dimmer' variable.
     * 
     * @param  d    the new value of dimmer
     */
    public void setDimmerAdded(boolean d){
        dimmer = d;
    }

    /**
     * Accessor method for the 'numMoves' variable.
     * 
     * @return     the canMove variable
     */
    public int getNumMoves(){
        return numMoves;
    }

    /**
     * Mutator method for the 'numMoves' variable.
     * 
     * @param  moves    the new value of numMoves
     */
    public void setNumMoves(int moves){
        numMoves = moves;
    }

    /**
     * Mutator method for the 'isRanged' variable.
     * 
     * @param  r    the new value of isRanged
     */
    public void setIsRanged(boolean r){
        isRanged = r;
    }

    /**
     * Accessor method for the 'isRanged' variable.
     * 
     * @return     the isRanged variable
     */
    public boolean getIsRanged(){
        return isRanged;
    }

    /**
     * Mutator method for the 'health' variable.
     * 
     * @param  h    the new value of health
     */
    public void setHealth(int h){
        health = h;
        if(health > 10)
            health = 10;
    }

    /**
     * Accessor method for the 'health' variable.
     * 
     * @return     the health variable
     */
    public int getHealth(){
        return health;
    }

    /**
     * Mutator method for the 'armor' variable.
     * 
     * @param  a    the new value of armor
     */
    public void setArmor(int a){
        armor = a;
    }

    /**
     * Accessor method for the 'armor' variable.
     * 
     * @return     the armor variable
     */
    public int getArmor(){
        return armor;
    }

    /**
     * Mutator method for the 'attack' variable.
     * 
     * @param  a    the new value of attack
     */
    public void setAttack(int a){
        attack = a;
    }

    /**
     * Accessor method for the 'attack' variable.
     * 
     * @return     the attack variable
     */
    public int getAttack(){
        return attack;
    }

    /**
     * Mutator method for the 'healthRegen' variable.
     * 
     * @param  r    the new value of healthRegen
     */
    public void setRegen(int r){
        healthRegen = r;
    }

    /**
     * Accessor method for the 'healthRegen' variable.
     * 
     * @return     the healthRegen variable
     */
    public int getRegen(){
        return healthRegen;
    }

    /**
     * Mutator method for the 'range' variable.
     * 
     * @param  r    the new value of range
     */
    public void setRange(int r){
        range = r;
    }

    /**
     * Accessor method for the 'range' variable.
     * 
     * @return     the range variable
     */
    public int getRange(){
        return range;
    }

    /**
     * Mutator method for the 'healthIndicator' variable.
     * 
     * @param  hI    the reference of the new UnitHealth.
     */
    public void setHealthIndicator(UnitHealth hI){
        healthIndicator = hI;
    }

    /**
     * Accessor method for the 'healthIndicator' variable.
     * 
     * @return     the healthIndicator variable
     */
    public UnitHealth getHealthIndicator(){
        return healthIndicator;
    }

    /**
     * Mutator method for the 'unitClass' variable.
     * 
     * @param  uc    the new value of unitClass.
     */
    public void setUnitClass(String uc){
        unitClass = uc;
    }

    /**
     * Accessor method for the 'unitClass' variable.
     * 
     * @return     the unitClass variable
     */
    public String getUnitClass(){
        return unitClass;
    }

    /**
     * Mutator method for the 'name' variable.
     * 
     * @param  n    the new value of name.
     */
    public void setName(String n){
        name = n;
    }

    /**
     * Accessor method for the 'name' variable.
     * 
     * @return     the name variable
     */
    public String getName(){
        return name;
    }
}
