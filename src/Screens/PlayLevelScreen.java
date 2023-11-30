package Screens;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.*;
import Maps.*;
import Menus.InventoryMenu;
import NPCs.W1Infected1;
import Players.Cat;
import Utils.Point;

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected static FlagManager flagManager;
    protected FlagSaves flagSaves;
    protected int worldNum = -1; // **temp: set default to 4 once other menu options removed**/
    private InventoryMenu inventory;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        flagManager = new FlagManager();
        flagSaves = new FlagSaves();
        flagSaves.initializeFlags(flagManager);
    }

    public void initialize() {
        // **temp: remove this if statement once other menu items are removed**
        if (worldNum == -1)
            worldNum = Screens.MenuScreen.worldNumber;

        flagSaves.loadFlags(flagManager);
        this.map = newMap();
        map.setFlagManager(flagManager);
        this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        Point playerStartPosition = map.getPlayerStartPosition();
        this.player.setLocation(playerStartPosition.x, playerStartPosition.y);
        this.playLevelScreenState = PlayLevelScreenState.RUNNING;

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

        // setup inventory menu
        inventory = new InventoryMenu(map, player);

        // setup win screen (**from old test map)
        winScreen = new WinScreen(this);
    }

    public void update() {

        // based on screen state, perform specific actions
        switch (playLevelScreenState) {

            // if level is "running" update player and map to keep game logic for the level
            // going
            case RUNNING:
                if (Keyboard.isKeyDown(Key.E) && !KeyLocker.isKeyLocked(Key.E) && !map.getTextbox().isActive()) {
                    inventory.setActive(!inventory.isActive());
                    KeyLocker.lockKey(Key.E);

                } else if (Keyboard.isKeyUp(Key.E) && KeyLocker.isKeyLocked(Key.E)) {
                    KeyLocker.unlockKey(Key.E);
                }
                if (inventory.isActive()) {
                    inventory.update();
                } else {
                    player.update();
                }

                checkForTraversal();
                map.update(player);

                break;

            // if level has been completed, bring up level cleared screen (**OLD TEST MAP
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
        }

        // **DEBUGING KEY**
        if (Keyboard.isKeyDown(Key.M) && !KeyLocker.isKeyLocked(Key.M)) {
            KeyLocker.lockKey(Key.M);
            flagDebugger();
        } else if (Keyboard.isKeyUp(Key.M)) {
            KeyLocker.unlockKey(Key.M);
        }

        if (worldNum == 1 || worldNum == 11) {
            if (flagManager.isFlagSet("w1CuredNPC1") && flagManager.isFlagSet("w1CuredNPC2")
                    && flagManager.isFlagSet("w1CuredNPC3")
                    && flagManager.isFlagSet("w1CuredNPC4")
                    && flagManager.isFlagSet("w1CuredNPC5")) {
                flagManager.setFlag("w1CuredAllNPCs");
            }
            if (flagManager.isFlagSet("hasFinishedOMJ") && flagManager.isFlagSet("hasFinishedNSE")
                    && flagManager.isFlagSet("hasFinishedLib")
                    && !flagManager.isFlagSet("worldOneComlete")) {
                flagManager.setFlag("worldOneComplete");
            }
        }

        // **OLD TEST MAP CODE**
        if (flagManager.isFlagSet("hasFoundBall")) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
        }
    }
    // changes map if certain conditions are met
    public void checkForTraversal() {
        // to world 1 overworld (internet explorer)
        if (flagManager.isFlagSet("portalOneActivated") || (flagManager.isFlagSet("exitLibrary"))
                || (Keyboard.isKeyDown(Key.ONE) && !KeyLocker.isKeyLocked(Key.ONE))) {
            worldNum = 1;
            initialize();
        }
        // to world 1 library
        if (flagManager.isFlagSet("enterLibrary")) {
            worldNum = 11;
            initialize();
        }
        // to world 2 overworld (Safari)
        if ((flagManager.isFlagSet("portalTwoActivated"))
                || flagManager.isFlagSet("exitBuilding1") || flagManager.isFlagSet("exitBuilding2")
                || flagManager.isFlagSet("exitBuilding3") || flagManager.isFlagSet("exitBuilding4")
                || (Keyboard.isKeyDown(Key.TWO) && !KeyLocker.isKeyLocked(Key.TWO))) {
            worldNum = 2;
            initialize();
        }
        // to amazon
        if (flagManager.isFlagSet("enterBuilding1")) {
            worldNum = 21;
            initialize();
        }
        // to apple
        if (flagManager.isFlagSet("enterBuilding2")) {
            worldNum = 22;
            initialize();
        }
        // to starbucks
        if (flagManager.isFlagSet("enterBuilding3")) {
            worldNum = 23;
            initialize();
        }
        // to spotify
        if (flagManager.isFlagSet("enterBuilding4")) {
            worldNum = 24;
            initialize();
        }
        // to world 3 ground (Chrome)
        if (flagManager.isFlagSet("portalThreeActivated")
                || (map.getSubmapNum() == 1 && flagManager.isFlagSet("goDownLevel"))
                || (Keyboard.isKeyDown(Key.THREE) && !KeyLocker.isKeyLocked(Key.THREE))) {
            worldNum = 3;
            initialize();
        }
        // to world 3 level 1
        if ((map.getSubmapNum() == 0 && flagManager.isFlagSet("goUpLevel"))
                || (map.getSubmapNum() == 2 && flagManager.isFlagSet("goDownLevel"))) {
            worldNum = 31;
            initialize();
        }
        // to world 3 level 2
        if ((map.getSubmapNum() == 1 && flagManager.isFlagSet("goUpLevel"))
                || (map.getSubmapNum() == 3 && flagManager.isFlagSet("goDownLevel"))) {
            worldNum = 32;
            initialize();
        }
        // to world 3 level 3
        if ((map.getSubmapNum() == 2 && flagManager.isFlagSet("goUpLevel"))
                || (map.getSubmapNum() == 4 && flagManager.isFlagSet("goDownLevel"))) {
            worldNum = 33;
            initialize();
        }
        // to world 3 level 4
        if (map.getSubmapNum() == 3 && flagManager.isFlagSet("goUpLevel")) {
            worldNum = 34;
            initialize();
        }
        // to world 4 (hub)
        if (flagManager.isFlagSet("teleportToHub")
                || (Keyboard.isKeyDown(Key.FOUR) && !KeyLocker.isKeyLocked(Key.FOUR))) {
            worldNum = 4;
            initialize();
        }
        // to world 0 (**temp**)
        if (Keyboard.isKeyDown(Key.ZERO) && !KeyLocker.isKeyLocked(Key.ZERO)) {
            worldNum = 0;
            initialize();
        }
        // to world 5 (**temp**)
        if (Keyboard.isKeyDown(Key.FIVE) && !KeyLocker.isKeyLocked(Key.FIVE)) { // Evan
            worldNum = 5;
            initialize();
        }
        // to world 6 (**temp**)
        if (Keyboard.isKeyDown(Key.SIX) && !KeyLocker.isKeyLocked(Key.SIX)) { // Calvin
            worldNum = 6;
            initialize();
        }
        // to world 7 (**temp**)
        if (Keyboard.isKeyDown(Key.SEVEN) && !KeyLocker.isKeyLocked(Key.SEVEN)) { // Shannon
            worldNum = 7;
            initialize();
        }
        // to world 8 (**temp**)
        if (Keyboard.isKeyDown(Key.EIGHT) && !KeyLocker.isKeyLocked(Key.EIGHT)) { // Juliet
            worldNum = 8;
            initialize();
        }
        // to world 9 (**temp**)
        if (Keyboard.isKeyDown(Key.NINE) && !KeyLocker.isKeyLocked(Key.NINE)) { // Aaron
            worldNum = 9;
            initialize();
        }

    }

    // choses map based on world number
    public Map newMap() {
        Map newMap;
        if (worldNum == 0)
            newMap = new WorldZeroMap();
        else if (worldNum == 1)
            newMap = new W1GMap();
        else if (worldNum == 2)
            newMap = new W2GMap();
        else if (worldNum == 21)
            newMap = new W2AmazonMap();
        else if (worldNum == 22)
            newMap = new W2AppleMap();
        else if (worldNum == 23)
            newMap = new W2SpotifyMap();
        else if (worldNum == 24)
            newMap = new W2StarbucksMap();
        else if (worldNum == 3)
            newMap = new W3GMap();
        else if (worldNum == 31)
            newMap = new W31Map();
        else if (worldNum == 32)
            newMap = new W32Map();
        else if (worldNum == 33)
            newMap = new W33Map();
        else if (worldNum == 34)
            newMap = new W34Map();
        else if (worldNum == 4)
            newMap = new HubMap();
        else if (worldNum == 5)
            newMap = new EvanTestMap();
        else if (worldNum == 6)
            newMap = new CalvinTestMap();
        else if (worldNum == 7)
            newMap = new ShannonTestMap();
        else if (worldNum == 8)
            newMap = new JulietTestMap();
        else if (worldNum == 9)
            newMap = new AaronTestMap();
        else if (worldNum == 11)
            newMap = new W1LibraryMap();
        else
            newMap = new WorldZeroMap();
        return newMap;
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

    public void resetLevel() {
        initialize();
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    public static FlagManager getFlagManager() {
        return flagManager;
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    public void flagDebugger() {
        if (worldNum == 1) {
            System.out.println("\nOMJ");
            flagManager.debugFlag("hasTalkedToOMJ");
            flagManager.debugFlag("hasFoundDentures");
            flagManager.debugFlag("hasFinishedOMJ");
            System.out.println("\nNSE");
            flagManager.debugFlag("hasTalkedToNSE");
            flagManager.debugFlag("w1CuredNPC1");
            flagManager.debugFlag("w1CuredNPC2");
            flagManager.debugFlag("w1CuredNPC3");
            flagManager.debugFlag("w1CuredNPC4");
            flagManager.debugFlag("w1CuredNPC5");
            flagManager.debugFlag("w1Btl1");
            flagManager.debugFlag("w1Btl2");
            flagManager.debugFlag("w1Btl3");
            flagManager.debugFlag("w1Btl4");
            flagManager.debugFlag("w1Btl5");
            flagManager.debugFlag("w1CuredAllNPCS");
            flagManager.debugFlag("hasFinishedNSE");
            System.out.println("\nLIB");
            flagManager.debugFlag("hasTalkedToNSE");
            flagManager.debugFlag("scannedLibrarian");
            flagManager.debugFlag("hasFinishedNSE");
            flagManager.debugFlag("hasTalkedToLibrarian");
            flagManager.debugFlag("w1FoundFrag3");
            flagManager.debugFlag("hasFinishedLib");
            System.out.println();
        } else if (worldNum == 3 || worldNum == 31 || worldNum == 32 || worldNum == 33 || worldNum == 34) {
            flagManager.debugFlag("goUpLevel");
            flagManager.debugFlag("goDownLevel");
            flagManager.debugFlag("wentUpLevel");
            flagManager.debugFlag("wentDownLevel");
            System.out.println();
        }
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED
    }

}