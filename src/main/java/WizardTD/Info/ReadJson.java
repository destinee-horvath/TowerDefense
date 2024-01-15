package WizardTD.Info;

import java.util.*;

import processing.core.PApplet;
import processing.data.JSONObject;
import processing.data.JSONArray;

public class ReadJson {
    private int wave;
    private JSONObject file;
    private JSONObject object;
    private JSONArray waveArray;
    private JSONArray monsterProperties;
    private JSONObject monsterObject;

    public ReadJson(JSONObject file, int wave) {
        this.file = file;
        this.wave = wave;
        waveArray = file.getJSONArray("waves");
        // object = waveArray.getJSONObject(wave);
        // monsterProperties = object.getJSONArray("monsters");
        // monsterObject = monsterProperties.getJSONObject(wave);
    }

    public String getLayout() {
        String layout = file.getString("layout");
        System.out.println(layout);
        return layout;
    }

    public int readWaveDuration() {
        JSONArray waveArray = file.getJSONArray("waves");
        if (wave >= 0 && wave < waveArray.size()) {
            JSONObject waveObject = waveArray.getJSONObject(wave);
            return waveObject.getInt("duration");
        }

        return -1; //error
    }   

    public double preWavePause() {
        JSONArray waveArray = file.getJSONArray("waves");

        if (wave >= 0 && wave < waveArray.size()) {
            JSONObject waveObject = waveArray.getJSONObject(wave);
            return waveObject.getDouble("pre_wave_pause");
        }

        return -1.0; //error
    }

    public JSONArray getMonsters() {
        JSONArray waveArray = file.getJSONArray("waves");
        if (wave >= 0 && wave < waveArray.size()) {
            JSONObject monsterObject = waveArray.getJSONObject(wave);
            monsterProperties = (monsterObject.getJSONArray("monsters"));
            return monsterObject.getJSONArray("monsters");
        }

        return new JSONArray(); //error
    }

    public String getMonsterType() {
        JSONArray monsters = getMonsters();
        JSONObject monster = monsters.getJSONObject(0);
        return monster.getString("type");
    }

    public String monsterType() {
        JSONArray monsterProperties = getMonsters();
        JSONObject monsterObject = monsterProperties.getJSONObject(0); 
        String monsterType = monsterObject.getString("type");
        return monsterType;
    }

    public int monsterHP() {
        JSONArray monsterProperties = getMonsters();
        JSONObject monsterObject = monsterProperties.getJSONObject(0); 
        int monsterHP = monsterObject.getInt("hp");
        return monsterHP;
    }

    public double monsterSpeed() {
        JSONArray monsterProperties = getMonsters();
        JSONObject monsterObject = monsterProperties.getJSONObject(0); 
        double monsterSpeed = monsterObject.getDouble("speed");
        return monsterSpeed;
    }

    public double monsterArmour() {
        JSONArray monsterProperties = getMonsters();
        JSONObject monsterObject = monsterProperties.getJSONObject(0); 
        double monsterArmour = monsterObject.getDouble("armour");
        return monsterArmour;
    }

    public int monsterManaKill() {
        JSONArray monsterProperties = getMonsters();
        JSONObject monsterObject = monsterProperties.getJSONObject(0); 
        int monsterManaKill = monsterObject.getInt("mana_gained_on_kill");
        return monsterManaKill;
    }

    public int monsterQuantity() {
        JSONArray monsterProperties = getMonsters();
        JSONObject monsterObject = monsterProperties.getJSONObject(0); 
        int monsterQuantity = monsterObject.getInt("quantity");
        return monsterQuantity;
    }
    
    public int initialTowerRange() {
        int initialTowerRange = file.getInt("initial_tower_range");
        return initialTowerRange;
    }

    public double initialFiringSpeed() {
        double initialFiringSpeed = file.getInt("initial_tower_firing_speed");
        return initialFiringSpeed;
    }

    public int initialTowerDamage() {
        int initialTowerDamage = file.getInt("initial_tower_damage");
        return initialTowerDamage;
    }

    public int initialMana() {
        int initialMana = file.getInt("initial_mana");
        return initialMana;
    }

    public int initialManaCap() {
        int initialManaCap = file.getInt("initial_mana_cap");
        return initialManaCap;
    }

    public int initialManaPerSecond() {
        int initialManaPerSecond = file.getInt("initial_mana_gained_per_second");
        return initialManaPerSecond;
    }

    public int towerCost() {
        int towerCost = file.getInt("tower_cost");
        return towerCost;
    }

    public int MPSinitialCost() {
        int MPScostPerUse = file.getInt("mana_pool_spell_initial_cost");
        return MPScostPerUse;
    }

    public int MPScostIncreasePerUse() {
        int MPScostIncreasePerUse = file.getInt("mana_pool_spell_cost_increase_per_use");
        return MPScostIncreasePerUse;
    }

    public double MPScapMultiplier() {
        double MPScapMultiplier = file.getDouble("mana_pool_spell_cap_multiplier");
        return MPScapMultiplier;
    }    

    public double MPSmanaGainedMultiplier() {
        double MPSmanaGainedMultiplier = file.getDouble("mana_pool_spell_mana_gained_multiplier");
        return MPSmanaGainedMultiplier;
    }

    /*
     * count number of waves in json
     */
    public int numberWaves() {
        JSONArray numberWavesArray = file.getJSONArray("waves");
        return numberWavesArray.size();
    }
}

