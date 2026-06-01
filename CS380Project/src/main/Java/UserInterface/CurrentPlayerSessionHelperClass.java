package UserInterface;

/**
 * Creation of a session for the player, this helper class is what allows the end user to access the save slot and live update as it sets the Player to a currentPlayer
 * In player.java the creation of the player is created here it becomes an instance
 */
public class CurrentPlayerSessionHelperClass {
    private static Player currentPlayer;

    public static void setCurrentPlayer(Player Player){
        currentPlayer = Player;
    }
    public static Player getCurrentPlayer(){
        return currentPlayer;
}
public static void clearCurrentPlayer(){
    currentPlayer = null;
}
}
