package client.controller;

import javafx.event.Event;
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
    @FXML Label compteurJaune;
    @FXML Label compteurBleu;
    @FXML Label compteurVert;
    @FXML Label compteurRouge;
    public int compteurJ;
    public int compteurB;
    public int compteurV;
    public int compteurR;
    @FXML Button buttonRoll;
    HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
    int value = 0;

    public void createTree() {
        hashMap.put("start", new ArrayList<>(Arrays.asList("case1", "case6", "case19")));
        hashMap.put("case1", new ArrayList<>(Arrays.asList("start", "case2", "case3")));
        hashMap.put("case2", new ArrayList<>(Arrays.asList("case1")));
        hashMap.put("case3", new ArrayList<>(Arrays.asList("case1", "pomme1")));
        hashMap.put("pomme1", new ArrayList<>(Arrays.asList("case3", "case4")));
        hashMap.put("case4", new ArrayList<>(Arrays.asList("pomme1", "case5")));
        hashMap.put("case5", new ArrayList<>(Arrays.asList("case4", "potion1")));
        hashMap.put("potion1", new ArrayList<>(Arrays.asList("case5")));
        hashMap.put("case6", new ArrayList<>(Arrays.asList("start", "case7")));
        hashMap.put("case7", new ArrayList<>(Arrays.asList("case6", "pomme2")));
        hashMap.put("pomme2", new ArrayList<>(Arrays.asList("case7", "case8", "case12")));
        hashMap.put("case8", new ArrayList<>(Arrays.asList("pomme2", "case9")));
        hashMap.put("case9", new ArrayList<>(Arrays.asList("case8", "case10")));
        hashMap.put("case10", new ArrayList<>(Arrays.asList("case9", "case11")));
        hashMap.put("case11", new ArrayList<>(Arrays.asList("case10", "potion2")));
        hashMap.put("potion2", new ArrayList<>(Arrays.asList("case11", "case16")));
        hashMap.put("case12", new ArrayList<>(Arrays.asList("pomme2", "case13", "case17")));
        hashMap.put("case13", new ArrayList<>(Arrays.asList("case12", "case14")));
        hashMap.put("case14", new ArrayList<>(Arrays.asList("case13", "case15")));
        hashMap.put("case15", new ArrayList<>(Arrays.asList("case14", "case16")));
        hashMap.put("case16", new ArrayList<>(Arrays.asList("case15", "potion2")));
        hashMap.put("case17", new ArrayList<>(Arrays.asList("case12", "case18")));
        hashMap.put("case18", new ArrayList<>(Arrays.asList("case17", "potion3")));
        hashMap.put("potion3", new ArrayList<>(Arrays.asList("case18")));
        hashMap.put("case19", new ArrayList<>(Arrays.asList("start", "case20")));
        hashMap.put("case20", new ArrayList<>(Arrays.asList("case19", "case21", "case24")));
        hashMap.put("case21", new ArrayList<>(Arrays.asList("case20", "pomme3")));
        hashMap.put("pomme3", new ArrayList<>(Arrays.asList("case21", "case22")));
        hashMap.put("case22", new ArrayList<>(Arrays.asList("pomme3", "case23")));
        hashMap.put("case23", new ArrayList<>(Arrays.asList("case22")));
        hashMap.put("case24", new ArrayList<>(Arrays.asList("case20", "case25")));
        hashMap.put("case25", new ArrayList<>(Arrays.asList("case24", "case26")));
        hashMap.put("case26", new ArrayList<>(Arrays.asList("case25")));
    }

    @FXML
    public void initialize() {
        createTree();
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
        if(value>0) {
            ImageView target = (ImageView) event.getSource();
            String targetId = target.getId();
            ArrayList<String> possibleTarget = hashMap.get(targetTempId1);
            if (possibleTarget.contains(targetId)) {
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
                compteurB += 1;
                compteurBleu.setText("Bleu: " + compteurB);
            } else if ("pion2".equals(pionDragged.getId())) {
                compteurV += 1;
                compteurVert.setText("Vert: " + compteurV);
            } else if ("pion3".equals(pionDragged.getId())) {
                compteurR += 1;
                compteurRouge.setText("Rouge: " + compteurR);
            } else if ("pion4".equals(pionDragged.getId())) {
                compteurJ += 1;
                compteurJaune.setText("Jaune: " + compteurJ);
            }
        } else if (((ImageView) event.getSource()).getId() != null && url.contains("potion")) {
            if ("pion".equals(pionDragged.getId())) {
                compteurB += 2;
                compteurBleu.setText("Bleu: " + compteurB);
            } else if ("pion2".equals(pionDragged.getId())) {
                compteurV += 2;
                compteurVert.setText("Vert: " + compteurV);
            } else if ("pion3".equals(pionDragged.getId())) {
                compteurR += 2;
                compteurRouge.setText("Rouge: " + compteurR);
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
        value--;
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
