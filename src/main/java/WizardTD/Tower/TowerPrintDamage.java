package WizardTD.Tower;

import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PGraphics;


public class TowerPrintDamage {
    private PApplet xPrint;
    private PImage image;
    private int levelDamage, x, y;

    public TowerPrintDamage(Tower towerHash, PApplet xPrint, int x, int y) {
        this.xPrint = xPrint;
        this.x = x;
        this.y = y;
        levelDamage = towerHash.getLevelDamage();
    }

    public void printTowerDamage() {
        if (levelDamage == 0) {
            return;
        }
        else if (levelDamage == 1) {
            drawX((x*32)+3, (y*32 + 25) + 40);
        }
        else if (levelDamage == 2) {
            drawX((x*32)+3, (y*32 + 25) + 40); 
            drawX((x*32)+15, (y*32 + 25) + 40);
        }
        else if (levelDamage >= 3) {
            drawX((x*32)+3, (y*32 + 25) + 40);
            drawX((x*32)+15, (y*32 + 25) + 40);
            drawX((x*32)+27, (y*32 + 25) + 40); 
        }
        

        //to alternate between the X position 
        // int numX = (levelDamage - 1)%3 + 1;

        // if (numX == 0) {
        //     return;
        // }
    
        // for (int i = 0; i < numX; i++) {
        //     int xPos = (x*32)+ 3 +(i*12);
        //     drawX(xPos, (y*32 + 25)+ 40);
        // }
    }

    public void drawX(int x, int y) {
        xPrint.textSize(17);
        xPrint.fill(202, 58, 242);
        xPrint.text("X", x, y);
    }
}
