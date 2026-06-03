package UserInterface;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.xml.crypto.Data;

import static UserInterface.DatabaseConnection.obstacleUpdates;

/**
 * this is where the backend logic for the gameplay will go
 */
public class GameplayController {
    @FXML private Label enemyNameDisp;
    @FXML private Label playerNameLabel;
    @FXML private TextArea dialogueText;
    @FXML private Button attackAction;
    @FXML private Button shieldAction;
    @FXML private Button fleeAction;
    @FXML private Button healAction;
    @FXML private ProgressBar healthBar;
    @FXML private ProgressBar enemyBar;

    private Enemy enemy;
    private Player player;
    private boolean flipForObstacles;

    @FXML public void initialize() {
        enemy = new Enemy("Werewolf", 30, -1);
        player = CurrentPlayerSessionHelperClass.getCurrentPlayer();
        dialogueText.setText("You come across a " + enemy.getName() + "!\nMake A Move!\n");
        if(player != null) {
            updateUI();
        }else{
            System.out.println("Player is null");
        }

    }

    public void setPlayer(Player player) {
        this.player = player;
        updateUI();
    }

    @FXML private void attack(){ action("attack");}
    @FXML private void shield(){ action("shield");}
    @FXML private void flee() { action("flee");}
    @FXML private void health() { action("heal");}

    @FXML private void action(String action) {
        String actionTaken = enemy.enemyAttack(action, player);

        if(action.equalsIgnoreCase("flee")){
            dialogueText.setText("You've decided to flee");
            disableButtons();
            return;
        }

        dialogueText.appendText(actionTaken + "\n");
        updateUI();
        /**
         * Checks the enemy health and implements the obstacles compleated by one for every enemy compleated. This will tie into the end of the game and how the player
         * wins the game.
         */
        if(enemy.getHealth() <= 0 && !flipForObstacles) {
            flipForObstacles = true;

            boolean addingToObsticle = DatabaseConnection.obstacleUpdates(player.getSaveslotRotation());
            boolean playerReward = DatabaseConnection.currencyOnEnemyDefeat(player.getSaveslotRotation(), 10);

            if (addingToObsticle && playerReward) {

                Player playerRefresh = DatabaseConnection.loadPlayer(player.getSaveslotRotation());


            if (playerRefresh != null) {
                CurrentPlayerSessionHelperClass.setCurrentPlayer(playerRefresh);
                player = playerRefresh;
            }
                dialogueText.setText("===Enemy Defeated===");

        }
            updateUI();
            disableButtons();
            return;
        }

        if(player.getPlayerHP() <= 0){
            dialogueText.setText("===Player Defeated===");
        }
        updateUI();
    }

    private void updateUI(){
        enemyNameDisp.setText(enemy.getName());
        playerNameLabel.setText(player.getPlayerName());
        healthBar.setProgress((double)player.getPlayerHP()/ 25.0);
        enemyBar.setProgress((double)enemy.getHealth()/ 25.0);
    }

    private void disableButtons(){
        attackAction.setDisable(true);
        shieldAction.setDisable(true);
        fleeAction.setDisable(true);
        healAction.setDisable(true);
    }


    /**
     * when gameplay is finished returns to map
     */
    @FXML
    private void onExitGameplayLoop() {
        SceneManager.switchScene("map.fxml");
    }

}