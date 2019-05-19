/*
    Erwin Aquario, Victor Gallardo and Kevin Raya
    May 15, 2019
    BluFinal.java
    Dependencies: Character.java, Game.java, Party.java
    Credit: none
*/
//----------------------------------------------------------------------------------------------------------------------
//import classes
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class BluFinal //start BluFinal class
{
    static Scanner input = new Scanner(System.in);

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
                            System.out.println("Test 1");
                            break;
                        case 2:
                            System.out.println("Test 2");
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

    private static void leaderboard() //start leaderboard method
    {
        //open file
        String filename = "Scoreboard.txt"; //create String variable with file name
        File myFile = new File(filename); //finds the relative path to Scoreboard.txt

        //read and print data from Scoreboard.txt
        String line;
        System.out.println("~~~~~PRINTING LEADERBOARD~~~~~");

        try //try finding and printing the file
        {
            Scanner fileStream = new Scanner(myFile); //Scanner reads Scoreboard.txt file instead of user input

            while(fileStream.hasNext())
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
    } //end leaderoard method

    private static void quitGame() //start quitGame method
    {
        System.out.print("\n~~~~~End Game~~~~~\n");
        System.exit(0); //terminates program
    } //end quitGame method
} //end BluFinal class
