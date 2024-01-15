package WizardTD.Info;

import processing.core.PApplet;

public class MouseLocation extends PApplet{
    // public void location() {
    //     System.out.println(mouseX + ", " + mouseY);
    // }
    double x;
    double y;
    double tileSize = 32;

    public void location (double x, double y) {
        //click location
        this.x = x;
        this.y = y;
        //determine which tile is clicked
        tileX();
        tileY();
    }

    public int tileX() {
        double tmp = x / tileSize;
        int tileX = (int) Math.floor(tmp);
        return tileX;
    }

    public int tileY() {
        double tmp = y / tileSize;
        int tileY = (int) Math.floor(tmp);
        return tileY;
    }

}
