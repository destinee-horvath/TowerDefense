package WizardTD;

import java.util.*;
import java.io.*;
import processing.core.PImage;
import processing.core.PApplet;
import processing.data.JSONObject;
import processing.data.JSONArray;

import WizardTD.Info.*;

public class Countdown {
    private boolean tmp = true;
    private boolean preWavePause = true, waveEnd = false, canSpawnGremlin = false;
    private int countdown, wave; 
    private PApplet block;
    private String label = " ";

    private ReadJson readJson;

    public Countdown(ReadJson readJson, int wave, PApplet block) {
       this.readJson = readJson;
       this.block = block;
       //this.wave = wave;
       this.countdown = (int) readJson.preWavePause();

    }

    public void printTimer(int wave, boolean speedButton) {
        int delay = 1000;
        if (speedButton) {
            delay/=2;
        }
        
        toPrint(wave);
        //timer print 
        if (tmp) {
            tmp = false;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                tmp = true;    
                countdown--;
                System.out.println(countdown); 
                if (countdown <= 0) {

                    if (preWavePause) { //wave duration 
                        preWavePause = false;
                        waveEnd = false;
                        canSpawnGremlin = true;

                        countdown = (int) readJson.readWaveDuration();
                        

                        System.out.println("Wave " + (wave + 1) + " started.");
                        
                    } 

                    else { //prewave duration 
                        preWavePause = true;
                        waveEnd = false;

                        if (countdown == 0) {
                            waveEnd = true;
                        }

                        canSpawnGremlin = false;
                        countdown = (int) readJson.preWavePause();
                        System.out.println("Pre-wave pause Wave " + (wave));
                    }
                }

                }
            }, delay);
        }
    }

    public void toPrint(int wave) {
        block.textSize(16); 
        block.fill(255); 

        if (preWavePause) {
            String label = "Wave: " + (wave + 1) + " | Starts : " + Integer.toString(countdown);
            block.text(label, 90, 20);
        }
        else if (!preWavePause) {
            String label = "Wave: " + (wave + 1) + " | In Progress : " + Integer.toString(countdown);
            block.text(label, 100, 20);
        }
    }

    public int getCountdown() {
        return countdown;
    }


    public boolean waveEnd() {
        if (waveEnd) {
            waveEnd = false;
            return true;
        }
        return false;
    }

    public boolean canSpawn() {
        return canSpawnGremlin;
    }

    public boolean preWavePause() {
        return preWavePause;
    }

}
