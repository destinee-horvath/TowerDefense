package WizardTD.Tower;

import WizardTD.Tower.*;
import WizardTD.Gremlins.*;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.*;


/*
 * Prints shooter 
 */
public class ShooterPrint {
    private PApplet shooterBox;
    private PImage fireball;
    private Shooter shooter; 
    private ShooterLogic shooterLogic;
    private Tower tower; 
    private Gremlin gremlin;

    public ShooterPrint(Shooter shooter, ShooterLogic shooterLogic, PImage fireball, Tower tower, Gremlin gremlin, PApplet shooterBox) {
        this.shooter = shooter;
        this.shooterLogic = shooterLogic;
        this.fireball = fireball;
        this.tower = tower;
        this.gremlin = gremlin;
        this.shooterBox = shooterBox;

    }   
    public void printShooterPath(double speed, boolean buttonSpeed, boolean buttonPause) {
        if (buttonPause) {
            //dont update anything 
        }
        else if (buttonSpeed) {
            shooterLogic.updateShooterPosition(speed*2);
        }
        else {
            shooterLogic.updateShooterPosition(speed);
        }
        
        shooterBox.image(fireball, (float) (shooterLogic.getShooterX())*32 + 14, (float) shooterLogic.getShooterY()*32 +40 + 14);
    }

}
