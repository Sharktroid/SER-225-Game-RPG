package Screens;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.*;
import Maps.WorldOneMap;
import Maps.WorldTwoMap;
import Maps.WorldThreeFloors;
import Maps.WorldZeroMap;
import Maps.CalvinTestMap;
import Maps.EvanTestMap;
import Maps.ShannonTestMap;
import Maps.JulietTestMap;
import Maps.LibraryMap;
import Maps.AaronTestMap;
import Menus.InventoryMenu;
import Maps.HubMap;
import Players.Cat;
import Utils.Direction;
import Utils.Point;

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected FlagManager flagManager;
    protected int worldNum = -1;
    protected int floorNum = 0;
    private InventoryMenu inventory;

   
    private KeyLocker keyLocker = new KeyLocker();

    //public String[] persistentFlags = {"sawHubMsg","hasTalkedToFirefox0","hasTalkedToFirefox1", "hasTalkedToFirefox2","hasTalkedToFirefox3","unlockedPortal1","unlockedPortal2","unlockedPortal2","unlockedPortal3","worldOneComplete","worldTwoComplete","worldThreeComplete"};
    
    

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        //String[] persistentFlags = {"sawHubMsg","hasTalkedToFirefox0","hasTalkedToFirefox1", "hasTalkedToFirefox2","hasTalkedToFirefox3","unlockedPortal1","unlockedPortal2","unlockedPortal2","unlockedPortal3","worldOneComplete","worldTwoComplete","worldThreeComplete"};
        //FlagSaves flagSaves = new FlagSaves();
        //FlagSaves.initialize(persistentFlags);
    }

    public void initialize() {

        // setup state
        flagManager = new FlagManager();
                

        // takes world number variable form menu screen to choose world
        if (worldNum == -1) {
            worldNum = Screens.MenuScreen.worldNumber;
        }

        //setup world zero map
        if (worldNum == 0) {
            this.map = new WorldZeroMap();
        }
        //set world one map
        else if (worldNum == 1) {
            this.map = new WorldOneMap();

            flagManager.addFlag("hasLostBall", false);
            flagManager.addFlag("hasTalkedToWalrus", false);
            flagManager.addFlag("hasTalkedToDinosaur", false);
            flagManager.addFlag("hasFoundBall", false);
            flagManager.addFlag("sawHubMsg", false);
            // flagManager.addFlag("hasTalkedToElder", false);
            // flagManager.addFlag("findCat", false);
            // flagManager.addFlag("foundCat", false);
            // flagManager.addFlag("foundCat2", false);
            // flagManager.addFlag("hasTalkedToGarfunkle", false);
            // flagManager.addFlag("finishedElder", false);
            // flagManager.addFlag("hasTalkedToElder2", false);
            // flagManager.addFlag("findFetch", false);
            // flagManager.addFlag("foundFetch", false);

            //old man jenkins
            flagManager.addFlag("hasTalkedToOMJ", false);
            flagManager.addFlag("hasFoundDentures", false);
            flagManager.addFlag("hasFinishedOMJ", false);
            //engineer
            flagManager.addFlag("hasTalkedToNSE", false);
            //librarian
            flagManager.addFlag("hasTalkedToLibrarian", false);
            flagManager.addFlag("hasRanVirusScanLibrarian", false);
            flagManager.addFlag("hasFoundLibraryShard", false);
            //normal npcs
            flagManager.addFlag("hasRanVirusScanNPC1", false);
            flagManager.addFlag("hasRanVirusScanNPC2", false);
            //infected npcs
            flagManager.addFlag("curedNPC1", false);
            flagManager.addFlag("curedNPC2", false);
            flagManager.addFlag("curedNPC3", false);
            flagManager.addFlag("curedNPC4", false);
            flagManager.addFlag("curedNPC5", false);
            flagManager.addFlag("hasCuredAllNPCs", false);

            flagManager.addFlag("enteredLibrary", false);

            flagManager.addFlag("worldOneCleared", false);


        }

        
        //setup world two map
        else if (worldNum == 2) {
            this.map = new WorldTwoMap();

            flagManager.addFlag("hasTalkedToBeaver", false);
        }

        //setup world three map
        else if (worldNum == 3) {

            this.map = new WorldThreeFloors(floorNum);

            flagManager.addFlag("wentUpLevel", false);
            flagManager.addFlag("wentDownLevel", false);
            flagManager.addFlag("hasTalkedToRedPanda", WorldThreeFloors.redPandaFlagState());
            flagManager.addFlag("hasTalkedToDino", WorldThreeFloors.DinoFlagState());

            

        }

        

        //setup hub world map
        else if (worldNum == 4) {
            this.map = new HubMap();
            
            
            flagManager.addFlag("beenToWorldOne", false);
            flagManager.addFlag("beenToWorldTwo", false);
            flagManager.addFlag("beenToWorldThree", false);

            for (int i = 0; i < 4; i++){
                if (!HubMap.getTalkedFS(i)){
                    flagManager.addFlag("hasTalkedToFirefox"+i, false);
                    
                }
                else if (HubMap.getTalkedFS(i)){
                    flagManager.addFlag("hasTalkedToFirefox"+i, true);
                    
                }
            }
            
            
            flagManager.addFlag("portalOneActivated", false);
            flagManager.addFlag("portalTwoActivated", false);
            flagManager.addFlag("portalThreeActivated", false);

            flagManager.addFlag("startWorldThree", false);
            flagManager.addFlag("sawHubMsg", HubMap.getHubMsgFS());


            for (int i = 1; i <= 3; i++){
                if (!HubMap.getUnlockedPortalFS(i)){
                    flagManager.addFlag("unlockedPortal"+i, HubMap.getUnlockedPortalFS(i));
                }
                else if (HubMap.getUnlockedPortalFS(i)){
                    flagManager.addFlag("unlockedPortal"+i, HubMap.getUnlockedPortalFS(i));
                }
            }

            

            flagManager.addFlag("worldOneComplete", WorldOneMap.w1ClearedFS);
            flagManager.addFlag("worldTwoComplete", false);
            flagManager.addFlag("worldThreeComplete", false);

        }

        else if (worldNum == 5){
            this.map = new EvanTestMap();
        }
        else if (worldNum == 6){
            this.map = new CalvinTestMap();
        }
        else if (worldNum == 7){
            this.map = new ShannonTestMap();
            flagManager.addFlag("hasTalkedToOMJ", false);
            flagManager.addFlag("hasFoundDentures", false);

            flagManager.addFlag("hasTalkedToNSE", false);

            flagManager.addFlag("curedNPC1", false);
            flagManager.addFlag("curedNPC2", false);
            flagManager.addFlag("curedNPC3", false);
            flagManager.addFlag("curedNPC4", false);
            flagManager.addFlag("curedNPC5", false);
            flagManager.addFlag("hasCuredAllNPCs", false);
        }
        else if (worldNum == 8){
            this.map = new JulietTestMap();
        }
        else if (worldNum == 9){
            this.map = new AaronTestMap();
        }


        else if (worldNum == 11){
            this.map = new LibraryMap();

            flagManager.addFlag("exitedLibrary", false);

            
        }


        map.setFlagManager(flagManager);

        // setup player
        this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        Point playerStartPosition = map.getPlayerStartPosition();
        this.player.setLocation(playerStartPosition.x, playerStartPosition.y);
        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
        this.player.setFacingDirection(Direction.LEFT);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // setup map scripts to have references to the map and player
        for (MapTile mapTile : map.getMapTiles()) {
            if (mapTile.getInteractScript() != null) {
                mapTile.getInteractScript().setMap(map);
                mapTile.getInteractScript().setPlayer(player);
            }
        }
        for (NPC npc : map.getNPCs()) {
            if (npc.getInteractScript() != null) {
                npc.getInteractScript().setMap(map);
                npc.getInteractScript().setPlayer(player);
            }
        }
        for (EnhancedMapTile enhancedMapTile : map.getEnhancedMapTiles()) {
            if (enhancedMapTile.getInteractScript() != null) {
                enhancedMapTile.getInteractScript().setMap(map);
                enhancedMapTile.getInteractScript().setPlayer(player);
            }
        }
        for (Trigger trigger : map.getTriggers()) {
            if (trigger.getTriggerScript() != null) {
                trigger.getTriggerScript().setMap(map);
                trigger.getTriggerScript().setPlayer(player);
            }
        }

        //setup inventory menu
        inventory = new InventoryMenu(player);

        //setup win screen (**from old test map)
        winScreen = new WinScreen(this);
    }

    public void update() {

        // based on screen state, perform specific actions
        switch (playLevelScreenState) {

            // if level is "running" update player and map to keep game logic for the level going
            case RUNNING:
                if (Keyboard.isKeyDown(Key.E) && !keyLocker.isKeyLocked(Key.E) && !map.getTextbox().isActive()) {
                    inventory.setActive(!inventory.isActive());
                    keyLocker.lockKey(Key.E);

                } else if (Keyboard.isKeyUp(Key.E) && keyLocker.isKeyLocked(Key.E)) {
                    keyLocker.unlockKey(Key.E);
                }
                if (inventory.isActive()) {
                    
                    inventory.update();
                }

                else {
                    player.update();
                }

                map.update(player);
                break;

            // if level has been completed, bring up level cleared screen (**from old test map)
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
        }


        //world traversal

        //to internet explorer world
        if (map.getFlagManager().isFlagSet("portalOneActivated")|| (map.getFlagManager().isFlagSet("exitedLibrary")) || Keyboard.isKeyDown(Key.ONE) && !keyLocker.isKeyLocked(Key.ONE)) {
            worldNum = 1;
            initialize();

        //to safari world 
        } else if ((map.getFlagManager().isFlagSet("portalTwoActivated"))||Keyboard.isKeyDown(Key.TWO) && !keyLocker.isKeyLocked(Key.TWO)) {
            worldNum = 2;
            initialize();

        //to chrome word
        } else if (map.getFlagManager().isFlagSet("portalThreeActivated") || Keyboard.isKeyDown(Key.THREE) && !keyLocker.isKeyLocked(Key.THREE)) {
            worldNum = 3;
            initialize();
        }
        
        //to hub world
        else if (map.getFlagManager().isFlagSet("worldOneCleared")|| map.getFlagManager().isFlagSet("worldTwoCleared") || map.getFlagManager().isFlagSet("worldThreeCleared") || Keyboard.isKeyDown(Key.FOUR) && !keyLocker.isKeyLocked(Key.FOUR)) {
            worldNum = 4;
            initialize();
            
        //to world zero -> nine
        }else if (Keyboard.isKeyDown(Key.ZERO) && !keyLocker.isKeyLocked(Key.ZERO)) {
            worldNum = 0;
            initialize();
        }else if (Keyboard.isKeyDown(Key.FIVE) && !keyLocker.isKeyLocked(Key.FIVE)) { //Evan
            worldNum = 5;
            initialize();
        }else if (Keyboard.isKeyDown(Key.SIX) && !keyLocker.isKeyLocked(Key.SIX)) { //Calvin
            worldNum = 6;
            initialize();
        }else if (Keyboard.isKeyDown(Key.SEVEN) && !keyLocker.isKeyLocked(Key.SEVEN)) { //Shannon
            worldNum = 7;
            initialize();
        }else if (Keyboard.isKeyDown(Key.EIGHT) && !keyLocker.isKeyLocked(Key.EIGHT)) { //Juliet
            worldNum = 8;
            initialize();
        }else if (Keyboard.isKeyDown(Key.NINE) && !keyLocker.isKeyLocked(Key.NINE)) { //Aaron
            worldNum = 9;
            initialize();
        }        

        //world one area traversal
        if (worldNum == 1 || worldNum == 11){
            if (map.getFlagManager().isFlagSet("enteredLibrary")) {
                worldNum = 11;
                initialize();
            }

            if (map.getFlagManager().isFlagSet("exitedLibrary")) {
                worldNum = 1;
                initialize();
            }
        }

        //world three floor traversal
        if (worldNum == 3) {
            if (map.getFlagManager().isFlagSet("enteredBuilding")) {
                flagManager.unsetFlag("enteredBuilding");
                initialize();
            }

            if (map.getFlagManager().isFlagSet("wentUpLevel")) {
                floorNum = WorldThreeFloors.getCurrentFloorNumber();
                initialize();
            }

            if (map.getFlagManager().isFlagSet("wentDownLevel")) {
                floorNum = WorldThreeFloors.getCurrentFloorNumber();
                WorldThreeFloors.setDownFromFloorTrue();
                initialize();
            }
        }


        // if flag is set at any point during gameplay, game is "won" (**from old test map)
        if (map.getFlagManager().isFlagSet("hasFoundBall")) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                if (inventory.isActive()) {
                    inventory.draw(graphicsHandler);
                }
                break;
            case LEVEL_COMPLETED:
                winScreen.draw(graphicsHandler);
                break;
        }
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    public boolean getFlagState(boolean flagState){

        return flagState;
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED
    }
}
