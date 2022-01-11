package newton.model;

public class Game {
    
    public static int compteurTour;
    public static int compteurManche;
    public static int compteurPiece;
    public static int compteurPotion;

    

    public static int getCompteurTour() {
        return compteurTour;
    }

    public static void setCompteurTour(int compteurTour) {
        Game.compteurTour = compteurTour;
    }

    public static int getCompteurManche() {
        return compteurManche;
    }

    public static void setCompteurManche(int compteurManche) {
        Game.compteurManche = compteurManche;
    }

    public static int getCompteurPiece() {
        return compteurPiece;
    }

    public static void setCompteurPiece(int compteurPiece) {
        Game.compteurPiece = compteurPiece;
    }

    public static int getCompteurPotion() {
        return compteurPotion;
    }

    public static void setCompteurPotion(int compteurPotion) {
        Game.compteurPotion = compteurPotion;
    }

    public static void incrementerTour() {
        Game.compteurTour++;
    }

    public static void incrementerManche() {
        Game.compteurManche++;
    }

    public static void incrementerPiece() {
        Game.compteurPiece++;
    }

    public static void incrementerPotion() {
        Game.compteurPotion++;
    }

    public static void incrementerPiece(int nb) {
        Game.compteurPiece += nb;
    }

    public static void incrementerPotion(int nb) {
        Game.compteurPotion += nb;
    }
}
