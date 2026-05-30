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

    public String toString() {
        return name
                + "\n" + health
                + "\n" + damage;
    }

    public void enemyAttack(Player player){

        System.out.println("===ENEMY ENCOUNTERED!===");

        Random randChance = new Random();

        Scanner userInp = new Scanner(System.in);
        String userChoice = "";

        while (health != 0){

            if(health == 0){
                System.out.println("=====Enemy has been vanquished!=====");
                break;
            }

            System.out.println(toString());
            System.out.print("Enemy is preparing to attack... What will you do?");
            userChoice = userInp.nextLine();


            if (userChoice.equalsIgnoreCase("attack")){
                int chance = randChance.nextInt(10);
                if (chance >=5){
                    health -= 2;
                }
                else{
                    System.out.println("No Luck! You've Missed!");
                }

            }

            int enemyChance =  randChance.nextInt(10);
                if(enemyChance >=5){
                    System.out.println("Enemy has struck you!");
                    player.setPlayerHP(damage);
                }else{
                    System.out.println("Enemy Missed!");
                }


        }
    }

}
