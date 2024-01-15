package WizardTD.Tower;

import java.util.*;
import java.io.*;

import processing.core.PApplet;
import processing.core.PImage;

import WizardTD.Info.*;
import WizardTD.UI.*;

public class PlaceTower {
    //determine if tower can be placed (check cost and position)
    private PApplet block;
    private ReadJson readJson;
    private int x, y;
    private Board board;
    private ArrayList<ArrayList<Character>> fileMap;
    private List<Integer> coords;
    private HashMap<List<Integer>, Tower> towersHashMap;

    public PlaceTower(ArrayList<ArrayList<Character>> fileMap, ReadJson readJson, HashMap<List<Integer>, Tower> towersHashMap, int x, int y) {
        this.block = block;
        this.x = x; 
        this.y = y - 1;
        this.readJson = readJson;
        this.fileMap = fileMap;
        this.towersHashMap = towersHashMap;
        List<Integer> coords = new ArrayList<>();
        coords.add(x);
        coords.add(y);
    }

    /*
     * Check if tower can be placed 
     */
    public boolean towerPlaced() {
        //check if can be placed 
        if ((x < 20) && (y < 20) && fileMap.get(y).get(x).equals(' ')) { //in map 
            return true;
        }
        return false;
    }

    /*
     * Return position of tower (x, y)
     */
    public List<Integer> towerPosition() { 
        List<Integer> towerCoords = new ArrayList<Integer>();
        towerCoords.add(x);
        towerCoords.add(y);
        return towerCoords;
    }
}
