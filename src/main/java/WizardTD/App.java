package WizardTD;


//for rotate image
    import java.awt.event.MouseListener;
    import java.awt.Graphics2D;
    import java.awt.event.KeyEvent;
    import java.awt.geom.AffineTransform;
    import java.awt.image.BufferedImage;
//

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.event.MouseEvent;

import java.io.*;
import java.util.*;

//class imports 
import WizardTD.Info.*;
import WizardTD.ButtonsFolder.*;
import WizardTD.Tower.*;
import WizardTD.Gremlins.*;
import WizardTD.UI.*;


// import main.java.WizardTD.MouseLocation;
// import main.java.WizardTD.ReadJson;
// import main.java.WizardTD.ButtonsFolder.AdditionalCosts;
// import main.java.WizardTD.ButtonsFolder.ButtonBuild;
// import main.java.WizardTD.ButtonsFolder.ButtonBuildFreeze;
// import main.java.WizardTD.ButtonsFolder.ButtonManaPool;
// import main.java.WizardTD.ButtonsFolder.ButtonPause;
// import main.java.WizardTD.ButtonsFolder.ButtonSpeed;
// import main.java.WizardTD.ButtonsFolder.ButtonUpgradeDamage;
// import main.java.WizardTD.ButtonsFolder.ButtonUpgradeRange;
// import main.java.WizardTD.ButtonsFolder.ButtonUpgradeSpeed;
// import main.java.WizardTD.ButtonsFolder.Buttons;
// import main.java.WizardTD.Gremlins.Gremlin;
// import main.java.WizardTD.Gremlins.GremlinPrint;
// import main.java.WizardTD.Gremlins.InitialiseGremlins;
// import main.java.WizardTD.Gremlins.NodeNew;
// import main.java.WizardTD.Gremlins.PathFinder;
// import main.java.WizardTD.Tower.DisplayTowerInfo;
// import main.java.WizardTD.Tower.PlaceTower;
// import main.java.WizardTD.Tower.ShooterLogic;
// import main.java.WizardTD.Tower.ShooterPrint;
// import main.java.WizardTD.Tower.Shooter;
// import main.java.WizardTD.Tower.Tower;
// import main.java.WizardTD.Tower.TowerPrint;
// import main.java.WizardTD.Tower.TowerPrintDamage;
// import main.java.WizardTD.Tower.TowerPrintRange;
// import main.java.WizardTD.Tower.TowerPrintSpeed;
// import main.java.WizardTD.Tower.UpgradeTower;
// import main.java.WizardTD.UI.Lose;
// import main.java.WizardTD.UI.ManaBar;
// import main.java.WizardTD.UI.PrintManaBar;
// import main.java.WizardTD.UI.Win;

public class App extends PApplet {

    public static final int CELLSIZE = 32;
    public static final int SIDEBAR = 120;
    public static final int TOPBAR = 40;
    public static final int BOARD_WIDTH = 20;

    public static int WIDTH = CELLSIZE*BOARD_WIDTH+SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH*CELLSIZE+TOPBAR;
//////
    public static final int FPS = 60;  //SET TO 60

    public String configPath;

    public Random random = new Random();
	
	// Feel free to add any additional methods or attributes you want. Please put classes in different files.
    public Board board;
    public JSONObject jsonFile;
    public ReadJson readJson;

    //button classes 
    public Buttons buttons;
    public ButtonSpeed buttonSpeed;
    public ButtonPause buttonPause;
    public ButtonBuild buttonBuild;
    public ButtonUpgradeRange buttonUpgradeRange;
    public ButtonUpgradeSpeed buttonUpgradeSpeed;
    public ButtonUpgradeDamage buttonUpgradeDamage;
    public ButtonManaPool buttonManaPool;
    public ButtonBuildFreeze buttonBuildFreeze;

    //gremlin classes
    public PathFinder pathFinder;
    public NodeNew NodeNew;
    public GremlinPrint gremlinPrint;
    public InitialiseGremlins initialiseGremlins;

    //tower classes
    public PlaceTower placeTower;
    public Tower tower, towerClicked;
    public DisplayTowerInfo displayTowerInfo;
    public ShooterLogic shooterLogic;
    public Shooter shooter;

    //UI
    public Lose loseScreen;
    public ManaBar manaBar;
    public PrintManaBar printManaBar;
    public Win winScreen;

    //getter classes 
    public MouseLocation mouseLocation;
    public Countdown countdown;

    //images
    public PImage wizard_house;
    public PImage gremlin0;
    public PImage gremlin1;
    public PImage gremlin2;
    public PImage gremlin3;
    public PImage gremlin4;
    public PImage gremlin5;
    public PImage worm;
    public PImage beetle;
    public ArrayList<PImage> gremlinImages;
    
    public PImage towerZero;
    public PImage towerOne;
    public PImage towerTwo;
    public PImage fireball;

    public PApplet block;

    //initial
    public int level = 1;  ///
    public int wave = 0;
    public double speedTower = 1;
    public double speedGremlin = 1;
    public int mana;
    public int manaCap;

    public int totalCost;

    public boolean currentTowerClicked = false;

    //mouse
    public int mouseX;
    public int mouseY;
    public int lastMouseX = -1;
    public int lastMouseY = -1;
    List<Integer> lastClick;
    public ClickInfo lastClickInfo;
    int clickCount = 0;
    boolean playerLose = false;
    boolean freezeHitGremlin = false;

    ArrayList<ArrayList<Character>> fileMap;
    HashMap<List<Integer>, Tower> towersHashMap;
    ArrayList<Gremlin> gremlinArraylist;
    ArrayList<Integer> frozenGremlins;
    HashMap<Integer, ShooterLogic> shooterList;
    public boolean mouseReleased = false;
    boolean playerWon = false;
    boolean waveInProgress = false;

    public Timer timer;

    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
     */
	@Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
     */
	@Override
    public void setup() {
        frameRate(FPS);
        //backgrounds
        board = new Board(loadImage("src/main/resources/WizardTD/grass.png"), loadImage("src/main/resources/WizardTD/shrub.png"), 
        loadImage("src/main/resources/WizardTD/path0.png"), loadImage("src/main/resources/WizardTD/path1.png"),
        loadImage("src/main/resources/WizardTD/path2.png"), loadImage("src/main/resources/WizardTD/path3.png"),
        loadImage("src/main/resources/WizardTD/wizard_house.png"));
        
        wizard_house = loadImage("src/main/resources/WizardTD/wizard_house.png");
        
        gremlin0 = loadImage("src/main/resources/WizardTD/gremlin.png");
        gremlin1 = loadImage("src/main/resources/WizardTD/gremlin1.png");
        gremlin2 = loadImage("src/main/resources/WizardTD/gremlin2.png");
        gremlin3 = loadImage("src/main/resources/WizardTD/gremlin3.png");
        gremlin4 = loadImage("src/main/resources/WizardTD/gremlin4.png");
        gremlin5 = loadImage("src/main/resources/WizardTD/gremlin5.png");
        worm = loadImage("src/main/resources/WizardTD/worm.png");
        beetle = loadImage("src/main/resources/WizardTD/beetle.png");
        
        towerZero = loadImage("src/main/resources/WizardTD/tower0.png");
        towerOne = loadImage("src/main/resources/WizardTD/tower1.png");
        towerTwo = loadImage("src/main/resources/WizardTD/tower2.png");
        fireball = loadImage("src/main/resources/WizardTD/fireball.png");

        jsonFile = loadJSONObject(configPath);
        readJson = new ReadJson(jsonFile, wave);
        mouseLocation = new MouseLocation();

        buttonSpeed = new ButtonSpeed(readJson, "FF", "2 x Speed", 650, 50, 40, 40, this);
        buttonPause = new ButtonPause(readJson, "P", "PAUSE", 650, 100, 40, 40, this);
        buttonBuild = new ButtonBuild(readJson, "T", "Build Tower", 650, 150, 40, 40, this);
        buttonUpgradeRange = new ButtonUpgradeRange(readJson, "U1", "Upgrade Range", 650, 200, 40, 40, this);
        buttonUpgradeSpeed = new ButtonUpgradeSpeed(readJson, "U2", "Upgrade Speed", 650, 250, 40, 40, this);
        buttonUpgradeDamage = new ButtonUpgradeDamage(readJson, "U3", "Upgrade Damage", 650, 300, 40, 40, this);
        buttonManaPool = new ButtonManaPool(readJson,"M", "Mana Pool Cost: 100", 650, 350, 40, 40, this);
        buttonBuildFreeze = new ButtonBuildFreeze(readJson, "Q", "Build Freeze Tower", 650, 400, 40, 40, this);

        manaBar = new ManaBar(readJson, readJson.initialMana(), readJson.initialManaCap(), readJson.initialManaPerSecond(), readJson.MPSinitialCost());
        printManaBar = new PrintManaBar(manaBar, readJson, this, this);

        lastClickInfo = new ClickInfo(-1, -1);
        
        towersHashMap = new HashMap<List<Integer>, Tower>();
        shooterList = new HashMap<Integer, ShooterLogic>();
        
        fileMap = board.fileMap(level);

        //gremlin
        pathFinder = new PathFinder(fileMap);  
        gremlinImages = new ArrayList<PImage>();
        gremlinImages.add(gremlin0);
        gremlinImages.add(gremlin1);
        gremlinImages.add(gremlin2);
        gremlinImages.add(gremlin3);
        gremlinImages.add(gremlin4);
        gremlinImages.add(gremlin5);
        gremlinImages.add(worm);
        gremlinImages.add(beetle);
        

        gremlinArraylist = new ArrayList<>();
        frozenGremlins = new ArrayList<>();

        initialiseGremlins = new InitialiseGremlins(gremlinArraylist, readJson, pathFinder, wave);
        gremlinArraylist = initialiseGremlins.setGremlins();

        countdown = new Countdown(readJson, wave, this);

        //shooterMap = new HashMap<>();
        winScreen = new Win(this);
        loseScreen = new Lose(this);
    }

    /**
     * Receive key pressed signal from the keyboard.
     */
	//@Override
    public void keyPressed(){
        // if f - double speed - if clicked again return to normal
        if (key == 'f' || key == 'F') {
            System.out.println("f pressed");
            buttonSpeed.printButton();
        }
        // if p - pause - if clicked again return to normal 
        if (key == 'p' || key == 'P') {
            System.out.println("p pressed");
            buttonPause.printButton();
        }
        // if t - click on grass tile to place tower
        if (key == 't' || key == 'T') { 
            System.out.println("t pressed");
            buttonBuild.keyPressed();
            buttonBuild.printButton();
        }
        // if q - click on grass tile to place tower (Freeze tower)
        if (key == 'q' || key == 'Q') { 
            System.out.println("q pressed");
            buttonBuildFreeze.keyPressed();
            buttonBuildFreeze.printButton();
        }

        // if 1,2,3 - upgrade tower 
        if (key == '1') {
            System.out.println("1 pressed");
            buttonUpgradeRange.keyPressed();
            buttonUpgradeRange.printButton();
            
        }
        if (key == '2') {
            System.out.println("2 pressed");
            buttonUpgradeSpeed.keyPressed();
            buttonUpgradeSpeed.printButton();
            
        }
        if (key == '3') {
            System.out.println("3 pressed");
            buttonUpgradeDamage.keyPressed();
            buttonUpgradeDamage.printButton();
            
        }
        // if m - activate Mana pool spell and IMMEDIATELY deactivate 
        if (key == 'm' || key == 'M') { //check OTHER conditions
            System.out.println("m pressed");
            buttonManaPool.keyPressed();
            buttonManaPool.printButton();
        }

        if ((key == 'r' || key == 'R')) { 
            System.out.println("r pressed");
            level = 1;
            wave = 0;
            playerWon = false;

            //reset lists
            gremlinArraylist = new ArrayList<>();
            fileMap = board.fileMap(level);
            pathFinder = new PathFinder(fileMap);

            initialiseGremlins = new InitialiseGremlins(gremlinArraylist, readJson, pathFinder, wave);
            gremlinArraylist = initialiseGremlins.setGremlins();

            countdown = new Countdown(readJson, wave, this);

            shooterList = new HashMap<Integer, ShooterLogic>();
            manaBar = new ManaBar(readJson, readJson.initialMana(), readJson.initialManaCap(), readJson.initialManaPerSecond(), readJson.MPSinitialCost());
            printManaBar = new PrintManaBar(manaBar, readJson, this, this);

            playerLose = false;
        }
    }

    /**
     * Receive key released signal from the keyboard.
     */
	@Override
    public void keyReleased(){

    }

    @Override
    public void mousePressed(MouseEvent e) { //called when clicked anywhere on board
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {   
        boolean towerJustPlaced = false;
        currentTowerClicked = false;
        fileMap = board.fileMap(level);

        // mana += manaBar.increaseManaTimer();
        // mana = manaBar.getMana();
        // manaCap = manaBar.getManaCap();

        //System.out.println(mana);

        /*
         * Mouse info
         */
        double x = e.getX(); 
        double y = e.getY();
        mouseLocation.location(x, y);
        mouseX = mouseLocation.tileX();
        mouseY = mouseLocation.tileY();

        //System.out.println("|| mouseX: " + mouseX + "  ||  " + "mouseY: " + mouseY + " ||");
        lastClickInfo = new ClickInfo(mouseX, mouseY);
        

        placeTower = new PlaceTower(fileMap, readJson, towersHashMap, mouseX, mouseY);

        /*
         * Placing regular tower
         */
        if (buttonBuild.getState() && mana >= readJson.towerCost()) {
            if (placeTower.towerPlaced() && !towersHashMap.containsKey(placeTower.towerPosition())) { //check grass and if tower doesnt already exist
                
                AdditionalCosts additionalCosts = new AdditionalCosts(buttonUpgradeSpeed.getState(), buttonUpgradeRange.getState(), buttonUpgradeDamage.getState());
                int extras = additionalCosts.getAdditionalCosts();
                int levelBase = additionalCosts.getBaseLevel();
                int levelRange = additionalCosts.getLevelRange();
                int levelSpeed = additionalCosts.getLevelSpeed();
                int levelDamage = additionalCosts.getLevelDamage();
                buttonBuild.newCost(extras);

                mana = manaBar.decreaseMana(readJson.towerCost() + extras);
                tower = new Tower(placeTower.towerPosition(), levelBase, levelRange, levelSpeed, levelDamage, readJson);
                
                towersHashMap.put(placeTower.towerPosition(), tower);
                towerJustPlaced = true;
                
                //match shooter logic with location 
                List<Integer> shooterTowerLocation = new ArrayList<>();
                shooterTowerLocation.add(tower.getLocation().get(0));
                shooterTowerLocation.add(tower.getLocation().get(1));

                Shooter shooter = new Shooter(tower.getLocation().get(0), tower.getLocation().get(1) , tower.getLevelDamage());
                ShooterLogic shooterLogic = new ShooterLogic(shooter, tower, null);
                shooterList.put((towersHashMap.size() - 1), shooterLogic); //note: should match size of hash

                for (Map.Entry<Integer, ShooterLogic> shooterEntry : shooterList.entrySet()) {
                    ShooterLogic shooterLogicToPrint = shooterEntry.getValue();
                }
                
            }
        }
        
        /*
         * Selecting regular tower
         */
        towerClicked = towersHashMap.get(placeTower.towerPosition());
        if (towersHashMap.containsKey(placeTower.towerPosition()) && !towerJustPlaced && towerClicked.getBaseLevel() != 100) {
            UpgradeTower beforeUpgrade = new UpgradeTower(towerClicked, false, false, false); //set to false to prevent upgrade
            displayTowerInfo = new DisplayTowerInfo(placeTower.towerPosition(), towerClicked, beforeUpgrade, this);
        
            clickCount++;
            if (clickCount == 2) {
                UpgradeTower upgradeTower = new UpgradeTower(towerClicked, buttonUpgradeDamage.getState(), buttonUpgradeSpeed.getState(), buttonUpgradeRange.getState());
                totalCost = upgradeTower.totalCost();
                System.out.println("Total Cost: " + totalCost);

                if (totalCost <= mana) {
                    Tower newTower = new Tower(placeTower.towerPosition(), upgradeTower.newLevelBase(), upgradeTower.newLevelRange(), upgradeTower.newLevelSpeed(), upgradeTower.newLevelDamage(), readJson);
                    displayTowerInfo = new DisplayTowerInfo(placeTower.towerPosition(), newTower, upgradeTower, this);
                    
                    List<Integer> tmpKey = placeTower.towerPosition();
                    towersHashMap.put(tmpKey, newTower);
                    mana = manaBar.decreaseMana(totalCost);

                    //when upgrading tower, tower in shooterlogic needs to be upgraded (tower upgraded)
                    int index = 0;
                    for (Map.Entry<List<Integer>, Tower> entry : towersHashMap.entrySet()) {
                        if (entry.getKey().equals(tmpKey)) {
                            break;
                        }
                        index++;
                    }
                    try {
                        ShooterLogic oldShooterLogic = shooterList.get(index);
                        ShooterLogic shooterLogicUpgradeTower = new ShooterLogic(oldShooterLogic.getShooter(), newTower, oldShooterLogic.getGremlin());
                        shooterList.put(index, shooterLogicUpgradeTower);
                    }
                    catch (NullPointerException n) {}
                        


                    clickCount = 0;
                }
            }
            currentTowerClicked = true; 
        }

        /*
         * Mana pool upgraded
         */
        if (buttonManaPool.getState() && mana >= manaBar.getManaCost()) {
            System.out.println("Upgrade Mana Pool");
            int manaDecreased = manaBar.decreaseMana(manaBar.getManaCost());
            int manaCapIncreased = manaBar.increaseManaCap();
            int MPSincreased = manaBar.increaseManaPerSecond();
            int manaUpgradeCostIncreased = manaBar.manaUpgradeCost();

            manaBar = new ManaBar(readJson, manaDecreased, manaCapIncreased, MPSincreased, manaUpgradeCostIncreased);
            printManaBar = new PrintManaBar(manaBar, readJson, this, this);

            String newButtonLabel = "Mana Pool Cost: " + manaBar.getManaCost();
            buttonManaPool = new ButtonManaPool(readJson,"M", newButtonLabel, 650, 350, 40, 40, this);
        }

        /*
         * Placing freeze tower 
         */
        if (buttonBuildFreeze.getState() && mana >= 150) {
            if (placeTower.towerPlaced() && !towersHashMap.containsKey(placeTower.towerPosition())) {
                mana = manaBar.decreaseMana(150);
                //freeze tower has level 100 base (to differentiate from regular tower)
                tower = new Tower(placeTower.towerPosition(), 100, 0, 0, 0, readJson);

                towersHashMap.put(placeTower.towerPosition(), tower);
                towerJustPlaced = true;

                //match freeze shooter 
                List<Integer> shooterTowerLocation = new ArrayList<>();
                shooterTowerLocation.add(tower.getLocation().get(0));
                shooterTowerLocation.add(tower.getLocation().get(1));

                Shooter shooter = new Shooter(tower.getLocation().get(0), tower.getLocation().get(1) , 0);
                ShooterLogic shooterLogic = new ShooterLogic(shooter, tower, null);
                shooterList.put((towersHashMap.size() - 1), shooterLogic); 

                for (Map.Entry<Integer, ShooterLogic> shooterEntry : shooterList.entrySet()) {
                    ShooterLogic shooterLogicToPrint = shooterEntry.getValue();
                }
            }
        }

    }

    
    /*@Override
    public void mouseDragged(MouseEvent e) {

    }*/

    /*
     * Draw all elements in the game by current frame.
     */
	@Override
    public void draw() {
        if (!playerWon) {
            readJson = new ReadJson(jsonFile, wave);

            //speeds
            speedTower = readJson.initialFiringSpeed();
            speedGremlin = readJson.monsterSpeed();
            //System.out.println(speedGremlin);

            /*
             * Mana
             */
            if (!buttonPause.getState()) { //not paused
                mana = manaBar.getMana();
                mana += manaBar.increaseManaTimer(buttonSpeed.getState());
                manaCap = manaBar.getManaCap();
            }
            else {
                int manaCost = manaBar.getManaCost();
                int manaGainPerSec = manaBar.getManaGainPerSec();
                manaBar = new ManaBar(readJson, mana, manaCap, manaGainPerSec, manaCost);
                mana = manaBar.getMana();
            }
            

            //background
            background(0,0,0);
            
            //file contents
            ArrayList<ArrayList<Character>> fileMap = board.fileMap(level);

            //board tiles - grasss, shrubs, path 
            ArrayList<ArrayList<PImage>> boardList = board.toDraw(fileMap);

            //wizard house coordinates 
            ArrayList<Integer> wCoord = board.getWizardCoord(fileMap); //column, row

            /*
             * Draw board
             */
            int yAxis = 40;
            for (ArrayList<PImage> row : boardList) {
                int xAxis = 0;
                for (PImage image : row) {
                    image(image, xAxis, yAxis);
                    xAxis += 32;
                }
                yAxis+=32;
            }

            /*
             * Print Towers
             */
            if (towersHashMap != null) {
                for (Map.Entry<List<Integer>, Tower> entry : towersHashMap.entrySet()) {
                    List<Integer> towerCoord = entry.getKey();
                    Tower towerHash = entry.getValue();

                    int towerX = towerCoord.get(0);
                    int towerY = towerCoord.get(1);
                    
                    //base 
                    TowerPrint towerPrint = new TowerPrint(towerHash, towerZero, towerOne, towerTwo, this);
                    image(towerPrint.printTowerBase(), towerX*32, (towerY*32 + TOPBAR));
                    
                    //upgrades
                    TowerPrintRange towerPrintRange = new TowerPrintRange(towerHash, this, towerX, towerY);
                    towerPrintRange.printTowerRange();
                    TowerPrintDamage towerPrintDamage = new TowerPrintDamage(towerHash, this, towerX, towerY);
                    towerPrintDamage.printTowerDamage();
                    TowerPrintSpeed towerPrintSpeed = new TowerPrintSpeed(towerHash, this, towerX, towerY);
                    towerPrintSpeed.printTowerSpeed();

                    //if freeze
                    if (towerHash.getBaseLevel() == 100) {
                        towerPrint.printTowerFreeze(towerX, towerY);
                    }
                }   
            }


            //Path finding 
            PathFinder pathFinder = new PathFinder(fileMap);
            ArrayList<ArrayList<NodeNew>> paths = pathFinder.findAllPaths();

            /*
             * Print gremlins
             */
            for (Gremlin object : gremlinArraylist) {
                gremlinPrint = new GremlinPrint(object, fileMap, paths, gremlinImages, readJson, this, wave);
                //System.out.println(object.getID() + " | " + object.getHP());
                
                double gremlinSpeed;
                if (buttonPause.getState()) {
                    gremlinSpeed = 0;
                }
                else if (buttonSpeed.getState()) {
                    gremlinSpeed = object.getSpeed() + object.getSpeed();
                }
                else {
                    gremlinSpeed = object.getSpeed();
                }

                gremlinPrint.printGremlins(buttonSpeed.getState(), buttonPause.getState());
                
                if (object.reachedEnd()) {
                    //game over 
                    //set speeds to zero 
                    //display game over 
                    if (!object.manaDecreased()) {
                        manaBar.decreaseMana(20);
                        object.decreaseManaOnce();
                        System.out.println("REACHED END");
                    }
                }
            }      
            
            
            /*
             * Tower Shooting
             */
            Map<Tower, Gremlin> towerGremlin = new HashMap<>();
            
            //to determine which gremlin the tower should hit 
            if (towersHashMap != null) {                
                for (Map.Entry<List<Integer>, Tower> entry : towersHashMap.entrySet()) { //for tower in hash
                    tower = entry.getValue();
                
                    Gremlin targetGremlin = null;
                    boolean breakLoop = false;
                    for (Gremlin object : gremlinArraylist) { //for gremlin in array list
                        for (Map.Entry<Integer, ShooterLogic> shooterEntry : shooterList.entrySet()) {
                            shooterLogic = shooterEntry.getValue();
                            shooter = shooterLogic.getShooter();
                            Tower shooterTower = shooterLogic.getTower();
                            int shooterID = shooterEntry.getKey();
                            
                            //assign gremlin to shooter 
                            if (object.isAlive() && shooterLogic.getGremlin() == null) {
                                targetGremlin = object;
                                ShooterLogic newShooterLogic = new ShooterLogic(shooter, shooterTower, targetGremlin);
                                
                                //freezer tower
                                if (tower.getBaseLevel() == 100 && !frozenGremlins.contains(targetGremlin.getID()) && targetGremlin.getSpeed() != 0 && !freezeHitGremlin) {
                                    frozenGremlins.add(targetGremlin.getID());
                                    shooterList.put(shooterID, newShooterLogic);
                                    //System.out.println("SIZE " + frozenGremlins.size());
                                    breakLoop = true;
                                    freezeHitGremlin = true;
                                    break;
                                }

                                if (newShooterLogic.gremlinInRange() && targetGremlin.getSpeed() != 0) { //shoot gremlins which arent frozen
                                    // System.out.println("Gremlin in range: " + targetGremlin.getID());
                                    // System.out.println("KEY: " + shooterEntry.getKey());
                                    shooterList.put(shooterID, newShooterLogic);
                                    breakLoop = true;
                                    break;
                                }

                                else { //no target
                                    newShooterLogic = new ShooterLogic(shooter, shooterTower, null);
                                    shooterList.put(shooterEntry.getKey(), newShooterLogic);
                                }
                            }

                             
                        }
                        if (breakLoop) {
                            break; //break from big loop
                        }
                    } 
                    
                    
                    if (targetGremlin != null) {
                        shooterLogic.setTargetGremlin(targetGremlin);
                        towerGremlin.put(tower, targetGremlin);
                    }  

                    //to print 
                    if (shooterList != null) {
                        int count = 0;
                        for (Map.Entry<Integer, ShooterLogic> shooterEntry : shooterList.entrySet()) {
                            //take out values from hash 
                            int shooterID = shooterEntry.getKey();
 
                            ShooterLogic shooterLogicToPrint = shooterEntry.getValue();

                            Shooter shooterToPrint = shooterLogicToPrint.getShooter();
                            Tower tower = shooterLogicToPrint.getTower();
                            Gremlin gremlin = shooterLogicToPrint.getGremlin();

                            //print
                            ShooterPrint shooterPrint = new ShooterPrint(shooterToPrint, shooterLogicToPrint, fireball, tower, gremlin, this);
                            shooterPrint.printShooterPath(tower.getSpeed(), buttonSpeed.getState(), buttonPause.getState());

                            //update with new values 
                            shooterLogicToPrint.getShooter().setX(shooterLogicToPrint.getShooterX());
                            shooterLogicToPrint.getShooter().setY(shooterLogicToPrint.getShooterY());
                            Shooter updateShooter = new Shooter(shooterLogicToPrint.getShooter().getX(),shooterLogicToPrint.getShooter().getY(), tower.getLevelDamage());

                            if (gremlin != null) {
                                if (!gremlin.isAlive() || !shooterLogicToPrint.gremlinInRange()) { //null if not in range or ded
                                    gremlin = null;
                                }  
                                else if (gremlin.isAlive() && tower.getBaseLevel() == 100 && gremlin.getSpeed() == 0 && freezeHitGremlin == true) { //shot by freze
                                    freezeHitGremlin = false;
                                    gremlin = null;
                                }
                            }             
                            

                            shooterLogicToPrint = new ShooterLogic(updateShooter, tower, gremlin);
                            
                            //System.out.println("THIS shooterX: " + shooterLogicToPrint.getShooterX() + " | shooterY: " + shooterLogicToPrint.getShooterY());
                            
                            //update hashmap
                            shooterList.put(shooterID, shooterLogicToPrint);

                        }
                    }
                }

            }   

 
            /*
             * Wizord house
             */
            int WxAxis = (wCoord.get(0))*32;
            int WyAxis = (wCoord.get(1))*32 + TOPBAR;
            int rotateW = board.WizardRotate(fileMap, wCoord);

            if (rotateW == 180 || rotateW == 0) { //on left or right of path 
                WxAxis -= 32/4;
                WyAxis -=  32/4;
            }
            else if (rotateW == 90 || rotateW == 270) { //on top or bottom of path
                WxAxis -= 32/2;
                WyAxis -= 32/2;
            }

            PImage rotatedWizardHouse = rotateImageByDegrees(wizard_house, rotateW);

            image(rotatedWizardHouse, WxAxis, WyAxis, 64, 64); //increase size to 64x64
            
            /*
             * If tower clicked, show yello circle
            */
            if (currentTowerClicked){
                displayTowerInfo.displayRange();
            }

            /*
             * GUI
             */
            //topbar
            fill(148, 72, 40);
            noStroke(); //to remove outline
            rect(0, 0, WIDTH, TOPBAR);

            //right sidebar
            fill(148, 72, 40);
            noStroke();
            rect(BOARD_WIDTH*CELLSIZE, TOPBAR, SIDEBAR, HEIGHT);

            //Tower costs (when upgrade buttons active)
            AdditionalCosts additionalCosts = new AdditionalCosts(buttonUpgradeSpeed.getState(), buttonUpgradeRange.getState(), buttonUpgradeDamage.getState());
            int extras = additionalCosts.getAdditionalCosts();
            //right sidebar buttons
            buttonSpeed.printButton();
            buttonPause.printButton();
            buttonBuild.newCost(extras);
            buttonBuild.printButton();
            buttonUpgradeRange.printButton();
            buttonUpgradeSpeed.printButton();
            buttonUpgradeDamage.printButton();
            buttonManaPool.printButton();
            buttonBuildFreeze.printButton();

            //mana counter
            printManaBar.printManaBar(mana, manaCap);

            //display tower info (click once)
            if (currentTowerClicked) {
                displayTowerInfo.buttonSpeedState(buttonUpgradeSpeed.getState());
                displayTowerInfo.buttonDamageState(buttonUpgradeDamage.getState());
                displayTowerInfo.buttonRangeState(buttonUpgradeRange.getState());
                displayTowerInfo.printTowerInfo();
            } 


            /*
             * When wave ends (reinitialise some classes, etc..)
             */
            if (countdown.waveEnd() && wave < readJson.numberWaves()) { //less than length of waveArray
                System.out.println("wave ended");
                wave++;
                pathFinder = new PathFinder(fileMap);
                ReadJson readJson = new ReadJson(jsonFile, wave);
                System.out.println("NEW READ JSON-" + wave);

                if (wave != readJson.numberWaves()) { //to prevent index error in initialise gremlins
                    initialiseGremlins = new InitialiseGremlins(gremlinArraylist, readJson, pathFinder, wave); 
                    gremlinArraylist = initialiseGremlins.setGremlins();  
                }
                 
                countdown = new Countdown(readJson, wave, this);
                
            }

            else if (!countdown.waveEnd() && countdown.preWavePause() && countdown.canSpawn()) {
                System.out.println("pre-wave pause");
            }
            

            /*
             * Level cleared 
             */
            if (wave == readJson.numberWaves()) {
                level++;
                wave = 0; 
                gremlinArraylist = new ArrayList<>();
                //reset towers 
                towersHashMap = new HashMap<>();

                //file contents
                fileMap = board.fileMap(level);

                if (fileMap == null) {
                    System.out.println("Player Won!");
                    winScreen.printWin();
                    playerWon = true;
                }
                else {
                    //board tiles - grasss, shrubs, path 
                    boardList = board.toDraw(fileMap);

                    //wizard house coordinates 
                    wCoord = board.getWizardCoord(fileMap); //column, row
                    
                    pathFinder = new PathFinder(fileMap);
                    ReadJson readJson = new ReadJson(jsonFile, wave);
                    initialiseGremlins = new InitialiseGremlins(gremlinArraylist, readJson, pathFinder, wave); 
                    gremlinArraylist = initialiseGremlins.setGremlins(); 
                    countdown = new Countdown(readJson, wave, this);
                }
                
            }

            /*
             * Timer
             */
            if (!buttonPause.getState()) { //not paused
                countdown.printTimer(wave, buttonSpeed.getState());
            }
            else { 
                countdown.toPrint(wave);
            }

            /*
             * Win/Lose 
             */
            if (mana < 0) {
                loseScreen.printLose();
                playerWon = false;
            }

            if (playerLose) {
                loseScreen.printLose();
            }
        }
            
    }


    public static void main(String[] args) {
        PApplet.main("WizardTD.App");
    }

    /**
     * Source: https://stackoverflow.com/questions/37758061/rotate-a-buffered-image-in-java
     * @param pimg The image to be rotated
     * @param angle between 0 and 360 degrees
     * @return the new rotated image
     */
    public PImage rotateImageByDegrees(PImage pimg, double angle) {
        BufferedImage img = (BufferedImage) pimg.getNative();
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        PImage result = this.createImage(newWidth, newHeight, ARGB);
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
}
