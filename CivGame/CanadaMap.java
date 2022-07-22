import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * World for the premade 'Canada' map
 * 
 * @author Michael Zou, Chengze Cai, Jonathan Hai 
 * @version June 2, 2018
 */
public class CanadaMap extends GameWorld
{

    /**
     * Constructor for objects of class CanadaMap.
     * 
     */
    public CanadaMap()
    {
        super("CanadaMap");

        numPlayers = 3;

        addStartingPieces();
    }

    /**
     * Method that overwrites the addStartingPieces in the GameWorld superclass.
     * Initializes the units and buildings that spawn in the world.
     */
    public void addStartingPieces(){
        // Add buildings for Republicans.
        Building portAustin = new Building(0, 7, 1, 10);
        addBuilding(portAustin, 0, 7);

        CashCow warren = new CashCow(2, 19, 1, 10);
        addBuilding(warren, 2, 19);

        Building random = new Building(10, 19, 1, 10);
        addBuilding(random, 10, 19);

        Factory detroit = new Factory(2, 20, 1, 10);
        addBuilding(detroit, 2, 20);

        Factory sarnia = new Factory(6, 15, 1, 10);
        addBuilding(sarnia, 6, 15);

        // Add buildings for Democrats.
        CashCow buffalo1 = new CashCow(29, 16, 2, 10);
        addBuilding(buffalo1, 29, 16);

        Factory buffalo2 = new Factory(28, 17, 2, 10);
        addBuilding(buffalo2, 28, 17);

        Building buffalo3 = new Building(24, 20, 2, 10);
        addBuilding(buffalo3, 24, 20);

        Building buffalo4 = new Building(20, 22, 2, 10);
        addBuilding(buffalo4, 20, 22);

        Factory niagara = new Factory(26,14, 2, 10);
        addBuilding(niagara, 26, 14);

        // Add neutral buildings.
        Factory london = new Factory(13, 15, 0, 10);
        addBuilding(london, 13, 15);
        
        Factory hamilton = new Factory(22, 12, 0, 10);
        addBuilding(hamilton, 22, 12);
        
        CashCow barne3 = new CashCow(14, 3, 0, 10);
        addBuilding(barne3, 14, 3);
        
        Building barne4 = new Building(12, 5, 0, 10);
        addBuilding(barne4, 12, 5);

        // Add buildings for Canada.
        Factory mississauga = new Factory(23, 9, 3, 10);
        addBuilding(mississauga, 23, 9);

        CashCow toronto = new CashCow(25, 9, 3, 10);
        addBuilding(toronto, 25, 9);

        Building vaughan = new Building(24, 7, 3, 10);
        addBuilding(vaughan, 24, 7);
        
        Building georgina = new Building(25, 3, 3, 10);
        addBuilding(georgina, 25, 3);

        Factory barne = new Factory(23, 2, 3, 10);
        addBuilding(barne, 23, 2);
        
        Factory barne2 = new Factory(18, 2, 3, 10);
        addBuilding(barne2, 18, 2);        
        
        //Update road images
        for(Road road: getObjects(Road.class)){
            road.updateArray();
        }
        for(Road road: getObjects(Road.class)){
            road.updateRoadImage();
        }
    }
}
