package WizardTD.UI;

import processing.core.PApplet;
import processing.core.PImage;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.*;
import java.util.*;

//grass, shrubs, path 
public class Board {

    private PImage grass;
    private PImage shrub;
    private PImage path0;
    private PImage path1;
    private PImage path2;
    private PImage path3;
    private PImage wizard_house;

    public Board(PImage grass, PImage shrub, PImage path0, PImage path1, PImage path2, PImage path3, PImage wizard_house) {
        this.grass = grass;
        this.shrub = shrub;
        this.path0 = path0;
        this.path1 = path1;
        this.path2 = path2;
        this.path3 = path3;
        this.wizard_house = wizard_house;
    }

    /*
    read .txt file 
    */
    public ArrayList<ArrayList<Character>> fileMap(int level) {
        try {
            ArrayList<ArrayList<Character>> CharMap = new ArrayList<ArrayList<Character>>();
            Scanner scanner = new Scanner(new File("level" + level + ".txt"));
            for (int a = 0; a < 20; a++) { //rows
                ArrayList<Character> row = new ArrayList<Character>();
                String line = scanner.nextLine();
                for (int b = 0; b < 20; b++) { //columns
                    try {
                        row.add(line.charAt(b));
                    }
                    catch (StringIndexOutOfBoundsException e) {
                        row.add(' ');
                    }  
                }
                CharMap.add(row);
            }
            return CharMap;
        }

        catch (IOException e) {
            System.out.println("File not found.");
            return null;
        }
    }

    /*
    make a list of all tiles 
    */
    public ArrayList<ArrayList<PImage>> toDraw(ArrayList<ArrayList<Character>> CharMap) {
        ArrayList<ArrayList<PImage>> map = new ArrayList<ArrayList<PImage>>();
        for (int a = 0; a < 20; a++) { //iterate through file rows
            ArrayList<Character> CharRow = CharMap.get(a);
            ArrayList<PImage> row = new ArrayList<PImage>();
                
            for (int b = 0; b < 20; b++) { //iterate through file column
                Character CharCol = CharRow.get(b);
                switch (CharCol) {
                    case 'S':
                        row.add(shrub); 
                        break;
                    case 'W':
                        //row.add(wizard_house); 
                        row.add(grass);
                        break;
                    case 'X':
                        PImage path = pathJoints(CharMap, a, b);
                        row.add(rotateImageByDegrees(path, 0));
                        break;
                    default:
                        row.add(grass);
                        break;
                }

            }
            map.add(row);
        }

        return map;
    }

    /*
    to determine joints
    */
    public PImage pathJoints(ArrayList<ArrayList<Character>> CharMap, int row, int column) {
        char top = 'N'; 
        char bottom = 'N'; 
        char left = 'N'; 
        char right = 'N';

        //to ensure within bounds of list
        if (!(row - 1 < 0)) top = CharMap.get(row - 1).get(column); 
        if (!(row + 1 >= 20)) bottom = CharMap.get(row + 1).get(column); 
        if (!(column - 1 < 0)) left = CharMap.get(row).get(column - 1); 
        if (!(column + 1 >= 20)) right = CharMap.get(row).get(column + 1); 

        //**4 way joint
        if (top == 'X' && bottom == 'X' && left == 'X' && right == 'X') {
            return path3;
        }
        //**3 way joints
        if (top == 'X' && left == 'X' && bottom == 'X') {
            return rotateImageByDegrees(path2, 90);
        }
        if (left == 'X' && bottom == 'X' && right == 'X') {
            return rotateImageByDegrees(path2, 0);
        }
        if (bottom == 'X' && right == 'X' && top == 'X') {
            return rotateImageByDegrees(path2, 270);
        }
        if (right == 'X' && left == 'X' && top == 'X') {
            return rotateImageByDegrees(path2, 180);
        }
        //**2 way joints
        //corners
        if (top == 'X' && left == 'X') {
            return rotateImageByDegrees(path1, 90);
        }
        if (left == 'X' && bottom == 'X') {
            return rotateImageByDegrees(path1, 0);
        }
        if (bottom == 'X' && right == 'X') {
            return rotateImageByDegrees(path1, 270);
        }
        if (right == 'X' && top == 'X') {
            return rotateImageByDegrees(path1, 180);
        }
        //straight
        if (right == 'X' || left == 'X') {
            return rotateImageByDegrees(path0, 0);
        }
        if (top == 'X' || bottom == 'X') {
            return rotateImageByDegrees(path0, 90);
        }
        return null;
    }

    /*
    find wizard house
    */ 
    public ArrayList<Integer> getWizardCoord(ArrayList<ArrayList<Character>> CharMap) {
        ArrayList<Integer> wCoord = new ArrayList<>(2);
        for (int a = 0; a < 20; a++) {  //rows
            for (int b = 0; b < 20; b++) { //columns
                if (CharMap.get(a).get(b) == 'W') {              
                    wCoord.add(b);
                    wCoord.add(a);
                    return wCoord;
                }
            }
        }
        return null;
    }
    

    public int WizardRotate(ArrayList<ArrayList<Character>> CharMap, ArrayList<Integer> wCoord) {
        char Wtop = 'N'; 
        char Wbottom = 'N'; 
        char Wleft = 'N'; 
        char Wright = 'N';

        int Wcol = wCoord.get(0);
        int Wrow = wCoord.get(1);

        //to ensure within bounds of list
        if (!(Wrow - 1 < 0)) Wtop = CharMap.get(Wrow-1).get(Wcol); 
        if (!(Wrow + 1 >= 20)) Wbottom = CharMap.get(Wrow + 1).get(Wcol); 
        if (!(Wcol - 1 < 0)) Wleft = CharMap.get(Wrow).get(Wcol - 1); 
        if (!(Wcol + 1 >= 20)) Wright = CharMap.get(Wrow).get(Wcol + 1);

        if (Wtop == 'X') return 270;
        if (Wleft == 'X') return 180;
        if (Wbottom == 'X') return 90;
        if (Wright == 'X') return 0;
        return -1;
    }

    /**
     * Source: https://stackoverflow.com/questions/37758061/rotate-a-buffered-image-in-java
     * @param pimg The image to be rotated
     * @param angle between 0 and 360 degrees
     * @return the new rotated image
     */

    //ROTATES ANTICLOCKWISE
    public PImage rotateImageByDegrees(PImage pimg, double angle) {
        BufferedImage img = (BufferedImage) pimg.getNative();
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        int RGB = 0;
        PImage result = this.createImage(newWidth, newHeight, RGB);
        //BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        BufferedImage rotated = (BufferedImage) result.getNative();
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                result.set(i, j, rotated.getRGB(i, j));
            }
        }

        return result;
    }

    PImage createImage(int width, int height, int format) {
        //Create new PImage
        PImage img = new PImage(width, height, format);
        img.loadPixels();
        return img;
    }
    

}
