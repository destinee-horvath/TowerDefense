package WizardTD.UI;

import processing.core.PApplet;
import processing.core.PImage;

public class Win {
    PApplet win, win2, box;

    public Win(PApplet win){
        this.win = win;
        this.win2 = win;
        this.box = win;
    }

    public void printWin() {
        box.stroke(0, 68, 255);
        box.strokeWeight(5);
        box.fill(255);
        box.rect(250,290,150,70);

        win.fill(0);
        win.textSize(25);
        win.text("You Win!", 325, 310);
        
        win2.fill(0);
        win2.textSize(15);
        win2.text("Press R to restart.", 325, 340);
        
    }
}
