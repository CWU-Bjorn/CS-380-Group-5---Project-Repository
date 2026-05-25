import java.util.Scanner;

public class Enemy {
    private String name = "Dummy name";
    private int health = 20;
    private int damage = 2;

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

        Scanner userInp = new Scanner(System.in);
        String userChoice = "";

        while (health != 0){

            System.out.println(toString());
            System.out.print("Enemy is preparing to attack... What will you do?");
            userChoice = userInp.nextLine();

            if (userChoice.equalsIgnoreCase("attack")){
                health -= 2;
            }

            if(health == 0){
                System.out.println("=====Enemy has been vanquished!=====");
            }
        }
    }
    
}
