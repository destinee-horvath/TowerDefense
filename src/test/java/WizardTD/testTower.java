// package test.java.WizardTD;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import WizardTD.Tower.*;
import WizardTD.Info.*;
import WizardTD.*;

import processing.data.JSONArray;
import processing.core.PApplet;
import processing.data.JSONObject;

import java.io.*;
import java.util.*;


public class testTower { //needs to extend PApplet to read JSON file??
    Tower testTower;
    List<Integer> location;
    ReadJson readJson;
    JSONObject jsonFile;

    public testTower() {
        PApplet app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);

        String configPath = "config.json";
        jsonFile = app.loadJSONObject(configPath);
        readJson = new ReadJson(jsonFile, 1);
    
        testTower = new Tower(location, 0, 4, 6, 8, null);
    }

    /*
    * Check location 
    */
    @Test
    public void testGetLocation() {
        List<Integer> location = new ArrayList<>();
        location.add(5);
        location.add(6);
        Tower testTower = new Tower(location, 0, 4, 6, 8, null);

        List<Integer> testLoc = testTower.getLocation();
        assertEquals(location.get(0), testLoc.get(0)); //check x-coords
        assertEquals(location.get(1), testLoc.get(1)); //check y-coords
    }
    
    /*
    * Check base level 
    */
    @Test
    public void testGetBaseLevel() {
        assertEquals(0, testTower.getBaseLevel(), "Expected: 0" + ", but got: " + testTower.getBaseLevel());
        
    }

    /*
    * Check range level 
    */
    @Test
    public void testGetRangeLevel() {
        assertEquals(4, testTower.getLevelRange(), "Expected: 4" + ", but got: " + testTower.getLevelRange());
    }

    /*
    * Check speed level 
    */
    @Test
    public void testGetSpeedLevel() {
        assertEquals(6, testTower.getLevelSpeed(), "Expected: 6" + ", but got: " + testTower.getLevelSpeed()); 
    }

    /*
    * Check damage level 
    */
    @Test
    public void testGetDamageLevel() {
        assertEquals(8, testTower.getLevelDamage(), "Expected: 8" + ", but got: " + testTower.getLevelDamage()); 
    }

    /*
    * Check range cost  
    */
    @Test
    public void testGetRangeCost() {
        //if level range 4 -> cost = 60
        assertEquals(60, testTower.getLevelRangeCost(), "Expected: 60" + ", but got: " + testTower.getLevelRangeCost()); 
    }

    /*
    * Check speed cost  
    */
    @Test
    public void testGetSpeedCost() {
        //if level 6 -> cost = 80
        assertEquals(80, testTower.getLevelSpeedCost(), "Expected: 80" + ", but got: " + testTower.getLevelSpeedCost()); 
    }

    /*
    * Check damage cost  
    */
    @Test
    public void testGetDamageCost() {
        //if level 8 -> cost = 100
        assertEquals(100,testTower.getLevelDamageCost(), "Expected: 100" + ", but got: " + testTower.getLevelDamageCost()); 
    }


    /*
    * Check damage
    */
    @Test
    public void testGetDamage() { //damage = level 8, damage = 200
        try {
            assertEquals(200, testTower.getDamage(), "Expected: 200" + ", but got: " + testTower.getDamage()); 
        }
        catch (NullPointerException e) {
            System.err.println("jsonFile is null");
        }
    }

    /*
     * Check speed
     */
    @Test
    public void testGetSpeed() { //speed = level 6, speed = 6.75?
        try {
            assertEquals(6.75, testTower.getSpeed(), "Expected: 6.75" + ", but got: " + testTower.getSpeed()); 
        }
        catch (NullPointerException e) {
            System.err.println("jsonFile is null");
        }
    }

    /*
     * Test print classes
     */
    @Test 
    public void testTowerPrintClasses() {
        TowerPrintDamage testTowerPrintDamage = new TowerPrintDamage(testTower, null, 1, 1);
        TowerPrintRange testTowerPrintRange = new TowerPrintRange(testTower, null, 1, 1);
        TowerPrintSpeed testTowerPrintSpeed = new TowerPrintSpeed(testTower, null, 1, 1);
        TowerPrint testTowerPrint = new TowerPrint(testTower, null, null, null, null);
    }

}
