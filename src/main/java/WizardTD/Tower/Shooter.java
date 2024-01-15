package WizardTD.Tower;

import WizardTD.Tower.*;
import WizardTD.Gremlins.*;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.*;

/*
 * shooter info (position, damage) 
 */
public class Shooter {
    double x, y;
    int damage;

    public Shooter(double x, double y, int damage) {
        this.x = x; 
        this.y = y;
        this.damage = damage;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getDamage() {
        return damage;
    }

    public void setX(double newX) {
        x = newX;
    }

    public void setY(double newY) {
        y = newY;
    }
}
