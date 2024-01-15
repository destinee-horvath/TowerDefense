// package test.java.WizardTD;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import WizardTD.App;
import WizardTD.Gremlins.*;


import java.util.*;
import processing.core.PApplet;

public class testGremlin {

    Gremlin testGremlin;
    ArrayList<NodeNew> path;

    public testGremlin() {
        PApplet app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);

        testGremlin = new Gremlin(0, "monster", 50, 100, 2.0, 5, 6, path, 0);
    }

    /*
     * Check ID 
     */
    @Test
    public void testGetID() {
        assertEquals(0, testGremlin.getID(), "Expected: 0" + ", but got: " + testGremlin.getID()); 
    }

    /*
     * Check type 
     */
    @Test
    public void testGetType() {
        assertEquals("monster", testGremlin.getType(), "Expected: monster" + ", but got: " + testGremlin.getType()); 
    }

    /*
     * Check HP
     */
    @Test
    public void testGetHP() {
        assertEquals(50, testGremlin.getHP(), "Expected: 50" + ", but got: " + testGremlin.getHP()); 
    }

    /*
     * Check maximum HP
     */
    @Test
    public void testGetMaxHP() {
        assertEquals(100, testGremlin.getMaxHP(), "Expected: 100" + ", but got: " + testGremlin.getMaxHP()); 
    }

    /*
     * Check speed
     */
    @Test
    public void testGetSpeed() {
        assertEquals(2.0, testGremlin.getSpeed(), "Expected: 2.0" + ", but got: " + testGremlin.getSpeed()); 
    }

    /*
     * Check alive
     */
    @Test
    public void testGetAlive() {
        assertEquals(true, testGremlin.isAlive(), "Expected: true" + ", but got: " + testGremlin.isAlive()); 
    }

    /*
     * Check if there is a path, in this test there is no path ( = null)
     */
    @Test
    public void testGetPath() {
        boolean result;
        if (path == null) {
            result = true;
        }
        else {
            result = false;
        }
        assert result;
    }

}
