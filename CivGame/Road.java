import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A object that represents a tile is a road and thus has a movement cost of 1.
 * 
 * @author Jonathan Hai, Michael Zou
 * @version June 3, 2018
 */
public class Road extends ScrollActor
{
    // Variables that stores the coordinates of the road object. 
    int coorX;
    int coorY;

    // Variables that change the act method to puesdo-constructor methods since getWorld() cannot be 
    // called in the constructor.
    boolean init1 = false;
    boolean init2 = false;

    // Variables that stores whether or not there is a road to the left, right, up and down.
    boolean left;
    boolean right;
    boolean up;
    boolean down;

    /**
     * Constructor of objects of class Roads, initializes the two coordinates. 
     */
    public Road(int x, int y){
        coorX = x;
        coorY = y;
    }

    /**
     * Act - do whatever the NextTurnButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
    }

    public void updateRoadImage() 
    {
        // The the locations around the road and update the four direction variables.
        if (coorX > 0)
            left = ((GameWorld) getWorld()).getMapAt(coorX-1,coorY).getRoad();            
        else
            left = false;

        if(coorX < ((GameWorld) getWorld()).getPixelWidth()-1)              
            right = ((GameWorld) getWorld()).getMapAt(coorX+1,coorY).getRoad();
        else
            right = false;

        if(coorY > 1)
            up = ((GameWorld) getWorld()).getMapAt(coorX,coorY-1).getRoad();
        else
            up = false;

        if(coorY < ((GameWorld) getWorld()).getPixelHeight() - 3)
            down = ((GameWorld) getWorld()).getMapAt(coorX,coorY+1).getRoad();
        else
            down = false;

        // Change images based on the four locations.
        if(left && right && up && down)
            setImage(new GreenfootImage("RoadCross.png"));            
        else if (left && right && up)
            setImage(new GreenfootImage("TDownways.png"));           
        else if (left && right && down)
            setImage(new GreenfootImage("TUpways.png"));            
        else if (left && up && down)
            setImage(new GreenfootImage("TRightways.png"));            
        else if (right && up && down)
            setImage(new GreenfootImage("TLeftways.png"));            
        else if (left && right)
            setImage(new GreenfootImage("RoadTileHoriz.png"));            
        else if (up && down)
            setImage(new GreenfootImage("RoadTileVert.png"));            
        else if (left && up)
            setImage(new GreenfootImage("TurnTopLeft.png"));            
        else if (left && down)
            setImage(new GreenfootImage("TurnBottomLeft.png"));            
        else if (right && up)
            setImage(new GreenfootImage("TurnTopRight.png"));           
        else if (right && down)
            setImage(new GreenfootImage("TurnBottomRight.png"));            
        else if (left)
            setImage(new GreenfootImage("DeadRight.png"));           
        else if (right)
            setImage(new GreenfootImage("DeadLeft.png"));            
        else if (up)
            setImage(new GreenfootImage("DeadBottom.png"));           
        else if (down)
            setImage(new GreenfootImage("DeadTop.png"));            
        else
            setImage(new GreenfootImage("SingleRoad.png"));            
    } 

    public void updateArray(){
        // Update the world, changing the movement cost at that tile as well as 
        ((GameWorld) getWorld()).getMapAt(coorX,coorY).setRoad(true);
        ((GameWorld) getWorld()).getMapAt(coorX,coorY).setMoves(1);
    }
}
