/*
    Erwin Aquario, Victor Gallardo and Kevin Raya
    May 15, 2019
    BluFinal.java
    Dependencies: Game.java, Party.java, Character.java, Projectile.java
    Credit: none
*/
//----------------------------------------------------------------------------------------------------------------------
//import classes
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class BluFinal //start BluFinal class
{
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) //start main method
    {
        int choice; //delcare int choice to store user choice
        boolean validCommand = true; //validCommand set to true if command is int

        do
        {
            validCommand = true;

            try //attempt this block of code
            {
                do {
                    printMenu(); //printMenu method prints menu
                    choice = getChoice(); //initialize choice with getChoice

                    switch (choice) //start switch statement
                    {
                        case 1:
                            newGame();
                            break;
                        case 2:
                            loadGame();
                            break;
                        case 3:
                            leaderboard();
                            break;
                        case 4:
                            quitGame();
                            break;
                        default:
                            System.out.println("Invalid command. Try again.");
                            break;
                    } //end switch statement
                } while(choice != 4);
            }
            catch(InputMismatchException error) //throw exception if input does not match
            {
                input.nextLine(); //clear the rest of the line
                validCommand = false; //set to false if exception is thrown
                System.out.println("Invalid command. Try again.");
            }
        } while(!validCommand); //do while valid command is not true
    } //end main method

    private static void printMenu() //start printMenu method
    {
        System.out.println("\n~~~~~MAIN MENU~~~~~");
        System.out.println("1.New Game");
        System.out.println("2.Load Game");
        System.out.println("3.Scoreboard");
        System.out.println("4.Quit Game");
    } //end printMenu method

    private static int getChoice() //start getChoice method
    {
        System.out.print("\nEnter your choice: ");
        int choice = input.nextInt();
        return choice;
    } //end getChoice method

    private static void newGame() //start newGame method
    {
        Game newGame = new Game(); //create a new game instance
        newGame.start(); //call the start method from Game class
    } //end newGame method

    private static void loadGame() //start loadGame method
    {
       ArrayList <String> saveFiles = new ArrayList<>();

        //open file Saves.txt
        String filename = "Saves.txt"; //create a String variable and initialize it with Saves.txt
        File myFile = new File(filename); //find the relative path to Saves.txt

        //read and print data from Saves.txt so users can select a save file
        System.out.println("~~~~~PRINTING SAVE FILES~~~~~");

        try //try finding and printing the file
        {
            Scanner fileStream = new Scanner(myFile); //Scanner reads Saves.txt file
            String fileString; //used to see if fileStream equals "&&"

            while(fileStream.hasNext()) //read file from data while there is still data to be read
            {
                fileString = fileStream.next(); //initialize fileString with Scanner
                if(fileString.equals("&&")) //if fileString equals "&&", add username saveFiles array list
                {
                    saveFiles.add(fileStream.next());
                }
            }

            for(int i = 0; i < saveFiles.size(); i++) //loop that prints save file names
            {
                System.out.println(saveFiles.get(i));
            }
        }
        catch(FileNotFoundException error) //if file cannot be found, throw FileNotFoundException
        {
            System.out.println(error);
        }
    } //end loadGame method

    private static void leaderboard() //start leaderboard method
    {
        //open file Scoreboard.txt
        String filename = "Scoreboard.txt"; //create String variable and initialize it with Scoreboard.txt
        File myFile = new File(filename); //finds the relative path to Scoreboard.txt

        //read and print data from Scoreboard.txt
        System.out.println("~~~~~PRINTING LEADERBOARD~~~~~");

        try //try finding and printing the file
        {
            Scanner fileStream = new Scanner(myFile); //Scanner reads Scoreboard.txt file

            while(fileStream.hasNext()) //read data from file while there is still data to be read
            {
                String username = fileStream.next();
                fileStream.next();
                int score = fileStream.nextInt();

                System.out.printf("%s -- %d\n", username, score);
            }
        }
        catch(FileNotFoundException error) //if file cannot be found, throw a FileNotFoundExcpetion
        {
            System.out.println(error);
        }
    } //end leaderboard method

    private static void quitGame() //start quitGame method
    {
        System.out.print("\n~~~~~End Game~~~~~\n");
        System.exit(0); //terminates program
    } //end quitGame method
} //end BluFinal class
