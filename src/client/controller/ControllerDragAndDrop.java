package client.controller;

import client.model.Tree;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

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
    @FXML Label compteurJoueur2;
    @FXML Label compteurJoueur3;
    @FXML Label compteurJoueur4;
    public int compteurJ1;
    public int compteurJ2;
    public int compteurJ3;
    public int compteurJ4;
    @FXML Button buttonRoll;
    int value = 0;
    Tree tree = new Tree();



    @FXML
    public void initialize() {
        compteurJoueur1.setText("Jaune: 0");
        compteurJoueur2.setText("Bleu: 0");
        compteurJoueur3.setText("Vert: 0");
        compteurJoueur4.setText("Rouge: 0");
        compteurJ1 = 0;
        compteurJ2 = 0;
        compteurJ3 = 0;
        compteurJ4 = 0;
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
                compteurJ2 += 1;
                compteurJoueur2.setText("Joueur2: " + compteurJ2);
            } else if ("pion2".equals(pionDragged.getId())) {
                compteurJ3 += 1;
                compteurJoueur3.setText("Joueur3: " + compteurJ3);
            } else if ("pion3".equals(pionDragged.getId())) {
                compteurJ4 += 1;
                compteurJoueur4.setText("Joueur4: " + compteurJ4);
            } else if ("pion4".equals(pionDragged.getId())) {
                compteurJ1 += 1;
                compteurJoueur1.setText("Joueur1: " + compteurJ1);
            }
        } else if (((ImageView) event.getSource()).getId() != null && url.contains("potion")) {
            if ("pion".equals(pionDragged.getId())) {
                compteurJ2 += 2;
                compteurJoueur2.setText("Joueur2: " + compteurJ2);
            } else if ("pion2".equals(pionDragged.getId())) {
                compteurJ3 += 2;
                compteurJoueur3.setText("Joueur3: " + compteurJ3);
            } else if ("pion3".equals(pionDragged.getId())) {
                compteurJ4 += 2;
                compteurJoueur4.setText("Joueur4: " + compteurJ4);
            } else if ("pion4".equals(pionDragged.getId())) {
                compteurJ1 += 2;
                compteurJoueur1.setText("Joueur1: " + compteurJ1);
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
            buttonRoll.setDisable(false);
        }
        event.consume();

        if(partieTerminee()) {
            System.out.println(trouveGagnant());
        }
    }

    public boolean partieTerminee() {
        return compteurJ2 + compteurJ4 + compteurJ1 + compteurJ3 == 15;
    }

    public String trouveGagnant() {
        if (compteurJ3 > compteurJ4 && compteurJ3 > compteurJ1 && compteurJ3 > compteurJ2) {
            return "le joueur3 gagne avec " + compteurJ3 + " points";
        }
        else if (compteurJ2 > compteurJ4 && compteurJ2 > compteurJ1 && compteurJ2 > compteurJ3) {
            return "le joueur2 gagne avec " + compteurJ2 + " points";
        }
        else if (compteurJ1 > compteurJ4 && compteurJ1 > compteurJ3 && compteurJ1 > compteurJ2) {
            return "le joueur1 gagne avec " + compteurJ1 + " points";
        }
        else if (compteurJ4 > compteurJ3 && compteurJ4 > compteurJ1 && compteurJ4 > compteurJ2) {
            return "le joueur4 gagne avec " + compteurJ4 + " points";
        }
        return "égalité";
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
