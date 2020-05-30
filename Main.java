package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    /*
     *Creates a new scene and stage and uses them to show the intro screen using Intro.fxml.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Intro.fxml"));
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    /*
     *Calls the start method when the program is run.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
