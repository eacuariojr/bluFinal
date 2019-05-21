/*
    Erwin Aquario, Victor Gallardo and Kevin Raya
    May 15, 2019
    Game.java
    Dependencies: Character.java, Party.java, Projectile.java
    Credit: none
*/
//----------------------------------------------------------------------------------------------------------------------
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

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
        boolean userConfirm = false;
        char userResponse;

        //asks for name of new character
        System.out.print("Enter starting character name: ");
        saveName = input.next();//works because "new game" save file name is the same as the first character
        input.nextLine();

        //asks for confirmation until a name is confirmed
        while (!userConfirm)
        {
            System.out.printf("Your starting character will be named %s.", saveName);
            //asks user for confirmation
            System.out.print(" Is that okay? (Y) or (N): ");
            userResponse = input.next().toUpperCase().charAt(0);

            //evaluates user response
            switch(userResponse)
            {
                case 'Y': userConfirm = true;
                        break;
                case 'N': //makes user name character again
                    System.out.print("Enter starting character name: ");
                    saveName = input.next();
                    input.nextLine();
                        break;
                default: System.out.println("Invalid response. Try again.");
            }
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

        switch (getChoice(4))
        {
            case 1: trainOption();
                break;
            case 2: //explore
                break;
            case 3: //recruit
                break;
            case 4: System.out.println(playerParty.detailedPrint());
                    startDay(); //oooooh, recursion!
                break;
            default:
        }

    }//end method startDay

    //****************TRAINING METHODS****************
    private void trainOption()
    {
        printTrainMenu();

        switch (getChoice(4))
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
        final int MIN_GAIN = 5;
        final int MAX_GAIN = 13;
        Random randGen = new Random();

        return randGen.nextInt(MAX_GAIN - MIN_GAIN) + MIN_GAIN;
    }//end method calculateTrainGain

    private void nextDay()
    {
        daysPassed++;
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
    }//end method printMainMenu

    private void printTrainMenu()
    {
        System.out.println("What would you like to train?");
        System.out.println("1.) Strength");
        System.out.println("2.) Speed");
        System.out.println("3.) Endurance");
        System.out.println("4.) Go Back");
    }

    private int getChoice(int maxChoice)
    {
        //housekeeping
        int choice = -1;
        Scanner input = new Scanner(System.in);

        //code to prime loop
        try
        {
            System.out.print("\nEnter your choice : ");
            choice = input.nextInt();
        }
        catch (InputMismatchException e)
        {
            System.out.print("Must enter number. ");
        }

        //loops until there's a choice inside bounds
        while(choice < 0 || choice > maxChoice)
        {
            //prints error message for out of bounds
            System.out.println("Invalid choice, try again.");

            //catches mismatch errors.
            try
            {
                System.out.print("\nEnter your choice : ");
                choice = input.nextInt();

            }
            catch (InputMismatchException e)
            {
                System.out.print("Must enter number. ");
                //the full error message is "Must enter number. Invalid choice, try again."
            }
        }

        return choice;
    }

}
