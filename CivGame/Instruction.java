import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world that displays the instructions for the player. 
 * 
 * @author Michael Zou
 * @version June 11, 2018
 */
public class Instruction extends World
{
    private String[] instructionNames = new String[8];
    private int index;
    
    /**
     * Constructor for objects of class Instruction.
     */
    public Instruction(){
        super(750, 750, 1);
        
        instructionNames[0] = "Instructions1";
        instructionNames[1] = "Instructions2";
        instructionNames[2] = "Instructions3";
        instructionNames[3] = "Instructions4";
        instructionNames[4] = "Instructions5";
        instructionNames[5] = "Instructions6";
        instructionNames[6] = "Instructions7";
        instructionNames[7] = "Instructions8";
        
        index = 0;
        setBackground(new GreenfootImage(instructionNames[index] + ".png"));
        
        addButtons();       
    }
    
    /**
     * Method that adds buttons to the world.
     */
    public void addButtons(){
        addObject(new BackButton(), 150, 700);
        addObject(new NextButton(), 600, 700);
        addObject(new MainMenuButton(), 375, 700);
    }
    
    /**
     * Method that increases the index by 1 if allowed and updates the image.
     */
    public void nextPage(){
        if(index < instructionNames.length - 1)
            index++;
            
        setBackground(new GreenfootImage(instructionNames[index] + ".png"));
    }
    
    /**
     * Method that decreases the index by 1 if allowed and updates the image.
     */
    public void previousPage(){
        if(index > 0)
            index--;
            
        setBackground(new GreenfootImage(instructionNames[index] + ".png"));
    }
}
