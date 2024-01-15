package WizardTD.ButtonsFolder;
//import processing.event.MouseEvent;
import java.io.*;
import java.util.*;
import processing.core.PApplet;

import WizardTD.Info.*;

public class Buttons {

    protected PApplet block, costMenu;
    protected int button_x, button_y, button_w, button_h, time, delay, cost;
    protected String text, label;
    protected boolean hover, click, tmp;
    
    Timer timer = new Timer();

    public Buttons(ReadJson readJson, String text, String label, int button_x, int button_y, int button_w, int button_h, PApplet block) {
        this.text = text;
        this.label = label;
        this.button_x = button_x;
        this.button_y = button_y;
        this.button_w = button_w;
        this.button_h = button_h;
        this.block = block;
        this.hover = false;
        this.click = false;
        

        this.costMenu = block;
        this.tmp = true;
        cost = 0;

        this.timer = new Timer(); 
    }

    //https://stackoverflow.com/questions/4044726/how-to-set-a-timer-in-java
    public boolean onClick() { //to toggle between on and off
        if (tmp) {
            click = !click;
            tmp = false;
            timer.schedule(new TimerTask() {
                
                @Override
                public void run() {
                    tmp = true;
                }
            }, 500); //only run after time
        }
        return click;
    }

    public boolean getState() {
        return click;
    }

    // public boolean keyPressed() {
    //     return click;
    // }

    public void buttonCost() {
        //does nothing unless mana or build
    }

    public void buttonLabel() {

    }

    public void printButton() {
        buttonLabel();

        //outline
        block.stroke(0);
        block.strokeWeight(2);
        

        if (hover || click) { 
            block.fill(214, 211, 43); //yello
        } 
        if (hover) { //display cost 
            buttonCost();
            block.fill(214, 211, 43); //yello
        }
        else if (!click && !hover) {
            block.fill(148, 72, 40); //brown
        }

        //box dimensions
        block.rect(button_x, button_y, button_w, button_h); 

        //text
        block.fill(0); //text colour
        block.textSize(25);
        block.textAlign(PApplet.CENTER, PApplet.CENTER);
        block.text(text, button_x + button_w / 2, button_y + button_h / 2);

    
        if (block.mousePressed && 
        block.mouseX > button_x && block.mouseX < button_x + button_w && 
        block.mouseY > button_y && block.mouseY < button_y + button_h) {
            onClick();
        }
        else { //hover
            if (block.mouseX > button_x && block.mouseX < button_x + button_w && 
            block.mouseY > button_y && block.mouseY < button_y + button_h) {
                hover = true;
            }
            else {
                hover = false;
            }
        }
    }

}