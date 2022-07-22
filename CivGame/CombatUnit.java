import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * A subclass of the Unit class that stores the variables methods needed for the unit to engage in combat with other units.
 * 
 * @author Michael Zou
 * @version May 18, 2018
 */
public class CombatUnit extends Unit
{
    // Variables that store whether this unit can attack at a specific place...
    protected boolean[][] avaliableStaticAttacks;     // ... before moving.
    protected boolean[][] avaliableGlobalAttacks;     // ... after moving.
    protected int[][] remainingGlobalRange;
    protected boolean globalAttacksDetermined;
    protected boolean staticAttacksDetermined;

    // 
    protected boolean isBDown = false;

    public CombatUnit(int x, int y, int t){
        super(x, y, t);
    }

    /**
     * Act - do whatever the Unit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        super.act();

        displayDangerTiles();

        // If the health is less than or equal to 0, remove this object.
        if(health <= 0){
            getWorld().removeObject(healthIndicator);
            getWorld().removeObject(this);
        }
    }

    /**
     * Method that is called when the object is initialized. 
     * Overides the initialization in Unit by also initilizing the attack arrays.
     */
    public void initialization(){
        super.initialization();

        // Initialize variables.
        globalAttacksDetermined = false;
        staticAttacksDetermined = false;

        // Initialize Arrays.
        avaliableStaticAttacks = new boolean[((GameWorld) getWorld()).getPixelWidth()][((GameWorld) getWorld()).getPixelHeight()];
        avaliableGlobalAttacks = new boolean[((GameWorld) getWorld()).getPixelWidth()][((GameWorld) getWorld()).getPixelHeight()];     
        remainingGlobalRange = new int[((GameWorld) getWorld()).getPixelWidth()][((GameWorld) getWorld()).getPixelHeight()];     
    }

    /**
     * The recursive method that is used to find all the unit that the unit can 
     * attack onto and creates an attack tile on thoses slots
     * 
     * @param  x    the x coordinate
     * @param  y    the y coordinate
     * @param  rangeLeft    the number of range avaliable
     * @param  global   whether to add this to the staticAttacks or globalAttacksArray
     */
    public void getAttackTiles(int x, int y, int rangeLeft, boolean global){
        // If coordinates are outside the world, exit this method immediately.
        if(x < 0 || y < 0 || x >= ((GameWorld) getWorld()).getPixelWidth() || y >= ((GameWorld) getWorld()).getPixelHeight())
            return;

        // If     

        // Reduce the range by 1.
        rangeLeft--;

        //
        if(global){
            if(avaliableGlobalAttacks[x][y] && remainingGlobalRange[x][y] > rangeLeft)
                return;
        }

        // If the tile contains a unit - occupation is greater than 0 - and the unit is not on the same team, set the .
        if(((GameWorld) getWorld()).getMapAt(x, y).getOccupation() != team){
            if(global){
                avaliableGlobalAttacks[x][y] = true;
                remainingGlobalRange[x][y] = rangeLeft;
            }
            else{
                if(((GameWorld) getWorld()).getMapAt(x, y).getOccupation() > 0)
                    avaliableStaticAttacks[x][y] = true;
            }
        }

        // If unit has no more range left, exit this method.
        if(rangeLeft <= 0)
            return; 

        // Perform getAttackTiles on all the four tiles around it.
        getAttackTiles(x + 1, y, rangeLeft, global);
        getAttackTiles(x - 1, y, rangeLeft, global);
        getAttackTiles(x, y + 1, rangeLeft, global); 
        getAttackTiles(x, y - 1, rangeLeft, global);
    }

    public void determinePossibilities(){
        super.determinePossibilities();

        if(!staticAttacksDetermined){
            getAttackTiles(coorX + 1, coorY, range, false);
            getAttackTiles(coorX - 1, coorY, range, false);
            getAttackTiles(coorX, coorY + 1, range, false);
            getAttackTiles(coorX, coorY - 1, range, false);
            staticAttacksDetermined = true;
        }
        if(!globalAttacksDetermined){
            getGlobalAttackTiles();
            globalAttacksDetermined = true;
        }
    }

    /**
     * Method that displays all tiles of interest.
     * Overides the displayAllTiles in the Unit class but also showing the displaying the attack tiles.
     */
    public void displayAllTiles(){
        super.displayAllTiles();
        if(canAttack)
            displayTiles("STATICATTACK");
    }

    /**
     * Method that is called when the object's possibilties (moving, attacking, etc.) are reset.
     * Overides the clearPossibilties in Unit by also reset the attack arrays and variables.
     */
    public void clearPossibilities(){
        super.clearPossibilities();

        // Also clears attack in addition to moves.
        if(avaliableMoves != null){
            for(int row = 0; row < avaliableMoves.length; row++){
                for(int col = 0; col < avaliableMoves[row].length; col++){
                    avaliableStaticAttacks[row][col] = false;   
                    avaliableGlobalAttacks[row][col] = false;
                    remainingGlobalRange[row][col] = 0;
                }
            }
        }
        // Reset variables.
        globalAttacksDetermined = false;
        staticAttacksDetermined = false;
    }

    public void displayTiles(String tileType){
        super.displayTiles(tileType);
        determinePossibilities();
        for(int row = 0; row < avaliableMoves.length; row++){
            for(int col = 0; col < avaliableMoves[row].length; col++){
                if(tileType.indexOf("STATICATTACK") != -1 && (avaliableStaticAttacks[row][col]))
                    ((GameWorld) getWorld()).addTile(new AttackTile(row, col), row, col);   
                if(tileType.indexOf("GLOBALATTACK") != -1 && (avaliableGlobalAttacks[row][col]))
                    ((GameWorld) getWorld()).addTile(new DangerTile(row, col), row, col);   
            }
        }
    }

    /** 
     * Returns the number of targets the unit can attack
     */
    public int numTargets(){
        int counter = 0;
        for(int row = 0; row < avaliableMoves.length; row++)
            for(int col = 0; col < avaliableMoves[row].length; col++)
                if(avaliableStaticAttacks[row][col])
                    counter++;
        return counter;
    }

    /**
     * The recursive method that is used to find all the tiles that the unit can 
     * move onto and hightlight them.
     * 
     * @param  x    the x coordinate
     * @param  y    the y coordinate
     * @param  movesLeft    the number of moves avaliable
     */
    public void getGlobalAttackTiles(){
        getAttackTiles(coorX - 1, coorY, range, true);
        getAttackTiles(coorX + 1, coorY, range, true);
        getAttackTiles(coorX, coorY + 1, range, true);
        getAttackTiles(coorX, coorY - 1, range, true);

        if(canAttackAfterMove){
            for(int row = 0; row < avaliableMoves.length; row++){
                for(int col = 0; col < avaliableMoves[row].length; col++){
                    if(avaliableMoves[row][col]){
                        getAttackTiles(row + 1, col, range, true);
                        getAttackTiles(row - 1, col, range, true);
                        getAttackTiles(row, col + 1, range, true);
                        getAttackTiles(row, col - 1, range, true);
                    }
                    if(row == coorX && col == coorY){
                    }
                }
            }
        }
    }

    /**
     * Displays the danger tiles when the mouse is hovering over the unit and the 'B' key is pressed
     */
    public void displayDangerTiles(){
        boolean isUnderMouse = false;
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null){
            List<CombatUnit> objects = getWorld().getObjectsAt(mouse.getX(), mouse.getY(), CombatUnit.class);
            for (CombatUnit object : objects)
                if (object == this)
                    isUnderMouse = true;
        }

        if (isUnderMouse && /*!isBDown &&*/ Greenfoot.isKeyDown("B"))
        {
            if(!((GameWorld) getWorld()).getShowingDanger()){
                displayTiles("GLOBALATTACK");
                ((GameWorld) getWorld()).setShowingDanger(true);
            }
        }
        if (!Greenfoot.isKeyDown("B"))
        {
            ((GameWorld) getWorld()).removeDangerTiles();
            ((GameWorld) getWorld()).setShowingDanger(false);
        }
    }
}
