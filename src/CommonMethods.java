/*
    Erwin Aquario, Victor Gallardo
    May 15, 2019
    Game.java
    Dependencies: CommonMethods.java, Character.java, Party.java, Projectile.java
    Description: this class contains all the methods that would be reused
    throughout multiple classes
    Credit: none
*/
//----------------------------------------------------------------------------------------------------------------------
import java.util.InputMismatchException;
import java.util.Scanner;

public class CommonMethods
{
    private static Scanner input = new Scanner(System.in);

    public static int getChoice(int maxChoice)
    {
        //this method returns an integer within bounds [1, maxChoice]
        //based off of the user's choice

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
            input.nextLine();
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
                input.nextLine();
                //the full error message is "Must enter number. Invalid choice, try again."
            }
        }

        input.nextLine();   //resets scanner for pauseProgram method
        return choice;
    }//end method getChoice

    public static void pauseProgram()
    {
        System.out.println("Press enter to continue...");
        input.nextLine();
    }//end pauseProgram

    public static boolean promptYes(String prompt)
    {
        //this method prompts the user and asks for a "Yes or No" confirmation
        //returns true if yes, and false if no

        char userResponse;

        System.out.printf(prompt);
        //asks user for confirmation
        System.out.print("(Y) or (N): ");
        userResponse = input.next().toUpperCase().charAt(0);

        //evaluates user response

        switch(userResponse)
        {
            case 'Y': return true;
            case 'N': return false;
            default: System.out.println("Invalid response. Try again.");
                     return promptYes(prompt);  //recursion eventually results in returning T or F
        }

    }//end method promptYes

    public static boolean promptYes()
    {
        //this method prompts the user and asks for a "Yes or No" confirmation
        //returns true if yes, and false if no

        char userResponse;

        //asks user for confirmation
        System.out.print("(Y) or (N): ");
        userResponse = input.next().toUpperCase().charAt(0);

        //evaluates user response

        switch(userResponse)
        {
            case 'Y': return true;
            case 'N': return false;
            default: System.out.println("Invalid response. Try again.");
                return promptYes();  //recursion eventually results in returning T or F
        }

    }//end method promptYes
}
