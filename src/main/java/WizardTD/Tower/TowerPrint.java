package WizardTD.Tower;

import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PGraphics;

import WizardTD.Tower.*;

public class TowerPrint {
    //determine tower type, upgrade - change image accordingly
    private int levelRange, levelSpeed, levelDamage, baseLevel;
    private List<Integer> location;
    private PImage t0, t1, t2;
    private PApplet block;
    private Tower tower;
    private PGraphics newTower;
    
    public TowerPrint(Tower tower, PImage t0, PImage t1, PImage t2, PApplet block) {
        this.tower = tower;
        this.t0 = t0; 
        this.t1 = t1; 
        this.t2 = t2;
        this.block = block;

        this.levelRange = tower.getLevelRange();
        this.levelSpeed = tower.getLevelSpeed();
        this.levelDamage = tower.getLevelDamage();
        this.location = tower.getLocation();
        this.baseLevel = tower.getBaseLevel();
        
    }

    public PImage printTowerBase() {
        int x = location.get(0);
        int y = location.get(1);

        //determine base tower 

        if (baseLevel == 2) {
            return t2;
        }

        else if (baseLevel == 1) {
            return t1;
        }
        return t0;
    }

    public void printTowerFreeze(int x, int y) {
        block.fill(0);
        block.stroke(99, 221, 255);
        block.strokeWeight(10);
        block.rect(x*32 + 7, y*32 + 47, 17, 17);
    }

}

