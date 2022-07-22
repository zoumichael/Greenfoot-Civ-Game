import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Specific stats for particular units. Determines health, attack, armor, and other stats
 * 
 * @author Michael Zou 
 * @version May 15, 2018
 */
public class SpecificUnit extends CombatUnit
{
    public SpecificUnit(int x, int y, int t, String unitType, String unlockedTechs){
        super(x, y, t);
        
        // Initialize the image based on unitType.
        setImage(new GreenfootImage(unitType + t + ".png"));
        
        // Initialize the rest of the stats based on the unitType.
        if(unitType.equals("PEASANT")){
            // Initialize the stats.       
            numMoves = 4;
            health = 10;
            attack = 7;
            armor = 2;
            range = 1;
                        
            // Initialize the objects avaliablity to perform actions.
            canAct = true;
            canMove = true;
            canAttack = true;
            canAttackAfterMove = true;
            canMoveAfterAttack = false;
            
            // Initialize the unit class.
            unitClass = "MELEE";
            name = "Peasant";
            
            if(unlockedTechs.indexOf("PEASANTGOD") != -1){
                attack += 10;
                armor += 10;
            }
        }
        else if(unitType.equals("MILITIA")){
            // Initialize the stats.       
            numMoves = 6;
            health = 10;
            attack = 9;
            armor = 4;
            range = 1;

            // Initialize the objects avaliablity to perform actions.
            canAct = true;
            canMove = true;
            canAttack = true;
            canAttackAfterMove = true;
            canMoveAfterAttack = false;
            
            // Initialize the unit class.
            unitClass = "MELEE";
            name = "Militia";
        }
        else if(unitType.equals("HORSEMAN")){
            // Initialize the stats.       
            numMoves = 12;
            health = 10;
            attack = 8;
            armor = 3;
            range = 1;

            // Initialize the objects avaliablity to perform actions.
            canAct = true;
            canMove = true;
            canAttack = true;
            canAttackAfterMove = true;
            canMoveAfterAttack = false;
            
            // Initialize the unit class.
            unitClass = "MOBILE";
            name = "Horseman";
        }
        else if(unitType.equals("ARCHER")){
            // Initialize the stats.       
            numMoves = 6;
            health = 10;
            attack = 7;
            armor = 2;
            range = 2;

            // Initialize the objects avaliablity to perform actions.
            canAct = true;
            canMove = true;
            canAttack = true;
            canAttackAfterMove = true;
            canMoveAfterAttack = false;          
            isRanged = true;
            
            // Initialize the unit class.
            unitClass = "RANGE";
            name = "Archer";
        }
        else if(unitType.equals("SWORDSMAN")){
            // Initialize the stats.       
            numMoves = 6;
            health = 10;
            attack = 12;
            armor = 7;
            range = 1;

            // Initialize the objects avaliablity to perform actions.
            canAct = true;
            canMove = true;
            canAttack = true;
            canAttackAfterMove = true;
            canMoveAfterAttack = false;
            
            // Initialize the unit class.
            unitClass = "melee";
            name = "Paladin";
        }
        else if(unitType.equals("KNIGHT")){
            // Initialize the stats.       
            numMoves = 10;
            health = 10;
            attack = 10;
            armor = 5;
            range = 1;

            // Initialize the objects avaliablity to perform actions.
            canAct = true;
            canMove = true;
            canAttack = true;
            canAttackAfterMove = true;
            canMoveAfterAttack = false;
            
            // Initialize the unit class.
            unitClass = "mobile";
            name = "Knight";
        }
        else if(unitType.equals("TREBUCHET")){
            // Initialize the stats.       
            numMoves = 4;
            health = 10;
            attack = 13;
            armor = 0;
            range = 3;

            // Initialize the objects avaliablity to perform actions.
            canAct = true;
            canMove = true;
            canAttack = true;
            canAttackAfterMove = true;
            canMoveAfterAttack = false;
            isRanged = true;
            
            // Initialize the unit class.
            unitClass = "range";
            name = "Trebuchet";
        }
        else if(unitType.equals("IVAN")){
            // Initialize the stats.       
            numMoves = 2;
            health = 10;
            attack = 15;
            armor = 6;
            range = 3;

            // Initialize the objects avaliablity to perform actions.
            canAct = true;
            canMove = true;
            canAttack = true;
            canAttackAfterMove = true;
            canMoveAfterAttack = false;
            isRanged = true;
            
            // Initialize the unit class.
            unitClass = "ultimate";
            name = "The Chad Ivan";
        }
        else if(unitType.equals("OGRE")){
            // Initialize the stats.       
            numMoves = 6;
            health = 10;
            attack = 13;
            armor = 6;
            range = 1;

            // Initialize the objects avaliablity to perform actions.
            canAct = true;
            canMove = true;
            canAttack = true;
            canAttackAfterMove = true;
            canMoveAfterAttack = false;
            
            // Initialize the unit class.
            unitClass = "melee";
            name = "Ogre";
        }
        else if(unitType.equals("UNICORN")){
            // Initialize the stats.       
            numMoves = 12;
            health = 10;
            attack = 11;
            armor = 4;
            range = 1;

            // Initialize the objects avaliablity to perform actions.
            canAct = true;
            canMove = true;
            canAttack = true;
            canAttackAfterMove = true;
            canMoveAfterAttack = false;
            
            // Initialize the unit class.
            unitClass = "mobile";
            name = "Unicorn";
        }
        else if(unitType.equals("WIZARD")){
            // Initialize the stats.       
            numMoves = 8;
            health = 10;
            attack = 12;
            armor = 3;
            range = 2;

            // Initialize the objects avaliablity to perform actions.
            canAct = true;
            canMove = true;
            canAttack = true;
            canAttackAfterMove = true;
            canMoveAfterAttack = false;
            isRanged = true;
            
            // Initialize the unit class.
            unitClass = "range";
            name = "Wizard";
        }
        else if(unitType.equals("DRAGON")){
            // Initialize the stats.       
            numMoves = 8;
            health = 10;
            attack = 13;
            armor = 7;
            range = 1;

            // Initialize the objects avaliablity to perform actions.
            canAct = true;
            canMove = true;
            canAttack = true;
            canAttackAfterMove = true;
            canMoveAfterAttack = false;
            flying = true;
            
            // Initialize the unit class.
            unitClass = "ultimate";
            name = "Dragon";
        }        
        
        if(unlockedTechs.indexOf(unitType + "UPGRADE") != -1){
            if(unitClass.equals("ultimate"))
                attack += 4;
            else if(unitClass.equals("melee"))
                attack += 3;
            else if(unitClass.equals("range"))
                attack += 2;
            else
                attack += 3;
        }
    }
}
