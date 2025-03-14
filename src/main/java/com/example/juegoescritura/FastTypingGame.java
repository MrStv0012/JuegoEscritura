package com.example.juegoescritura;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The {@code FastTypingGame} class serves as the main entry point for the JavaFX application.
 * It initializes and launches the game interface where players must type words quickly.
 *
 * @author Jhon Steven Angulo Nieves
 * @version 1.0
 * @since 1.0
 * @see javafx.application.Application
 */
public class FastTypingGame extends Application {

    /**
     * Starts the JavaFX application by loading the FXML file and displaying the primary stage.
     *
     * @param primaryStage The main window of the application.
     * @throws Exception If the FXML file cannot be loaded.
     * @see javafx.stage.Stage
     * @since 1.0
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui.fxml"));

        primaryStage.setTitle("Escritura Rápida");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * The main entry point of the application, responsible for launching the JavaFX UI.
     *
     * @param args Command-line arguments passed to the application.
     * @see javafx.application.Application#launch(String...)
     * @since 1.0
     */
    public static void main(String[] args) {
        launch(args);
    }
}