/*
    Erwin Aquario, Victor Gallardo
    May 15, 2019
    Party.java
    Dependencies: Character.java, Projectile.java
    Credit: none
*/
//----------------------------------------------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Party
{
    private ArrayList<Character> members = new ArrayList<>();
    private final int MAX_MEMBERS = 3;
    private int gold;

    //creates a new party with one existing character
    public Party(Character firstCharacter)
    {
        gold = 100;
        members.add(firstCharacter);

    } //end newgame constructor

    //creates the party based on what's read from the file
    public Party(Scanner fileReader)
    {
        fileReader.next();  //skips "**"
        gold = fileReader.nextInt();

        //loads characters until save file data ends
        String charName;
        int strength, speed, endurance;
        double health;
        boolean notIncapacitated, isAlive;

        String fileText = fileReader.next();
        while(!fileText.equals("&&"))
        {
            //reads the line of data for character's variables
            charName = fileReader.next(); fileReader.next();
            strength = fileReader.nextInt(); fileReader.next();
            speed = fileReader.nextInt(); fileReader.next();
            endurance = fileReader.nextInt(); fileReader.next();
            health = fileReader.nextDouble(); fileReader.next();
            notIncapacitated = fileReader.nextBoolean(); fileReader.next();
            isAlive = fileReader.nextBoolean();

            members.add(new Character(charName, strength, speed, endurance,
                                      health, notIncapacitated, isAlive));
            if (!fileReader.hasNext())
                break;
            else
                fileText = fileReader.next();
        }
    } //end loading game constructor

    public Party(double statMultiplier)
    {
        Random randGen = new Random();
        final int BASE_GOLD = 50;

        //a random amount of gold (0 - some multiple of 50)
        gold = randGen.nextInt((int)(BASE_GOLD * statMultiplier));

        //fills a party with random members that have the stat multiplier
        for(int i = 0; i < MAX_MEMBERS; i++)
        {
            members.add(new Character(statMultiplier));
        }
    } // end random party constructor

    //****************METHODS****************
    public void attackParty(Party opposingParty)
    {

    }//end method attackParty

    public String simplePrint()
    {
        String simplePrint = "Gold: " + gold + "\n";

        for (int i = 0; i < members.size(); i++)
        {
            simplePrint += members.get(i).simplePrint() + "\n";
        }

        return simplePrint;
    }//end method simplePrint

    public String detailedPrint()
    {
        String detailedPrint = "PARTY DETAILS: \n";
        detailedPrint += "Gold: " + gold + "\n";

        for (int i = 0; i < members.size(); i++)
        {
            detailedPrint += members.get(i).detailedPrint() + "\n";
        }

        return detailedPrint;
    }//end method detailedPrint

    public String writeData()
    {
        String partyData;

        partyData = "** " + gold + "\n";
        //adds data of every member
        for (int i = 0; i < members.size(); i++)
        {
            partyData += members.get(i).writeData();
        }

        return partyData;
    }//end method writeData

    public void addCharacter(Character recruit)
    {
        members.add(recruit);

        if (members.size() > MAX_MEMBERS)
        {
            //if party size exceeds max members, then a choice is presented on who to kick out
            int kickSelection;
            String prompt;

            //loops until user selects a character to kick
            do
            {
                //displays menu and gets a choice
                kickMenu();
                kickSelection = CommonMethods.getChoice(members.size());
                prompt = "You chose to kick " + members.get(kickSelection - 1).getName() + "\n" +
                         "Is this correct? ";
            } while(!CommonMethods.promptYes(prompt));
        }
    }//end method addCharacter

    private void kickMenu()
    {
        System.out.println("Your party has too many members!");
        System.out.println("Choose one to kick out\n");

        for (int i = 0; i < members.size(); i++)
        {
            System.out.println( (i + 1) + ".) " + members.get(i).getName());
        }

    }//end method kickMenu

    //****************GETTERS & SETTERS****************
    public ArrayList<Character> getMembers()
    {
        return members;
    }

    //no bounds checking on this method so be careful when calling it
    public Character getMember(int whichMember)
    {
        return members.get(whichMember);
    }

    public int getGold()
    {
        return gold;
    }

    public void changeGold(int amountGained)
    {
        gold += amountGained;
    }
}
