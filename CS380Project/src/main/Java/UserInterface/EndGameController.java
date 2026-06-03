package UserInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndGameController {
    @FXML
    private Label statsLabel;

    @FXML
    private void onReturnMenuClick() {
        SceneManager.switchScene("map.fxml");
    }

    @FXML
    private void onExitClick() {
        SceneManager.switchScene("saveSelect.fxml");
    }
    @FXML
    public void initialize() {

        Player player = CurrentPlayerSessionHelperClass.getCurrentPlayer();

        if (player == null) {
            statsLabel.setText("No player data found.");
            return;
        }

        String keyStatus = player.getKey() ? "Yes" : "No";

        statsLabel.setText(
                "Player: " + player.getPlayerName() + "\n" +
                        "Obstacles Completed: " + player.getNumberOfCompleatedObstacles() + "\n" +
                        "Currency: " + player.getCurrencyAmount() + "\n" +
                        "Final HP: " + player.getPlayerHP() + " / 25\n" +
                        "Key Collected: " + keyStatus
        );
    }
}
