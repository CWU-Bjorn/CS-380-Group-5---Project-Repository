package UserInterface;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * this class is where the backend logic for the map goes
 */
public class MapController {
    
 private boolean doorOpened = false;


    @FXML
    private void completion(){

        Player player =
                CurrentPlayerSessionHelperClass.getCurrentPlayer();

        if (player == null) return;

        int obstacles = player.getNumberOfCompleatedObstacles();

        boolean hasWon =
                player.isDoorOpened() && obstacles >= 3;

        if (hasWon) {

            SceneManager.switchScene("endGame.fxml");

        } else {

            textAreaForInventory.setText(
                    "You cannot finish yet!\n\n" +
                            "Requirements:\n" +
                            "- Open the door\n" +
                            "- Defeat at least 3 enemies\n\n" +
                            "Progress:\n" +
                            "Obstacles: " + obstacles + "/3\n" +
                            "Door opened: " + player.isDoorOpened()
            );
        }
    }

    /**
     * changes scene to Gameplay.fxml when selecting location
     */
    @FXML
    private TextArea textAreaForInventory;

    //Updates the player when action is clicked
    @FXML
    private void buttonActionClick(){

        playerUpdate();
        }

    @FXML
    private void onEnterConflictClick() {
        SceneManager.switchScene("gameplay.fxml");
    }

    /**
     * changes scene back to save selection
     */
    @FXML
    private void onBackToMenuClick() {
        SceneManager.switchScene("saveSelect.fxml");
    }

    @FXML
    private void onShopClick() {
        SceneManager.switchScene("shop.fxml");
    }

    /**
     * This is how the player is updated when a change happens to the inventory, this will live update the GUI with
     * the CurrentPlayerSessionHelperClass.java
     */
    private void playerUpdate(){
        Player currentPlayerUpdate = CurrentPlayerSessionHelperClass.getCurrentPlayer();

        int updateToSave = currentPlayerUpdate.getSaveslotRotation();

        Player newPlayerPostUpdate = DatabaseConnection.loadPlayer(updateToSave);

        CurrentPlayerSessionHelperClass.setCurrentPlayer(newPlayerPostUpdate);

        assert newPlayerPostUpdate != null;
        textAreaForInventory.setText(newPlayerPostUpdate.getDisplayToGUI());
    }

    @FXML
    private void onPuzzleClick(){
        SceneManager.switchScene("puzzle.fxml");
    }

    /**
     * This is what is linked to the door button. This uses the key if the player has it thuis fulfilling the other requirment for compleating the gmae. 
     */
    @FXML
    public void keyChecking(){

        Player currentPlayerUpdate =
                CurrentPlayerSessionHelperClass.getCurrentPlayer();

        boolean hasKey =
                DatabaseConnection.updateMethodForItems(
                        currentPlayerUpdate.getSaveslotRotation(),
                        "Key",
                        false,
                        0
                );

        if (hasKey) {

            currentPlayerUpdate.setDoorOpened(true);

            textAreaForInventory.setText("You have opened the door!");

        } else {

            textAreaForInventory.setText("The door blocks your path");
        }
    }
    }



