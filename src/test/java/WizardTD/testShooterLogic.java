import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import WizardTD.Tower.*;
import WizardTD.Info.*;
import WizardTD.Gremlins.*;
import WizardTD.*;

import java.beans.Transient;
import java.io.*;
import java.util.*;

public class testShooterLogic {
    Shooter testShooter;
    ShooterLogic testShooterLogic;
    Tower testTower;
    Gremlin testGremlin;

    public testShooterLogic() {
        List<Integer> location = new ArrayList<>();
        location.add(5);
        location.add(6);
        testTower = new Tower(location, 0, 1, 1, 1, null);
        testShooter = new Shooter(5, 6, 2);
        //gremlin below tower 
        testGremlin = new Gremlin(0, "monster", 50, 100, 2.0, 5, 7, null, 0); //path is null
        testShooterLogic = new ShooterLogic(testShooter, testTower, testGremlin);
    }   

    /*
     * Test gremlin hit 
     */
    @Test
    public void testGremlinHit() {
        assertEquals(false, testShooterLogic.gremlinHit());
    }

    /*
     * Test find direction
     */
    @Test
    public void testFindDirection() {
        assertNotNull(testShooterLogic.findDirection().get(0));
        assertNotNull(testShooterLogic.findDirection().get(1));
    }

    /*
     * Update position 
     */
    @Test 
    public void testUpdateShooterPosition() {
        testShooterLogic.updateShooterPosition(1);
    }

    /*
     * Getters
     */
    @Test 
    public void testGetShooterX() {
        assertEquals(5, testShooterLogic.getShooterX());
    }

    @Test 
    public void testGetShooterY() {
        assertEquals(6, testShooterLogic.getShooterY());
    }

    @Test 
    public void testGetGremlin() {
        assertEquals(testGremlin, testShooterLogic.getGremlin());
    }

    @Test 
    public void testGetShooter() {
        assertEquals(testShooter, testShooterLogic.getShooter());
    }

    @Test 
    public void testGetTower() {
        assertEquals(testTower, testShooterLogic.getTower());
    }

    /*
     * Check range
     */
    @Test 
    public void testGremlinInRange() {
        assertEquals(true, testShooterLogic.gremlinInRange());
    }

    @Test 
    public void testShooterInRange() {
        assertEquals(true, testShooterLogic.shooterInRange());
    }
}
