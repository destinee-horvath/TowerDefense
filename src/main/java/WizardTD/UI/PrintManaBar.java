package WizardTD.UI;

import java.util.*;
import processing.core.PApplet;


import WizardTD.UI.*;
import WizardTD.Info.*;

public class PrintManaBar {
    ManaBar manaBar; 
    PApplet block, colour;
    int mana, manaCap;

    public PrintManaBar(ManaBar manaBar, ReadJson readJSON, PApplet block, PApplet colour) {
        
        this.block = block;
        this.colour = colour;
        this.manaBar = manaBar;
    }


    public void printManaBar(int mana, int manaCap) {
        //mana += manaBar.increaseManaTimer();
        //outline
        block.stroke(0);
        block.strokeWeight(2);

        //colour
        block.fill(255,255,255); //white

        //box dimensions
        int width = 350;
        block.rect(400, 7, width, 25); 

        //blu bar
        colour.fill(8, 143, 143);
        double tmp = width*((double) mana/(double) manaCap);
        int colourWidth = (int) Math.round(tmp);
        colour.rect(400, 7, colourWidth, 25);

        //text
        block.fill(0); //text colour
        block.textSize(25);
        block.textAlign(PApplet.CENTER, PApplet.CENTER);
        block.text("MANA: ", 350, 15);

        //text
        block.fill(0); //text colour
        block.textSize(25);
        block.textAlign(PApplet.CENTER, PApplet.CENTER);
        String text = mana + " / " + manaCap;
        block.text(text, 575, 15);
  
    }
}
