package client.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import java.util.Random;


public class ControllerDragAndDrop extends Controller {
    @FXML ImageView imageDice;
    ImageView pionDragged;
    String targetTempId1 = "start";
    String targetTempId2;
    @FXML ImageView pion;
    @FXML Label compteurJaune;
    @FXML Label compteurBleu;
    @FXML Label compteurVert;
    @FXML Label compteurRouge;
    public int compteurJ;
    public int compteurB;
    public int compteurV;
    public int compteurR;

    @FXML
    public void initialize() {
        compteurJaune.setText("Jaune: 0");
        compteurBleu.setText("Bleu: 0");
        compteurVert.setText("Vert: 0");
        compteurRouge.setText("Rouge: 0");
        compteurJ = 0;
        compteurB = 0;
        compteurV = 0;
        compteurR = 0;
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
        ImageView source = (ImageView) event.getSource();

        if (source.getId() == null || source.getId().contains("pomme") || source.getId().contains("potion")) {
            if (event.getGestureSource() != source && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        }
    }

    public void dragDropped(DragEvent event) {
        if (((ImageView) event.getSource()).getId() != null && ((ImageView) event.getSource()).getId().contains("pomme")) {
            if ("pion".equals(pionDragged.getId())) {
                compteurR += 1;
                compteurRouge.setText("Rouge: " + compteurR);
            } else if ("pion2".equals(pionDragged.getId())) {
                compteurV += 1;
                compteurVert.setText("Vert: " + compteurV);
            } else if ("pion3".equals(pionDragged.getId())) {
                compteurB += 1;
                compteurBleu.setText("Bleu: " + compteurB);
            } else if ("pion4".equals(pionDragged.getId())) {
                compteurJ += 1;
                compteurJaune.setText("Jaune: " + compteurJ);
            }
        } else if (((ImageView) event.getSource()).getId() != null && ((ImageView) event.getSource()).getId().contains("potion")) {
            if ("pion".equals(pionDragged.getId())) {
                compteurR += 2;
                compteurRouge.setText("Rouge: " + compteurR);
            } else if ("pion2".equals(pionDragged.getId())) {
                compteurV += 2;
                compteurVert.setText("Vert: " + compteurV);
            } else if ("pion3".equals(pionDragged.getId())) {
                compteurB += 2;
                compteurBleu.setText("Bleu: " + compteurB);
            } else if ("pion4".equals(pionDragged.getId())) {
                compteurJ += 2;
                compteurJaune.setText("Jaune: " + compteurJ);
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

        event.consume();

        if(partieTerminee()) {
            System.out.println(trouveGagnant());
        }
    }

    public boolean partieTerminee() {
        return compteurB + compteurR + compteurJ + compteurV == 15;
    }

    public String trouveGagnant() {
        if (compteurV > compteurR && compteurV > compteurJ && compteurV > compteurB) {
            return "le vert gagne avec " + compteurV + " points";
        }
        else if (compteurB > compteurR && compteurB > compteurJ && compteurB > compteurV) {
            return "le bleu gagne avec " + compteurB + " points";
        }
        else if (compteurJ > compteurR && compteurJ > compteurV && compteurJ > compteurB) {
            return "le jaune gagne avec " + compteurJ + " points";
        }
        else if (compteurR > compteurV && compteurR > compteurJ && compteurR > compteurB) {
            return "le rouge gagne avec " + compteurR + " points";
        }
        else {
            return "égalité";
        }
    }
    @FXML
    public void roll(Event event) {
        Random rand = new Random();
        int value = rand.nextInt(3)+1;
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
