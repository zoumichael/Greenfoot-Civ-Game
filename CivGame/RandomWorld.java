import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A world with a randomly generated biome. Has preset roads and utilises random generation from gameworld.
 * 
 * @author Michael Zou
 * @version June 11, 2018
 */
public class RandomWorld extends GameWorld
{
    /**
     * Constructor for objects of class RandomWorld.
     *  
     */
    public RandomWorld(int numP)
    {
        super();
        numPlayers = numP;

        if(numP == 2){
            // Add Buildings for Player 1.
            addBuilding(new Factory(2, 3, 1, 10), 2, 3); 
            addBuilding(new Building(4, 3, 1, 10), 4, 3);
            addBuilding(new Building(2, 5, 1, 10), 2, 5);
            addBuilding(new CashCow(4, 5, 1, 10), 4, 5);

            addObject(new Road(2, 4), 2*dimensionsOfImage + dimensionsOfImage/2, 4*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(4, 4), 4*dimensionsOfImage + dimensionsOfImage/2, 4*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(3, 3), 3*dimensionsOfImage + dimensionsOfImage/2, 3*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(3, 5), 3*dimensionsOfImage + dimensionsOfImage/2, 5*dimensionsOfImage + dimensionsOfImage/2);

            // Add Buildings for Player 2.
            addBuilding(new Factory(27, 20, 2, 10), 27, 20); 
            addBuilding(new CashCow(25, 18, 2, 10), 25, 18); 
            addBuilding(new Building(27, 18, 2, 10), 27, 18); 
            addBuilding(new Building(25, 20, 2, 10), 25, 20); 

            addObject(new Road(26, 20), 26*dimensionsOfImage + dimensionsOfImage/2, 20*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(26, 18), 26*dimensionsOfImage + dimensionsOfImage/2, 18*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(27, 19), 27*dimensionsOfImage + dimensionsOfImage/2, 19*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(25, 19), 25*dimensionsOfImage + dimensionsOfImage/2, 19*dimensionsOfImage + dimensionsOfImage/2);

            // Add Neutral Buildings.
            addBuilding(new Factory(27, 3, 0, 10), 27, 3); 
            addBuilding(new CashCow(25, 5, 0, 10), 25, 5); 
            addBuilding(new Building(27, 4, 0, 10), 27, 5); 
            addBuilding(new Building(25, 3, 0, 10), 25, 3); 

            addObject(new Road(26, 3), 26*dimensionsOfImage + dimensionsOfImage/2, 3*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(26, 5), 26*dimensionsOfImage + dimensionsOfImage/2, 5*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(27, 4), 27*dimensionsOfImage + dimensionsOfImage/2, 4*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(25, 4), 25*dimensionsOfImage + dimensionsOfImage/2, 4*dimensionsOfImage + dimensionsOfImage/2);

            addBuilding(new Factory(2, 20, 0, 10), 2, 20); 
            addBuilding(new CashCow(4, 18, 0, 10), 4, 18); 
            addBuilding(new Building(2, 18, 0, 10), 2, 18); 
            addBuilding(new Building(4, 20, 0, 10), 4, 20); 

            addObject(new Road(3, 20), 3*dimensionsOfImage + dimensionsOfImage/2, 20*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(3, 18), 3*dimensionsOfImage + dimensionsOfImage/2, 18*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(2, 19), 2*dimensionsOfImage + dimensionsOfImage/2, 19*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(4, 19), 4*dimensionsOfImage + dimensionsOfImage/2, 19*dimensionsOfImage + dimensionsOfImage/2);

            // Add Connecting Roads.
            for(int i = 5; i <= 24; i++)
                addObject(new Road(i, 4), i*dimensionsOfImage + dimensionsOfImage/2, 4*dimensionsOfImage + dimensionsOfImage/2);

            for(int i = 5; i <= 24; i++)
                addObject(new Road(i, 19), i*dimensionsOfImage + dimensionsOfImage/2, 19*dimensionsOfImage + dimensionsOfImage/2);

            for(int i = 6; i <= 17; i++)
                addObject(new Road(26, i), 26*dimensionsOfImage + dimensionsOfImage/2, i*dimensionsOfImage + dimensionsOfImage/2);

            for(int i = 6; i <= 17; i++)
                addObject(new Road(3, i), 3*dimensionsOfImage + dimensionsOfImage/2, i*dimensionsOfImage + dimensionsOfImage/2);
        }
        else if(numP == 3){
            // Add Buildings for Player 1.
            addBuilding(new Factory(2, 3, 1, 10), 2, 3); 
            addBuilding(new Building(4, 3, 1, 10), 4, 3);
            addBuilding(new Building(2, 5, 1, 10), 2, 5);
            addBuilding(new CashCow(4, 5, 1, 10), 4, 5);

            addObject(new Road(2, 4), 2*dimensionsOfImage + dimensionsOfImage/2, 4*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(4, 4), 4*dimensionsOfImage + dimensionsOfImage/2, 4*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(3, 3), 3*dimensionsOfImage + dimensionsOfImage/2, 3*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(3, 5), 3*dimensionsOfImage + dimensionsOfImage/2, 5*dimensionsOfImage + dimensionsOfImage/2);

            // Add Buildings for Player 2.
            addBuilding(new Factory(27, 20, 2, 10), 27, 20); 
            addBuilding(new CashCow(25, 18, 2, 10), 25, 18); 
            addBuilding(new Building(27, 18, 2, 10), 27, 18); 
            addBuilding(new Building(25, 20, 2, 10), 25, 20); 

            addObject(new Road(26, 20), 26*dimensionsOfImage + dimensionsOfImage/2, 20*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(26, 18), 26*dimensionsOfImage + dimensionsOfImage/2, 18*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(27, 19), 27*dimensionsOfImage + dimensionsOfImage/2, 19*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(25, 19), 25*dimensionsOfImage + dimensionsOfImage/2, 19*dimensionsOfImage + dimensionsOfImage/2);

            // Add Buildings for Player 3.
            addBuilding(new Factory(27, 3, 3, 10), 27, 3); 
            addBuilding(new CashCow(25, 5, 3, 10), 25, 5); 
            addBuilding(new Building(27, 4, 3, 10), 27, 5); 
            addBuilding(new Building(25, 3, 3, 10), 25, 3); 

            addObject(new Road(26, 3), 26*dimensionsOfImage + dimensionsOfImage/2, 3*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(26, 5), 26*dimensionsOfImage + dimensionsOfImage/2, 5*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(27, 4), 27*dimensionsOfImage + dimensionsOfImage/2, 4*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(25, 4), 25*dimensionsOfImage + dimensionsOfImage/2, 4*dimensionsOfImage + dimensionsOfImage/2);

            // Add Neutral Buildings.
            addBuilding(new Factory(2, 20, 0, 10), 2, 20); 
            addBuilding(new CashCow(4, 18, 0, 10), 4, 18); 
            addBuilding(new Building(2, 18, 0, 10), 2, 18); 
            addBuilding(new Building(4, 20, 0, 10), 4, 20); 

            addObject(new Road(3, 20), 3*dimensionsOfImage + dimensionsOfImage/2, 20*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(3, 18), 3*dimensionsOfImage + dimensionsOfImage/2, 18*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(2, 19), 2*dimensionsOfImage + dimensionsOfImage/2, 19*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(4, 19), 4*dimensionsOfImage + dimensionsOfImage/2, 19*dimensionsOfImage + dimensionsOfImage/2);

            // Add Connecting Roads.
            for(int i = 5; i <= 24; i++)
                addObject(new Road(i, 4), i*dimensionsOfImage + dimensionsOfImage/2, 4*dimensionsOfImage + dimensionsOfImage/2);

            for(int i = 5; i <= 24; i++)
                addObject(new Road(i, 19), i*dimensionsOfImage + dimensionsOfImage/2, 19*dimensionsOfImage + dimensionsOfImage/2);

            for(int i = 6; i <= 17; i++)
                addObject(new Road(26, i), 26*dimensionsOfImage + dimensionsOfImage/2, i*dimensionsOfImage + dimensionsOfImage/2);

            for(int i = 6; i <= 17; i++)
                addObject(new Road(3, i), 3*dimensionsOfImage + dimensionsOfImage/2, i*dimensionsOfImage + dimensionsOfImage/2);
        }
        else if(numP == 4){
            // Add Buildings for Player 1.
            addBuilding(new Factory(2, 3, 1, 10), 2, 3); 
            addBuilding(new Building(4, 3, 1, 10), 4, 3);
            addBuilding(new Building(2, 5, 1, 10), 2, 5);
            addBuilding(new CashCow(4, 5, 1, 10), 4, 5);

            addObject(new Road(2, 4), 2*dimensionsOfImage + dimensionsOfImage/2, 4*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(4, 4), 4*dimensionsOfImage + dimensionsOfImage/2, 4*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(3, 3), 3*dimensionsOfImage + dimensionsOfImage/2, 3*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(3, 5), 3*dimensionsOfImage + dimensionsOfImage/2, 5*dimensionsOfImage + dimensionsOfImage/2);

            // Add Buildings for Player 2.
            addBuilding(new Factory(27, 20, 2, 10), 27, 20); 
            addBuilding(new CashCow(25, 18, 2, 10), 25, 18); 
            addBuilding(new Building(27, 18, 2, 10), 27, 18); 
            addBuilding(new Building(25, 20, 2, 10), 25, 20); 

            addObject(new Road(26, 20), 26*dimensionsOfImage + dimensionsOfImage/2, 20*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(26, 18), 26*dimensionsOfImage + dimensionsOfImage/2, 18*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(27, 19), 27*dimensionsOfImage + dimensionsOfImage/2, 19*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(25, 19), 25*dimensionsOfImage + dimensionsOfImage/2, 19*dimensionsOfImage + dimensionsOfImage/2);

            // Add Buildings for Player 3.
            addBuilding(new Factory(27, 3, 3, 10), 27, 3); 
            addBuilding(new CashCow(25, 5, 3, 10), 25, 5); 
            addBuilding(new Building(27, 4, 3, 10), 27, 5); 
            addBuilding(new Building(25, 3, 3, 10), 25, 3); 

            addObject(new Road(26, 3), 26*dimensionsOfImage + dimensionsOfImage/2, 3*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(26, 5), 26*dimensionsOfImage + dimensionsOfImage/2, 5*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(27, 4), 27*dimensionsOfImage + dimensionsOfImage/2, 4*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(25, 4), 25*dimensionsOfImage + dimensionsOfImage/2, 4*dimensionsOfImage + dimensionsOfImage/2);

            // Add Buildings for Player 4.
            addBuilding(new Factory(2, 20, 4, 10), 2, 20); 
            addBuilding(new CashCow(4, 18, 4, 10), 4, 18); 
            addBuilding(new Building(2, 18, 4, 10), 2, 18); 
            addBuilding(new Building(4, 20, 4, 10), 4, 20); 

            addObject(new Road(3, 20), 3*dimensionsOfImage + dimensionsOfImage/2, 20*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(3, 18), 3*dimensionsOfImage + dimensionsOfImage/2, 18*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(2, 19), 2*dimensionsOfImage + dimensionsOfImage/2, 19*dimensionsOfImage + dimensionsOfImage/2);
            addObject(new Road(4, 19), 4*dimensionsOfImage + dimensionsOfImage/2, 19*dimensionsOfImage + dimensionsOfImage/2);

            // Add Connecting Roads.
            for(int i = 5; i <= 24; i++)
                addObject(new Road(i, 4), i*dimensionsOfImage + dimensionsOfImage/2, 4*dimensionsOfImage + dimensionsOfImage/2);

            for(int i = 5; i <= 24; i++)
                addObject(new Road(i, 19), i*dimensionsOfImage + dimensionsOfImage/2, 19*dimensionsOfImage + dimensionsOfImage/2);

            for(int i = 6; i <= 17; i++)
                addObject(new Road(26, i), 26*dimensionsOfImage + dimensionsOfImage/2, i*dimensionsOfImage + dimensionsOfImage/2);

            for(int i = 6; i <= 17; i++)
                addObject(new Road(3, i), 3*dimensionsOfImage + dimensionsOfImage/2, i*dimensionsOfImage + dimensionsOfImage/2);
        }

        for(Road road: getObjects(Road.class))
            road.updateArray();        
        for(Road road: getObjects(Road.class))
            road.updateRoadImage();        
    }
}
