import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button that takes the user to the mainmenu.
 * 
 * @author Michael Zou, Chengze Cai
 * @version June 11, 2018
 */
public class MainMenuButton extends Button
{
    public MainMenuButton(){
        // Initializes and sets the Images.
        ready = new GreenfootImage("menuReady.png");
        clicked = new GreenfootImage("menuClicked.png");
        setImage(ready);

        // Initializes the animation variables.
        isClicked = false;
        animationCounter = 0;
    }
    
    /**
     * Method that overrides abstract the method in the parent class.
     */
    public void whenClicked(){
        MainMenu m = new MainMenu();
        Greenfoot.setWorld(m);
    }
}
