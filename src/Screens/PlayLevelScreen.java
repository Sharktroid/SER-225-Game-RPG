package Screens;

import java.util.ArrayList;

import Combatants.PlayerCombatant;
import Combatants.PsychicPsycho;
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
    
    protected boolean flagStates[];
    private KeyLocker keyLocker = new KeyLocker();

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
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

            flagManager.addFlag("portalOneActivated", false);
            flagManager.addFlag("portalTwoActivated", false);
            flagManager.addFlag("portalThreeActivated", false);
            flagManager.addFlag("startWorldThree", false);
            flagManager.addFlag("sawHubMsg", false);
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
        inventory = new InventoryScreen(player);

        //setup win screen (**from old test map)
        winScreen = new WinScreen(this);

        ArrayList<Combatant> combatants = new ArrayList<Combatant>();
        combatants.add(new PlayerCombatant(player, map, Combatant.ControlType.HUMAN));
        combatants.add(new PsychicPsycho(player, map));
        map.initiateCombat(player, new ArrayList<Combatant>(combatants));
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

        //hub world teleportation **(temporary quick world swap keys)
        if (map.getFlagManager().isFlagSet("portalOneActivated") || Keyboard.isKeyDown(Key.ONE) && !keyLocker.isKeyLocked(Key.ONE)) {
            worldNum = 1;
            initialize();
        } else if (map.getFlagManager().isFlagSet("portalTwoActivated")||Keyboard.isKeyDown(Key.TWO) && !keyLocker.isKeyLocked(Key.TWO)) {
            worldNum = 2;
            initialize();
        } else if (map.getFlagManager().isFlagSet("portalThreeActivated") || Keyboard.isKeyDown(Key.THREE) && !keyLocker.isKeyLocked(Key.THREE)) {
            worldNum = 3;
            initialize();
        }else if (Keyboard.isKeyDown(Key.FOUR) && !keyLocker.isKeyLocked(Key.FOUR)) {
            worldNum = 4;
            initialize();
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

    public boolean setFlagState(boolean flagState){
        if (flagState == true){
            return true;
        }
        else{
            return false;
        }
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED
    }
}
