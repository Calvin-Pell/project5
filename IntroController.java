package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class IntroController implements Initializable {

    //INSTANCE VARIABLES
    @FXML
    AnchorPane pane;
    @FXML
    Button playBtn;
    @FXML
    Label title;
    @FXML
    Label introLab;
    @FXML
    Button exitBtn;

    /*
     *Method implemented from the initializable interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /*
     *Refreshes the stage and scene with the main game screen using Game.fxml.
     */
    @FXML
    public void play(ActionEvent event) throws Exception {
        Parent gameParent = FXMLLoader.load(getClass().getResource("Game.fxml"));
        Scene gameScene = new Scene(gameParent);
        Stage gameStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();
    }

    /*
     *Exits the program and stops it from continuing to run.
     */
    @FXML
    private void exit() {
        System.exit(0);
    }
}
