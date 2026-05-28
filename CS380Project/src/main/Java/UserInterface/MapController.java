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


        Player currentPlayerVar = CurrentPlayerSessionHelperClass.getCurrentPlayer();

        System.out.println("Good test map: " + currentPlayerVar);

        if(currentPlayerVar == null){
            textAreaForInventory.setText("You are not in inventory!");
            return;
        }
        
        textAreaForInventory.setText(currentPlayerVar.getDisplayToGUI());

        }

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



