import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Testing gameworld with preset spawns to test out game features, should never be called normally
 * 
 * @author Michael Zou
 * @version May 28, 2018
 */
public class TestingGrounds extends GameWorld
{

    /**
     * Constructor for objects of class TestingGrounds.
     * 
     */
    public TestingGrounds()
    {
        super();

        numPlayers = 3;

        addStartingPieces();
    }

    /**
     * Method that overwrites the addStartingPieces in the GameWorld superclass.
     * Initializes the units and buildings that spawn in the world.
     */
    public void addStartingPieces(){
        // 1v1 SUPER BASIC Testing
        /*
        //Unit teamOneTester = new Unit(1, 1, 1);
        //addUnit(teamOneTester, 1, 1);

        //Unit teamOneTester2 = new Unit(3, 3, 1);
        //addUnit(teamOneTester2, 3, 3);

        Unit teamTwoTester = new Unit(14, 11, 2);
        addUnit(teamTwoTester, 14, 11);

        Unit teamTwoTester2 = new Unit(12, 9, 2);
        addUnit(teamTwoTester2, 12, 9);

        Factory fact = new Factory(2, 2, 0, 10);
        addBuilding(fact, 2, 2);

        Factory fact2 = new Factory(13, 10, 0, 10);
        addBuilding(fact2, 13, 10);
         */

        // Unit Testing
        /*
        SpecificUnit teamTwoTester = new SpecificUnit(14, 11, 2, "PEASANT");
        addUnit(teamTwoTester, 14, 11);

        SpecificUnit teamTwoTester2 = new SpecificUnit(12, 9, 2, "PEASANT");
        addUnit(teamTwoTester2, 12, 9);

        Factory fact = new Factory(2, 2, 0, 10);
        addBuilding(fact, 2, 2);

        //Factory fact3 = new Factory(4, 4, 0, 1000);
        //addBuilding(fact3, 4, 4);

        Factory fact2 = new Factory(13, 10, 0, 10);
        addBuilding(fact2, 13, 10);

        SpecificUnit teamTwoTester3 = new SpecificUnit(3, 3, 2, "PEASANT");
        addUnit(teamTwoTester3, 3, 3);

        //Unit teamThreeTester = new Unit(2, 1, 3);
        //addUnit(teamThreeTester, 2, 1);

        //Unit teamFourTester = new Unit(2, 2, 4);
        //addUnit(teamFourTester, 2, 2);

        SpecificUnit unit = new SpecificUnit(1, 1, 1, "ARCHER");
        addUnit(unit, 1, 1);
        */

        
        //avaliableUnits[0] = "PEASANTMILITIAHORSEMANARCHER";
        //avaliableUnits[1] = "PEASANTMILITIAHORSEMANARCHER";

        //SpecificUnit teamTwoTester = new SpecificUnit(12, 10, 2, "PEASANT", unlockedTechs[1]);
        //addUnit(teamTwoTester, 12, 10);
        //SpecificUnit teamTwoTester2 = new SpecificUnit(13, 9, 2, "PEASANT", unlockedTechs[1]);
        //addUnit(teamTwoTester2, 13, 9);
        //SpecificUnit teamTwoTester4 = new SpecificUnit(14, 11, 2, "HORSEMAN", unlockedTechs[1]);
        //addUnit(teamTwoTester4, 14, 11);      
        //SpecificUnit teamTwoTester3 = new SpecificUnit(13, 10, 2, "MILITIA", unlockedTechs[1]);
        //addUnit(teamTwoTester3, 13, 10);
        
        
        SpecificUnit unit = new SpecificUnit(1, 1, 1, "HORSEMAN", unlockedTechs[0]);
        addUnit(unit, 1, 1);
        SpecificUnit unit2 = new SpecificUnit(2, 2, 1, "MILITIA", unlockedTechs[0]);
        addUnit(unit2, 2, 2);
        SpecificUnit unit3 = new SpecificUnit(2, 3, 1, "PEASANT", unlockedTechs[0]);
        addUnit(unit3, 2, 3);
        SpecificUnit unit4 = new SpecificUnit(3, 2, 1, "PEASANT", unlockedTechs[0]);
        addUnit(unit4, 3, 2);
        
        SpecificUnit unit5 = new SpecificUnit(5, 5, 3, "DRAGON", unlockedTechs[2]);
        addUnit(unit5, 5, 5);
        
        
        
        Factory fact = new Factory(2, 2, 1, 10);
        addBuilding(fact, 2, 2);

        CashCow fact2 = new CashCow(13, 10, 2, 10);
        addBuilding(fact2, 13, 10);
        
        Building fact3 = new Building(13, 2, 0, 10);
        addBuilding(fact3, 13, 2);
        
        Factory fact4 = new Factory(2, 10, 0, 10);
        addBuilding(fact4, 2, 10);
        
    }
}
