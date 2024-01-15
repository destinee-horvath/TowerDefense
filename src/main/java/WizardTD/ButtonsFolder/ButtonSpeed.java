package WizardTD.ButtonsFolder;

import processing.event.MouseEvent;
import java.io.*;
import java.util.*;
import processing.core.PApplet;

import WizardTD.Info.*;


public class ButtonSpeed extends Buttons {
    private double monsterSpeed;
    private PApplet labelBox;

    public ButtonSpeed(ReadJson readJson, String text, String label, int button_x, int button_y, int button_w, int button_h, PApplet block) {
        super(readJson, text, label, button_x, button_y, button_w, button_h, block);
        this.monsterSpeed = readJson.monsterSpeed();
        // this.shooterSpeed
        this.tmp = true;
        this.labelBox = block;
    }

    
    // public boolean onClick() { 
    //     //timer system
    //     if (tmp) {
    //         click = !click;
    //         tmp = false;

    //         getSpeed();

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
            if (block.keyPressed && (block.key == 'f' || block.key == 'F')) {
                click = !click;
            } 
            tmp = false;

            getSpeed();

            timer.schedule(new TimerTask() {
                
                @Override
                public void run() {
                    tmp = true;
                }
            }, 0); 
        }
        return click;
    }

    public double getSpeed() {
        keyPressed();
        if (click) {
            return monsterSpeed*2;
        }
        else {
            return monsterSpeed;
        }
    }

    public void buttonLabel() {
        labelBox.fill(0);
        labelBox.textSize(13);
        labelBox.textAlign(PApplet.LEFT);
        labelBox.text(label, (button_x + 45), (button_y + 20));
    }
 
}

