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
    private String passwordForSaveToSave;
    private String storedItem1;
    private String storedItem2;
    private String storedItem3;
    private String storedItem4;
    private String storedItem5;
    private String storedItem6;
    private int currencyAmount;
    private int NumberOfCompleatedObstacles;

    /**
     * This is the constructor, built on the many different declared items above.
     */
    public Player(int saveslotRotation, String playerName, String passwordForSaveToSave, String storedItem1, String storedItem2,
                  String storedItem3, String storedItem4, String storedItem5, String storedItem6, int currencyAmount, int NumberOfCompleatedObstacles){

        this.saveslotRotation = saveslotRotation;
        this.playerName = playerName;
        this.passwordForSaveToSave = passwordForSaveToSave;
        this.storedItem1 = storedItem1;
        this.storedItem2 = storedItem2;
        this.storedItem3 = storedItem3;
        this.storedItem4 = storedItem4;
        this.storedItem5 = storedItem5;
        this.storedItem6 = storedItem6;
        this.currencyAmount = currencyAmount;
        this.NumberOfCompleatedObstacles = NumberOfCompleatedObstacles;

    }

    public int getNumberOfCompleatedObstacles(){
        return NumberOfCompleatedObstacles;
    }

    public String getPlayerName(){
        return playerName;
    }

    public String getPasswordForSaveToSave(){
        return passwordForSaveToSave;
    }
    public String getStoredItem1(){
        return storedItem1;
    }
    public String getStoredItem2(){
        return storedItem2;
    }
    public String getStoredItem3(){
        return storedItem3;
    }
    public String getStoredItem4(){
        return storedItem4;
    }
    public String getStoredItem5(){
        return storedItem5;
    }
    public String getStoredItem6(){
        return storedItem6;
    }
    public int getCurrencyAmount(){
        return currencyAmount;
    }
    public int getSaveslotRotation(){
        return saveslotRotation;
    }

    /**
     * The actual "print" statement that sends what is read from the database to the screen.
     */

    public String getDisplayToGUI(){
        return
                "\nAmount of currency in satchel: " + currencyAmount +
                "\nItems collected: " + storedItem1 + ", " + storedItem2 + ", " + storedItem3
                        + ", " + storedItem4 + ", " + storedItem5 + ", " + storedItem6;
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


