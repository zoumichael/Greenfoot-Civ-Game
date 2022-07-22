import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Buttn that opens the Tech Tree.
 * 
 * @author Michael Zou, Chengze Cai
 * @version May 30, 2018
 */
public class TechButton extends Actor
{
    GreenfootImage nextTurn;        // Variable that handles the animation
    GreenfootImage nextTurnClicked; // Variable that handles the animation
    int animationCounter;           // Counter to change picture for button feedback
    boolean clicked;                // Boolean to confirm button was clicked

    /**
     * Constructor of objects NextTurnButton. Initializes the images to be the normal and
     * the flashing button. Initializes the 
     */
    public TechButton(){
        // Initializes and sets the Images.
        nextTurn = new GreenfootImage("techtreeReady.png");
        nextTurnClicked = new GreenfootImage("techtreeClicked.png");
        setImage(nextTurn);

        // Initializes the animation variables.
        clicked = false;
        animationCounter = 0;
    }

    /**
     * Act - do whatever the NextTurnButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        // Handle the events that open the tech tree.
        if(Greenfoot.mouseClicked(this)){

            // Change variables for the flashing animation of the button.
            clicked = true;
            animationCounter = 0;
        }

        // Animate the Flickering portion of the Button.
        if(clicked && animationCounter <= 7){
            setImage(nextTurnClicked);
            openTechTree();
            animationCounter++;
        }
        else{
            setImage(nextTurn);
            clicked = false;
        }
    }    
    
    //Opens the tech tree with the added buttons
    public void openTechTree(){
        if(getWorld().getObjects(TechTreeBackground.class).size() == 0){
            //  Add the Background and the Back Buttom
            TechTreeBackground ttBack = new TechTreeBackground();
            getWorld().addObject(ttBack, 375, 375);

            TechTreeBackButton ttBackButton = new TechTreeBackButton();
            getWorld().addObject(ttBackButton, 710, 40);

            // Get the Team that will have techs added to it.
            int team = ((GameWorld) getWorld()).getTurn();

            // Add the Age Techs.
            TechBubble age1 = new TechBubble("AGE1", "AGE0", team, 50);
            getWorld().addObject(age1, 75, 95);

            TechBubble age2 = new TechBubble("AGE2", "AGE1", team, 150);
            getWorld().addObject(age2, 75, 175);

            TechBubble age3a = new TechBubble("AGE3A", "AGE2", team, 250);
            getWorld().addObject(age3a, 75, 255);

            TechBubble age4a = new TechBubble("AGE4A", "AGE3A", team, 500);
            getWorld().addObject(age4a, 75, 345);

            TechBubble age5a = new TechBubble("AGE5A", "AGE4A", team, 1000);
            getWorld().addObject(age5a, 75, 430);

            TechBubble age3b = new TechBubble("AGE3B", "AGE2", team, 250);
            getWorld().addObject(age3b, 75, 510);

            TechBubble age4b = new TechBubble("AGE4B", "AGE3B", team, 500);
            getWorld().addObject(age4b, 75, 590);

            TechBubble age5b = new TechBubble("AGE5B", "AGE4B", team, 1000);
            getWorld().addObject(age5b, 75, 680);
            
            //Distance between buttons
            int xDis = 30;
            int yDis = 25;

            // Add the Techs in Age 1            
            TechBubble peasantUp = new TechBubble("PEASANTUPGRADE", "AGE1", team, 100); 
            getWorld().addObject(peasantUp, 200, 95);
            getWorld().addObject(new TechCost(100, peasantUp), 200 + xDis, 95 + yDis);

            TechBubble militaUnlock = new TechBubble("MILITIAUNLOCK", "AGE1", team, 50);
            getWorld().addObject(militaUnlock, 300, 95);
            getWorld().addObject(new TechCost(50, militaUnlock), 300 + xDis, 95 + yDis);

            TechBubble archerUnlock = new TechBubble("ARCHERUNLOCK", "AGE1", team, 50);
            getWorld().addObject(archerUnlock, 400, 95);
            getWorld().addObject(new TechCost(50, archerUnlock), 400 + xDis, 95 + yDis);

            TechBubble horsemanUnlock = new TechBubble("HORSEMANUNLOCK", "AGE1", team, 50); 
            getWorld().addObject(horsemanUnlock, 500, 95);
            getWorld().addObject(new TechCost(50, horsemanUnlock), 500 + xDis, 95 + yDis);

            // Add the Techs in Age 2
            TechBubble militiaUp = new TechBubble("MILITIAUPGRADE", "MILITIAUNLOCK AGE2", team, 150); 
            getWorld().addObject(militiaUp, 300, 175);
            getWorld().addObject(new TechCost(150, militiaUp), 300 + xDis, 175 + yDis);

            TechBubble archerUp = new TechBubble("ARCHERUPGRADE", "ARCHERUNLOCK AGE2", team, 150); 
            getWorld().addObject(archerUp, 400, 175);
            getWorld().addObject(new TechCost(150, archerUp), 400 + xDis, 175 + yDis);

            TechBubble horsemanUp = new TechBubble("HORSEMANUPGRADE", "HORSEMANUNLOCK AGE2", team, 150); 
            getWorld().addObject(horsemanUp, 500, 175);
            getWorld().addObject(new TechCost(150, horsemanUp), 500 + xDis, 175 + yDis);

            TechBubble resourceUp1 = new TechBubble("RESOURCEBOOST1", "AGE2", team, 300);
            getWorld().addObject(resourceUp1, 700, 175);
            getWorld().addObject(new TechCost(300, resourceUp1), 700 + xDis, 175 + yDis);

            // Add the Techs in Age 3A
            TechBubble swordsmanUnlock = new TechBubble("SWORDSMANUNLOCK", "AGE3A", team, 200);
            getWorld().addObject(swordsmanUnlock, 300, 255);
            getWorld().addObject(new TechCost(200, swordsmanUnlock), 300 + xDis, 255 + yDis);

            TechBubble trebuchetUnlock = new TechBubble("TREBUCHETUNLOCK", "AGE3A", team, 200); 
            getWorld().addObject(trebuchetUnlock, 400, 255);
            getWorld().addObject(new TechCost(200, trebuchetUnlock), 400 + xDis, 255 + yDis);

            TechBubble knightUnlock = new TechBubble("KNIGHTUNLOCK", "AGE3A", team, 200);
            getWorld().addObject(knightUnlock, 500, 255);
            getWorld().addObject(new TechCost(200, knightUnlock), 500 + xDis, 255 + yDis);

            // Add the Techs in Age 4A
            TechBubble swordsmanUp = new TechBubble("SWORDSMANUPGRADE", "SWORDSMANUNLOCK AGE4A", team, 300); 
            getWorld().addObject(swordsmanUp, 300, 345);
            getWorld().addObject(new TechCost(300, swordsmanUp), 300 + xDis, 345 + yDis);

            TechBubble trebuchetUp = new TechBubble("TREBUCHETUPGRADE", "TREBUCHETUNLOCK AGE4A", team, 300); 
            getWorld().addObject(trebuchetUp, 400, 345);
            getWorld().addObject(new TechCost(300, trebuchetUp), 400 + xDis, 345 + yDis);

            TechBubble knightUp = new TechBubble("KNIGHTUPGRADE", "KNIGHTUNLOCK AGE4A", team, 300); 
            getWorld().addObject(knightUp, 500, 345);
            getWorld().addObject(new TechCost(300, knightUp), 500 + xDis, 345 + yDis);

            TechBubble ivanUnlock = new TechBubble("IVANUNLOCK", "AGE4A", team, 400);
            getWorld().addObject(ivanUnlock, 600, 345);
            getWorld().addObject(new TechCost(400, ivanUnlock), 600 + xDis, 345 + yDis);

            TechBubble resourceUp2A = new TechBubble("RESOURCEBOOST2A", "AGE4A RESOURCEBOOST1", team, 600);
            getWorld().addObject(resourceUp2A, 700, 345);
            getWorld().addObject(new TechCost(600, resourceUp2A), 700 + xDis, 345 + yDis);

            // Add the Techs in Age 5A
            TechBubble peasantGodA = new TechBubble("PEASANTGODA", "AGE5A PEASANTUP", team, 10000); 
            //getWorld().addObject(peasantGodA, 200, 430);
            //getWorld().addObject(new TechCost(10000, peasantGodA), 200 + xDis, 430 + yDis);

            TechBubble ivanUp = new TechBubble("IVANUPGRADE", "IVANUNLOCK AGE5A", team, 500);     
            getWorld().addObject(ivanUp, 600, 430);
            getWorld().addObject(new TechCost(500, ivanUp), 600 + xDis, 430 + yDis);

            TechBubble resourceUp3A = new TechBubble("RESOURCEBOOST3A", "AGE5A RESOURCEBOOST2A", team, 900);
            getWorld().addObject(resourceUp3A, 700, 430);
            getWorld().addObject(new TechCost(900, resourceUp3A), 700 + xDis, 430 + yDis);

            // Add the Techs in Age 3B
            TechBubble orgeUnlock = new TechBubble("OGREUNLOCK", "AGE3B", team, 200);
            getWorld().addObject(orgeUnlock, 300, 510);
            getWorld().addObject(new TechCost(200, orgeUnlock), 300 + xDis, 510 + yDis);

            TechBubble wizardUnlock = new TechBubble("WIZARDUNLOCK", "AGE3B", team, 200); 
            getWorld().addObject(wizardUnlock, 400, 510);
            getWorld().addObject(new TechCost(200, wizardUnlock), 400 + xDis, 510 + yDis);

            TechBubble unicornUnlock = new TechBubble("UNICORNUNLOCK", "AGE3B", team, 200);
            getWorld().addObject(unicornUnlock, 500, 510);
            getWorld().addObject(new TechCost(200, unicornUnlock), 500 + xDis, 510 + yDis);

            // Add the Techs in Age 4B
            TechBubble orgeUp = new TechBubble("OGREUPGRADE", "OGREUNLOCK AGE4B", team, 300); 
            getWorld().addObject(orgeUp, 300, 590);
            getWorld().addObject(new TechCost(300, orgeUp), 300 + xDis, 590 + yDis);

            TechBubble wizardUp = new TechBubble("WIZARDUPGRADE", "WIZARDUNLOCK AGE4B", team, 300); 
            getWorld().addObject(wizardUp, 400, 590);
            getWorld().addObject(new TechCost(300, wizardUp), 400 + xDis, 590 + yDis);

            TechBubble unicornUp = new TechBubble("UNICORNUPGRADE", "UNICORNUNLOCK AGE4B", team, 300); 
            getWorld().addObject(unicornUp, 500, 590);
            getWorld().addObject(new TechCost(300, unicornUp), 500 + xDis, 590 + yDis);

            TechBubble dragonUnlock = new TechBubble("DRAGONUNLOCK", "AGE4B", team, 400);
            getWorld().addObject(dragonUnlock, 600, 590);
            getWorld().addObject(new TechCost(400, dragonUnlock), 600 + xDis, 590 + yDis);

            TechBubble resourceUp2B = new TechBubble("RESOURCEBOOST2B", "AGE4B RESOURCEBOOST1", team, 600);
            getWorld().addObject(resourceUp2B, 700, 590);
            getWorld().addObject(new TechCost(600, resourceUp2B), 700 + xDis, 590 + yDis);

            // Add the Techs in Age 5B
            //TechBubble peasantGodB = new TechBubble("PEASANTGODB", "AGE5B PEASANTUP", team, 10000); 
            //getWorld().addObject(peasantGodB, 200, 680);
            //getWorld().addObject(new TechCost(10000, peasantGodB), 200 + xDis, 680 + yDis);

            TechBubble dragonUp = new TechBubble("DRAGONUPGRADE", "DRAGONUNLOCK AGE5B", team, 500);     
            getWorld().addObject(dragonUp, 600, 680);
            getWorld().addObject(new TechCost(500, dragonUp), 600 + xDis, 680 + yDis);

            TechBubble resourceUp3B = new TechBubble("RESOURCEBOOST3B", "AGE5B RESOURCEBOOST2B", team, 900);
            getWorld().addObject(resourceUp3B, 700, 680);
            getWorld().addObject(new TechCost(900, resourceUp3B), 700 + xDis, 680 + yDis);
        }
    }
}
