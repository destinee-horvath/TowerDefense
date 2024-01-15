// package test.java.WizardTD;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import WizardTD.Info.*;
import WizardTD.ButtonsFolder.*;

import processing.data.JSONArray;
import processing.core.PApplet;
import processing.data.JSONObject;

import java.io.*;
import java.util.*;


public class testButtons {
    Buttons buttons;
    ButtonBuildFreeze buttonBuildFreeze;
    ButtonBuild buttonBuild;
    ButtonManaPool buttonManaPool;
    ButtonSpeed buttonSpeed;
    ButtonPause buttonPause;
    AdditionalCosts additionalCosts;
    ButtonUpgradeRange buttonUpgradeRange;
    ButtonUpgradeSpeed buttonUpgradeSpeed;
    ButtonUpgradeDamage buttonUpgradeDamage;
    AdditionalCosts testAdditionalCosts;

    public testButtons() {
        buttons = new Buttons(null, "TestText", "TestLabel", 650, 50, 40, 40, null);
        buttonBuildFreeze = new ButtonBuildFreeze(null, "TestText", "TestLabel", 650, 50, 40, 40, null);
        //buttonBuild = new ButtonBuild(null, "TestText", "TestLabel", 650, 50, 40, 40, new PApplet());
        buttonManaPool = new ButtonManaPool(null, "TestText", "TestLabel", 650, 50, 40, 40, null);
        //buttonSpeed = new ButtonSpeed(null, "TestText", "TestLabel", 650, 50, 40, 40, new PApplet());
        buttonPause = new ButtonPause(null, "TestText", "TestLabel", 650, 50, 40, 40, null);
        buttonUpgradeRange = new ButtonUpgradeRange(null, "TestText", "TestLabel", 650, 50, 40, 40, null);
        buttonUpgradeSpeed = new ButtonUpgradeSpeed(null, "TestText", "TestLabel", 650, 50, 40, 40, null);
        buttonUpgradeDamage = new ButtonUpgradeDamage(null, "TestText", "TestLabel", 650, 50, 40, 40, null);

        testAdditionalCosts = new AdditionalCosts(true, true, true);

    }

    /*
     * Test state (buttons should all be false)
     */
    @Test
    public void testState() {
        assertFalse(buttons.getState());
    }

    /*
     * Test changing state
     */
    @Test
    public void testOnClick() {
        assertFalse(buttons.getState()); //off
        assertTrue(buttons.onClick());  //on
        assertTrue(buttons.getState());  //off
    }

    /*
     * Button freeze
     */
    @Test
    public void testOnKeyPressedBuildFreeze() {
       assertFalse(buttonBuildFreeze.keyPressed());
    }

    /*
     * Button build
     */
    @Test
    public void testOnKeyPressedBuild() {
        //assertFalse(buttonBuild.keyPressed());
    }

    /*
     * Mana button
     */
    @Test 
    public void testOnClickMana() {
        assertFalse(buttonManaPool.getState());
        assertTrue(buttonManaPool.onClick());  
        assertTrue(buttonManaPool.getState());  
    }

    @Test 
    public void testOnKeyPressedMana() {
        assertFalse(buttonManaPool.keyPressed());
    }

    /*
     * Button speed
     */
    @Test
    public void testOnKeyPressedSpeed() {
        //assertFalse(buttonSpeed.keyPressed());
    }

    /*
    * Button pause
    */
    @Test
    public void testOnKeyPressedPause() {
        assertFalse(buttonPause.keyPressed());
    }

    /*
     * Additional costs
     */
    @Test 
    public void testAdditionalCosts() {
        assertEquals(60, testAdditionalCosts.getAdditionalCosts());
    }

    /*
    * Button upgrade range
    */
    @Test
    public void testOnKeyPressedUpgradeRange() {
        assertFalse(buttonUpgradeRange.keyPressed());
    }

    /*
     * Button upgrade speed
     */
    @Test
    public void testOnKeyPressedUpgradeSpeed() {
        assertFalse(buttonUpgradeSpeed.keyPressed());
    }

     /*
     * Button upgrade damage
     */
    @Test
    public void testOnKeyPressedUpgradeDamage() {
        assertFalse(buttonUpgradeDamage.keyPressed());
    }
}

