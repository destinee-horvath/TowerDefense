package WizardTD.Tower;

import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PGraphics;

import WizardTD.Tower.*;


public class DisplayTowerInfo {
    private PApplet menu;
    private PApplet title, middle, range, speed, damage, total, titleText, totalText, rangeRadius; 
    private PApplet displayLevel, displayLevelText, displayDamageText, displayRangeText, displaySpeedText;
    private int x, y, width, height, totalCost, count;
    private int baseLevel, levelRange, levelSpeed, levelDamage;
    private int costDamage, costRange, costSpeed;
    private boolean rangeState, speedState, damageState;
    private Tower tower;
    private UpgradeTower towerCosts;

    public DisplayTowerInfo(List<Integer> location, Tower tower, UpgradeTower towerCosts, PApplet menu) {
        //super(location, baseLevel, levelRange, levelSpeed, levelDamage);
        this.tower = tower;
        this.baseLevel = tower.getBaseLevel();
        this.levelRange = tower.getLevelRange();
        this.levelSpeed = tower.getLevelSpeed();
        this.levelDamage = tower.getLevelDamage();

        this.towerCosts = towerCosts;
        this.costRange = towerCosts.costRange();
        this.costDamage = towerCosts.costDamage(); 
        this.costSpeed = towerCosts.costSpeed();

        this.rangeState = towerCosts.getButtonRange();
        this.speedState = towerCosts.getButtonSpeed();
        this.damageState = towerCosts.getButtonDamage();
        
        this.rangeRadius = menu;

        this.title = menu;
        this.middle = menu;
        this.total = menu;
        this.titleText = menu;
        this.totalText = menu;

        this.displayLevel = menu;
        this.displayLevelText = menu;
        this.displayDamageText = menu;
        this.displayRangeText = menu;
        this.displaySpeedText = menu;
        
        x = 650;
        y = 550;
        width = 90;
        height = 20;
    }

    /*
     * Drawing
     */
    public void printTowerInfo() {
        if (tower.getBaseLevel() != 100) { //not freeze tower
            title();
            middle();
            total();
            displayLevels();
        }
    }

    public void displayRange() {
        rangeRadius.noFill();
        rangeRadius.stroke(214, 211, 43);
        rangeRadius.strokeWeight(2);
        int radius = (tower.getLevelRange()+1)*32*4;
        rangeRadius.ellipse(tower.getLocation().get(0)*32+15, tower.getLocation().get(1)*32+55, radius, radius);
    }

    public void title() {
        title.fill(255); 
        title.rect(x, y, width, height);
        
        titleText.fill(0);
        titleText.textSize(12);
        titleText.text("Costs: ", x+25, y + height/2);
    }

    public void middle() {
        middle.fill(255);
        middle.rect(x, y+height, width, height+(height*(countStates()-1)));

        int offset = y + height; 

        if (rangeState) {
            middle.fill(0); 
            middle.textSize(12);
            middle.text("Range: " + costRange, x+35, offset + height/2); 
            offset += height; 
        }

        if (speedState) {
            middle.fill(0); 
            middle.textSize(12);
            middle.text("Speed: " + costSpeed, x+35, offset + height/2);
            offset += height; 
        }

        if (damageState) {
            middle.fill(0);
            middle.textSize(12);
            middle.text("Damage: " + costDamage, x+40, offset + height/2);
        }
    }

    public int totalCost() {
        int totalCost = 0;
        if (rangeState) {
            totalCost += costRange;
        }
        if (speedState) {
            totalCost += costSpeed;
        }
        if (damageState) {
            totalCost += costDamage;
        }
        return totalCost;
    }

    public void total() {
        total.fill(255); 
        total.rect(x, y+(height*(countStates() + 1)), width, height);

        totalText.fill(0);
        totalText.textSize(12);
        totalText.text("Total: " + totalCost(), x+30, y + (height*(countStates() + 1)) + height/2);
    }

    public void displayLevels() {
        displayLevel.fill(255); 
        displayLevel.rect(x, y  - 100, width, height*4);
        displayLevelText.fill(0);
        displayLevelText.textSize(12);
        displayLevelText.text("Tower Levels:", x+45, 460);

        displayRangeText.fill(0);
        displayRangeText.textSize(12);
        displayRangeText.text("Range: " + tower.getLevelRange(), x+30, 480);

        displaySpeedText.fill(0);
        displaySpeedText.textSize(12);
        displaySpeedText.text("Speed: " + tower.getLevelSpeed(), x+30, 500);

        displayDamageText.fill(0);
        displayDamageText.textSize(12);
        displayDamageText.text("Damage: " + tower.getLevelDamage(), x+35, 520);
        
    }

    /*
     * States
     */
    public boolean buttonRangeState(boolean rangeState) {
        this.rangeState = rangeState;
        return rangeState;
    }

    public boolean buttonSpeedState(boolean speedState) {
        this.speedState = speedState;
        return speedState;
    }

    public boolean buttonDamageState(boolean damageState) {
        this.damageState = damageState;
        return damageState;
    }

    public int countStates() {
        int count = 0;
        if (rangeState) {
            count++;
        }
        if (speedState) {
            count++;
        }
        if (damageState) {
            count++;
        }
        return count;
    }

}
