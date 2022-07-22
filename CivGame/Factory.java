import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The neutral objective on the world that produces units and 
 * can be captured.
 * 
 * @author Michael Zou, Chengze Cai
 * @version May 8, 2018
 */
public class Factory extends Building
{
    // Boolean that stores whether this factory is selected or not.
    private boolean factorySelected;

    /**
     * Constructors of objects of class Factory
     */
    public Factory(int x, int y, int t, int i){
        super(x, y, t, i);

        imagePrefix = "Factory";
        setImage(new GreenfootImage(imagePrefix + team + ".png"));

        factorySelected = false;
    }

    /**
     * Act - do whatever the Factory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // The build menu will only pop up if the current turn is equal to the team of this factory.
        if(Greenfoot.mouseClicked(this)){
            if(team == ((GameWorld) getWorld()).getTurn()){
                if(!factorySelected){
                    // Open the Build Menu on the bottom.
                    openBuildMenu();
                    
                    // Deselect all factories.
                    ((GameWorld) getWorld()).deselectAllFactories();
                    
                    // Select this factory.
                    factorySelected = true;
                    ((GameWorld) getWorld()).setSelectedBuilding(this);
                }
                else{
                    // Close the Build Menu on the bottom.
                    closeBuildMenu();
                    
                    // Deselect all factories.
                    factorySelected = false;
                    ((GameWorld) getWorld()).setSelectedBuilding(null);
                }
            }
        }
    }    

    /**
     * 
     */
    public void openBuildMenu(){
        // If the current occupation of this tile is empty.
        if(((GameWorld) getWorld()).getMapAt(coorX, coorY).getUnit() == null){
            // Deselect all units and remove all attack tiles, focus on buliding.
            ((GameWorld) getWorld()).deselectAllUnits();
            ((GameWorld) getWorld()).removeAttackTiles();

            (getWorld().getObjects(BotHud.class).get(0)).openBuild();
        }
    }

    public void closeBuildMenu(){
        getWorld().getObjects(BotHud.class).get(0).closeBuild();
    }

    /**
     * Mutator method for the 'factorySelected' variable.
     * 
     * @param  s    the new value of isSelected
     */
    public void setSelected(boolean s){
        factorySelected = s;
    }

    /**
     * Accessor method for the 'factorySelected' variable.
     * 
     * @return     the factorySelected variable
     */
    public boolean getSelected(){
        return factorySelected;
    }
}
