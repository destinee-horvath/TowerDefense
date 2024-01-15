import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import WizardTD.Tower.*;
import WizardTD.Info.*;
import WizardTD.*;

import java.io.*;
import java.util.*;
import processing.core.PApplet;

public class testDisplayTowerInfo {
    DisplayTowerInfo testDisplayTowerInfo;
    Tower testTower;
    UpgradeTower testUpgradeTower;

    public testDisplayTowerInfo() {
        PApplet app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);

        List<Integer> location = new ArrayList<>();
        location.add(5);
        location.add(6);
        testTower = new Tower(location, 0, 1, 1, 1, null);
        //upgrade damage and speed
        testUpgradeTower = new UpgradeTower(testTower, true, true, true);
        testDisplayTowerInfo = new DisplayTowerInfo(location, testTower, testUpgradeTower, null);

    }

    /*
     * Total cost
     */
    @Test 
    public void checkTotalCost() {
        assertEquals(90, testDisplayTowerInfo.totalCost());
    }

    /*
     * Check button states
     */
    @Test
    public void CheckButtonRangeState() {
        assertEquals(true, testDisplayTowerInfo.buttonRangeState(true));
    }

    @Test
    public void CheckButtonDamageState() {
        assertEquals(true, testDisplayTowerInfo.buttonDamageState(true));
    }

    @Test
    public void CheckButtonSpeedState() {
        assertEquals(true, testDisplayTowerInfo.buttonSpeedState(true));
    }

    /*
     * Check number buttons active
     */
    @Test 
    public void testCountStates() {
        assertEquals(3, testDisplayTowerInfo.countStates());
    }

}
