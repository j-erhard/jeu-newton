package newton.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Dice {
    public int value;
    public int nbFaces;
    ImageView image = new ImageView(new Image("../ressources/image/1_dot.png"));

    public Dice(){
        nbFaces = 3;
    }

    public void roll(){
        Random rand = new Random();
        this.value = rand.nextInt(nbFaces);
        switch (value){
            case 1:
                image.setImage(new Image("../ressources/image/1_dot.png"));
                break;
            case 2:
                image.setImage(new Image("../ressources/image/2_dots.png"));
                break;
            case 3:
                image.setImage(new Image("../ressources/image/3_dots.png"));
                break;
            default:
                System.out.println("Erreur");
        }
    }
}
