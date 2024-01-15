package WizardTD.ButtonsFolder;

import processing.event.MouseEvent;
import java.io.*;
import java.util.*;
import processing.core.PApplet;

import WizardTD.Info.*;
import WizardTD.UI.*;


public class ButtonManaPool extends Buttons {

    private String cost, label;
    private ReadJson readJson;
    private ManaBar manaBar;
    private PApplet labelBox;

    public ButtonManaPool(ReadJson readJson, String text, String label, int button_x, int button_y, int button_w, int button_h, PApplet block) {
        super(readJson, text, label, button_x, button_y, button_w, button_h, block);
        this.costMenu = block;
        // this.manaBar = manaBar;
        // this.cost = "Cost: " + manaBar.getManaCost();
        this.tmp = true;

        this.label = label;
        this.labelBox = block;
    }

    public boolean onClick() {  //manaPool button doesnt stay
        if (tmp) {
            click = !click;
            tmp = false;

//            updateMana();

            timer.schedule(new TimerTask() {
                
                @Override
                public void run() {
                    tmp = true;
                    click = false;
                }
            }, 10); //only run after time

        }
        return click;
    }

    public boolean keyPressed() {
        if (tmp) {
            if (block.keyPressed && block.key == 'm') {
                click = !click;
            } 

            tmp = false;
            //updateMana();

            timer.schedule(new TimerTask() {
                
                @Override
                public void run() {
                    tmp = true;
                    click = false; //make click false after some time 
                }
            }, 500);

        }
        return click;
    }

    public void buttonCost() {
        if (hover) {
            String[] labelList = label.split(" ");
            costMenu.fill(255, 255, 255);  
            //outline
            costMenu.stroke(0);
            costMenu.strokeWeight(1);
            
            //box dimensions
            costMenu.rect(button_x - 80, button_y - 5, button_w + 15, button_h/2);
            
            //format button text 
            costMenu.fill(0); //text colour
            costMenu.textAlign(PApplet.CENTER, PApplet.CENTER);
            costMenu.textSize(11);
            costMenu.text(labelList[2] + " " + labelList[3], (button_x - 52), (button_y + 5)); //string, xCoord, yCoord
        }
    }

    public void buttonLabel() {
        String[] labelList = label.split(" ");
        labelBox.fill(0);
        labelBox.textSize(13);
        labelBox.textAlign(PApplet.LEFT);
        labelBox.text(labelList[0] + " " + labelList[1], (button_x + 45), (button_y + 15));
        labelBox.text(labelList[2] + " " + labelList[3], (button_x + 45), (button_y + 35));
    }

    // public void updateMana() {
    //     if (click && manaBar.getMana() >=200) {
    //         manaBar.buttonUpdate();
    //     }
    // }
}
