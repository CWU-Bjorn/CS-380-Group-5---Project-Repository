package UserInterface;

import java.util.Scanner;
import java.util.Random;

/*Enemy Class
Sets up an enemy and contains an attack event for interactions
between the player
 */

public class Enemy {

    //Enemy Attributes
    private String name = "Dummy name";
    private int health = 20;
    private int damage = -2;

    //Constructor
    public Enemy(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    //Getter methods to retrieve enemy attributes
    public String getName() {return name;}
    public int getHealth() {return health;}
    public int getDamage() {return damage;}

    //toString Method to combine attributes into a string variable
    public String toString() {
        return name
                + "\n" + health
                + "\n" + damage;
    }

    /*EnemyAttack attribute
      Places the player an enemy into an attack event
      Uses the player and action as parameters
     */
    public String enemyAttack(String action, Player player){

        //actionText. StringBuilder variable to display action taken by players
        StringBuilder actionText = new StringBuilder();

        //Random randChance to determine attack success for player
        Random randChance = new Random();

        /* Switch block
           Determines action based on string given by parameter
           Using GUI buttons to hold values to then pass on to the parameters
         */

        switch(action.toLowerCase()){

            //Attack Case. Uses Random value for attack chance as well as scales damage
            case "attack":
                int chance = randChance.nextInt(10);
                if (chance >=5){

                    int damageModifyerCalc = player.attackDamageForModification();

                    health -= damageModifyerCalc;
                }
                else{
                    actionText.append("No Luck! You've Missed!\n");
                }
                break;

            //Flee. Simply Exits the interaction
            case "flee":
                return "You've decided to flee!";

            //Shield Block. Prevents damage given to player
            case "shield":
                actionText.append("You decided to Shield\n");
                return actionText.toString();

            //Heal Block. Heals the player when executed
            case "heal":
                actionText.append("You decided to Heal\n");
                player.setPlayerHP(5);
                if(player.getPlayerHP() > 25){
                    player.setPlayerHP(25 -  player.getPlayerHP());
                }
                break;

        }

        /*If-else statements.
          Checks enemy health. If enemy still has health, enemy may attack
          Also checks to see if enemy is vanquished
         */
        if(health > 0) {
            int enemyChance = randChance.nextInt(10);
            if (enemyChance >= 5) {
                actionText.append("Enemy has struck you!");
                player.setPlayerHP(damage);
            } else {
                actionText.append("Enemy Missed!");
            }
        }else{
            actionText.append("Enemy Has Been Defeated!");
        }
        return actionText.toString();

    }

}
