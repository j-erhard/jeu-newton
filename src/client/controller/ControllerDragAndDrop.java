package client.controller;

import client.model.Tree;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;


public class ControllerDragAndDrop extends Controller {
    @FXML ImageView imageDice;
    ImageView pionDragged;
    String targetTempId1 = "start";
    String targetTempId2;
    @FXML Label compteurJoueur1;
    @FXML Label finPartie;
    public int compteurJ1;
    @FXML Button buttonRoll;
    int value = 0;
    Tree tree = new Tree();
    @FXML Label nbTours;
    int tours;


    @FXML
    public void initialize() {
        finPartie.setDisable(true);
        finPartie.setStyle("-fx-font: 0 arial;");
        tours = 0;
        compteurJoueur1.setText("Nombres de points: 0");
        nbTours.setText("Nombre de tours : " + tours);
        compteurJ1 = 0;
    }

    @FXML
    public void dragDetected(MouseEvent event) {
        ImageView source = (ImageView) event.getSource();
        if (source.getId() != null && source.getId().contains("pion")) {
            Dragboard db = source.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putImage(source.getImage());
            db.setContent(content);
            pionDragged = source;
        }

        event.consume();

    }

    @FXML
    public void dragDone(DragEvent event) {
        ImageView source = (ImageView) event.getSource();
        if (event.getTransferMode() == TransferMode.MOVE) {
            source.setId(targetTempId1);
            targetTempId1 = targetTempId2;
            Image caseGrise = new Image("client/ressources/image/case.png");
            source.setImage(caseGrise);
        }

        event.consume();
    }

    @FXML
    public void dragOver(DragEvent event) {
        if(value>0) {
            ImageView target = (ImageView) event.getSource();
            String targetId = target.getId();
            if (tree.keyHas(targetTempId1, targetId)) {
                if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        }
    }

    public void dragDropped(DragEvent event) {
        String url = ((ImageView) event.getSource()).getImage().getUrl();
        if (((ImageView) event.getSource()).getId() != null && url.contains("pomme")) {
            if ("pion".equals(pionDragged.getId())) {
                compteurJ1 += 1;
                compteurJoueur1.setText("Nombre de points : " + compteurJ1);
            }
        } else if (((ImageView) event.getSource()).getId() != null && url.contains("potion")) {
            if ("pion".equals(pionDragged.getId())) {
                compteurJ1 += 2;
                compteurJoueur1.setText("Nombre de points : " + compteurJ1);
            }
        }
        ImageView target = (ImageView) event.getSource();
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasImage()) {
            targetTempId2 = target.getId();
            target.setImage(pionDragged.getImage());
            target.setId(pionDragged.getId());
            success = true;
        }
        event.setDropCompleted(success);
        value--;
        if(value == 0){
            tours++;
            nbTours.setText("Nombre de tours : " + tours);
            buttonRoll.setDisable(false);
        }
        if(partieTerminee()) {
            finPartie();
        }
        event.consume();

    }

    public boolean partieTerminee() {
        return tours==10;
    }

    public void finPartie() {
        buttonRoll.setDisable(true);
        finPartie.setText("Le joueur a fini la partie avec " + compteurJ1 + " points ! Bravo !");
        finPartie.setStyle("-fx-font: 24 arial;");
        finPartie.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.8), null, null)));
        finPartie.setDisable(false);
    }
    @FXML
    public void roll() {
        buttonRoll.setDisable(true);
        Random rand = new Random();
        value = rand.nextInt(3)+1;
        Image image;
        switch (value){
            case 1:
                image = new Image("client/ressources/image/1_dot.png");
                imageDice.setImage(image);
                break;
            case 2:
                image = new Image("client/ressources/image/2_dots.png");
                imageDice.setImage(image);
                break;
            case 3:
                image = new Image("client/ressources/image/3_dots.png");
                imageDice.setImage(image);
                break;
            default:
                System.out.println("Erreur");
        }
    }
}
