package UserInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PuzzleController {
    @FXML private Button confirm;
    @FXML private TextField wordGuess;
    @FXML private Label hiddenWordDisplay;

    private Player player;
    private Puzzle puzzle = new Puzzle();

    @FXML
    private void initialize(){
        puzzle.puzzleEvent();
        hiddenWordDisplay.setText(puzzle.getHiddenWord());
    }

    @FXML
    private void handleGuess(){
        String userGuess = wordGuess.getText().trim().toLowerCase();

        if(userGuess.isEmpty()) {return ;}

        boolean success = puzzle.guessLetter(userGuess.charAt(0));
        hiddenWordDisplay.setText(puzzle.getHiddenWord());
        wordGuess.clear();

        if(success) {
            hiddenWordDisplay.setText("Word Correct! Answer: " + puzzle.getWordToGuess());
            confirm.setDisable(true);
            wordGuess.setDisable(true);
        }
    }

    @FXML
    private void onExitGameplayLoop() {
        SceneManager.switchScene("map.fxml");
    }

}
