package newton.controller;

import org.junit.*;

public class DragAndDropUnitTest {

    @Test
    public void testPartieTerminee() {
        ControllerDragAndDrop controller = new ControllerDragAndDrop();
        controller.compteurB = 5;
        controller.compteurJ = 3;
        controller.compteurR = 1;
        controller.compteurV = 4;
        Assert.assertFalse(controller.partieTerminee());
        controller.compteurR = 3;
        Assert.assertTrue(controller.partieTerminee());
    }

    @Test
    public void testTrouveGagnantJauneGagne() {
        ControllerDragAndDrop controller = new ControllerDragAndDrop();
        controller.compteurB = 0;
        controller.compteurJ = 10;
        controller.compteurR = 1;
        controller.compteurV = 4;
        Assert.assertEquals("le jaune gagne avec 10 points", controller.trouveGagnant());
    }

    @Test
    public void testTrouveGagnantVertGagne() {
        ControllerDragAndDrop controller = new ControllerDragAndDrop();
        controller.compteurB = 2;
        controller.compteurJ = 3;
        controller.compteurR = 1;
        controller.compteurV = 4;
        Assert.assertEquals("le vert gagne avec 4 points", controller.trouveGagnant());
    }

    @Test
    public void testTrouveGagnantRougeGagne() {
        ControllerDragAndDrop controller = new ControllerDragAndDrop();
        controller.compteurB = 2;
        controller.compteurJ = 3;
        controller.compteurR = 6;
        controller.compteurV = 4;
        Assert.assertEquals("le rouge gagne avec 6 points", controller.trouveGagnant());
    }

    @Test
    public void testTrouveGagnantBleuGagne() {
        ControllerDragAndDrop controller = new ControllerDragAndDrop();
        controller.compteurB = 5;
        controller.compteurJ = 3;
        controller.compteurR = 1;
        controller.compteurV = 4;
        Assert.assertEquals("le bleu gagne avec 5 points", controller.trouveGagnant());
    }

    @Test
    public void testTrouveGagnantEgalite() {
        ControllerDragAndDrop controller = new ControllerDragAndDrop();
        controller.compteurB = 3;
        controller.compteurJ = 2;
        controller.compteurR = 5;
        controller.compteurV = 5;
        Assert.assertEquals("égalité", controller.trouveGagnant());
    }
}