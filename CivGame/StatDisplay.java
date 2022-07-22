import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Display's the various stats of the unit at the bottom.
 * 
 * @author Michael Zou
 * @version May 16, 2018
 */
public class StatDisplay extends Actor
{
    // The transparent color we define so there is no background.
    private final Color transparent = new Color(0, 0, 0, 0);
    
    /**
     * Constructor for Objects of class FundDisplay. Since the game just started, the funds is 0.
     */
    public StatDisplay(Unit u){
        int attack = u.getAttack();
        int armor = u.getArmor();
        int range = u.getRange();
        int moves = u.getNumMoves();
        String name = u.getName();
        setImage(new GreenfootImage(name + "\nAttack: " + attack + " Armor: " + armor + "\nMoves: " + moves + " Range: " + range, 30, Color.YELLOW, transparent));
        
    }
}
