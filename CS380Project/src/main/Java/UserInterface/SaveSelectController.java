package UserInterface;

import javafx.fxml.FXML;
// java.lang.classfile.Label;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * this is where the backend logic for the saveselect scene will go
 */
public class SaveSelectController {
    /**
     * What allows the GUI to access the save slots. This checks the password and then allows access to the save if it is correct.
     * Once the password is correct the end user is transported to the map.fxml.
     */
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

    /**
     * This is what displays the message that there is no password to the GUI
     */
    @FXML
    private void initialize(){

        showingEmptySlotFromLabel(1,message1);
        showingEmptySlotFromLabel(2,message2);
        showingEmptySlotFromLabel(3,message3);
    }

    /**
     * This is what allows the player to access the game by loading their save, if there is no password then the password the player
     * enters will become the password for that save. If there is a password and they enter the correct one then the player can access
     * the save and associated information.
     */
    private void showingEmptySlotFromLabel(int saveSlotID, Label label){

        Player clientSideUser = DatabaseConnection.loadPlayer(saveSlotID);

        if(!clientSideUser.passwordCheck()){
            label.setText("Empty save slot");

        }

    }

    /**
     *This is a very important method, this is what logs the plays into the save slot. This will access
     * the live player and use that to enter the save slot, there are also some proections however, in hindsight
     * I do not know how useful they are or how necessary they are. But at the time of creation they made sense.
     *
     * This is also what will update to the SQL when there is no password currenctly in the selected save slot.
     * Then they are transposted to the map same as if there is a password and they use the correct one.
     */
    private void tryLogin(int saveSlotID, String password){

        if(password == null || password.isBlank()){
            message.setText("Please enter your password: ");
            return;
        }
        Player clientSideUser = DatabaseConnection.loadPlayer(saveSlotID);



        if(clientSideUser == null){
            message.setText("Error in loading save slot number: " + saveSlotID);
            return;
        }

        if(!clientSideUser.passwordCheck()){
            boolean newSave = DatabaseConnection.emptySave(saveSlotID, password);

            if(newSave){
                Player saveNowLocked = DatabaseConnection.loadPlayer(saveSlotID);

                if(saveNowLocked != null){
                    CurrentPlayerSessionHelperClass.setCurrentPlayer(saveNowLocked);
                    message.setText("Save slot has been successful!");
                    SceneManager.switchScene("map.fxml");
                }
            }
        }

        if(clientSideUser.isPasswordCorrect(password)){
            System.out.println("Password Correct");

            CurrentPlayerSessionHelperClass.setCurrentPlayer(clientSideUser);

            System.out.println("Good Test: " + CurrentPlayerSessionHelperClass.getCurrentPlayer());

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
        SceneManager.switchScene("map.fxml");
    }


    }
