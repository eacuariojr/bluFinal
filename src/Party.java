/*
    Erwin Aquario, Victor Gallardo and Kevin Raya
    May 15, 2019
    Party.java
    Dependencies: Character.java, Projectile.java
    Credit: none
*/
//----------------------------------------------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.Scanner;

public class Party
{
    private ArrayList<Character> members = new ArrayList<>();
    private int gold;

    //creates a new party with one existing character
    public Party(Character firstCharacter)
    {
        gold = 100;
        members.add(firstCharacter);
    }

    //creates the party based on what's read from the file
    public Party(Scanner fileReader)
    {

    }

    public Party(String enemyPartyType)
    {

    }

    //****************METHODS****************
    public void attackParty(Party opposingParty)
    {

    }//end method attackParty

    public void simplePrint()
    {

    }//end method simplePrint

    public void detailedPrint()
    {

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
