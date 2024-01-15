package WizardTD.ButtonsFolder;


import processing.event.MouseEvent;
import java.io.*;
import java.util.*;
import processing.core.PApplet;

import WizardTD.Info.*;


public class ButtonUpgradeSpeed extends Buttons {
    private PApplet labelBox;

    public ButtonUpgradeSpeed(ReadJson readJson, String text, String label, int button_x, int button_y, int button_w, int button_h, PApplet block) {
        super(readJson, text, label, button_x, button_y, button_w, button_h, block);
        this.tmp = true;
        this.labelBox = block;
    }

    // public boolean onClick() {
    //     if (tmp) {
    //         click = !click;
    //         tmp = false;

    //         if (click) {
    //             //when clicked
    //         }
    //         else {
    //             //else 
    //         }

    //         timer.schedule(new TimerTask() {
                
    //             @Override
    //             public void run() {
    //                 tmp = true;
    //             }
    //         }, 500); //only run after time

    //     }
    //     return click;
    // }

    public boolean keyPressed() {
        if (tmp) {
            if (block.keyPressed && block.key == '2') {
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

    public void buttonLabel() {
        String[] labelList = label.split(" ");
        labelBox.fill(0);
        labelBox.textSize(14);
        labelBox.textAlign(PApplet.LEFT);
        labelBox.text(labelList[0], (button_x + 45), (button_y + 15));
        labelBox.text(labelList[1], (button_x + 45), (button_y + 35));
    }
}
