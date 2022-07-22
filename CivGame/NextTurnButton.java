import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The button that triggers the next turn event when its pressed.
 * 
 * @author Chengze Cai, Michael Zou
 * @version May 9, 2018
 */
public class NextTurnButton extends Button
{
    int currentPlayer;              // Variable that stores the current active player in the world.

    /**
     * Constructor of objects NextTurnButton. Initializes the images to be the normal and
     * the flashing button. Initializes the 
     */
    public NextTurnButton(){
        // Initializes and sets the Images.
        ready = new GreenfootImage("nextturnReady.png");
        clicked = new GreenfootImage("nextturnClicked.png");
        setImage(ready);

        // Initializes the animation variables.
        isClicked = false;
        animationCounter = 0;
    }

    /**
     * Method that overwrites the abstract method in the parent class.
     */
    public void whenClicked(){
        // Call the next turn method in the world.
        ((GameWorld) getWorld()).nextTurn();

        // Change the Turn Indicator to update whose turn it is.
        currentPlayer = ((GameWorld) getWorld()).getTurn();
        //((GameWorld) getWorld()).getObjects(TurnIndicator.class).get(0).turnIcon = new GreenfootImage("Background" + currentPlayer + "Icon.png");
        ((GameWorld) getWorld()).getObjects(TurnIndicator.class).get(0).setTurnIcon(new GreenfootImage("Background" + currentPlayer + "Icon.png"));
        ((GameWorld) getWorld()).getObjects(TurnIndicator.class).get(0).updateImage();
    }
}
