package UserInterface;

import javafx.fxml.FXML;

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
    private void onEnterLocationClick() {
        SceneManager.switchScene("gameplay.fxml");
    }

    /**
     * changes scene back to save selection
     */
    @FXML
    private void onBackToMenuClick() {
        SceneManager.switchScene("saveSelect.fxml");
    }
}