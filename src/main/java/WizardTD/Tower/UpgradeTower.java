package WizardTD.Tower;

import java.util.*;

import WizardTD.Tower.*;

public class UpgradeTower {
    private boolean damageState, speedState, rangeState;
    private DisplayTowerInfo displayTowerInfo;
    private Tower towerClicked;
    private int baseLevel, levelRange, levelSpeed, levelDamage; 


    /*
     * Calculates costs 
     * Increases levels 
     * Returns state of buttons
     */
    public UpgradeTower(Tower towerClicked, boolean damageState, boolean speedState, boolean rangeState) {
        this.towerClicked = towerClicked;
        this.baseLevel = towerClicked.getBaseLevel();
        this.levelRange = towerClicked.getLevelRange();
        this.levelSpeed = towerClicked.getLevelSpeed();
        this.levelDamage = towerClicked.getLevelDamage();
        this.rangeState = rangeState;
        this.damageState = damageState;
        this.speedState = speedState;
    }

    public int newLevelBase() {
        if (levelDamage >= 2 && levelRange >= 2 && levelSpeed >= 2) {
            baseLevel = 2;
        }
        else if (levelDamage >= 1 && levelRange >= 1 && levelSpeed >= 1) {
            baseLevel = 1;
        }
        else {
            baseLevel = 0;
        }
        return baseLevel;
    }

    public int countStates() {
        int count = 0;
        if (rangeState) {
            count++;
        }
        if (speedState) {
            count++;
        }
        if (damageState) {
            count++;
        }
        return count;
    }

    public int totalCost() {
        int totalCost = 0;
        if (rangeState) { 
            totalCost += costRange();
        }
        if (speedState) {
            totalCost += costSpeed();
        }
        if (damageState) {
            totalCost += costDamage();
        }
        //System.out.println("C:" + totalCost);
        return totalCost;
    }

    public int costRange() {
        if (levelRange >= 0) {
            return (levelRange + 1) * 10 + 10;
        }
        return 0;
    }

    public int costDamage() {
        if (levelDamage >= 0) {
            return (levelDamage + 1) * 10 + 10;
        }
        return 0;
    }

    public int costSpeed() {
        if (levelSpeed >= 0) {
            return (levelSpeed + 1) * 10 + 10;
        }
        return 0;
    }

    //if button was active when clicked twice, increase
    public int newLevelRange() {
        // if (rangeState) {
        //     if (baseLevel == 0 && levelRange < 3) {
        //         levelRange += 1;
        //     } 
        //     else if (baseLevel == 1 && levelRange < 3) {
        //         levelRange += 1;
        //     } 
        //     else if (baseLevel == 2 && levelRange < 3) {
        //         levelRange += 1;
        //     }
        //     System.out.println("LEVEL RANGE: " + levelRange);
        // }

        if (rangeState) {
            return levelRange += 1;
        }
        return levelRange;
    }

    public int newLevelSpeed() {
        // if (speedState) {
        //     if (baseLevel == 0 && levelSpeed < 3) {
        //         levelSpeed += 1;
        //     } 
        //     else if (baseLevel == 1 && levelSpeed < 3) {
        //         levelSpeed += 1;
        //     } 
        //     else if (baseLevel == 2 && levelSpeed < 3) {
        //         levelSpeed += 1;
        //     }
        //     System.out.println("LEVEL SPEED: " + levelSpeed);
        // }

        if (speedState) {
            levelSpeed+= 1;
        }

        return levelSpeed;
    }

    public int newLevelDamage() {
        // if (damageState) {
        //     if (baseLevel == 0 && levelDamage < 3) {
        //         levelDamage += 1;
        //     } 
        //     else if (baseLevel == 1 && levelDamage < 3) {
        //         levelDamage += 1;
        //     } 
        //     else if (baseLevel == 2 && levelDamage < 3) {
        //         levelDamage += 1;
        //     }
        //     System.out.println("LEVEL DAMAGE: " + levelDamage);
        // }
        if (damageState) {
            levelDamage += 1;
        }
        return levelDamage;
    }

    /*
     * return button states
     */
    public boolean getButtonRange() {
        return rangeState;
    }

    public boolean getButtonSpeed() {
        return speedState;
    }

    public boolean getButtonDamage() {
        return damageState;
    }
}
