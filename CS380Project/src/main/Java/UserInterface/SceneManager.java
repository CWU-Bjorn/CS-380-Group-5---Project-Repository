package UserInterface;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

/**
 * This class manages the switching between scenes
 */
public class SceneManager {
    private static Stage primaryStage;

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    /**
     * finds fxml url and switches to new scene
     * automatically sets application size to size of new scene
     */
    public static void switchScene(String fxmlFile) {
        try {

            URL fxmlLocation = SceneManager.class.getResource("/" + fxmlFile);

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            if (primaryStage.getScene() == null) {
                primaryStage.setScene(new Scene(root));
            } else {
                primaryStage.getScene().setRoot(root);
            }

            primaryStage.sizeToScene();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}