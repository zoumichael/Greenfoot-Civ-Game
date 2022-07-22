import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The object that moves the camera of the world around.
 * 
 * @author Michael Zou
 * @version May 8, 2018
 */
public class CameraMover extends Actor
{
    private final int cameraSpeed = 5;
    private boolean canMove;
    private boolean init = false;
    private int[][] previousLocations;
    private int playerCount;

    /**
     * Act - do whatever the GameRunner wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!init){
            playerCount = ((GameWorld) getWorld()).getPlayerCount();
            previousLocations = new int[2][playerCount];
            for(int i = 0; i<playerCount; i++){
                previousLocations[0][i] = getWorld().getWidth()/2;
                previousLocations[1][i] = getWorld().getHeight()/2;
            }
            init = true;
        }

        //--------------------------------------------------------
        //SECTION THAT MANAGES THE SCROLLING PORTION OF THE WORLD.
        //--------------------------------------------------------

        if (Greenfoot.isKeyDown("down"))
            ((ScrollWorld) getWorld()).setCameraLocation(((ScrollWorld) getWorld()).getCameraX(), ((ScrollWorld) getWorld()).getCameraY() + cameraSpeed);

        if (Greenfoot.isKeyDown("up"))
            ((ScrollWorld) getWorld()).setCameraLocation(((ScrollWorld) getWorld()).getCameraX(), ((ScrollWorld) getWorld()).getCameraY() - cameraSpeed);

        if (Greenfoot.isKeyDown("left")) 
            ((ScrollWorld) getWorld()).setCameraLocation(((ScrollWorld) getWorld()).getCameraX() - cameraSpeed, ((ScrollWorld) getWorld()).getCameraY());

        if (Greenfoot.isKeyDown("right")) 
            ((ScrollWorld) getWorld()).setCameraLocation(((ScrollWorld) getWorld()).getCameraX() + cameraSpeed, ((ScrollWorld) getWorld()).getCameraY());
    }    
    
    /**
     * Sets the Camera position for each individual player's previous camera position
     */
    public void nextTurnPanning(){
        int turn = ((GameWorld) getWorld()).getTurn();

        if(turn != 1){            
            previousLocations[0][turn - 2] = ((ScrollWorld) getWorld()).getCameraX();
            previousLocations[1][turn - 2] = ((ScrollWorld) getWorld()).getCameraY();
        }
        else{
            previousLocations[0][playerCount - 1] = ((ScrollWorld) getWorld()).getCameraX();
            previousLocations[1][playerCount - 1] = ((ScrollWorld) getWorld()).getCameraY();
        }

        ((ScrollWorld) getWorld()).setCameraLocation(previousLocations[0][turn - 1], previousLocations[1][turn - 1]);
    }
}
