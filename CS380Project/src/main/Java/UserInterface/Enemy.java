package UserInterface;

import java.util.Scanner;
import java.util.Random;

public class Enemy {
    private String name = "Dummy name";
    private int health = 20;
    private int damage = -2;

    public Enemy(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public String getName() {return name;}
    public int getHealth() {return health;}
    public int getDamage() {return damage;}

    public String toString() {
        return name
                + "\n" + health
                + "\n" + damage;
    }

    public String enemyAttack(String action, Player player){

        StringBuilder actionText = new StringBuilder();
        Random randChance = new Random();

        switch(action.toLowerCase()){
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
            case "flee":
                return "You've decided to flee!";
            case "shield":
                actionText.append("You decided to Shield\n");
                return actionText.toString();
            case "heal":
                actionText.append("You decided to Heal\n");
                player.setPlayerHP(5);
                if(player.getPlayerHP() > 25){
                    player.setPlayerHP(25 -  player.getPlayerHP());
                }
                break;

        }

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
