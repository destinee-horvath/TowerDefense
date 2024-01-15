package WizardTD.UI;

import processing.core.PApplet;
import processing.core.PImage;

public class Lose {
    PApplet lose, lose2, box;

    public Lose(PApplet lose) {
        this.lose = lose;
        this.lose2 = lose;
        this.box = lose;
    }

    public void printLose() {
        box.stroke(255, 77, 0);
        box.strokeWeight(5);
        box.fill(255);
        box.rect(250,290,150,70);

        lose.fill(0);
        lose.textSize(25);
        lose.text("You Lost", 325, 310);
        
        lose2.fill(0);
        lose2.textSize(15);
        lose2.text("Press R to restart.", 325, 340);
        
    }
}
