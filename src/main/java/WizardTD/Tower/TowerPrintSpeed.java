package WizardTD.Tower;

import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PGraphics;


public class TowerPrintSpeed {
    PApplet box;
    PImage image;
    int levelSpeed, x, y;

    public TowerPrintSpeed(Tower towerHash, PApplet box, int x, int y) {
        this.box = box;
        this.x = x;
        this.y = y;
        levelSpeed = towerHash.getLevelSpeed();
    }

    public void printTowerSpeed() {
        if (levelSpeed >=3) {
            levelSpeed = 3; //prevent outline from becoming too big 
        }
        box.noFill();
        box.stroke(99, 221, 255);
        box.strokeWeight(levelSpeed);
        box.rect(x*32 + 7, y*32 + 47, 17, 17);
    }
}
