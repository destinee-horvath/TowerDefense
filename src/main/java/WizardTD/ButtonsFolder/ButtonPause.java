package WizardTD.ButtonsFolder;

import processing.event.MouseEvent;
import java.io.*;
import java.util.*;
import processing.core.PApplet;

import WizardTD.Info.*;

public class ButtonPause extends Buttons {

    private Boolean pause;
    private PApplet labelBox;

    public ButtonPause(ReadJson readJson, String text, String label, int button_x, int button_y, int button_w, int button_h, PApplet block) {
        super(readJson, text, label, button_x, button_y, button_w, button_h, block);
        this.pause = false;

        this.tmp = true;

        this.labelBox = block;
    }

    // public boolean onClick() {
    //     if (tmp) {
    //         click = !click;
    //         tmp = false;

    //         getPause();

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
            if (block.keyPressed && (block.key == 'p' || block.key == 'P')) {
                click = !click;
            } 

            tmp = false;

            getPause();

            timer.schedule(new TimerTask() {
                
                @Override
                public void run() {
                    tmp = true;
                }
            }, 0);

        }
        return click;
    }

    public boolean getPause() {
        keyPressed();
        if (click) {
            return true;
        }
        else {
            return false;
        }
    }

    public void buttonLabel() {
        labelBox.fill(0);
        labelBox.textSize(14);
        labelBox.textAlign(PApplet.LEFT);
        labelBox.text(label, (button_x + 45), (button_y + 20));
    }

}
