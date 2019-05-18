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
    private final int lastDay = 20; //sets the last day of the game
    private Party playerParty;

    //****************CONSTRUCTORS****************
    //default constructor for "New Game"
    public Game()
    {

    } //end new game constructor

    //constructor when loading in a game
    public Game(Scanner fileReader)
    {

    } //end loading constructor

    //****************METHODS****************

    //Game can only start in this way
    public void start()
    {

    }//end method start

    private void nextDay()
    {
        daysPassed++;
    }//end method nextDay



}
