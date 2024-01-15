import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import WizardTD.Tower.*;
import WizardTD.Info.*;
import WizardTD.*;
import WizardTD.UI.*;

import java.io.*;
import java.util.*;

import processing.data.JSONArray;
import processing.core.PApplet;
import processing.data.JSONObject;

public class testManaBar {
    ManaBar testManaBar;
    ReadJson readJson;
    JSONObject jsonFile;


    public testManaBar() { 
        PApplet app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);

        String configPath = "config.json";
        jsonFile = app.loadJSONObject(configPath);
        readJson = new ReadJson(jsonFile, 1);

        testManaBar = new ManaBar(readJson, 500, 1000, 2, 250);
    }

    /*
     * Increase/decrease mana
     */
    @Test 
    public void testIncreaseMana() {
        assertEquals(600, testManaBar.increaseMana(100));
    }

    @Test 
    public void testDecreaseMana() {
        assertEquals(400, testManaBar.decreaseMana(100));
    }

    /*
     * Increase mana cap 
     */
    @Test 
    public void testIncreaseManaCap() {
        assertEquals(1500, testManaBar.increaseManaCap());
    }

    /*
     * Increase mana per sec
     */
    @Test 
    public void testIncreaseManaPerSec() {
        assertEquals(2, testManaBar.increaseManaPerSecond());
    }

    /*
     * Mana upgrade cost
     */
    @Test 
    public void testManaUpgradeCost() {
        assertEquals(400, testManaBar.manaUpgradeCost());
    }

    /*
     * Getter methods
     */
    @Test 
    public void testGetMana() {
        assertEquals(500, testManaBar.getMana());
    }

    @Test 
    public void testGetManaCap() {
        assertEquals(1000, testManaBar.getManaCap());
    }

    @Test 
    public void testGetManaCost() {
        assertEquals(250, testManaBar.getManaCost());
    }

    @Test 
    public void testGetManaPerSec() {
        assertEquals(2, testManaBar.getManaGainPerSec());
    }

}
