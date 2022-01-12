package client.model;

import org.junit.*;

public class TreeUnitTest {
    @Test
    public void testDeplacementPossible() {

        Tree tree = new Tree();
        Assert.assertTrue(tree.keyHas("case7", "case6"));
        Assert.assertFalse(tree.keyHas("pomme1", "case7"));
    }
}
