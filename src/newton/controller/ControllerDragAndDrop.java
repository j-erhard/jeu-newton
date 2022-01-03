package newton.controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;


public class ControllerDragAndDrop extends Controller {
    ImageView imageDuBled;
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
            imageDuBled = source;
        }

        event.consume();

    }

    @FXML
    public void dragDone(DragEvent event) {
        ImageView source = (ImageView) event.getSource();
        if (event.getTransferMode() == TransferMode.MOVE) {
            source.setVisible(false);
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
            if ("pion".equals(imageDuBled.getId())) {
                compteurR += 1;
                compteurRouge.setText("Rouge: " + compteurR);
            } else if ("pion2".equals(imageDuBled.getId())) {
                compteurV += 1;
                compteurVert.setText("Vert: " + compteurV);
            } else if ("pion3".equals(imageDuBled.getId())) {
                compteurB += 1;
                compteurBleu.setText("Bleu: " + compteurB);
            } else if ("pion4".equals(imageDuBled.getId())) {
                compteurJ += 1;
                compteurJaune.setText("Jaune: " + compteurJ);
            }
        } else if (((ImageView) event.getSource()).getId().contains("potion")) {
            if ("pion".equals(imageDuBled.getId())) {
                compteurR += 2;
                compteurRouge.setText("Rouge: " + compteurR);
            } else if ("pion2".equals(imageDuBled.getId())) {
                compteurV += 2;
                compteurVert.setText("Vert: " + compteurV);
            } else if ("pion3".equals(imageDuBled.getId())) {
                compteurB += 2;
                compteurBleu.setText("Bleu: " + compteurB);
            } else if ("pion4".equals(imageDuBled.getId())) {
                compteurJ += 2;
                compteurJaune.setText("Jaune: " + compteurJ);
            }
        }
        ImageView target = (ImageView) event.getSource();
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                target.setImage(imageDuBled.getImage());
                target.setId(imageDuBled.getId());
                success = true;
            }
            event.setDropCompleted(success);

            event.consume();
    }
}
