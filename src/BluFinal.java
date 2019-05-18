/*
    Erwin Aquario, Victor Gallardo and Kevin Raya
    May 15, 2019
    BluFinal.java
    Dependencies: Game.java, Party.java, Character.java, Projectile.java
    Credit: none
*/
//----------------------------------------------------------------------------------------------------------------------
import java.util.Scanner;

public class BluFinal //start BluFinal class
{
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) //start main method
    {
        printMenu();
        int choice = getChoice();

        switch(choice) //start switch statement
        {
            case 1:
                System.out.println("Hello!");
                break;
        } //end switch statement
    } //end main method

    private static void printMenu() //start printMenu method
    {
        System.out.println("*****MAIN MENU*****");
        System.out.println("1.New Game");
        System.out.println("2.Load Game");
        System.out.println("3.Score Board");
        System.out.println("4.Quit");
    } //end printMenu method

    private static int getChoice() //start getChoice method
    {
        System.out.print("\nEnter your choice: ");
        int choice = input.nextInt();
        return choice;
    } //end getChoice method
} //end BluFinal class
