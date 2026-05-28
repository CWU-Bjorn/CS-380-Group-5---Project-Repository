package UserInterface;

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
