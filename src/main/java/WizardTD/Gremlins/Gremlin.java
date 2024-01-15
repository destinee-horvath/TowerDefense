package WizardTD.Gremlins;

import java.util.ArrayList;

public class Gremlin {
    private String type;
    private int ID, hp, maxHP, linkListIndex;  
    private double x, y, speed;
    private ArrayList<NodeNew> path;
    private boolean manaDecreased = false;

    public Gremlin(int ID, String type, int hp, int maxHP, double speed, double x, double y, ArrayList<NodeNew> path, int linkListIndex) {
        this.ID = ID; //unique identifier 
        this.type = type; 
        this.hp = hp; 
        this.maxHP = maxHP;
        this.speed = speed;
        this.path = path;
        this.linkListIndex = linkListIndex;
        this.x = x; 
        this.y = y;
    }

    public int getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public int getHP() {
        return hp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isAlive() {
        if (hp <= 0) {
            return false;
        }
        return true;
    }
    
    public ArrayList<NodeNew> getPath() {
        return path;
    }


    //get tile location 
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public int getLinkListIndex() {
        return linkListIndex;
    }

    //set location 
    public void setX(double inX) {
        x = Math.round(inX*1000.0) / 1000.0;
    }

    public void setY(double inY) {
        y = Math.round(inY*1000.0) / 1000.0;
    }

    public void setLinkListIndex() {
        linkListIndex++;
    }

    //check gremlin reached end 
    public boolean reachedEnd() {
        NodeNew lastNode = path.get(path.size()- 1);
        if (lastNode.x == x && lastNode.y == y) {
            return true;
        }
        return false;
    }

    public void gremlinHitHealth(double damage) {
        hp -= damage;
    }

    public boolean manaDecreased() {
        return manaDecreased;
    }

    public void decreaseManaOnce() {
        if (!manaDecreased) {
            // manaBar.decreaseMana(20);
            manaDecreased = true;
        }
    }

    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }
}
