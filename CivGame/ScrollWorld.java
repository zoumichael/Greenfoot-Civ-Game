import greenfoot.*;
import java.util.ArrayList;

/**
 * A world which has the possibility to scroll
 * over a really big area. That big area is called
 * the big space, It scrolls over it using a camera.
 * The camera's location starts as x=half width, y=half height
 * of the world. It can't get outside of the big space.<p>
 * 
 * The background is always scrolling, you don't have
 * to do anything for it. Just make a background like you
 * always do in your scenarios, and this scenario will
 * scroll over it. The only note is that the method
 * {@link setBackground} is replaced by {@link 
 * setNewBackground}.<p>
 * 
 * The world supports adding ScrollActors in 2 ways:
 * <Li>
 * Adding it in the regular way, which means that it can
 * go off screen and come back on screen.
 * </Li><Li>
 * Adding a camera follower, which means that it will
 * move just as much as the camera does. So if you add it
 * to the center of the screen, it will always be there 
 * (except for when you change it yourself).
 * </Li>
 * <b>IMPORTANT NOTE:</b> if your Actor isn't a subclass of ScrollActor, it
 * will looklike it is a camera follower.
 * 
 * @author Sven van Nigtevecht
 * @version 2.1.2
 */
public abstract class ScrollWorld extends World
{
    private final int width, height, cellSize;
    private final ArrayList<ScrollActor> objects;
    private final ArrayList<ScrollActor> camFollowers;
    private final int fullWidth, fullHeight;
    
    private int camX, camY, camDir;
    
    private final GreenfootImage bigBackground, back;
    private int scrollPosX, scrollPosY;
    
    /**
     * Create a new ScrollWorld.
     * @param width The width of the scroll world in cells.
     * @param height The height of the scroll world in cells.
     * @param cellSize The size of the cells (in pixels).
     * @param fullWidth The total width of the world.
     * That means, objects can't move further than this limit.
     * @param fullHeight The total height of the world.
     * That means, objects can't move further than this limit.
     * @throws IllegalArgumentException If one of the arguments
     * is smaller or equal to 0.
     */
    public ScrollWorld(int width, int height, int cellSize, int fullWidth, int fullHeight)
    {
        super(width, height, cellSize, false);
        this.back = getBackground();
        this.width = back.getWidth();
        this.height = back.getHeight();
        this.cellSize = cellSize;
        this.fullWidth = fullWidth;
        this.fullHeight = fullHeight;
        if (fullWidth <= 0)
            throw new IllegalArgumentException("The width of the big space ("+fullWidth
            +") can't be smaller then the width of the world ("+width+")");
        if (fullHeight <= 0)
            throw new IllegalArgumentException("The height of the big space ("+fullHeight
            +") can't be smaller then the height of the world ("+height+")");
        
        objects = new ArrayList<ScrollActor>();
        camFollowers = new ArrayList<ScrollActor>();
        
        camX = getWidth() /2;
        camY = getHeight() /2;
        camDir = 0;
        
        scrollPosX = 0;
        scrollPosY = 0;
        
        bigBackground = new GreenfootImage(width+width, height+height);
        setNewBackground(back);
    }
    
    /** EXTRA METHODS: */
    
    /**
     * Sets the background of the world. This will also initialize
     * everything to make the background scroll, something the
     * normal {@link setBackground} method doesn't.
     */
    public void setNewBackground(GreenfootImage background)
    {
        bigBackground.clear();
        if (background.getWidth() == bigBackground.getWidth() &&
            background.getHeight() == bigBackground.getHeight()) {
            bigBackground.drawImage(background, 0,0);
            back.clear();
            back.drawImage(bigBackground, scrollPosX,scrollPosY);
            return;
        }
        
        bigBackground.drawImage(background, 0,0);
        bigBackground.drawImage(background, background.getWidth(),0);
        bigBackground.drawImage(background, 0,background.getHeight());
        bigBackground.drawImage(background, background.getWidth(),background.getHeight());
        
        back.clear();
        back.drawImage(bigBackground, scrollPosX,scrollPosY);
    }
    
    /** ADDING + REMOVING OBJECTS: */
    
    /**
     * Adds an object which will follow the camera.
     * The location is seen from the camera, not from the
     * big space.
     * @param cameraFollower The object that will be added to the world
     * as a camera follower.
     * @param x The x coördinate seen from the camera where the object
     * will be added.
     * @param y The y coördinate seen from the camera where the object
     * will be added.
     * @see #addObject(ScrollActor, int, int)
     */
    public void addCameraFollower(ScrollActor cameraFollower, int x, int y)
    {
        super.addObject(cameraFollower, getWidth() /2 +x, getHeight() /2 +y);
        camFollowers.add(cameraFollower);
        cameraFollower.setIsCameraFollower(true);
    }
    
    /**
     * Adds an object to the the world. If the given object
     * is a ScrollActor or a subclass of it, the x and y
     * coördinates are in the big space.
     * 
     * @param object The object that will be added to the world.
     * @param x The x coördinate in the world where the object
     * will be added.
     * @param y The y coördinate in the world where the object
     * will be added.
     * @see #addCameraFollower(ScrollActor, int, int)
     */
    public void addObject(Actor object, int x, int y)
    {
        if (object instanceof ScrollActor) {
            if (x >= fullWidth)
                x = fullWidth -1;
            else if (x < 0)
                x = 0;
            if (y >= fullHeight)
                y = fullHeight -1;
            else if (y < 0)
                y = 0;
            ScrollActor sa = (ScrollActor) object;
            super.addObject(sa, x -(camX -getWidth() /2), y -(camY -getHeight() /2));
            objects.add(sa);
            sa.setIsCameraFollower(false);
        } else
            super.addObject(object,x,y);
    }
    
    /**
     * Removes an object from the world.
     * @param object The object that will be removed
     * from the world. This can either be a camera follower,
     * or just a regular object.
     */
    public void removeObject(Actor object)
    {
        super.removeObject(object);
        if (object instanceof ScrollActor) {
            ScrollActor a = (ScrollActor) object;
            objects.remove(a);
            camFollowers.remove(a);
            a.setIsCameraFollower(false);
        }
    }
    
    /** RETURN VALUES: */
    
    /**
     * Returns the camera's x coördinate in big space.
     * @see #getCameraY
     */
    public int getCameraX()
    {
        return camX;
    }
    
    /**
     * Returns the camera's y coördinate in big space.
     * @see #getCameraX
     */
    public int getCameraY()
    {
        return camY;
    }
    
    /**
     * Returns the width of the big space.
     * @see #getFullHeight
     */
    public int getFullWidth()
    {
        return fullWidth;
    }
    
    /**
     * Returns the height of the big space.
     * @see #getFullWidth
     */
    public int getFullHeight()
    {
        return fullHeight;
    }
    
    /** CAMERA MOVEMENT + ROTATION: */
    
    /**
     * Moves the camera to a particular location.
     * Note that this is a location in the big space.
     * @param x The new x coördinate of the camera.
     * @param y The new y coördinate of the camera.
     */
    public void setCameraLocation(int x, int y)
    {
        if (camX == x && camY == y) return;
        if (x > fullWidth -getWidth() /2)
            x = fullWidth -getWidth() /2;
        else if (x < getWidth() /2)
            x = getWidth() /2;
        if (y > fullHeight -getHeight() /2)
            y = fullHeight -getHeight() /2;
        else if (y < getHeight() /2)
            y = getHeight() /2;
        int dx = x -camX;
        int dy = y -camY;
        camX = x;
        camY = y;
        for (ScrollActor a : objects)
            a.setLocation(a.getX() -dx, a.getY() -dy);
        for (ScrollActor a : camFollowers)
            a.setLocation(a.getX(), a.getY());
        moveBackgroundRight(dx *cellSize);
        moveBackgroundUp(dy *cellSize);
    }
    
    /**
     * Sets the direction the camera is facing.
     * It doesn't change anything you see, but it makes
     * it possible to use the {@link moveCamera} method.
     * @param degrees The new rotation in degrees.
     * @see #turnCamera(int)
     * @see #moveCamera(int)
     */
    public void setCameraDirection(int degrees)
    {
        if (degrees >= 360) {
            if (degrees < 720)
                degrees -= 360;
            else
                degrees %= 360;
        } else if (degrees < 0) {
            if (degrees >= -360)
                degrees += 360;
            else
                degrees = 360 +(degrees %360);
        }
        if (camDir == degrees) return;
        camDir = degrees;
    }
    
    /**
     * Turns the camera.
     * It doesn't change anything you see, but it makes
     * it possible to use the {@link moveCamera} method.
     * @param amount The number of degrees the camera will
     * turn clockwise. If this is negative, it will turn
     * counter-clockwise.
     * @see #setCameraDirection(int)
     * @see #moveCamera(int)
     */
    public void turnCamera(int amount)
    {
        setCameraDirection(camDir +amount);
    }
    
    /**
     * Moves the camera forward to the direction
     * it's facing (to go backwards, enter a negative number).
     * @param amount The number of cells the camera will move.
     * When this is negative, the camera will move forward.
     */
    public void moveCamera(int amount)
    {
        if (amount == 0) return;
        double radians = Math.toRadians(camDir);
        double dx = Math.cos(radians) *amount;
        double dy = Math.sin(radians) *amount;
        setCameraLocation((int)(camX +dx +0.5), (int)(camY +dy +0.5));
    }
    
    /** MOVING BACKGROUND: */
    
    /**
     * All the honor for this goes to Busch2207 from
     * greenfoot.org
     */
    private void moveBackgroundUp(int amount)
    {
        if (amount == 0) return;
        int height = getHeight();
        scrollPosY -= amount;
        while (scrollPosY < 0)
            scrollPosY += height;
        scrollPosY %= height;
        getBackground().drawImage(bigBackground, scrollPosX -getWidth(),scrollPosY -height);
    }
    
    /**
     * All the honor for this goes to Busch2207 from
     * greenfoot.org
     */
    private void moveBackgroundRight(int amount)
    {
        if (amount == 0) return;
        int width = getWidth();
        scrollPosX -= amount;
        while (scrollPosX < 0)
            scrollPosX += width;
        scrollPosX %= width;
        getBackground().drawImage(bigBackground, scrollPosX -width,scrollPosY -getHeight());
    }
}