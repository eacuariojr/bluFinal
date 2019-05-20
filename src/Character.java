/*
    Erwin Aquario, Victor Gallardo and Kevin Raya
    May 15, 2019
    Character.java
    Dependencies: Projectile.java
    Credit: none
*/
//----------------------------------------------------------------------------------------------------------------------
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
        this.name = name;
        strength = 20;
        speed = 20;
        endurance = 20;
        health = 100;
        notIncapacitated = true;
        isAlive = true;
    }

    //receives a double to influence random stats
    public Character(double statMultiplier)
    {

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

    //****************RANDOM CHARACTER METHODS****************


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


}
