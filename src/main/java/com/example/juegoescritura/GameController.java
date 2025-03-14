package com.example.juegoescritura;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.Random;

/**
 * The `GameController` class manages the core logic and UI interactions for the typing game.
 * It handles user input, updates the UI, manages game states such as level and score,
 * and enforces game rules including timing and error handling.
 */

public class GameController {

    @FXML private Label levelLabel;
    @FXML private Label scoreLabel;
    @FXML private Label timeLabel;
    @FXML private Label wordLabel;
    @FXML private Label sunLabel;
    @FXML private TextField inputField;

    private final String[] words = { "Luz", "Mil", "Rey", "Voz", "Pan", "Sol",
            "Aprender", "Nivel", "Juego", "Tiempo", "Código", "Rápido", "Texto", "JavaFX",
            "Escritura", "Xilofono", "Rápidamente", "Camaleónicamente", "Cálidamente", "Anímicamente", "Devuélveselas"};
    private int level = 1;
    private int score = 0;
    private int errors = 0;
    private int timeLeft = 20;
    private int initialTime = 20;
    private Timeline timeline;
    private final Random random = new Random();

    /**
     * Initializes the game by setting up event handlers, preparing the UI, and starting the timer.
     * This method is automatically called by JavaFX after the FXML file has been loaded.
     *
     */
    public void initialize() {
        updateUI();
        setNewWord();
        startTimer();

        inputField.setOnAction(e -> checkWord());
    }

    /**
     * Starts the countdown timer for the game, updating the time label each second.
     * If the timer reaches zero, it triggers the handling of an incorrect attempt.
     *
     */
    private void startTimer() {
        if (timeline != null) {
            timeline.stop();
        }
        timeLeft = initialTime;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeLeft--;
            timeLabel.setText(" " + timeLeft + "s");
            if (timeLeft <= 0) {
                handleIncorrect();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Validates the word entered by the user. If correct, updates the score, level, and timer.
     * If incorrect, it triggers the logic for incorrect answers.
     * Clears the input field after each attempt.
     *
     */
    private void checkWord() {
        String enteredWord = inputField.getText().trim();
        if (enteredWord.equals(wordLabel.getText())) {
            score++;
            if (score % 5 == 0) {
                level++;
                if (level % 5 == 0) {
                    initialTime = Math.max(5, initialTime - 2);
                }
            }
            updateUI();
            setNewWord();
            startTimer();
        } else {
            handleIncorrect();
        }
        inputField.clear();
    }

    /**
     * Handles incorrect attempts by incrementing the error count, updating the "sun" visual,
     * and checking if the maximum number of errors has been reached to end the game.
     */
    private void handleIncorrect() {
        errors++;
        updateSun();
        if (errors >= 5) {
            gameOver();
        }
    }

    /**
     * Updates the user interface, including the level, score, and time labels, based on the current game state.
     */
    private void updateUI() {
        levelLabel.setText(" " + level);
        scoreLabel.setText("Puntos: " + score);
        timeLabel.setText("Tiempo: " + timeLeft + "s");
    }

    /**
     * Selects a random word from the predefined list and displays it on the game UI.
     */
    private void setNewWord() {
        wordLabel.setText(words[random.nextInt(words.length)]);
    }

    /**
     * Updates the "sun" visual indicator in the UI to reflect the current number of errors.
     * If the errors reach the maximum limit, the sun turns gray.
     */
    private void updateSun() {
        String sun = "☀☀☀☀☀".substring(0, Math.max(0, 5 - errors));
        sunLabel.setText(" " + sun);
        if (errors >= 5) {
            sunLabel.setTextFill(Color.GRAY);
        }
    }

    /**
     * Ends the game when the maximum number of errors is reached by stopping the timer,
     * disabling the input field, and displaying a "Game Over" message.
     */
    private void gameOver() {
        timeline.stop();
        wordLabel.setText("Juego Terminado");
        inputField.setDisable(true);
    }
}
