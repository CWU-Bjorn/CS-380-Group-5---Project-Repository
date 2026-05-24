package UserInterface;

import javafx.fxml.FXML;
import java.lang.classfile.Label;

/**
 * this is where the backend logic for the saveselect scene will go
 */
public class SaveSelectController {
    @FXML
    private Label welcomeText;

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
