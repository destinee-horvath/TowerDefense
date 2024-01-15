package WizardTD.ButtonsFolder;

public class AdditionalCosts {

    private boolean range, speed, damage;
    private int additionalCosts, levelDamage, levelRange, levelSpeed, levelBase;

    public AdditionalCosts(boolean speed, boolean range, boolean damage) {
        this.speed = speed;
        this.range = range; 
        this.damage = damage;

        additionalCosts = 0;
        levelDamage = 0;
        levelSpeed = 0;
        levelRange = 0;
        levelBase = 0;
    }

    public int getAdditionalCosts() {
        if (damage) {
            additionalCosts +=20;
            levelDamage = 1;
        }
        if (range) {
            additionalCosts += 20;
            levelRange = 1;
        }
        if (speed) {
            additionalCosts +=20;
            levelSpeed = 1;
        }

        return additionalCosts;
    }

    public int getLevelDamage() {
        return levelDamage;
    }

    public int getLevelRange() {
        return levelRange;
    }

    public int getLevelSpeed() {
        return levelSpeed;
    }

    public int getBaseLevel() {
        if (levelDamage == 1 && levelRange == 1 && levelSpeed == 1) {
            levelBase = 1;
        }
        return levelBase;
    }
}
