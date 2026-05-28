package UserInterface;

import javafx.fxml.FXML;
// java.lang.classfile.Label;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * this is where the backend logic for the saveselect scene will go
 */
public class SaveSelectController {

    @FXML
    private PasswordField saveSlotOne;

    @FXML
    private PasswordField saveSlotTwo;

    @FXML
    private PasswordField saveSlotThree;

    @FXML
    private Label message;
    @FXML
    private Label message1;
    @FXML
    private Label message2;
    @FXML
    private Label message3;

    @FXML
    private void clickUsageForSaveOne(){
        tryLogin(1, saveSlotOne.getText());
    }

    @FXML
    private void clickUsageForSaveTwo(){
        tryLogin(2, saveSlotTwo.getText());
    }

    @FXML
    private void clickUsageForSaveThree(){
        tryLogin(3, saveSlotThree.getText());
    }

    private void tryLogin(int saveSlotID, String password){
        Player clientSideUser = DatabaseConnection.loadPlayer(saveSlotID);

        if(clientSideUser == null){
            message.setText("Error in loading save slot number: " + saveSlotID);
            return;
        }

        if(clientSideUser.isPasswordCorrect(password)){
            System.out.println("Password Correct");

            String getPlayerNameVar = clientSideUser.getPlayerName();
            System.out.println("Character Name: " + getPlayerNameVar);

            int NumberOfCompleatedObstaclesVar = clientSideUser.getNumberOfCompleatedObstacles();
            System.out.println("Save progress indicator: " + NumberOfCompleatedObstaclesVar);

            int currencyAmountVar = clientSideUser.getCurrencyAmount();
            System.out.println("Money in coin purse: " + currencyAmountVar);



            SceneManager.switchScene("map.fxml");
        }else{
            // creates array of message labels and prints to the correct one based on saveSlot
            new Label[]{message1, message2, message3}[saveSlotID - 1].setText("Password does not match");
        }
    }
    /**
     * switches to map scene
     * This ideally will be used when a password is entered correctly
     */
    @FXML
    private void onSelectSaveClick() {
        // Once a user picks a save slot, transition directly to the map
        SceneManager.switchScene("Map.fxml");
    }


    }
