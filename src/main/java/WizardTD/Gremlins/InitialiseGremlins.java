package WizardTD.Gremlins;

import WizardTD.Info.*;
import WizardTD.Gremlins.*;

import java.util.*;
import java.io.*;

/*
 * New wave/level --> restart gremlins 
 */
public class InitialiseGremlins {
    private ReadJson readJson;
    private PathFinder pathFinder;
    private int wave;
    private Random rand;
    private NodeNew NodeNew;
    private Gremlin toAdd;

    private ArrayList<ArrayList<NodeNew>> initialAllPaths;
    private ArrayList<Gremlin> gremlinArraylist;
    private ArrayList<NodeNew> path;


    public InitialiseGremlins(ArrayList<Gremlin> gremlinArraylist, ReadJson readJson, PathFinder pathFinder, int wave) {
        this.readJson = readJson;
        this.pathFinder = pathFinder;
        this.wave = wave;
        this.gremlinArraylist = gremlinArraylist;
    }

    public ArrayList<Gremlin> setGremlins() {
        initialAllPaths = pathFinder.findAllPaths();

        double randomShift = 0;
        int lim = gremlinArraylist.size() + readJson.monsterQuantity();

        for (int i = 0 + gremlinArraylist.size(); i < lim; i++) {
            int max = initialAllPaths.size() - 1;
            int min = 0;

            //give random path 
            Random rand = new Random();
            int randomPath = rand.nextInt(max - min + 1) + min; //adjust range
            ArrayList<NodeNew> path = initialAllPaths.get(randomPath);
            
            NodeNew startNode = path.get(randomPath);
            int initialNodeX = startNode.x;
            int initialNodeY = startNode.y;

            
            if (i != 0) {
                randomShift += Math.round(((rand.nextDouble() * 2) + 1)*100)/100;
            }   

            //initialise at top (shift up)
            if (initialNodeY == 0) {
                Gremlin toAdd = new Gremlin(i, readJson.getMonsterType(), readJson.monsterHP(), readJson.monsterHP(), readJson.monsterSpeed(), initialNodeX, (initialNodeY - 1 - randomShift), path, 0);
                gremlinArraylist.add(i, toAdd);
            }
            //initialise at bottom 
            else if (initialNodeY == 19) {
                Gremlin toAdd = new Gremlin(i, readJson.getMonsterType(), readJson.monsterHP(), readJson.monsterHP(), readJson.monsterSpeed(), initialNodeX, initialNodeY + 1 + randomShift, path, 0);
                gremlinArraylist.add(i, toAdd);
            }
            //initialise at left 
            else if (initialNodeX == 0) {
                Gremlin toAdd = new Gremlin(i, readJson.getMonsterType(), readJson.monsterHP(), readJson.monsterHP(), readJson.monsterSpeed(), initialNodeX - 1 - randomShift, initialNodeY, path, 0);
                gremlinArraylist.add(i, toAdd);
            }
            //initialise at right 
            else if (initialNodeX == 19) {
                Gremlin toAdd = new Gremlin(i, readJson.getMonsterType(), readJson.monsterHP(), readJson.monsterHP(), readJson.monsterSpeed(), initialNodeX + 1 + randomShift, initialNodeY, path, 0);
                gremlinArraylist.add(i, toAdd);
            }
        }

        return gremlinArraylist;
    }
}
