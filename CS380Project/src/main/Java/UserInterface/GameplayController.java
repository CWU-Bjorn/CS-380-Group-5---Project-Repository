package UserInterface;

import javafx.fxml.FXML;

/**
 * this is where the backend logic for the gameplay will go
 */
public class GameplayController {


    /**
     * when gameplay is finished returns to map
     */
    @FXML
    private void onExitGameplayLoop() {
        SceneManager.switchScene("map.fxml");
    }

}