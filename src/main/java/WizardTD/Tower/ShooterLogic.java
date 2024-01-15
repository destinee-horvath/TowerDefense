package WizardTD.Tower;

import java.util.*;

import WizardTD.Gremlins.*;

/*
 * Determine direction of shooter and if gremlin is in range
 */
public class ShooterLogic {
    private Shooter shooter;
    private Tower tower; 
    private Gremlin gremlin;

    private double directionX, directionY, shooterX, shooterY;

    public ShooterLogic(Shooter shooter, Tower tower, Gremlin gremlin) {
        this.tower = tower;
        this.gremlin = gremlin;
        this.shooter = shooter;
        shooterX = shooter.getX();
        shooterY = shooter.getY();
    }

    public boolean gremlinHit() {
        List<Integer> towerLocation = tower.getLocation();
        if (gremlin != null) {
            //x 
            double tmp1 = Math.round(shooterX * 10.0) / 10.0; //shooter X
            double tmp2 =  Math.round(gremlin.getX() * 10.0) / 10.0; //gremlinX
            //y
            double tmp3 = Math.round(shooterY * 10.0)/10.0; //shooter Y 
            double tmp4 =  Math.round(gremlin.getY() * 10.0) / 10.0; //gremline Y

            double diff = Math.abs(tmp3 - tmp4); //gremlin has height

            if (tmp1 == tmp2 && diff <= 0.5) {
                if (tower.getBaseLevel() == 100) { //freeze tower
                    gremlin.gremlinHitHealth(0); //no damage done
                    gremlin.setSpeed(0);
                }
                else {
                    gremlin.gremlinHitHealth(tower.getDamage()); //reduce health
                    if (gremlin.getHP() <= 0) {
                        gremlin = null;
                    }
                }
                shooterX = towerLocation.get(0);
                shooterY = towerLocation.get(1);
                
                return true;
            }
            return false;
        }
        return false; //if no gremlin
        
    }



    public ArrayList<Double> findDirection() {

        ArrayList<Double> vectorXY = new ArrayList<>();
        List<Integer> towerLocation = tower.getLocation();
        double dx;
        double dy;

        if ((shooterX != towerLocation.get(0) - 1) && (shooterY != towerLocation.get(1) - 1)) { //shooter is not at tower
            dx = (gremlin.getX()) - (shooter.getX()); 
            dy = (gremlin.getY()) - (shooter.getY());  
        }
        else { //shooter at tower 
            dx = (gremlin.getX()) - towerLocation.get(0)-1; //-16 for gremlin in middle of tile 
            dy = (gremlin.getY()) - towerLocation.get(1)-1;  
        }
        
        double distance = (Math.sqrt(dx * dx + dy * dy));


        double directionX = (dx / distance);
        double directionY = (dy / distance);
    
        vectorXY.add(directionX);
        vectorXY.add(directionY);
        return vectorXY;
    }

    public void updateShooterPosition(double speed) {
        double vel = Math.round((speed / 60.0)*100.0)/100.0;
        List<Integer> towerLocation = tower.getLocation();
        if (gremlinInRange()) { //gremlinInRange() || !shooterInRange()
            shooterX = ((shooter.getX()) + (findDirection().get(0) * vel  ));
            shooterY = ((shooter.getY()) + (findDirection().get(1) * vel  ));
        }

        //no gremlin or hit 
        else if (!gremlinInRange() || gremlin == null) {
            shooterX = towerLocation.get(0);
            shooterY = towerLocation.get(1);
        }
        
        shooter.setX(shooterX);
        shooter.setY(shooterY);
        gremlinHit();
    }


    public double getShooterX() {
        return shooterX;
    }

    public double getShooterY() {
        return shooterY;
    }

    public Gremlin getGremlin() {
        return gremlin;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public Tower getTower() {
        return tower;
    }


    public boolean gremlinInRange() {
        try {
            List<Integer> towerLocation = tower.getLocation();
            // double 
            double dx = Math.abs(gremlin.getX() - towerLocation.get(0)+1); //gremlin begins at 1, tower begins at 0
            double dy = Math.abs(gremlin.getY() - towerLocation.get(1));

            double distance = Math.round((Math.sqrt(dx*dx + dy*dy) * 100) / 100.0);

            double dxRight = Math.abs(gremlin.getX() - (towerLocation.get(0) + 1)); 
            double dyDown = Math.abs(gremlin.getY()+1 - towerLocation.get(1)); 

            double distance2 = Math.round((Math.sqrt(dxRight*dxRight + dyDown*dyDown) * 100) / 100.0); //distance right and bottom different? 
            

          
            if (distance <= tower.getRange()/32 || distance2 <= tower.getRange()/32) { //if within range
                return true; 
            }
            return false;
        }
        catch (NullPointerException e) {
            return false;
        }
    }

    public boolean shooterInRange() { //check shooter not at tower but in range 
        List<Integer> towerLocation = tower.getLocation();
        if (shooterX == towerLocation.get(0) && shooterY == towerLocation.get(1)) {
            return true;
        }
        return false;
    }

    public void setTargetGremlin(Gremlin targetGremlin) {
        gremlin = targetGremlin;
    }
}
