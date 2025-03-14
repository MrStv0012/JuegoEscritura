package com.example.juegoescritura;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The `Main` class is the entry point for the JavaFX application.
 * This class initializes and launches a typing game where users aim to type words quickly.
 * It sets up the primary stage with the user interface loaded from an FXML file.
 *
 * @author Steven
 * @version 1.0
 */

public class Main extends Application {

    /**
     * The `start` method is called by the JavaFX framework to initialize the primary stage.
     * It loads the UI from an FXML file, sets the application's title, and displays the window.
     *
     * @param primaryStage The main window for the JavaFX application.
     * @throws Exception If the FXML resource cannot be loaded.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carga el archivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));

        // Configura la escena y la ventana principal
        primaryStage.setTitle("Juego de Escritura Rápida");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**
     * The `main` method serves as the entry point for the application.
     * It calls the `launch` method to start the JavaFX application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Inicia la aplicación JavaFX
        launch(args);
    }
}