package main;

/**
 * Main class
 * @author Micheal O' Sullivan
 * 
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Administration;

public class Main extends Application {
    private Scene scene;
    private Administration a = new Administration();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("MTU VACCINE PROGRAM");
        scene = a.administration(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
