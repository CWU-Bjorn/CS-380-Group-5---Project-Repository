package UserInterface;

import javafx.fxml.FXML;

/**
 * this is where the backend logic for the shop will go
 */
public class ShopController {


    /**
     * when gameplay is finished returns to map
     */
    @FXML
    private void onExitShop() {
        SceneManager.switchScene("map.fxml");
    }

}