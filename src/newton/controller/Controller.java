package newton.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller {
    private static Stage primaryStage;

    public Controller() {
    }

    /**
     * Change de scène en fonction du bouton sur lequel le joueur a cliqué
     * @param event évènement du bouton
     * @throws IOException peut retourner une exception
     */
    @FXML
    public void switchToScene(ActionEvent event) throws IOException {
        Parent scoreView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/"+ ((Node) event.getSource()).getId() +".fxml")));
        if (primaryStage  == null) primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(scoreView));
        primaryStage.sizeToScene();
        primaryStage.show();
        scoreView.requestFocus();
    }
}
