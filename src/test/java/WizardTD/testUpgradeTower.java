import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import WizardTD.Tower.*;
import WizardTD.Info.*;
import WizardTD.*;

import java.io.*;
import java.util.*;


public class testUpgradeTower {
    Tower testTower;
    UpgradeTower testUpgradeTower;

    public testUpgradeTower() {
        List<Integer> location = new ArrayList<>();
        location.add(5);
        location.add(6);
        testTower = new Tower(location, 0, 1, 1, 1, null);
        testUpgradeTower = new UpgradeTower(testTower, true, true, true);
    }

    /*
     * Test base level
     */
    @Test
    public void testBaseLevel() {
        assertEquals(1, testUpgradeTower.newLevelBase(),  "Expected: 1" + ", but got: " + testUpgradeTower.newLevelBase());
    }

    /*
     * Count buttons active
     */
    @Test
    public void testCountStates() {
        assertEquals(3, testUpgradeTower.countStates(),  "Expected: 3" + ", but got: " + testUpgradeTower.countStates());
    }

    /*
     * Total cost
     */
    @Test
    public void testTotalCost() {
        assertEquals(90, testUpgradeTower.totalCost(), "Expected: 2" + ", but got: " + testUpgradeTower.totalCost());
    }

    /*
     * Individual upgrade costs 
     */
    @Test
    public void testCostRange() {
        assertEquals(30, testUpgradeTower.costRange(),  "Expected: 30" + ", but got: " + testUpgradeTower.costRange());
    }

    @Test
    public void testCostDamage() {
        assertEquals(30, testUpgradeTower.costDamage(),  "Expected: 30" + ", but got: " + testUpgradeTower.costDamage());
    }

    @Test
    public void testCostSpeed() {
        assertEquals(30, testUpgradeTower.costSpeed(),  "Expected: 30" + ", but got: " + testUpgradeTower.costSpeed());
    }

    /*
     * upgrading level
     */
    @Test
    public void testNewLevelRange() {
        assertEquals(2, testUpgradeTower.newLevelRange(),  "Expected: 2" + ", but got: " + testUpgradeTower.newLevelRange());
    }

    @Test
    public void testNewLevelSpeed() {
        assertEquals(2, testUpgradeTower.newLevelSpeed(),  "Expected: 2" + ", but got: " + testUpgradeTower.newLevelSpeed());
    }

    @Test
    public void testNewLevelDamage() {
        assertEquals(2, testUpgradeTower.newLevelDamage(),  "Expected: 2" + ", but got: " + testUpgradeTower.newLevelDamage());
    }
}

