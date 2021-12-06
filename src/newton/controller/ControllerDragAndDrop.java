package newton.controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import java.util.ArrayList;
import java.util.Objects;


public class ControllerDragAndDrop extends Controller {
    @FXML Image pion;

    @FXML
    public void dragDetected(MouseEvent event) {
        ImageView source = (ImageView) event.getSource();
        //if (source.getImage().equals(pion)) {
            Dragboard db = source.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putImage(source.getImage());
            db.setContent(content);
        //}

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

        if (event.getGestureSource() != source &&
                event.getDragboard().hasImage()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }

        event.consume();
    }

    public void dragDropped(DragEvent event) {
        ImageView source = (ImageView) event.getSource();
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasImage()) {
            source.setImage(db.getImage());
            success = true;
        }
        event.setDropCompleted(success);

        event.consume();
    }
}
