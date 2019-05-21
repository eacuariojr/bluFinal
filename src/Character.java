/*
    Erwin Aquario, Victor Gallardo and Kevin Raya
    May 15, 2019
    Character.java
    Dependencies: Projectile.java
    Credit: none
*/
//----------------------------------------------------------------------------------------------------------------------
import java.util.Random;

public class Character
{
    private String name;
    private int strength;
    private int speed;
    private int endurance;
    private double health;
    private final double MAX_HEALTH = 100;  //for this program, the max health is always 100
    private boolean notIncapacitated;
    private boolean isAlive;

    //****************CONSTRUCTORS****************
    //this constructor creates a player's starter character with a certain name
    public Character(String name)
    {
        final int DEFAULT_BASE = 20;    //the default base stats constant

        this.name = name;
        strength = DEFAULT_BASE;
        speed = DEFAULT_BASE;
        endurance = DEFAULT_BASE;
        //all new characters are alive and at max health
        health = MAX_HEALTH;
        notIncapacitated = true;
        isAlive = true;
    }

    //receives a double to influence random stats
    public Character(double statMultiplier)
    {
        //the double influences stats. A value of 1 has an average of 20 on each stat
        //when statMultiplier = 5, the average stats are 100

        name = randomName();
        randomStats(statMultiplier);
        //all new characters are alive and max health
        health = MAX_HEALTH;
        notIncapacitated = true;
        isAlive = true;
    }

    //used to load a character
    public Character(String name, int strength, int speed, int endurance,
                     double health, boolean notIncapacitated, boolean isAlive)
    {
        this.name = name;
        this.strength = strength;
        this.speed = speed;
        this.endurance = endurance;
        this.health = health;
        this.notIncapacitated = notIncapacitated;
        this.isAlive = isAlive;
    }


    //****************RANDOM CHARACTER METHODS****************
    private String randomName()
    {
        //in the future I'd like random name to accept a double which influences what names
        //could be chosen
        Random randGen = new Random();

        //list of random names
        String[] randomName = {"Philbert", "Truffles", "Wombowski", "Panini", "Ruffleo",
                "Ruskin", "Hubert", "Bob", "Javaman", "Qwerty", "Kevin",
                "Victor", "Jessica", "Erwin", "E.I.E.I.O.", "Vagrant",
                "XYZZY", "Krogan", "Banani", "Albin",
                "Brfxxccxxmnpcccclllmmnprxvclmnckssqlbb11116"};  //last name is pronounced "Albin"

        return randomName[randGen.nextInt(randomName.length)];
    }

    private void randomStats(double statMultiplier)
    {
        //this method assists the constructor. It creates 3 random stats for a character based on the multiplier
        Random randGen = new Random();
        final int BASE_STAT  = 20;
        final double STAT_VARIANCE = 0.2;
        double randomStatValue;

        //fun math huh :^)
        //generates a random number in the range  0.8 - 1.2 (1 +- STAT_VARIANCE)
        randomStatValue = (((randGen.nextDouble() * 2) - 1) * STAT_VARIANCE) + 1;
        //then gets that value, and multiplies base stat value and the stat multiplier from the parameter
        randomStatValue *= BASE_STAT;
        randomStatValue *= statMultiplier;

        strength = (int) randomStatValue;


        //repeat the above code for speed and endurance
        randomStatValue = (((randGen.nextDouble() * 2) - 1) * STAT_VARIANCE) + 1;
        randomStatValue *= BASE_STAT;
        randomStatValue *= statMultiplier;
        speed = (int)randomStatValue;

        randomStatValue = (((randGen.nextDouble() * 2) - 1) * STAT_VARIANCE) + 1;
        randomStatValue *= BASE_STAT;
        randomStatValue *= statMultiplier;
        endurance = (int)randomStatValue;
    }

    //****************METHODS****************

    //this method is used when a character attacks another character
    public void attack(Character target)
    {
        //first, creates a projectile
        Projectile theAttack = new Projectile(this);

        target.takeHit(theAttack);
    }

    //this method is the only way a character can get damaged (regularly)
    private void takeHit(Projectile attack)
    {
        final double FATAL_MULTI = -0.25;   //the multiplier that sets the threshold before a character dies

        health -= attack.getDamage();

        if (health <= MAX_HEALTH * FATAL_MULTI)
        {
            //after character reaches a damage threshold (-25 health), they die
            this.kill();
        }
        else if (health <= 0)
        {
            //if character reaches 0 or less health, they get incapacitated
            notIncapacitated = false;
        }
    }

    public String simplePrint()
    {
        String simplePrint = name + ": " + health + "/100";

        return simplePrint;
    }//end method simplePrint

    public String detailedPrint()
    {
        String detailedPrint = simplePrint();

        detailedPrint += "\n" +
                "Strength:  " + strength + "\n" +
                "Speed:     " + speed + "\n" +
                "Endurance: " + endurance + "\n" +
                "Status:    ";

        if (isAlive)
            detailedPrint += "ALIVE";
        else
            detailedPrint += "DEAD";

        return detailedPrint;
    }//end method detailedPrint

    public String writeData()
    {
        String characterData;

        characterData = "-- " + name + " -- " + strength + " -- " + speed +
                " -- " + endurance  + " -- " + health  + " -- " +
                notIncapacitated  + " -- " + isAlive + "\n";

        return characterData;
    }//end method writeData


    //****************GETTERS & SETTERS****************
    public String getName()
    {
        return name;
    }

    public int getStrength()
    {
        return strength;
    }

    public int getSpeed()
    {
        return speed;
    }

    public int getEndurance()
    {
        return endurance;
    }

    public double getHealth()
    {
        return health;
    }

    public boolean notIncapacitated()
    {
        return notIncapacitated;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public void kill()
    {
        isAlive = false;
    }

    public void addStrength(int amountAdded)
    {
        strength += amountAdded;
    }

    public void addSpeed(int amountAdded)
    {
        speed += amountAdded;
    }

    public void addEndurance(int amountAdded)
    {
        endurance += amountAdded;
    }
}
