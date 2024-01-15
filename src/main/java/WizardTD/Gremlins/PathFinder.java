package WizardTD.Gremlins;

import java.util.*;

import java.io.*;

import WizardTD.Gremlins.*;


//https://mat.uab.cat/~alseda/MasterOpt/AStar-Algorithm.pdf 
//https://www.youtube.com/watch?v=2JNEme00ZFA&t=1141s 
//https://github.com/patrykkrawczyk/2D-A-path-finding-in-Java/blob/master/PathFinding.java 


//https://medium.com/@nicholas.w.swift/easy-a-star-pathfinding-7e6689c7f7b2 :used pseudo code 


/*
 * Determine paths to end 
 */
public class PathFinder {
    private ArrayList<ArrayList<Character>> fileMap, copyFileMap; 
    private ArrayList<ArrayList<Integer>> directions;

    public PathFinder(ArrayList<ArrayList<Character>> fileMap) {
        this.fileMap = fileMap;

        this.directions = new ArrayList<>();
            
        ArrayList<Integer> top = new ArrayList<>();
        top.add(-1); top.add(0);
        directions.add(top);
        ArrayList<Integer> bottom = new ArrayList<>();
        bottom.add(1); bottom.add(0);
        directions.add(bottom);
        ArrayList<Integer> left = new ArrayList<>();
        left.add(0); left.add(-1);
        directions.add(left);
        ArrayList<Integer> right = new ArrayList<>();
        right.add(0);; right.add(1);
        directions.add(right);

        //make copy filemap
        copyFileMap = new ArrayList<>();

        for (ArrayList<Character> row : fileMap) {
            ArrayList<Character> copyRow = new ArrayList<>(row);
            copyFileMap.add(copyRow);
        }
    }

    //calculate manhattan distance two nodes (difference between x and y distances)
    public int distance (int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public ArrayList<ArrayList<NodeNew>> findAllPaths() {
        ArrayList<ArrayList<NodeNew>> paths = new ArrayList<ArrayList<NodeNew>>();

        for (int a = 0; a < copyFileMap.size(); a++) {
            for (int b = 0; b < copyFileMap.get(a).size(); b++) {
                
                if (a == 0 || a == copyFileMap.size()-1 || b == 0 || b == copyFileMap.get(a).size() - 1) { //check if on edge 
                    
                    if (copyFileMap.get(a).get(b) == 'X') {
                        NodeNew start = new NodeNew(a, b); 
                        ArrayList<NodeNew> newPath = findPath(start);
                        
                        if (!newPath.isEmpty()) { //add if not empty 
                            paths.add(newPath);
                        }
                    }
                }
            }
        }
        return paths; //all paths found
    }


    public ArrayList<NodeNew> findPath(NodeNew start) {
        ArrayList<NodeNew> openList = new ArrayList<>(); 
        ArrayList<NodeNew> closedList = new ArrayList<>(); 

        openList.add(start);

        while (!openList.isEmpty()) {
            //Node currentNode = openList.get(0);
            NodeNew currentNode = openList.remove(0);

            if (copyFileMap.get(currentNode.x).get(currentNode.y) == 'W') { //check if reached end 
                return constructPath(currentNode);
            } 
            else {
                copyFileMap.get(currentNode.x).set(currentNode.y, 'I'); //mark iterated to prevent iterating through path again 

                ArrayList<NodeNew> currentpath = new ArrayList<>();

                for (ArrayList<Integer> dir : directions) { //check possible directionss
                    int nextX = currentNode.x + dir.get(0);
                    int nextY = currentNode.y + dir.get(1);

                    if (nextX >= 0 && nextX < copyFileMap.size() && nextY >= 0 && nextY < copyFileMap.get(0).size()) { //check in bounds
                        if (copyFileMap.get(nextX).get(nextY) == 'W' || copyFileMap.get(nextX).get(nextY) == 'X') {
                            NodeNew nextNode = new NodeNew(nextX, nextY); //if path or W, add to currentpath 
                            currentpath.add(nextNode);
                        }
                    }
                }

                //to add currentpath to openList
                for (NodeNew child : currentpath) {
                    if (!closedList.contains(child) && !openList.contains(child)) { //check noted not already in list - prevent duplicates
                        openList.add(child);
                        child.parent = currentNode;
                    }
                }

                closedList.add(currentNode);
            }
        }

        return new ArrayList<>(); //No path found
    }

    public ArrayList<NodeNew> constructPath(NodeNew currentNode) {
        ArrayList<NodeNew> path = new ArrayList<>(); //construct path from end to beginning 
        while (currentNode != null) {
            path.add(new NodeNew(currentNode.y, currentNode.x)); //to flip along y = -x axis
            currentNode = currentNode.parent;
        }

        //reverse
        ArrayList<NodeNew> reversePath = new ArrayList<NodeNew>(path.size());
        for (int i = path.size() - 1; i >= 0; i--) {
            reversePath.add(path.get(i));
        }

        return reversePath;
    }
}


/*
 * f - h+g --> prioritise nodes to explore
 * h - estimate cost of  distance
 * g - actual cost of distance 
 */