/*
    Erwin Aquario, Victor Gallardo and Kevin Raya
    May 15, 2019
    Game.java
    Dependencies: Character.java, Party.java, Projectile.java
    Credit: none
*/
//----------------------------------------------------------------------------------------------------------------------
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

    //****************METHODS****************

    //Game can only start in this way
    public void start()
    {
        for (int i = 0; i < 20; i++)
        {

        }
    }//end method start

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

}
