import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Actor for the hud at the bottom of the screen.
 * Shows the possible units that can be built.
 * 
 * @author Michael Zou, Chengze Cai
 * @version May 17, 2018
 */
public class BotHud extends Actor
{
    // Variables that will store the image 
    private final GreenfootImage baseHud = new GreenfootImage("BaseBottomHud.png");
    private final GreenfootImage buildHud = new GreenfootImage("BuildBottomHud.png");   

    // Variables that will store where the build buttons will pop up.
    private final int numIconPerRow = 5;
    private final int iconDisplacement = 50;
    private final int initialX = 50;
    private final int initialY = 680;

    private final String[] unitNames = {"PEASANT", "MILITIA", "HORSEMAN", "ARCHER", "SWORDSMAN", 
            "KNIGHT", "TREBUCHET", "IVAN", "OGRE", "UNICORN", "WIZARD", "DRAGON"};

    private HashMap<String, Integer> costs;
    
    /**
     * Constructor for the bothud.
     */
    public BotHud(){
        setImage(baseHud);

        costs = new HashMap<String, Integer>();

        // Initialize the costs for the generic units.
        costs.put("PEASANT", 0);
        costs.put("MILITIA", 10);
        costs.put("HORSEMAN", 15);
        costs.put("ARCHER", 15);

        // Initalize the costs for the Tech units.
        costs.put("SWORDSMAN", 40);
        costs.put("TREBUCHET", 50);
        costs.put("KNIGHT", 60);
        costs.put("IVAN", 90);

        // Initalize the costs for the Magic units.
        costs.put("OGRE", 40);
        costs.put("UNICORN", 60);
        costs.put("WIZARD", 50);
        costs.put("DRAGON", 90);        
    }
    
    //Opens the build menu by updating the picture of the bottom hud and spawning build unit buttons.
    public void openBuild(){
        // Get the information regarding who's turn it is.
        int turn = ((GameWorld) getWorld()).getTurn();

        // Change the image to the open build image.
        setImage(buildHud);

        // Remove all of the BuildButtons from the class.
        ArrayList<BuildButton> buildButtons = (ArrayList<BuildButton>) getWorld().getObjects(BuildButton.class);
        for(BuildButton bb : buildButtons){
            getWorld().removeObject(bb);
        }

        short rowNumber = 0;
        short columnNumber = 0;
        for(String s : unitNames){      
            // Check if the Avaliable units of Team (turn - 1) can build this unit.
            if(((GameWorld) getWorld()).getAvaliableUnits(turn - 1).indexOf(s) != -1){

                // If the cost of the unit is less than the amount of funds that team has, change canBuild to true.
                boolean canBuild = false;
                if(((GameWorld) getWorld()).getFunds(turn - 1) >= costs.get(s))
                    canBuild = true;

                getWorld().addObject(new BuildButton(canBuild, s), initialX + columnNumber*iconDisplacement, initialY + rowNumber*iconDisplacement);

                columnNumber++;
                if(columnNumber >= numIconPerRow){
                    columnNumber = 0;
                    rowNumber++;
                }
            }
        }        
    }

    public void closeBuild(){
        // Remove all the Build Buttons in the world.
        ArrayList<BuildButton> buildButtons = (ArrayList<BuildButton>) getWorld().getObjects(BuildButton.class);
        for(BuildButton bb : buildButtons){
            getWorld().removeObject(bb);
        }

        // Return the Hud to the original image.
        setImage(baseHud);
    }

    public int getCost(String unitType){
        return costs.get(unitType);
    }
    
    //Display the stats of the selected unit
    public void openUnitStats(){
        ((GameWorld) getWorld()).addObject(new StatDisplay((((GameWorld) getWorld()).getSelectedUnit())), 120,670);
    }

    public void closeUnitStats(){
        if(getWorld().getObjects(StatDisplay.class).size() != 0)
            getWorld().removeObject(getWorld().getObjects(StatDisplay.class).get(0));
    }
}
