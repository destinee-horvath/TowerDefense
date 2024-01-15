package WizardTD.Tower;

import java.util.*;

import WizardTD.Info.*;
import WizardTD.ButtonsFolder.*;

public class Tower {
    /*
     * returns all tower info
     */
    private int levelRange, levelSpeed, levelDamage, baseLevel;
    private List<Integer> location;
    private ReadJson readJson;
    
    public Tower(List<Integer> location, int baseLevel, int levelRange, int levelSpeed, int levelDamage, ReadJson readJson) {
        this.location  = location;
        this.levelRange = levelRange;
        this.levelSpeed = levelSpeed; 
        this.levelDamage = levelDamage;
        this.baseLevel = baseLevel;
        this.readJson = readJson;
    }

    public List<Integer> getLocation() {
        return location;
    }

    public int getBaseLevel() {
        return baseLevel;
    }

    public int getLevelRange() {
        return levelRange;
    }

    public int getLevelSpeed() {
        return levelSpeed;
    }

    public int getLevelDamage() {
        return levelDamage;
    }

    public int getLevelRangeCost() {
        return 20 + (levelRange*10);
    }

    public int getLevelSpeedCost() {
        return 20 + (levelSpeed*10);
    }

    public int getLevelDamageCost() {
        return 20 + (levelDamage*10);
    }

    public int getDamage() {
        int damage = readJson.initialTowerDamage();
        for (int i = 0; i < levelDamage; i++) {
            damage = damage + (int) (damage*0.5);
        }
        return damage;
    }

    public double getSpeed() {
        double speed = readJson.initialFiringSpeed();
        for (int i = 0; i < levelSpeed; i++) {
            speed = speed + (speed*0.5);
        }
        return speed;
    }

    public int getRange() { // in pixels
        return (levelRange+1) *32;
    }
    
}
