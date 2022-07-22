import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Subclass for creating the money making building dubbed 'cashcow'
 * 
 * @author Chengze Cai
 * @version June 11, 2018
 */
public class CashCow extends Building
{
    public CashCow(int x, int y, int t, int i){
        super(x, y, t, 30);

        imagePrefix = "Cash";
        setImage(new GreenfootImage(imagePrefix + team + ".png"));
    }
}
