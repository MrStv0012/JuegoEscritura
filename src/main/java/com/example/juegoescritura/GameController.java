package com.example.juegoescritura;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.Random;

/**
 * The {@code GameController} class manages the game logic and UI interactions.
 * It processes user input, updates the UI, handles the game state,
 * and manages the countdown timer and errors.
 *
 * @author Jhon Steven Angulo Nieves
 * @version 1.0
 * @since 1.0
 * @see javafx.fxml.FXML
 */
public class GameController {

    /** Label displaying the current level. */
    @FXML private Label levelLabel;
    /** Label displaying the current score. */
    @FXML private Label scoreLabel;
    /** Label displaying the remaining time. */
    @FXML private Label timeLabel;
    /** Label displaying the word to type. */
    @FXML private Label wordLabel;
    /** ImageView displaying the sun's visual state based on errors. */
    @FXML private ImageView sunImageView;
    /** TextField for user input. */
    @FXML private TextField inputField;

    /** List of words used in the game. */
    private final String[] words = { "Luz", "Mil", "Rey", "Voz", "Pan", "Sol",
            "Aprender", "Nivel", "Juego", "Tiempo", "Código", "Rápido", "Texto", "JavaFX",
            "Escritura", "Xilofono", "Rápidamente", "Camaleónicamente", "Cálidamente", "Anímicamente", "Devuélveselas"};
    /** Current level of the game. */
    private int level = 1;
    /** Current score of the player. */
    private int score = 0;
    /** Number of errors made by the player. */
    private int errors = 0;
    /** Remaining time in the round. */
    private int timeLeft = 20;
    /** Initial time set for each round. */
    private int initialTime = 20;
    /** Timeline for managing the countdown timer. */
    private Timeline timeline;
    /** Random instance for selecting words randomly. */
    private final Random random = new Random();
    /** List of sun images representing error states. */
    private final String[] sunImages = {
            "/images/Sol_fade_0.png",
            "/images/Sol_fade_1.png",
            "/images/Sol_fade_2.png",
            "/images/Sol_fade_3.png",
            "/images/Sol_fade_4.png",
            "/images/Sol_fade_5.png"
    };

    /**
     * Initializes the game by setting up event handlers, preparing the UI, and starting the timer.
     *
     * @since 1.0
     * @see javafx.fxml.FXML
     */
    public void initialize() {
        updateUI();
        setNewWord();
        startTimer();
        updateSun();
        inputField.setOnAction(e -> checkWord());
    }

    /**
     * Starts the countdown timer for the game, updating the time label each second.
     * If the timer reaches zero, it triggers the handling of an incorrect attempt.
     *
     * @since 1.0
     * @see javafx.animation.Timeline
     */
    private void startTimer() {
        if (timeline != null) {
            timeline.stop();
        }
        timeLeft = initialTime;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeLeft--;
            timeLabel.setText("Tiempo: " + timeLeft + "s");
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
     * @since 1.0
     */
    private void checkWord() {
        String enteredWord = inputField.getText().trim();
        if (enteredWord.equals(wordLabel.getText())) {
            score++;
            if (score % 5 == 0) {
                level++;
                if (level % 5 == 0) {
                    initialTime = Math.max(5, initialTime - 2);
                    timeLeft = initialTime;
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
     *
     * @since 1.0
     */
    private void handleIncorrect() {
        errors++;
        updateSun();
        if (errors >= 5) {
            gameOver();
        }
    }

    /**
     * Selects a random word from the predefined list and displays it on the game UI.
     *
     * @since 1.0
     */
    private void setNewWord() {
        wordLabel.setText(words[random.nextInt(words.length)]);
    }

    /**
     * Updates the "sun" visual indicator in the UI to reflect the current number of errors.
     *
     * @since 1.0
     */
    private void updateSun() {
        int index = Math.min(errors, sunImages.length - 1);
        sunImageView.setImage(new Image(getClass().getResourceAsStream(sunImages[index])));
    }


    /**
     * Updates the user interface, including level, score, and time labels.
     * Ensures the UI reflects the current game state.
     */
    private void updateUI() {
        levelLabel.setText("Nivel: " + level);
        scoreLabel.setText("Puntos: " + score);
        timeLabel.setText("Tiempo: " + timeLeft + "s");
    }

    /**
     * Resets the game to its initial state, allowing the user to restart without closing the application.
     *
     * @since 1.0
     */
    @FXML
    private void restartGame() {
        level = 1;
        score = 0;
        errors = 0;
        timeLeft = initialTime;
        updateUI();
        setNewWord();
        updateSun();
        startTimer();
        inputField.setDisable(false);
        inputField.clear();
    }

    /**
     * Ends the game when the maximum number of errors is reached by stopping the timer,
     * disabling the input field, and displaying a "Game Over" message.
     *
     * @since 1.0
     */
    private void gameOver() {
        timeline.stop();
        wordLabel.setText("Juego Terminado");
        inputField.setDisable(true);
    }
}
