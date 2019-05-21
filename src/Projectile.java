/*
    Erwin Aquario, Victor Gallardo and Kevin Raya
    May 15, 2019
    Projectile.java
    Dependencies: Character.java
    Credit: none
*/
//----------------------------------------------------------------------------------------------------------------------
public class Projectile
{
    private Character attacker;
    private double damage;

    //****************CONSTRUCTORS****************
    public Projectile(Character attacker)
    {
        final double DAMAGE_MULTI = 0.80;   //a damage modifier


        this.attacker = attacker;
        damage = attacker.getStrength() * DAMAGE_MULTI;
    }

    //****************GETTERS & SETTERS****************
    public double getDamage()
    {
        return damage;
    }
}


