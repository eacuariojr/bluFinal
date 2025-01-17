/*
    Erwin Aquario, Victor Gallardo
    May 15, 2019
    Game.java
    Dependencies: CommonMethods.java, Character.java, Party.java, Projectile.java
    Credit: none
*/
//----------------------------------------------------------------------------------------------------------------------
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Game
{
    private String saveName;
    private int daysPassed;
    private final int LAST_DAY = 25; //sets the last day of the game
    private Party playerParty;

    //****************CONSTRUCTORS****************
    //default constructor for "New Game"
    public Game()
    {
        Scanner input = new Scanner(System.in);
        String prompt;

        //asks for name of new character
        System.out.print("Enter starting character name: ");
        saveName = input.next();//works because "new game" save file name is the same as the first character
        input.nextLine();

        //creates prompt
        prompt = "Your starting character will be named " + saveName + ".\nIs that okay? ";

        //asks for confirmation until a name is confirmed
        while (!CommonMethods.promptYes(prompt))
        {
            //gets a new name if user responds no, and recreate prompt
            System.out.print("Enter starting character name: ");
            saveName = input.next();

            prompt = "Your starting character will be named " + saveName + ".\nIs that okay? ";
        }

        //after a character name is settled, starts creating character and party
        Character initialCharacter = new Character(saveName);
        playerParty = new Party(initialCharacter);

        //then sets new game values of other instance variables
        daysPassed = 0;

    } //end new game constructor

    //constructor when loading in a game
    public Game(Scanner fileReader)
    {
        //fileReader should already be right before the filename of the save being loaded

        saveName = fileReader.next();   fileReader.next();  //reads save name and skips the "--"
        daysPassed = fileReader.nextInt();
        //passes the scanner into Party's constructor
        playerParty = new Party(fileReader);

    } //end loading constructor

    //***************************************
    //****************METHODS****************
    //***************************************
    //Game can only start in this way
    public void start()
    {
        while (daysPassed < LAST_DAY)
        {
            startDay();
            nextDay();
        }

    }//end method start

    private void startDay()
    {
        System.out.println("\n\nIt is day " + (daysPassed + 1) + "\n");

        System.out.println(playerParty.simplePrint());

        printMainMenu();

        //redirects to different methods based on user's choice
        switch (CommonMethods.getChoice(5))
        {
            case 1: trainOption();
                break;
            case 2: exploreOption();
                break;
            case 3: recruitOption();
                break;
            case 4: System.out.println(playerParty.detailedPrint());
                    startDay(); //oooooh, recursion!
                break;
            case 5: quit();
                break;
        }

    }//end method startDay

    //****************TRAINING METHODS****************
    private void trainOption()
    {
        //the menu and selection for training. Doesn't change any stats on its own

        printTrainMenu();

        switch (CommonMethods.getChoice(4))
        {
            case 1: train("Strength");
                    break;
            case 2: train("Speed");
                    break;
            case 3: train("Endurance");
                    break;
            case 4: startDay();
                    break;
            //any other outputs from getChoice() is impossible
        }
    }//end method trainOption

    private void train(String stat)
    {
        //this method actually increases the stat received in the parameter
        //it adds a random value of that stat to every party member in the player's party

        //the constants are used to make statements more concise
        final String STR = "Strength";
        final String SPD = "Speed";
        final String END = "Endurance" ;

        //nested if-else-if to evaluate which stat is being modified
        if(stat.equals(STR))
        {
            //this is where we get sad because we can't pass by reference in java :^(
            for(int i = 0; i < playerParty.getMembers().size(); i++)
            {
                playerParty.getMember(i).addStrength(calculateTrainGain());
            }
        }
        else if (stat.equals(SPD))
        {
            //this is where we get sad because we can't pass by reference in java :^(
            for(int i = 0; i < playerParty.getMembers().size(); i++)
            {
                playerParty.getMember(i).addSpeed(calculateTrainGain());
            }
        }
        else if (stat.equals(END))
        {
            //this is where we get sad because we can't pass by reference in java :^(
            for(int i = 0; i < playerParty.getMembers().size(); i++)
            {
                playerParty.getMember(i).addEndurance(calculateTrainGain());
            }
        }
        else
        {
            System.out.println("I think we need to report this to QA.");
        }
    }//end method train

    private int calculateTrainGain()
    {
        //gives a random integer within bounds 5 - 13 inclusive
        final int MIN_GAIN = 5;
        final int MAX_GAIN = 13;
        Random randGen = new Random();

        return randGen.nextInt(MAX_GAIN - MIN_GAIN) + MIN_GAIN;
    }//end method calculateTrainGain

    //****************EXPLORING METHODS****************
    private void exploreOption()
    {
        printExploreMenu();

        //gets user's choice after the menu
        switch(CommonMethods.getChoice(4))
        {
            case 1: generateEncounter(0.8);
                break;
            case 2: generateEncounter(1.2);
                break;
            case 3: generateEncounter(3.0);
                break;
            case 4: startDay();
                break;
        }

    }// end method exploreOption

    private void generateEncounter(double encounterModifier)
    {
        //create new random party
        Party enemyParty = new Party(encounterModifier);

        System.out.println("You find an enemy party!");
        System.out.println("Here are their stats");
        CommonMethods.pauseProgram();

        System.out.println(enemyParty.detailedPrint());

        //asks user if they want to fight or not
        String prompt = "Do you want to fight them?";
        if(CommonMethods.promptYes(prompt))
        {
            playerParty.attackParty(enemyParty);
        }
        else
        {
            System.out.println("You manage to escape.");
            CommonMethods.pauseProgram();
        }
    }//end method generateEnounter

    //****************RECRUITING METHODS****************
    private void recruitOption()
    {
        printRecruitMenu();

        //gets user's choice after the menu
        switch(CommonMethods.getChoice(5))
        {
            case 1: offerRecruit(50);
                break;
            case 2: offerRecruit(100);
                break;
            case 3: offerRecruit(300);
                break;
            case 4: offerRecruit(1000);
                break;
            case 5: startDay();
                break;
        }

    }//end method recruitOption

    private void offerRecruit(int priceLevel)
    {
        //given an integer price level, generate a random recruit to be offered

        //declares variables for objects
        Random randGen = new Random();
        Character possibleRecruit;

        //declares variables
        double statMulti;
        int goldCost;
        double goldWorkVariable;
        final double GOLD_VARIANCE = 0.2;
        String prompt;

        //determines the random gold cost
        //formula taken for Character.java's randomStats method (0.8 - 1.2 (1 +- GOLD_VARIANCE))
        goldWorkVariable = (((randGen.nextDouble() * 2) - 1) * GOLD_VARIANCE) + 1;
        goldWorkVariable *= priceLevel;

        goldCost = (int)goldWorkVariable;

        //determines the stats of the random recruit
        //this exact equation is kind of arbitrary
        //It's equivalent to 0.05(x^(3/5)). It has a desired scaling (check Desmos)
        statMulti = Math.pow(priceLevel, (double) 3/5) * 0.05;

        possibleRecruit = new Character(statMulti);


        //at this point, a semi-random recruit was created with a weighted-random gold cost
        System.out.printf("%s offers to join your party for %d gold.%n", possibleRecruit.getName(), goldCost);
        System.out.println("These are their stats:");
        System.out.println(possibleRecruit.detailedPrint());

        hireCharacter(goldCost, possibleRecruit);
    }//end method offerRecruit

    private void hireCharacter(int goldPrice, Character recruit)
    {
        //give the player the option to hire the offered recruit
        //has the checks if player can hire character
        String prompt;


        if(playerParty.getGold() < goldPrice)
        {
            //lack of money means that you just lose the day
            System.out.println("You're too broke. You just wasted your time.");
            CommonMethods.pauseProgram();
        }
        else
        {
            prompt = "Will you hire them? ";

            if(CommonMethods.promptYes(prompt))
            {
                //adds character to party and removes cost from party
                playerParty.addCharacter(recruit);
                playerParty.changeGold((goldPrice * -1));
            }
        }

    }//end method hireCharacter

    //****************QUIT METHODS****************
    private void quit()
    {
        //this method handles all the procedures necessary when quitting the game

        //gets all saves inside Saves.txt
        ArrayList<Game> saves = recordSaves();

        //removes the any save files that have the same name (should only be a problem with new games)
        for (int i = 0; i < saves.size(); i++)
        {
            if (saves.get(i).getSaveName().equals(saveName))
            {
                //if any file has the same name as this object, remove that object from ArrayList
                saves.remove(i);
            }
        }

        //starts saving process
        try
        {
            //creates objects required to write into file
            FileWriter fWriter = new FileWriter("Saves.txt", false);
            Formatter output = new Formatter(fWriter);

            //starts writing all the saves
            for (int i = 0; i < saves.size(); i++)
            {
                output.format(saves.get(i).writeData());
            }
            //finally, writes this object's save data
            output.format(writeData());

            //and close
            output.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.out.println("Could not save scores.");
            CommonMethods.pauseProgram();
        }

        System.out.println("Progress saved. Goodbye");
        CommonMethods.pauseProgram();
        System.exit(0); //maybe there's a more elegant solution, but time is running out :^(
    }//end method quit

    private ArrayList<Game> recordSaves()
    {
        //this method reads all the saves and stores them as game objects in an ArrayList

        //creates file and ArrayList
        File myFile = new File("Saves.txt");
        ArrayList<Game> saveFiles = new ArrayList<>();

        //attempts to write all save data into ArrayList
        try
        {
            //creates scanner and primes it for recording
            Scanner fileStream = new Scanner(myFile);
            fileStream.next();  //the first file needs to get past the "&&" for it to work properly

            while (fileStream.hasNext())
            {
                //calls upon loading constructor to create objects from scanner
                saveFiles.add(new Game(fileStream));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("\n\n***************ERROR***************\n" + e);
            CommonMethods.pauseProgram();
        }

        return saveFiles;
    }//end method recordSaves

    //****************GENERAL METHODS****************
    private void nextDay()
    {
        daysPassed++;

        for (int i = 0; i < playerParty.getMembers().size(); i++)
        {
            playerParty.getMember(i).heal();
        }
    }//end method nextDay

    //this method is used when writing the entire game into a save file
    public String writeData()
    {
        String gameData;

        //writes the data sepcific to the game
        gameData = "&& " + saveName + " -- " + daysPassed + "\n";
        //then adds data from party
        gameData += playerParty.writeData();

        return gameData;
    }//end method writeData

    //****************MENU METHODS****************

    private void printMainMenu()
    {
        System.out.println("What action do you take?");
        System.out.println("1.) Train");
        System.out.println("2.) Explore");
        System.out.println("3.) Recruit");
        System.out.println("4.) Check Status");
        System.out.println("5.) Quit Game");
    }//end method printMainMenu

    private void printTrainMenu()
    {
        System.out.println("What would you like to train?");
        System.out.println("1.) Strength");
        System.out.println("2.) Speed");
        System.out.println("3.) Endurance");
        System.out.println("4.) Go Back");
    }//end method printTrainMenu

    private void printRecruitMenu()
    {
        System.out.println("What kind of recruits are you looking for?");
        System.out.println("1.) Cheap     (~50 Gold)");
        System.out.println("2.) Modest    (~100 Gold)");
        System.out.println("3.) Expensive (~300 Gold)");
        System.out.println("4.) Ridiculously Overpriced");
        System.out.println("              (~1000 Gold)");
        System.out.println("5.) Go Back");
    }//end method printRecruitMenu

    private void printExploreMenu()
    {
        System.out.println("What do you want to explore?");
        System.out.println("1.) Noobie Nook");
        System.out.println("2.) Macho Mills");
        System.out.println("3.) Benny's Basement");
        System.out.println("4.) Go Back");
    }//end method printExploreMenu()


    public String getSaveName()
    {
        return saveName;
    }
}

