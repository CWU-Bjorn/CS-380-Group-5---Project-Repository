/**
 * Import of all java library for ease. Will probably change this to what is needed later
 */
package UserInterface;

/**
 * Start of the player class, this creates a private instance of everything in the database. As we go we will likely change the contents in here to better reflect
 * what the game becomes but for now it's a proof of concept.
 */
public class Player{

    private int saveslotRotation;
    private String playerName;
    private int playerHP = 25;
    private String passwordForSaveToSave;
    private boolean Sword;
    private boolean keyIngame;
    private boolean Shield;
    private boolean food;
    private int currencyAmount;
    private int NumberOfCompleatedObstacles;

    /**
     * This is the constructor, built on the many different declared items above.
     */
    public Player(int saveslotRotation, String playerName, int playerHP, String passwordForSaveToSave, boolean Sword, boolean keyIngame,
                  boolean Shield, boolean food, int currencyAmount, int NumberOfCompleatedObstacles){

        this.saveslotRotation = saveslotRotation;
        this.playerName = playerName;
        this.playerHP = playerHP;
        this.passwordForSaveToSave = passwordForSaveToSave;
        this.Sword = Sword;
        this.keyIngame = keyIngame;
        this.Shield = Shield;
        this.food = food;
        this.currencyAmount = currencyAmount;
        this.NumberOfCompleatedObstacles = NumberOfCompleatedObstacles;

    }

    public int getNumberOfCompleatedObstacles(){
        return NumberOfCompleatedObstacles;
    }

    public String getPlayerName(){
        return playerName;
    }
    public int getPlayerHP(){return playerHP;}
    public String getPasswordForSaveToSave(){
        return passwordForSaveToSave;
    }
    public int getCurrencyAmount(){
        return currencyAmount;
    }
    public int getSaveslotRotation(){
        return saveslotRotation;
    }

    /**
     * Setter - setHP. For the enemy event, The enemy attacks and affects the players health
    To affect the player, we need some kind of way to reduce health - Nelson B.
     */

    public void setPlayerHP(int healthAffect){
        this.playerHP += healthAffect;
    }



    /**
     * The actual "print" statement that sends what is read from the database to the screen.
     */

    public String getDisplayToGUI(){
        StringBuilder display = new StringBuilder();

        display.append("Player Name: ").append(playerName).append("\n");
        display.append("Health: ").append(playerHP).append("\n");
        display.append("Currency: ").append(currencyAmount).append("\n");
        display.append("Obstacles finished: ").append(NumberOfCompleatedObstacles).append("\n");

        display.append("Inventory:\n");

        boolean absentItems = false;

        if(Sword){
            display.append(" Sword ");
            absentItems = true;
        }

        if(keyIngame){
            display.append(" Key ");
            absentItems = true;
        }

        if(Shield){
            display.append(" Shield ");
            absentItems = true;
        }

        if(food){
            display.append(" food ");
            absentItems = true;
        }

        if(!absentItems){
            display.append("You have yet to quest and venture out, inventory empty!");

        }

        return display.toString();
    }

    /**
     *This is used to match the password from what the user enters to what is in the database. I call this later in main
     */
    public boolean isPasswordCorrect(String userChecking){
        return passwordForSaveToSave != null && passwordForSaveToSave.equals(userChecking);
    }

    /**
     * Made to see if the save slot is currently in use yet. However, since the test database is fully populated with data I don't call this anywhere yet.
     * Not sure if it works
     */
    public boolean emptySaveCheck(){
        if(playerName==null || playerName.isBlank()){

            System.out.println("Save slot is open!");
        }
        return true;
    }
}


