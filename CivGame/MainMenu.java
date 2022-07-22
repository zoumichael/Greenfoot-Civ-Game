import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world that displays the main menu to the player. 
 * 
 * @author Michael Zou
 * @version June 11, 2018
 */
public class MainMenu extends World
{
    /**
     * Constructor for objects of class MainMenu.
     */
    public MainMenu()
    {    
        // Create a new world with 750x750 cells with a cell size of 1x1 pixels.
        super(750, 750, 1); 

        setBackground(new GreenfootImage("TitleIvan.png"));
        addButtons();       
    }

    /**
     * Method that adds buttons to the world.
     */
    public void addButtons(){
        addObject(new InstructionsButton(), 375, 600);
        addObject(new RandomMapButton(2), 375, 300);
        addObject(new RandomMapButton(3), 375, 375);
        addObject(new RandomMapButton(4), 375, 450);
        addObject(new CanadaMapButton(), 375, 525);
    }
}
