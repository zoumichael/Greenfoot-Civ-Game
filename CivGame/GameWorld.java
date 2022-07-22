import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.*;
import java.io.*;

/**
 * The World 
 * 
 * @author Chengze Cai, Michael Zou
 * @version May 31, 2018
 */
public class GameWorld extends ScrollWorld
{
    // The number of cells in the world.
    protected final int width;
    protected final int height;

    // The dimensions of the square cell.
    protected final int dimensionsOfImage = 48;

    // Variable that stores the strength of the spreading roads.
    protected int roadSpreadStrength = 15;

    // The variable that stores all of the tiles in the world.
    Tile[][] map;

    // The variables that stores the number of players and whose turn the game is on.
    protected int numPlayers = 4;
    protected int turn = 1;

    // The array that stores all of the incomes of the players, the size of the array is dependent on the number of players.
    protected int[] funds;

    // The ArrayList that stores all of the avaliable units that each player can build.
    protected String[] avaliableUnits;
    protected String[] unlockedTechs;

    // The array that stores whether a player is out or not.
    protected boolean[] playerOut;

    // The current selected Unit and Building.
    protected Unit selectedUnit;
    protected Building selectedBuilding;
    protected boolean isShowingDanger = false;

    /**
     * Default constructor for objects of class MyWorld. 
     * Initializes the 25x25 map and creates a random biomne and roads.
     * Starts off with 4 players, and starts on turn 1.
     */
    public GameWorld(){    
        super(750, 750, 1, 1440, 1248); 

        // Initialize the width and height of the map, as well as creating it.
        width = 30;
        height = 26;
        map = new Tile[width][height];

        // Spawn Terrain, Biomes and Roads.
        spawnBiomes();
        spawnRoads();

        // Initialize incomes.
        funds = new int[numPlayers];

        // Initialize Avaliable Units to build.
        avaliableUnits = new String[numPlayers];
        unlockedTechs = new String[numPlayers];
        playerOut = new boolean[numPlayers];
        for(int i = 0; i<avaliableUnits.length; i++){
            avaliableUnits[i] = "PEASANT";
            unlockedTechs[i] = "AGE0";
            playerOut[i] = false;
        }

        // Prepares the rest of the world, Cameras, Huds, and Turn Indicator.
        prepare();
    }

    /**
     * Constructor of GameWorld that reads in a txt file to construct the world. 
     */
    public GameWorld(String filename){
        super(750, 750, 1, 1440, 1248); 

        // Initialize the width and height of the map, as well as creating it.
        width = 30;
        height = 26;
        map = new Tile[width][height];

        Scanner input = getScanner(filename + ".txt");
        for(int row = 0; row < height; row++){
            String line = input.nextLine();
            for(int col = 0; col < width; col++){
                String letter = line.substring(col, col+1);
                if(letter.equals("W"))
                    map[col][row] = new Tile(7, col, row, true);
                else if(letter.equals("S"))
                    map[col][row] = new Tile(2, col, row, true);
                else if(letter.equals("G"))
                    map[col][row] = new Tile(4, col, row, true);
                else if(letter.equals("M"))
                    map[col][row] = new Tile(6, col, row, true);
                addObject(map[col][row], col*dimensionsOfImage + dimensionsOfImage/2, row*dimensionsOfImage + dimensionsOfImage/2);
            }            
        }

        Scanner inputRoads = getScanner(filename + "Road.txt");
        for(int row = 0; row < height; row++){
            String line = inputRoads.nextLine();
            for(int col = 0; col < width; col++){
                String letter = line.substring(col, col+1);
                if(letter.equals("R")){
                    Road road = new Road(col,row);
                    addObject(road, col*dimensionsOfImage + dimensionsOfImage/2, row*dimensionsOfImage + dimensionsOfImage/2);
                }
            }
        }

        // Initialize incomes.
        funds = new int[numPlayers];

        // Initialize Avaliable Units to build.
        avaliableUnits = new String[numPlayers];
        unlockedTechs = new String[numPlayers];
        playerOut = new boolean[numPlayers];
        for(int i = 0; i<avaliableUnits.length; i++){
            avaliableUnits[i] = "PEASANT";
            unlockedTechs[i] = "AGE0";
            playerOut[i] = false;
        }

        // Prepares the rest of the world, Cameras, Huds, and Turn Indicator.
        prepare(); 
    }

    /**
     * Method that adds the actors to the world at the start of the world. 
     * Specifically the objects related to the Hud, as well as the paint order.
     */
    public void prepare(){
        // Add the Camera Mover to the world.
        CameraMover camera = new CameraMover();
        addObject(camera, 0, 0);

        // Add the objects that will appear on the top of the hud.
        TopHud topHud = new TopHud();
        TurnIndicator turnInt = new TurnIndicator();
        FundDisplay fundDis = new FundDisplay();

        addObject(topHud, 375, 24);        
        addObject(turnInt, 45, 22);        
        addObject(fundDis, 550, 22);

        // Add the objects that will appear on the bottom of the hud.
        BotHud botHud = new BotHud();
        NextTurnButton ntb = new NextTurnButton();
        TechButton tb = new TechButton();

        addObject(botHud, 375, 678);        
        addObject(ntb, 620, 715);
        addObject(tb, 620, 655);

        for(Road road: getObjects(Road.class))
            road.updateArray();       
        for(Road road: getObjects(Road.class))
            road.updateRoadImage();        

        // Set the appropriate Paint Order.
        setPaintOrder(MainMenuButton.class, VictoryMessage.class, VictoryBackdrop.class, FundDisplay.class, TechTreeBackButton.class, TechCost.class, TechBubble.class, TechTreeBackground.class,     // Objects that are on the Tech Tree, highest pirority.
            TurnIndicator.class, TopHud.class, NextTurnButton.class, TechButton.class, StatDisplay.class, BuildButton.class, BotHud.class,  // Objects related to Hud.
            DangerTile.class, AttackTile.class, SelectTile.class, // Tiles that are displayed.
            UnitHealth.class, Dimmer.class, Unit.class,       // Objects that are related to Units, 
            Building.class, Road.class, Tile.class,       // Objects that are part of the background, buildings, and terrain.
            CameraMover.class);     // The background 
    }

    /**
     * Method that Spawns the Biomes. 
     */
    public void spawnBiomes(){
        //Randomly spawn initial biome areas
        Random rand = new Random();

        // For each tile type, randomly select a spawning area to grow the biome from, ensuring that the initial spot has free space around it.
        for(int i = 2; i < 7; i+= 2){
            int rowInitial = rand.nextInt(width-2)+1;
            int colInitial = rand.nextInt(height-2)+1;
            while(map[rowInitial][colInitial] != null || map[rowInitial -1][colInitial] != null || map[rowInitial + 1][colInitial] != null || map[rowInitial][colInitial - 1] != null || map[rowInitial][colInitial + 1] != null){
                rowInitial = rand.nextInt(width-2)+1;
                colInitial = rand.nextInt(height-2)+1;
            }   
            map[rowInitial][colInitial] = new Tile(i, rowInitial, colInitial, true);
            addObject(map[rowInitial][colInitial], rowInitial*dimensionsOfImage + dimensionsOfImage/2, colInitial*dimensionsOfImage + dimensionsOfImage/2);
            biomeCheckStart(rowInitial, colInitial, (int) 60/i, i);
        }

        // Fill in the gaps with tile type 4.
        for(int row = 0; row<width; row++)
            for(int col = 0; col<height; col++)
                if(map[row][col] == null){
                    map[row][col] = new Tile(4, row, col, true);
                    addObject(map[row][col], row*dimensionsOfImage + dimensionsOfImage/2, col*dimensionsOfImage + dimensionsOfImage/2);
                }       
    }

    /**
     * Method that start the recursive check for biome checking, spreading to the left, right, up and down.
     */
    public void biomeCheckStart(int x, int y, int spreadLeft, int tile){
        biomeCheck(x - 1, y, spreadLeft, tile); 
        biomeCheck(x + 1, y, spreadLeft, tile);
        biomeCheck(x, y - 1, spreadLeft, tile);
        biomeCheck(x, y + 1, spreadLeft, tile);
    }

    /**
     * Method that recursively generate tiles for biome-like generation.
     */
    public void biomeCheck(int x, int y, int spreadLeft, int tile){
        Random rand = new Random();

        //If bounds of map are reached, stop.
        if(x < 0 || y < 0 || x >= width || y >= height)
            return;

        //if no more 'spreading strength' is left (aka tiles left to spawn), stop.
        if(spreadLeft <= 0)
            return;

        //if there is a tile currently there, stop.
        if(map[x][y] != null)
            return;    

        //Spawn in a tile.
        map[x][y] = new Tile(tile, x, y, true);
        addObject(map[x][y], x*dimensionsOfImage + dimensionsOfImage/2, y*dimensionsOfImage + dimensionsOfImage/2);

        biomeCheck(x - 1, y, spreadLeft - (rand.nextInt(3)), tile);
        biomeCheck(x + 1, y, spreadLeft - (rand.nextInt(3)), tile);
        biomeCheck(x, y - 1, spreadLeft - (rand.nextInt(3)), tile);
        biomeCheck(x, y + 1, spreadLeft - (rand.nextInt(3)), tile);
    }   

    /**
     * Generate roads given a random starting position within the middle area of the map.
     */
    public void spawnRoads(){
        Random rand = new Random();
        int rowInitial = rand.nextInt(height/2)+(height/4);
        int colInitial = rand.nextInt(width/2)+(width/4);
        roadCheckStart(colInitial, rowInitial, roadSpreadStrength);
    }

    /**
     * Begin the recursive loop to spawn roads.
     */
    public void roadCheckStart(int x, int y, int spreadLeft){
        Random rand = new Random();
        int side = rand.nextInt(4);
        int lengthOfRoadSegment = 3;
        Factory fact = new Factory(x, y, 0, 10);
        addBuilding(fact, x, y);
        if(side == 0){
            roadCheck(x, y + 1, spreadLeft - (rand.nextInt(3)), side, lengthOfRoadSegment);
        }else if(side == 1){
            roadCheck(x + 1, y, spreadLeft - (rand.nextInt(3)), side, lengthOfRoadSegment);
        }else if(side == 2){
            roadCheck(x, y - 1, spreadLeft - (rand.nextInt(3)), side, lengthOfRoadSegment);
        }else if(side == 3){
            roadCheck(x - 1, y, spreadLeft - (rand.nextInt(3)), side, lengthOfRoadSegment);
        }
    }

    /**
     * Recursively spawn the roads.
     */
    public void roadCheck(int x, int y, int spreadLeft, int side, int currentLength){
        Random rand = new Random();
        //If bounds of the map (including the water portions precoded in) are reached, don't spawn
        if(x < 0 || y < 1 || x >= width || y >= height - 3){
            if(x < 0){
                x = 0;
            }else if(x >= width){
                x = width - 1;
            }
            if(y < 1){
                y = 1;
            }else if(y >= height - 3){
                y = height - 4;
            }
            int building = rand.nextInt(2);
            if(building == 0){
                Factory fact = new Factory(x, y, 0, 10);
                addBuilding(fact, x, y);
            }else if(building == 1){
                CashCow cash = new CashCow(x, y, 0, 10);
                addBuilding(cash, x, y);
            }
            return;
        }

        //if no more tiles strength, stop
        if(spreadLeft <= 0){
            int building = rand.nextInt(2);
            if(building == 0){
                Factory fact = new Factory(x, y, 0, 10);
                addBuilding(fact, x, y);
            }else if(building == 1){
                CashCow cash = new CashCow(x, y, 0, 10);
                addBuilding(cash, x, y);
            }
            return;
        }

        //normal length of a road
        int lengthOfRoadSegment = 3;

        if(!map[x][y].getRoad()){
            Road road = new Road(x,y);
            addObject(road, x*dimensionsOfImage + dimensionsOfImage/2, y*dimensionsOfImage + dimensionsOfImage/2);
        }

        int newSide = rand.nextInt(4);
        while(newSide == ((side + 2)%4)){
            newSide = rand.nextInt(4);
        }

        if(currentLength <= 0){
            if(newSide == 0){
                roadCheck(x, y + 1, spreadLeft - (rand.nextInt(3)), newSide, lengthOfRoadSegment);
            }else if(newSide == 1){
                roadCheck(x + 1, y, spreadLeft - (rand.nextInt(3)), newSide, lengthOfRoadSegment);
            }else if(newSide == 2){
                roadCheck(x, y - 1, spreadLeft - (rand.nextInt(3)), newSide, lengthOfRoadSegment);
            }else if(newSide == 3){
                roadCheck(x - 1, y, spreadLeft - (rand.nextInt(3)), newSide, lengthOfRoadSegment);
            }
            int newSideTwo = rand.nextInt(4);
            if(newSideTwo != newSide && newSideTwo != ((side + 2)%4)){
                if(newSideTwo == 0){
                    roadCheck(x, y + 1, spreadLeft - (rand.nextInt(3)), newSideTwo, lengthOfRoadSegment);
                }else if(newSideTwo == 1){
                    roadCheck(x + 1, y, spreadLeft - (rand.nextInt(3)), newSideTwo, lengthOfRoadSegment);
                }else if(newSideTwo == 2){
                    roadCheck(x, y - 1, spreadLeft - (rand.nextInt(3)), newSideTwo, lengthOfRoadSegment);
                }else if(newSideTwo == 3){
                    roadCheck(x - 1, y, spreadLeft - (rand.nextInt(3)), newSideTwo, lengthOfRoadSegment);
                }
            }
        }else{
            currentLength = currentLength - rand.nextInt(2);
            if(side == 0){
                roadCheck(x, y + 1, spreadLeft - (rand.nextInt(3)), side, currentLength);
            }else if(side == 1){
                roadCheck(x + 1, y, spreadLeft - (rand.nextInt(3)), side, currentLength);
            }else if(side == 2){
                roadCheck(x, y - 1, spreadLeft - (rand.nextInt(3)), side, currentLength);
            }else if(side == 3){
                roadCheck(x - 1, y, spreadLeft - (rand.nextInt(3)), side, currentLength);
            }
        }
    }

    /**
     * Method that adds a building to the World based on pixel coordinates, updating the parameters at that location.
     */
    public void addBuilding(Building b, int x, int y){
        super.addObject(b, x*dimensionsOfImage + dimensionsOfImage/2, y*dimensionsOfImage + dimensionsOfImage/2);

        Road road = new Road(x,y);
        addObject(road, x*dimensionsOfImage + dimensionsOfImage/2, y*dimensionsOfImage + dimensionsOfImage/2);

        map[x][y].setBuilding(b);
    }

    /**
     * Method that adds a unit to the World based on pixel coordinates, updating the parameters at that location.
     */
    public void addUnit(Unit u, int x, int y){
        // Add the object to the world based on new coordinates.
        super.addObject(u, x*dimensionsOfImage + dimensionsOfImage/2, y*dimensionsOfImage + dimensionsOfImage/2);

        // Update the tile at that point to store the new unit.
        map[x][y].setOccupation(u.getTeam());
        map[x][y].setUnit(u);
        //resetPossibilties();
    }

    /** 
     * Method that adds a Tile to the World based on pixel coordinates.
     */
    public void addTile(Actor a, int x, int y){
        if(a instanceof SelectTile){
            if(!map[x][y].checkSelectTile())
                super.addObject(a, x*dimensionsOfImage + dimensionsOfImage/2, y*dimensionsOfImage + dimensionsOfImage/2);
        }
        else if(a instanceof AttackTile){
            if(!map[x][y].checkAttackTile())
                super.addObject(a, x*dimensionsOfImage + dimensionsOfImage/2, y*dimensionsOfImage + dimensionsOfImage/2);
        }
        else{
            super.addObject(a, x*dimensionsOfImage + dimensionsOfImage/2, y*dimensionsOfImage + dimensionsOfImage/2);
        }
    }

    /**
     * Method that moves the selected object, as well as handle the events that takes place after.
     */
    public void moveSelectedObject(int x, int y){
        ArrayList<Unit> units = (ArrayList<Unit>) getObjects(Unit.class);
        for(Unit u : units){
            if(u.getSelected()){
                // Change the occupation and currentUnit variables of the tile at that point to be unoccupied.
                map[u.getPixelX()][u.getPixelY()].setOccupation(0);
                map[u.getPixelX()][u.getPixelY()].setUnit(null);

                // Move the object to the appropriate location.
                u.setGlobalLocation(x*dimensionsOfImage + dimensionsOfImage/2, y*dimensionsOfImage + dimensionsOfImage/2);

                // Update the loaction coordinates of the object.
                u.setPixelX(x);
                u.setPixelY(y);

                // Change the occupation and currentUnit variables of the tile at the new point to be occupied.
                map[u.getPixelX()][u.getPixelY()].setOccupation(u.getTeam());
                map[u.getPixelX()][u.getPixelY()].setUnit(u);

                // Move the Unit health bar.
                u.getHealthIndicator().updateLocation();

                // If the unit is on a building, capture that building.
                if(map[u.getPixelX()][u.getPixelY()].getBuilding() != null)
                    map[u.getPixelX()][u.getPixelY()].getBuilding().capture(u.getTeam());

                // Change the canMove variable in the unit to be false.
                u.setCanMove(false);

                // Remove all AttackTiles
                removeAttackTiles();
                removeSelectTiles();

                // Restore everything else to originality.
                deselectAllUnits();

                // Clear possibilties.
                resetPossibilties();

                // Check if the unit can attack after moving, if it can do the following.
                if(u instanceof CombatUnit){
                    if(u.getAttackAfterMove()){
                        // Display the targets the selected unit can hit. 
                        // If the number is 0, this ends the units turn.
                        u.displayTiles("STATICATTACK");
                        int numTargets = ((CombatUnit) u).numTargets();
                        if(numTargets == 0){
                            u.setCanAttack(false);
                            u.setCanAct(false);
                            if(!u.getDimmerAdded()){
                                addTile(new Dimmer(), u.getPixelX(), u.getPixelY());
                                u.setDimmerAdded(true);
                            }
                        }
                        u.setSelected(true);
                    }
                    // If it can't set canAttack and canAct to false.
                    else{
                        u.setCanAttack(false);
                        u.setCanAct(false);
                        if(!u.getDimmerAdded()){
                            addTile(new Dimmer(), u.getPixelX(), u.getPixelY());
                            u.setDimmerAdded(true);
                        }
                    }
                }
            }
        }
    }

    /**
     * Method that resets the possiblities of all units. Their attacks, moves, etc. 
     * Since possible moves is stored in an array and is not updated, this method is called when a unit is removed.
     */
    public void resetPossibilties(){
        ArrayList<Unit> units = (ArrayList<Unit>) getObjects(Unit.class);
        for(Unit u : units)
            u.clearPossibilities();
    }

    /**
     * Method that initiates the fight between between two units. After fighting it checks and does a lot of things including:
     *      * Having both Units take damage.
     *      * Updating the parameters of the Attacking Unit, such as "CanAttack", "CanMove", etc.
     *      * Removing either unit from the world if needed.
     *      * Removing all attack, select tiles from the world and deselecting the current attacking unit.
     *      
     * @param  xLoc    the x coordinate that the selected unit is attacking.
     * @param  yLoc    the y coordinate that the selected unit is attacking.
     */
    public void fight(int xLoc, int yLoc){
        ArrayList<CombatUnit> units = (ArrayList<CombatUnit>) getObjects(CombatUnit.class);
        for(CombatUnit attackingUnit : units){
            // The attacking Unit is the one that is currently selected.
            if(attackingUnit.getSelected()){
                // The defending Unit will the the one at the coordinates where the attackTile is clicked.
                Unit defendingUnit = map[xLoc][yLoc].getUnit();

                // Have both the defending unit take damage.
                defendingUnit.takeDamage(attackingUnit, "NORMAL");

                // The attacking unit will only take damage if (The defending unit is an combat unit) AND (The attacking unit is not ranged OR The defending unit is ranged)
                if(defendingUnit instanceof CombatUnit && (!attackingUnit.getIsRanged() || defendingUnit.getIsRanged()))
                    attackingUnit.takeDamage(defendingUnit, "NORMAL");

                // Update health for attacking unit.
                if(attackingUnit.getHealth() > 0)
                    attackingUnit.getHealthIndicator().updateHealth();              
                else{
                    // Only dies if the defending unit stays alive.
                    if(defendingUnit.getHealth() > 0){
                        // If the attacking unit has health less than 0, remove the mentions at its coordinates.
                        map[attackingUnit.getPixelX()][attackingUnit.getPixelY()].setOccupation(0);
                        map[attackingUnit.getPixelX()][attackingUnit.getPixelY()].setUnit(null);
                    }
                    // If the attacking unit is suppose to die but the defending unit dies, the attacking unit is reduced to 1 health.
                    else{
                        attackingUnit.setHealth(1);
                        attackingUnit.getHealthIndicator().updateHealth();
                    }
                }

                // Update health for defending unit.
                if(defendingUnit.getHealth() > 0)
                    defendingUnit.getHealthIndicator().updateHealth();                                    
                else{
                    map[defendingUnit.getPixelX()][defendingUnit.getPixelY()].setOccupation(0);
                    map[defendingUnit.getPixelX()][defendingUnit.getPixelY()].setUnit(null);
                    resetPossibilties();
                    checkTargetsAfterUnitDeath();
                }

                // The attacking unit can no longer attack since it just attacked.
                attackingUnit.setCanAttack(false);

                // If attacking unit cannot move after attacking, its turn is over.
                if(!attackingUnit.getMoveAfterAttack()){
                    attackingUnit.setCanMove(false);
                    attackingUnit.setCanAct(false); 
                    // Only create a dimmer if both units are still alive if one die it should be checked in the other method.
                    if(attackingUnit.getHealth() > 0 && defendingUnit.getHealth() > 0){
                        if(!attackingUnit.getDimmerAdded()){
                            addTile(new Dimmer(), attackingUnit.getPixelX(), attackingUnit.getPixelY());
                            attackingUnit.setDimmerAdded(true);
                        }
                    }

                    // Deselect all (mostly this) unit.
                    deselectAllUnits();
                }              

                // Remove all AttackTiles.
                removeAttackTiles();
                removeSelectTiles();

                // Clear possibilties.
                resetPossibilties();
            }
        }
    }

    /**
     * Method that builds a unit at a specific factory.
     * 
     * @param  unitType    the type of unit that is produced.
     */
    public void build(String unitType){
        // Add the unit to the world with the specific properties.
        SpecificUnit unit = new SpecificUnit(selectedBuilding.getPixelX(), selectedBuilding.getPixelY(), turn, unitType, unlockedTechs[turn - 1]);
        addUnit(unit, selectedBuilding.getPixelX(), selectedBuilding.getPixelY());

        // Remove the Unit's ability to do anything.
        unit.setCanMove(false);
        unit.setCanAttack(false);
        unit.setCanAct(false);

        // Deduct funds from funds variable.
        funds[turn - 1] -= getObjects(BotHud.class).get(0).getCost(unitType);    
        getObjects(FundDisplay.class).get(0).updateImage(funds[turn - 1]);

        // Add the dimmer at the appropriate location.
        addTile(new Dimmer(), selectedBuilding.getPixelX(), selectedBuilding.getPixelY());
        unit.setDimmerAdded(true);

        resetPossibilties();
    }

    /**
     * Method that deselects all units.
     */
    public void deselectAllUnits(){
        ArrayList<Unit> units = (ArrayList<Unit>) getObjects(Unit.class);
        for(Unit u : units)
            u.setSelected(false);   

        // Set the selected unit to be null
        selectedUnit = null;

        // Close existing Unit Stat display.
        getObjects(BotHud.class).get(0).closeUnitStats();
    }

    /**
     * Method that deselects all factories.
     */
    public void deselectAllFactories(){
        ArrayList<Factory> factories = (ArrayList<Factory>) getObjects(Factory.class);
        for(Factory f : factories)
            f.setSelected(false);   

        // Set the selected unit to be null
        selectedBuilding = null;
    }

    /**
     * Method that removes all 'Attack Tiles' from the world.
     */
    public void removeAttackTiles(){
        // Get all of the AttackTiles in the world.
        ArrayList<AttackTile> tiles = (ArrayList<AttackTile>) getObjects(AttackTile.class);
        for(AttackTile at : tiles)
            removeObject(at);
    }

    /**
     * Method that removes all 'SelectTiles' from the world.
     */
    public void removeSelectTiles(){
        // Get all of the SelectTiles in the world.
        ArrayList<SelectTile> tiles = (ArrayList<SelectTile>) getObjects(SelectTile.class);
        for(SelectTile st : tiles)
            removeObject(st);
    }

    /**
     * Method that removes all 'DangerTiles' from the world.
     */
    public void removeDangerTiles(){
        // Get all of the SelectTiles in the world.
        ArrayList<DangerTile> tiles = (ArrayList<DangerTile>) getObjects(DangerTile.class);
        for(DangerTile dt : tiles)
            removeObject(dt);
    }

    /**
     * Method that iterates through all units when a unit is clicked to make them unable to
     * attack if the unit killed was their only target.
     */
    public void checkTargetsAfterUnitDeath(){
        ArrayList<CombatUnit> units = (ArrayList<CombatUnit>) getObjects(CombatUnit.class);
        for(CombatUnit u : units){
            if(u.getTeam() == turn){
                int numTargets = u.numTargets();
                // If it can act, but it can't move and there are no targets next to it, it can't act anymore.
                if(numTargets == 0 && !u.getCanMove() && u.getCanAct()){
                    u.setCanAttack(false);
                    u.setCanAct(false);
                    if(!u.getDimmerAdded()){
                        addTile(new Dimmer(), u.getPixelX(), u.getPixelY());
                        u.setDimmerAdded(true);
                    }
                }
            }
        }
    }

    /**
     * Method that displays the victory when one side dominated the entire map
     */
    public void victory(int team){
        addObject(new VictoryBackdrop(), 375, 375);
        addObject(new VictoryMessage(team), 375, 200);
        addObject(new MainMenuButton(), 375, 550);
    }

    /**
     * Method that starts the next turn, resetting all the units movability to true.
     */
    public void nextTurn(){
        // GET ALL THE UNITS.
        ArrayList<Unit> units = (ArrayList<Unit>) getObjects(Unit.class);
        ArrayList<Factory> factory = (ArrayList<Factory>) getObjects(Factory.class);
        ArrayList<Building> building = (ArrayList<Building>) getObjects(Building.class);

        // HEAL ALL THE UNITS THAT HAVEN'T MOVED ALL ALL THIS TURN.
        for(Unit u : units)
            if(u.getTeam() == turn)
                if(u.getCanAttack() && u.getCanMove()){
                    u.setHealth(u.getHealth() + u.getRegen());
                    u.getHealthIndicator().updateHealth();
                }

        while(true){
            turn++;
            if(turn > numPlayers)
                turn = 1;

            int numOwned = 0;
            for(Unit u : units)
                if(u.getTeam() == turn)
                    numOwned++;
            for(Factory f : factory)
                if(f.getTeam() == turn)
                    numOwned++;

            if(numOwned == 0){
                playerOut[turn - 1] = true;
                for(Building b : building)
                    if(b.getTeam() == turn)
                        b.capture(0);
            }

            if(!playerOut[turn - 1])
                break;

            int counter = 0;
            int winner = 0;
            for(int i = 0; i<numPlayers; i++){
                if(!playerOut[i]){
                    counter++;
                    winner = i;
                }
            }

            if(counter <= 1)
                victory(winner + 1);
        }
        
        getObjects(CameraMover.class).get(0).nextTurnPanning();

        // FIND ALL OF THE UNITS THAT BELONG TO THAT TEAM AND RESET THE CAN-X VARIABLES TO TRUE.
        for(Unit u : units){
            if(u.getTeam() == turn){
                u.setCanMove(true);
                u.setCanAttack(true);
                u.setCanAct(true);
                u.setDimmerAdded(false);
            }
        }

        // DESELECT EVERTHING IN THE WORLD.
        deselectAllUnits();
        deselectAllFactories();
        removeAttackTiles();
        removeSelectTiles();

        // ADD INCOME TO THE TEAM.
        ArrayList<Building> buildings = (ArrayList<Building>) getObjects(Building.class);
        for(Building b: buildings){
            if(b.getTeam() == turn){               
                if(unlockedTechs[turn - 1].indexOf("RESOURCEBOOST3") != -1)
                    funds[turn - 1] += b.getIncome() * 2.0;
                else if(unlockedTechs[turn - 1].indexOf("RESOURCEBOOST2") != -1)
                    funds[turn - 1] += b.getIncome() * 1.5;
                else if(unlockedTechs[turn - 1].indexOf("RESOURCEBOOST1") != -1)
                    funds[turn - 1] += b.getIncome() * 1.1;
                else
                    funds[turn - 1] += b.getIncome();
            }
        }
        getObjects(FundDisplay.class).get(0).updateImage(funds[turn - 1]);

        // CLOSE THE BUILD WINDOW.
        getObjects(BotHud.class).get(0).closeBuild();

        // REMOVE ALL THE DIMMERS IN THIS WORLD.
        ArrayList<Dimmer> dimmers = (ArrayList<Dimmer>) getObjects(Dimmer.class);
        for(Dimmer d : dimmers)
            removeObject(d);
    }

    /**
     * Opens a text file inside the package folder and returns a scanner to read it. Works for text files inside jar files.
     * 
     * @param name The name of the text file
     * @return A Scanner object that you can use to read the contents of the text file.
     */
    public Scanner getScanner(String filename){
        InputStream myFile = getClass().getResourceAsStream(filename);
        if(myFile != null){
            return new Scanner(myFile);
        }
        return null;
    }

    //------------------------------------------------------
    //EVERYTHING BELOW THIS ARE ACCESSOR AND MUTATOR METHODS
    //------------------------------------------------------

    /**
     * Mutator method for the a specific cell in the 'map' variable.
     * 
     * @param  m    the new value of the cell you want to change in map
     * @param  r    the row in the array
     * @param  c    the column in the array
     */
    public void setMapAt(Tile m, int r, int c){map[r][c] = m;}

    /**
     * Accessor method for a specific cell in the 'map' variable.
     * 
     * @param  r    the row in the array
     * @param  c    the column in the array
     * @return     the numMoves variable
     */
    public Tile getMapAt(int r, int c){return map[r][c];}

    /**
     * Accessor method for the 'height' variable.
     * 
     * @return     the height variable
     */
    public int getPixelHeight(){return height;}

    /**
     * Accessor method for the 'width' variable.
     * 
     * @return     the width variable
     */
    public int getPixelWidth(){return width;}

    /**
     * Accessor method for the 'dimensionsOfImage' variable.
     * 
     * @return     the dimensionsOfImage variable
     */
    public int getCellDimension(){return dimensionsOfImage;}

    /**
     * Accessor method for the 'numPlayers' variable.
     * 
     * @return     the numPlayers variable
     */
    public int getPlayerCount(){return numPlayers;}

    /**
     * Accessor method for the 'turn' variable.
     * 
     * @return     the turn variable
     */
    public int getTurn(){return turn;}

    /**
     * Mutator method for the 'selectedUnit' variable.
     * 
     * @param  u    the reference to the new Unit
     */
    public void setSelectedUnit(Unit u){selectedUnit = u;}

    /**
     * Accessor method for the 'selectedUnit' variable.
     * 
     * @return     the Unit being referenced by selectedUnit
     */
    public Unit getSelectedUnit(){return selectedUnit;}

    /**
     * Mutator method for the 'selectedBuilding' variable.
     * 
     * @param  b    the reference to the new Building
     */
    public void setSelectedBuilding(Building b){selectedBuilding = b;}

    /**
     * Accessor method for the 'selectedBuilding' variable.
     * 
     * @return     the Building being referenced by selectedBuilding
     */
    public Building getSelectedBuilding(){return selectedBuilding;}

    /**
     * Accessor method for the 'avaliableUnits' variable at the ith index.
     * 
     * @param  i    the index of the array
     * @return     the String value at the ith index of avaliableUnits
     */
    public String getAvaliableUnits(int i){return avaliableUnits[i];}

    /**
     * Mutator method for the 'avaliableUnits' variable at the ith index.
     * 
     * @param  i    the index of the array
     * @param  units    the new String at the index
     */
    public void setAvaliableUnits(int i, String units){avaliableUnits[i] = units;}

    /**
     * Special mutator method for the 'avaliableUnits' variable at the ith index.
     * Adds a unit instead of changing it altogether.
     * 
     * @param  i    the index of the array
     * @param  units    the new String you want to add to the variable
     */
    public void addAvaliableUnits(int i, String unit){avaliableUnits[i] = avaliableUnits[i] + unit;}

    /**
     * Accessor method for the 'avaliableTechs' variable at the ith index.
     * 
     * @param  i    the index of the array
     * @return     the String value at the ith index of avaliableTechs
     */
    public String getUnlockedTechs(int i){return unlockedTechs[i];}

    /**
     * Mutator method for the 'avaliableTechs' variable at the ith index.
     * 
     * @param  i    the index of the array
     * @param  units    the new String at the index
     */
    public void setUnlockedTechs(int i, String techs){unlockedTechs[i] = techs;}

    /**
     * Special mutator method for the 'avaliableTech' variable at the ith index.
     * Adds a tech instead of changing it altogether.
     * 
     * @param  i    the index of the array
     * @param  units    the new String you want to add to the variable
     */
    public void addUnlockedTechs(int i, String tech){unlockedTechs[i] = unlockedTechs[i] + tech;}

    /**
     * Accessor method for the 'funds' variable at the ith index.
     * 
     * @param  i    the index of the array
     * @return     the int value at the ith index of funds
     */
    public int getFunds(int i){return funds[i];}

    /**
     * Mutator method for the 'funds' variable at the ith index.
     * 
     * @param  i    the index of the array 
     * @param  units    the new value at the index
     */
    public void setFunds(int i, int amount){funds[i] = amount;}

    /**
     * Accessor method for the 'isShowingDanger' variable.
     * 
     * @return     the isShowingDanger variable.
     */
    public boolean getShowingDanger(){return isShowingDanger;}

    /**
     * Mutator method for the 'isShowingDanger' variable.
     * 
     * @param  sd    the new value of the isShowingDanger variable.
     */
    public void setShowingDanger(boolean sd){isShowingDanger = sd;}
}
