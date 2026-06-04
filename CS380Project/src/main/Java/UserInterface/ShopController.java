package UserInterface;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * this is where the backend logic for the shop will go
 */
public class ShopController {


    @FXML
    private TextArea textAreaForInventory;

    /**
     * when player presses back returns to map
     */
    @FXML
    private void onExitShop() {
        SceneManager.switchScene("map.fxml");
    }

    @FXML
    private void checkInventoryClick() {
        playerUpdate();
    }


    private void playerUpdate(){
        Player currentPlayerUpdate = CurrentPlayerSessionHelperClass.getCurrentPlayer();

        int updateToSave = currentPlayerUpdate.getSaveslotRotation();

        Player newPlayerPostUpdate = DatabaseConnection.loadPlayer(updateToSave);

        CurrentPlayerSessionHelperClass.setCurrentPlayer(newPlayerPostUpdate);

        assert newPlayerPostUpdate != null;
        textAreaForInventory.setText(newPlayerPostUpdate.getDisplayToGUI());
    }

    /**
     * Method for buying the sword item. This will deduct currency from the player
     */
    @FXML
    private void UpdateForSwordToGUIAdd() {
        Player currentPlayerUpdate = CurrentPlayerSessionHelperClass.getCurrentPlayer();

        boolean addedSword = DatabaseConnection.updateMethodForItems(currentPlayerUpdate.getSaveslotRotation(),"Sword", true,-20);

        if(addedSword){
            playerUpdate();
        }

    }

    /**
     * Method for selling the sword, this will add currency to the currency variable and will remove the sword from the inventory
     */
    @FXML
    private void UpdateForSwordToGUIRemove() {
        Player currentPlayerUpdate = CurrentPlayerSessionHelperClass.getCurrentPlayer();

        boolean removeSword = DatabaseConnection.updateMethodForItems(currentPlayerUpdate.getSaveslotRotation(),"Sword", false,20);

        if(removeSword){
            playerUpdate();
        }

    }

    /**
     * Method for buying the key same as buying the sword method this will add it to the inventory
     */
    @FXML
    private void UpdateForKeyToGUIAdd() {
        Player currentPlayerUpdate = CurrentPlayerSessionHelperClass.getCurrentPlayer();

        boolean addedKey = DatabaseConnection.updateMethodForItems(currentPlayerUpdate.getSaveslotRotation(),"Key", true,-30);

        if(addedKey){
            playerUpdate();
        }

    }

    /**
     * Method for selling the key. Will remove it from the inventory
     */
    @FXML
    private void UpdateForKeyToGUIRemove() {
        Player currentPlayerUpdate = CurrentPlayerSessionHelperClass.getCurrentPlayer();

        boolean removeKey = DatabaseConnection.updateMethodForItems(currentPlayerUpdate.getSaveslotRotation(),"Key", false,30);

        if(removeKey){
            playerUpdate();
        }

    }

    /**
     * Method to buy the shield
     */
    @FXML
    private void UpdateForShieldToGUIAdd() {
        Player currentPlayerUpdate = CurrentPlayerSessionHelperClass.getCurrentPlayer();

        boolean addedShield = DatabaseConnection.updateMethodForItems(currentPlayerUpdate.getSaveslotRotation(),"Shield", true,-25);

        if(addedShield){
            playerUpdate();
        }

    }

    /**
     * Method to sell the shield
     */
    @FXML
    private void UpdateForShieldToGUIRemove() {
        Player currentPlayerUpdate = CurrentPlayerSessionHelperClass.getCurrentPlayer();

        boolean removeShield = DatabaseConnection.updateMethodForItems(currentPlayerUpdate.getSaveslotRotation(),"Shield", false,25);

        if(removeShield){
            playerUpdate();
        }

    }

    /**
     * Method to buy food
     */
    @FXML
    private void UpdateForFoodToGUIAdd() {
        Player currentPlayerUpdate = CurrentPlayerSessionHelperClass.getCurrentPlayer();

        boolean addedFood = DatabaseConnection.updateMethodForItems(currentPlayerUpdate.getSaveslotRotation(),"Food", true, -5);

        if(addedFood){
            playerUpdate();
        }

    }

    /**
     * Method to eat the food. This uses the class in DatabaseConnection for adding hp to the player rather than selling the food for currency.
     */
    @FXML
    private void UpdateForFoodToGUIRemove() {
        Player currentPlayerUpdate = CurrentPlayerSessionHelperClass.getCurrentPlayer();

        boolean removeFood = DatabaseConnection.updateMethodForItems(currentPlayerUpdate.getSaveslotRotation(),"Food", false, 5);

        if(removeFood){
            playerUpdate();
        }

    }

}