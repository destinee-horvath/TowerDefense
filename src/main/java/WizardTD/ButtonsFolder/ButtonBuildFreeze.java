package WizardTD.ButtonsFolder;
import java.io.*;
import java.util.*;
import processing.core.PApplet;

import WizardTD.Info.*;

public class ButtonBuildFreeze extends Buttons {
    private String cost;
    private PApplet labelBox;
    private ReadJson readJson;

    public ButtonBuildFreeze(ReadJson readJson, String text, String label, int button_x, int button_y, int button_w, int button_h, PApplet block) {
        super(readJson, text, label, button_x, button_y, button_w, button_h, block);
        this.readJson = readJson;
        this.costMenu = block;
        this.cost = "Cost: 150";
        this.labelBox = block;
    }

    public boolean keyPressed() {
        if (tmp) {
            if (block.keyPressed && (block.key == 'q' || block.key == 'Q')) {
                click = !click;
            } 
            tmp = false;

            timer.schedule(new TimerTask() {
                
                @Override
                public void run() {
                    tmp = true;
                }
            }, 0);

        }
        return click;
    }

    public void buttonCost() {
        if (hover) {
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
            costMenu.text(cost, (button_x - 52), (button_y + 5)); //string, xCoord, yCoord
        }   
    }

    public void newCost(int additionalCost) {
        int newValue = (readJson.towerCost() + additionalCost);
        this.cost = "Cost: " + newValue;
    }

    public void buttonLabel() {
        // labelBox.fill(255); 
        // labelBox.rect(button_x - 80, button_y - 5, button_w + 15, button_h/2);
        String[] labelList = label.split(" ");
        labelBox.fill(0);
        labelBox.textSize(14);
        labelBox.textAlign(PApplet.LEFT);
        labelBox.text(labelList[0], (button_x + 45), (button_y + 15));
        labelBox.text(labelList[1], (button_x + 45), (button_y + 35));
    }
}
