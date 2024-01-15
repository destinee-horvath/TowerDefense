package WizardTD.UI;

import java.util.*;
import processing.core.PApplet;

import WizardTD.UI.*;
import WizardTD.Info.*;

public class ManaBar {
    public int mana, manaCap, add, manaGainPerSec, manaCost;
    public ReadJson readJSON;
    private boolean tmp;
    private Timer timer;
    private PApplet block, colour;
    private double manaGainPerSecFloat;

    public ManaBar(ReadJson readJSON, int mana, int manaCap, int manaGainPerSec, int manaCost) {
        this.readJSON = readJSON;
        this.mana = mana;
        this.manaCap = manaCap;
        this.manaGainPerSec = manaGainPerSec;
        this.manaCost = manaCost;

        this.tmp = true;
        this.timer = new Timer();
        this.block = new PApplet();
        this.colour = new PApplet();
        this.add = 0;
    }

    public int increaseManaTimer(boolean speedButton) {  //increase per second 
        int delay = 1000;
        if (speedButton) {
            delay/=2;
        }
        //timer system
        if (tmp) {
            tmp = false;

            timer.schedule(new TimerTask() {
                
                @Override
                public void run() {
                    tmp = true;
                    if (mana < manaCap) {
                        add += manaGainPerSec;
                    }
                }
            }, delay);

        }
        return add;
    }

    public void buttonUpdate() {
        decreaseMana(getManaCost());
        increaseManaCap();
        manaUpgradeCost();
    }

    public int increaseMana(int amount) {
        return mana += amount;
    }

    public int decreaseMana(int amount) {
        return mana -= amount;
    }

    public int increaseManaCap() {
        manaCap = (int) (manaCap * readJSON.MPScapMultiplier());
        return manaCap;
    }

    public int increaseManaPerSecond() {
        double manaGainPerSecFloat = (manaGainPerSec * readJSON.MPSmanaGainedMultiplier());
        manaGainPerSec = (int) (manaGainPerSec * readJSON.MPSmanaGainedMultiplier());
        return manaGainPerSec;
    }

    public int manaUpgradeCost() {
        return manaCost += readJSON.MPScostIncreasePerUse();
    }

    public int getMana() {
        return mana;
    }

    public int getManaCap() {
        return manaCap;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getManaGainPerSec() {
        return manaGainPerSec;
    }

}

