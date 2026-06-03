package UserInterface;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * this class is where the backend logic for the map goes
 */
public class MapController {


    @FXML
    private void completion(){}

    /**
     * changes scene to Gameplay.fxml when selecting location
     */
    @FXML
    private TextArea textAreaForInventory;

    @FXML
    private void buttonActionClick(){

        playerUpdate();
//        Player currentPlayerVar = CurrentPlayerSessionHelperClass.getCurrentPlayer();
//
//        System.out.println("Good test map: " + currentPlayerVar);
//
//        if(currentPlayerVar == null){
//            textAreaForInventory.setText("You are not in inventory!");
//            return;
//        }
//
//        textAreaForInventory.setText(currentPlayerVar.getDisplayToGUI());

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

        boolean removeFood = DatabaseConnection.updateToFood(currentPlayerUpdate.getSaveslotRotation(),10);

        if(removeFood){
            playerUpdate();
        }

    }

    /**
     * This is what is linked to the door button. This uses the key if the player has it thuis fulfilling the other requirment for compleating the gmae. 
     */
    @FXML
    public void keyChecking(){
        Player currentPlayerUpdate = CurrentPlayerSessionHelperClass.getCurrentPlayer();

        boolean hasKey = DatabaseConnection.updateMethodForItems(currentPlayerUpdate.getSaveslotRotation(),"Key", false,0);

        if(hasKey){
            textAreaForInventory.setText("You have opened the door!");
        }else{
            textAreaForInventory.setText("The door blocks your path");
        }
    }

    }



