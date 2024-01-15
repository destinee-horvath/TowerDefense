package test.java.WizardTD;

import processing.core.PImage;
import processing.core.PApplet;
import org.junit.jupiter.api.Test;

import WizardTD.UI.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class testBoard {
    PImage sample = new PImage(32, 32); //image with size 32, 32
    ArrayList<ArrayList<Character>> testCharMap;
    ArrayList<ArrayList<PImage>> map;

    Board b;

    public void setup() {
        PImage grass = sample;
        PImage shrub = sample;
        PImage path0 = sample;
        PImage wizard_house = sample;

        b = new Board(grass, shrub, path0, path0, path0, path0, wizard_house);
        testCharMap = b.fileMap(100); ///test file
        

        map = new ArrayList<ArrayList<PImage>>();
        for (int a = 0; a < 20; a++) { //iterate rows
            ArrayList<Character> CharRow = testCharMap.get(a);
            ArrayList<PImage> row = new ArrayList<PImage>();
                
            for (int c = 0; c < 20; c++) { //iterate column
                Character CharCol = CharRow.get(c);
                switch (CharCol) {
                    case 'S':
                        row.add(shrub); 
                        break;
                    case 'W':
                        //row.add(wizard_house); 
                        row.add(grass);
                        break;
                    case 'X':
                        row.add(path0); // add sample path 
                        break;
                    default:
                        row.add(grass);
                        break;
                }
            }
            map.add(row);
        }
    }
    
    /*
    * Check if size is correct
    */
    @Test
    public void testFileMap() {  
        setup();  
        assertNotNull(testCharMap, "testCharMap is null");
        assertEquals(20, testCharMap.size(), "Expected: 20" + ", but got: " + testCharMap.size()); //check 20 rows
        for (ArrayList<Character> row : testCharMap) {
            assertEquals(20, row.size(), "Expected: 20" + ", but got: " + row.size()); //check 20 columns
        }
    }

    /*
    * Check if anything in list is null/not PImage or wrong size
    */
    @Test
    public void testToDraw() { 
        setup();       
        assertNotNull(map, "map is null");
        assertEquals(20, map.size(), "Expected: 20" + ", but got: " + map.size()); //check 20 rows
        for (ArrayList<PImage> row : map) {
            assertEquals(20, row.size(), "Expected: 20" + ", but got: " + row.size()); //check 20 columns
            for (PImage PImage : row) {
                assertNotNull(PImage, "missing image");
            }
        }
    }

    /*
    * Check if returned null (shouldnt return null)
    */
    @Test
    public void testPathJoints() {
        setup();
        PImage result = b.pathJoints(testCharMap, 9, 5);
        assertNotNull(result, "missing image");
    }

    /*
    * Check if returned null
    */
    @Test
    public void testGetWizardCoord() {
        setup();
        ArrayList<Integer> coords = b.getWizardCoord(testCharMap);
        assertNotNull(coords);
    }

    /*
    * Check if returned -1 (shouldnt return -1)
    */
    @Test
    public void testWizardRotate() {
        setup();
        ArrayList<Integer> coords = b.getWizardCoord(testCharMap);
        int result = b.WizardRotate(testCharMap, coords);
        assertNotEquals(-1, result);
    }
}
