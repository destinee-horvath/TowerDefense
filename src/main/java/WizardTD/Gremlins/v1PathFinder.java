package WizardTD.Gremlins;

import java.util.*;


//https://mat.uab.cat/~alseda/MasterOpt/AStar-Algorithm.pdf 
//https://www.youtube.com/watch?v=2JNEme00ZFA&t=1141s 
//https://github.com/patrykkrawczyk/2D-A-path-finding-in-Java/blob/master/PathFinding.java 


//Main source: https://medium.com/@nicholas.w.swift/easy-a-star-pathfinding-7e6689c7f7b2 : used pseudo code 


/*
 * Determine paths to end 
 */

 /*
public class v1PathFinder {
    
    //calculate manhattan distance two nodes (difference between x and y distances)
    public int distance(Node current, Node other) {
        return Math.abs(current.x - other.x) + Math.abs(current.y - other.y); 
    }

    public List<List<Node>> findPath(Node start, Node end) {
        List<Node> openList = new ArrayList<>(); //to store current 'exploration' path 
        List<List<Node>> paths = new ArrayList<>(); 

        openList.add(start);

        while (!openList.isEmpty()) {
            Node currentNode = openList.get(0); //loop with lowest f value -> lowest g+h
            for (int i = 1; i < openList.size(); i++) {
                if (openList.get(i).f < currentNode.f) {
                    currentNode = openList.get(i);
                }
            }

            openList.remove(currentNode); //remove current node as has been explored
            List<Node> adjacent = new ArrayList<>(); //stores adjacent nodes


            for (Node child : adjacent) {
                if (child == end) { //check if reached end
                    List<Node> finalPath = new ArrayList<>(); //create new list and copy everything 
                    Node current = currentNode; 
                    while (current != null) {
                        finalPath.add(current);
                        current = current.parent;
                    }
                    paths.add(finalPath);
                }

                int tmpG = currentNode.g + distance(currentNode, child); //cost start current to child
                boolean newPath = false;

                if (!openList.contains(child)) { //not explored, new path 
                    openList.add(child);
                    newPath = true;
                } 
                else if (tmpG < child.g) { //if cost less than node value, path explored (exists) - more efficient
                    newPath = true;
                }

                if (newPath) { //takes efficient path 
                    child.parent = currentNode;
                    // child.g = tmpG;
                    // child.h = distance(child, end);
                    child.f = tmpG + distance(child, end);
                }
            }
        }

        return paths; //return all paths 
    }
}
*/

/*
 * f - h+g --> prioritise nodes to explore
 * h - estimate cost of  distance
 * g - actual cost of distance 
 */