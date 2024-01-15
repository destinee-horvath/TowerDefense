package WizardTD.Tower;

import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PGraphics;

import WizardTD.Tower.*;

public class TowerPrintRange {
    private PApplet circle;
    private int levelRange, x, y;
    private Tower towerHash;

    public TowerPrintRange(Tower towerHash, PApplet circle, int x, int y) {
        this.circle = circle;
        this.x = x;
        this.y = y;
        levelRange = towerHash.getLevelRange();
    }

    public void printTowerRange() {
        if (levelRange == 0) {
            return;
        }
        else if (levelRange == 1) {
            drawCircle((x*32)+3, (y*32 + 2) + 40);
        }

        else if (levelRange == 2) {
            drawCircle((x*32)+3, (y*32 + 2) + 40);
            drawCircle((x*32)+15, (y*32 + 2) + 40);
        }

        else if (levelRange >= 3) {
            drawCircle((x*32)+3, (y*32 + 2) + 40);
            drawCircle((x*32)+15, (y*32 + 2) + 40);
            drawCircle((x*32)+27, (y*32 + 2) + 40);
        }
    
        //to alternate between circles 
        // int circleNum = (levelRange - 1)%3 + 1;

        // if (circleNum == 0) {
        //     return; 
        // }
    
        // for (int i = 0; i < circleNum; i++) {
        //     int xPos = (x*32)+ 3 +(i*12); 
        //     drawCircle(xPos, (y*32 + 2) + 40);
        // }
    }

    public void drawCircle(int x, int y) {
        circle.noFill();
        circle.stroke(202, 58, 242); //purple
        circle.strokeWeight(2);
        circle.ellipse(x, y, 7, 7);
    }
}
