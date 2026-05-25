package UserInterface;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * This is the class that acts as the launcher class
 * sets the stage to be SaveSelect.fxml first, SceneManager then handles switching the scenes
 */
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("THE MOST EPIC GAME EVER.");

        SceneManager.setStage(stage);
        stage.setResizable(false);

        SceneManager.switchScene("SaveSelect.fxml");
        stage.sizeToScene();
    }

    public static void main(String[] args){
        launch(args);
    }
}
