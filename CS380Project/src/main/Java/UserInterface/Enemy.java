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
                    health -= 2;
                }
                else{
                    actionText.append("No Luck! You've Missed!\n");
                }
                break;
            case "flee":
                return "You've decided to flee!";
            case "shield":
                actionText.append("You decided to Shield\n");
                break;
            case "heal":
                actionText.append("You decided to Heal\n");
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



        /*
        while (health != 0){

            if(health == 0){
                System.out.println("=====Enemy has been vanquished!=====");
                break;
            }

            System.out.println(toString());
            System.out.print("Enemy is preparing to attack... What will you do?");
            userChoice = userInp.nextLine();

            if(userChoice.equalsIgnoreCase("run away")){
                System.out.println("You decided to run away");
                break;
            }

            if(userChoice.equalsIgnoreCase("shield")){
                System.out.println("You decided to shield");
            }

            if (userChoice.equalsIgnoreCase("attack")){
                int chance = randChance.nextInt(10);
                if (chance >=5){
                    health -= 2;
                }
                else{
                    System.out.println("No Luck! You've Missed!");
                }

            }

        }
        */

    }

}
