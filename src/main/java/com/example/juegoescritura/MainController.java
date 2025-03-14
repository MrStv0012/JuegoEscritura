package com.example.juegoescritura;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

/**
 * The MainController class manages the game logic and interactions in the typing game.
 * It handles the game flow including word generation, timers, progress updates,
 * and validating user input.
 */
public class MainController {
    @FXML private Label wordLabel;
    @FXML private TextField inputField;
    @FXML private Label timerLabel;
    @FXML private ProgressBar sunProgress;

    private GameLogic gameLogic;
    private AnimationTimer timer;

    /**
     * Initializes the controller by creating an instance of GameLogic
     * and starting the game.
     */
    @FXML
    public void initialize() {
        gameLogic = new GameLogic();
        startGame();
    }

    /**
     * Starts the game by showing the first word and starting the timer.
     */
    private void startGame() {
        showNewWord();
        startTimer();
    }

    /**
     * Fetches a new random word from the GameLogic and updates
     * the word label with this new word.
     */
    private void showNewWord() {
        String word = gameLogic.getRandomWord();
        wordLabel.setText(word);
    }

    /**
     * Starts a timer that decrements the remaining time every second.
     * When the timer expires, it triggers a timeout handling method.
     */
    private void startTimer() {
        timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 1_000_000_000) {
                    gameLogic.decreaseTime();
                    timerLabel.setText("Tiempo restante: " + gameLogic.getTimeLeft());

                    if (gameLogic.isTimeOut()) {
                        handleTimeOut();
                    }

                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }

    /**
     * Handles the scenario when the timer runs out.
     * Stops the timer, decreases opportunities, updates the progress bar,
     * and either ends the game or resets for a new round.
     */
    private void handleTimeOut() {
        timer.stop();
        gameLogic.decreaseOpportunities();
        updateSunProgress();

        if (gameLogic.isGameOver()) {
            endGame();
        } else {
            showNewWord();
            gameLogic.resetTime();
            startTimer();
        }
    }

    /**
     * Updates the progress bar to reflect the current state of the game
     * based on the player's remaining opportunities.
     */
    private void updateSunProgress() {
        sunProgress.setProgress(gameLogic.getSunProgress());
    }

    /**
     * Ends the game by stopping the interaction, displaying
     * a termination message, and outputting the levels completed.
     */
    private void endGame() {
        wordLabel.setText("Juego Terminado");
        inputField.setDisable(true);
        timerLabel.setText("Niveles completados: " + gameLogic.getCurrentLevel());
    }

    /**
     * Validates the word entered by the user. Checks it against the current word,
     * adjusts the game state accordingly, and handles success or failure scenarios.
     * <p>
     * If the word is validated, the level increases and the time limit may decrease.
     * If invalid, opportunities are decreased, and the game's end state is checked.
     */
    @FXML
    private void validateWord() {
        String inputWord = inputField.getText();
        String currentWord = wordLabel.getText();

        if (gameLogic.validateWord(inputWord, currentWord)) {
            gameLogic.increaseLevel();
            if (gameLogic.shouldDecreaseTime()) {
                gameLogic.decreaseTimeLimit();
            }
            showNewWord();
            inputField.clear();
        } else {
            gameLogic.decreaseOpportunities();
            updateSunProgress();

            if (gameLogic.isGameOver()) {
                endGame();
            } else {
                showNewWord();
                inputField.clear();
            }
        }
    }
}