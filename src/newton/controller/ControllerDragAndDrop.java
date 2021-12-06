package newton.controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;



public class ControllerDragAndDrop extends Application {
    @Override public void start(Stage stage) {
        stage.setTitle("Hello Drag And Drop");

        Group root = new Group();
        Scene scene = new Scene(root, 1500, 200);


        Image test = new Image("newton/ressources/image/carte-Pokemon-18-111-Leviator-GX-240-PV.png", 200,200,true, true);

        final ImageView source1 = new ImageView();
        source1.setImage(test);

        Image test2 = new Image("newton/ressources/image/919cr4oj5QL.png", 200,200,true, true);

        final ImageView source2 = new ImageView();
        source2.setImage(test2);
        source2.setX(500);

        final ImageView target1 = new ImageView();
        target1.setImage(test2);
        target1.setX(800);


        ArrayList<ImageView> sources = new ArrayList<>();
        sources.add(source1);
        sources.add(source2);

        ArrayList<ImageView> targets = new ArrayList<>();
        targets.add(target1);

        for( ImageView source:sources) {
            source.setOnDragDetected(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    /* drag was detected, start drag-and-drop gesture*/
                    System.out.println("onDragDetected");

                    /* allow any transfer mode */
                    Dragboard db = source.startDragAndDrop(TransferMode.ANY);

                    /* put a string on dragboard */
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(source.getImage());
                    db.setContent(content);

                    event.consume();
                }
            });

            source.setOnDragDone(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    /* the drag-and-drop gesture ended */
                    System.out.println("onDragDone");
                    /* if the data was successfully moved, clear it */
                    if (event.getTransferMode() == TransferMode.MOVE) {
                        source.setVisible(false);
                        System.out.println("prout");
                    }

                    event.consume();
                }
            });
            root.getChildren().add(source);
        }
        for (ImageView target: targets) {

            target.setOnDragOver(new EventHandler <DragEvent>() {
                public void handle(DragEvent event) {
                    /* data is dragged over the target */
                    System.out.println("onDragOver");

                    /* accept it only if it is  not dragged from the same node
                     * and if it has a string data */
                    if (event.getGestureSource() != target &&
                            event.getDragboard().hasImage()) {
                        /* allow for both copying and moving, whatever user chooses */
                        event.acceptTransferModes(TransferMode.MOVE);
                    }

                    event.consume();
                }
            });

            target.setOnDragDropped(new EventHandler <DragEvent>() {
                public void handle(DragEvent event) {
                    /* data dropped */
                    System.out.println("onDragDropped");
                    /* if there is a string data on dragboard, read it and use it */
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasImage()) {
                        target.setImage(db.getImage());
                        success = true;
                    }
                    /* let the source know whether the string was successfully
                     * transferred and used */
                    event.setDropCompleted(success);

                    event.consume();
                }
            });

        root.getChildren().add((target));
        }



        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
