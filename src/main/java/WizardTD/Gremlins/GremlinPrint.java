package WizardTD.Gremlins;

import java.util.*;
import java.io.*;
import processing.core.PImage;
import processing.core.PApplet;

import WizardTD.Gremlins.*;
import WizardTD.Info.*;

public class GremlinPrint {
    private ArrayList<ArrayList<Character>> fileMap;
    private ArrayList<ArrayList<NodeNew>> paths;
    private ArrayList<NodeNew> gremlinPath;
    private PathFinder pathFinder;
    private ArrayList<PImage> gremlinImages;
    private PApplet gremlinBox, gremlinID, gremlinHP, gremlinHPbar;
    private Gremlin gremlin;
    private ArrayList<Gremlin> gremlinArraylist;
    private ReadJson readJson;

    private int directionX, directionY;
    private float newX, newY;
    
    
    public GremlinPrint(Gremlin gremlin, ArrayList<ArrayList<Character>> fileMap, ArrayList<ArrayList<NodeNew>> paths, ArrayList<PImage> gremlinImages, ReadJson readJson, PApplet gremlinBox, int wave) {
        this.fileMap = fileMap;
        this.readJson = readJson;
        this.pathFinder = new PathFinder(fileMap);
        this.paths = pathFinder.findAllPaths();  
        this.gremlin = gremlin;
        this.gremlinImages = gremlinImages;
        this.gremlinBox = gremlinBox;
        this.gremlinID = gremlinBox;
        this.gremlinHP = gremlinBox;
        this.gremlinHPbar = gremlinBox;

        this.gremlinPath = gremlin.getPath();

    }

    public void printAllPaths() { //delete 
        if (paths != null && !paths.isEmpty()) {
            for (ArrayList<NodeNew> path : paths) {
                for (NodeNew node : path) {
                    float x = node.x * 32 + 10;
                    float y = node.y * 32 + 45;
                    gremlinBox.stroke(255, 0, 0);
                    gremlinBox.strokeWeight(4);
                    gremlinBox.rect(x, y, 10, 10); 
                }
            }
        }
    }

    /*
     * Prints all gremlins  
     */
    public void printGremlins(boolean speedButton, boolean pauseButton) {
        setGremlinPath(speedButton, pauseButton); //movement image

        // hp bar 
        double fractionLife = (double) gremlin.getHP() / (double) gremlin.getMaxHP();
        float lengthBar = (float) (fractionLife * 25); // Adjust the length of the health bar
        gremlinHPbar.fill(255,0,0);
        gremlinHPbar.strokeWeight((float) 0.7);
        gremlinHPbar.rect(newX * 32 + 2, newY * 32 + 30, (float) 25.0, 7);

        // remaining life
        gremlinHP.fill(22, 219, 4);
        gremlinHP.rect(newX * 32 + 2, newY * 32 + 30, lengthBar, 7);

    }


    /* 
     * Updates image per frame 
    */
    public void setGremlinPath(boolean speedButton, boolean pauseButton) {
        ArrayList<Integer> wCoord = new ArrayList<>(2);
        for (int a = 0; a < 20; a++) {  //rows
            for (int b = 0; b < 20; b++) { //columns
                if (fileMap.get(a).get(b) == 'W') {              
                    wCoord.add(b);
                    wCoord.add(a);
                }
            }
        }

        if (gremlinPImage() == null) { //dead gremlin
            return;
        }

        if (pauseButton) { //pause
            //do nothing
        }

        else if (wCoord.get(0) == gremlin.getX() && wCoord.get(1) == gremlin.getY()) {
            return; //don't print 
        }

        else if (gremlin.getLinkListIndex() + 1 < gremlinPath.size()) {
            NodeNew currentNode = gremlinPath.get(gremlin.getLinkListIndex());
            NodeNew nextNode = gremlinPath.get(gremlin.getLinkListIndex() + 1); 
    
            double dx = nextNode.x - currentNode.x;
            double dy = nextNode.y - currentNode.y;
            double distance = Math.sqrt(dx * dx + dy * dy); //distance between nodes 

            double gremlinSpeed = gremlin.getSpeed();

            double vel = Math.round((gremlinSpeed/60)*100.0)/100.0; //(speed/FPS)
            
            double dirX = (nextNode.x - gremlin.getX()) / distance; // Left (+) or right (-)
            if (dirX > 0) {
                directionX = 1; 
            } 
            else if (dirX < 0) {
                directionX = -1;
            } 
            else {
                directionX = 0;
            }

            double dirY = (nextNode.y - gremlin.getY()) / distance; // Up (+) or down (-)
            if (dirY > 0) {
                directionY = 1; 
            } 
            else if (dirY < 0) {
                directionY = -1;
            } 
            else {
                directionY = 0;
            }

            if (speedButton) {
                vel += vel;
            }
            
            gremlin.setX(gremlin.getX() + (directionX * vel));
            gremlin.setY(gremlin.getY() + (directionY * vel));
    
            if (nextNode.x == gremlin.getX() && nextNode.y == gremlin.getY()) { 
                gremlin.setLinkListIndex();
            }
        }
        NodeNew lastNode = gremlinPath.get(gremlinPath.size() - 1);

        newX = (float) (gremlin.getX());
        newY = (float) (gremlin.getY());
        
        if (newX == lastNode.x && newY == lastNode.y) { //if last node, dont print 
            return;
        }
        
        gremlinBox.image(gremlinPImage(), (float) (gremlin.getX()*32 + 5), (float) (gremlin.getY()*32 + 40));
    }

    public float getNewX() {
        return newX;
    }

    public float getNewY() {
        return newY;
    }

    
    public PImage gremlinPImage() {
        double fractionLife = (double) gremlin.getHP()/ (double) gremlin.getMaxHP();

        if (fractionLife > 0.9 && gremlin.getType().equals("gremlin")) {
            return gremlinImages.get(0);
        }

         //max health - different type
        else if (fractionLife > 0.9 && gremlin.getType().equals("worm")) {
            return gremlinImages.get(6);
        }

        else if (fractionLife > 0.9 && gremlin.getType() == "beetle") {
            return gremlinImages.get(7);
        }
        
        else if (fractionLife >= 0.8) {
            return gremlinImages.get(1);
        }

        else if (fractionLife >= 0.6) {
            return gremlinImages.get(2);
        }

        else if (fractionLife >= 0.4) {
            return gremlinImages.get(3);
        }

        else if (fractionLife >= 0.2) {
            return gremlinImages.get(4);
        }

        else if (fractionLife >= 0.1) {
            return gremlinImages.get(5);
        }

        return null; //monster ded 
    }
    
}
