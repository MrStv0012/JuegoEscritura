package com.example.juegoescritura;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The `FastTypingGame` class serves as the main entry point for the JavaFX application.
 * It initializes and launches a game where players attempt to type words as quickly as possible.
 *
 * @author Jhon Steven Angulo Nieves
 * @version 1.0
 *
 */

public class FastTypingGame extends Application {

    /**
     * The `start` method is the entry point for the JavaFX framework.
     * It loads the FXML resource to set up the game's user interface and displays the primary stage.
     *
     * @param primaryStage The main window for the application.
     * @throws Exception if the FXML resource cannot be loaded.
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui.fxml"));

        primaryStage.setTitle("Escritura Rápida");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * The `main` method serves as the application entry point.
     * It calls the `launch` method to initialize and start the JavaFX application.
     *
     * @param args Command-line arguments passed to the application.
     */

    public static void main(String[] args) {
        launch(args);
    }
}
