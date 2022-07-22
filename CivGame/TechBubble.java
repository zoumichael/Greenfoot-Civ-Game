import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Actor for the buttons in the tech tree
 * 
 * @author Michael Zou, Chengze Cai 
 * @version June 3, 2018
 */
public class TechBubble extends Actor
{
    private String techName;
    private ArrayList<String> prequisiteTechs;

    private boolean isClickable;
    private int team;
    private int cost;

    private boolean init = false;

    public TechBubble(String name, String preq, int t, int c){
        prequisiteTechs =  new ArrayList<String>();
        techName = name;
        team = t;
        cost = c;

        if(preq.length() > 0){

            // Add all the prequiste techs based on String preq, the techs are seperated by a space.
            while(preq.indexOf(" ") != -1){
                int temp = preq.indexOf(" ");
                prequisiteTechs.add(preq.substring(0, temp));
                preq = preq.substring(temp + 1);
            }
            prequisiteTechs.add(preq);
        }
        //updateImage();
    }

    /**
     * Act - do whatever the TechBubble wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!init){
            updateImage();
            init = true;
        }

        // When this is clicked.
        if(Greenfoot.mouseClicked(this)){
            whenClicked();
        }
    }    

    /**
     * Method that updates the image depending on the tech, funds, prereqs available
     */
    public void updateImage(){
        // If this tech is already unlocked for team team, change the image to techName + unlocked.
        String techsUnlocked = ((GameWorld) getWorld()).getUnlockedTechs(team - 1);
        if(techsUnlocked.indexOf(techName) != -1){
            setImage(new GreenfootImage(techName + "Bought.png"));
            isClickable = false;
            return;
        }

        if(techName.equals("AGE3A")){
            if(((GameWorld) getWorld()).getUnlockedTechs(team - 1).indexOf("AGE3B") != -1){
                setImage(new GreenfootImage(techName + "Disabled.png"));
                isClickable = false;
                return;
            }
        }
        else if(techName.equals("AGE3B")){
            if(((GameWorld) getWorld()).getUnlockedTechs(team - 1).indexOf("AGE3A") != -1){
                setImage(new GreenfootImage(techName + "Disabled.png"));
                isClickable = false;
                return;
            }
        }

        // Get the number of prequeisite techs that that team has unlocked for this tech.
        int counter = 0;
        for(String s : prequisiteTechs)
            if(((GameWorld) getWorld()).getUnlockedTechs(team - 1).indexOf(s) != -1)
                counter++;

        // If the amount is equal to the number of prerequiste techs, set this to be unlockable.     
        if(counter == prequisiteTechs.size() && ((GameWorld) getWorld()).getFunds(team - 1) >= cost){
            setImage(new GreenfootImage(techName + "Ready.png"));
            isClickable = true;
            return;
        }
        else{
            setImage(new GreenfootImage(techName + "Disabled.png"));
            isClickable = false;
        }      
    }

    public void whenClicked(){
        if(isClickable){
            // Add this deck to the avaliable techs that the player unlocked.
            ((GameWorld) getWorld()).addUnlockedTechs(team - 1, techName);

            // If the tech contains the Unlock String, also unlock the unit for the class.
            int temp = techName.indexOf("UNLOCK");
            if(temp != -1)
                ((GameWorld) getWorld()).addAvaliableUnits(team - 1, techName.substring(0, temp));

            // Reduce the amount of funds of the player on the world.
            ((GameWorld) getWorld()).setFunds(team - 1, ((GameWorld) getWorld()).getFunds(team - 1) - cost);
            ((GameWorld) getWorld()).getObjects(FundDisplay.class).get(0).updateImage(((GameWorld) getWorld()).getFunds(team - 1));

            for(TechBubble t : getWorld().getObjects(TechBubble.class))
                t.updateImage();
        }        
    }
    
    //Accessor method to get the name of the tech for that button
    public String getName(){
        return techName;
    }
}
