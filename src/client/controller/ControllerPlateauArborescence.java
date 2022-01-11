package client.controller;

import client.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerPlateauArborescence extends Controller{
    @FXML public Label compteurTour;
    @FXML public Label compteurManche;
    @FXML public Label compteurPotion;
    @FXML public Label compteurPiece;

    public ControllerPlateauArborescence() {
        super();
    }

    @FXML
    public void initialize() {
        Game.incrementerTour();
        compteurTour.setText("Tour numéro: " + String.valueOf(Game.getCompteurTour()));
    }

    @FXML
    public void testDemoImplementerPotion() {
        Game.incrementerPotion();
        compteurPotion.setText("Nombre de potions: " + String.valueOf(Game.getCompteurPotion()));
    }
}
