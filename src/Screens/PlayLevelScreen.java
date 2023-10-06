package Screens;

import java.util.Scanner;
import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Item;
import Items.EraserEraser;
import Items.Grafcalibur;
import Items.GutsyBat;
import Items.PencilEraser;
import Items.VideoRelaxant;
import Items.WhackaBump;
import Level.*;
import Maps.WorldOneMap;
import Maps.WorldZeroMap;
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
    private InventoryScreen inventory;
    private int keyPressTimer;
    private KeyLocker keyLocker = new KeyLocker();

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state


        flagManager = new FlagManager();
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasFoundBall", false);
        flagManager.addFlag("portalActive", false);

        
        this.map = new WorldOneMap();
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

        Item[] items = {new Grafcalibur(), new GutsyBat(), new VideoRelaxant(), new WhackaBump()};
        Item[] keyItems = {new PencilEraser(), new EraserEraser()};

        inventory = new InventoryScreen(items, keyItems);

        winScreen = new WinScreen(this);
    }

    public void initializeTwo() {
        // setup state


        flagManager = new FlagManager();
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasFoundBall", false);
        flagManager.addFlag("portalActive", false);

        
        this.map = new WorldZeroMap();
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

        //String[] items = {"Test A", "Test B"};
        //inventory = new InventoryScreen(items);

        //winScreen = new WinScreen(this);

    }
    public void update() {
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                if (Keyboard.isKeyDown(Key.E) && !keyLocker.isKeyLocked(Key.E)) {
                    inventory.setActive(!inventory.isActive());
                    keyLocker.lockKey(Key.E);
                    
                }
                else if (Keyboard.isKeyUp(Key.E) && keyLocker.isKeyLocked(Key.E)) {
                    keyLocker.unlockKey(Key.E);
                }
                if (inventory.isActive()) {
                    inventory.update();
                }
                
                else {
                    player.update();
                }

                if (Keyboard.isKeyDown(Key.L) && keyPressTimer == 0) {
                    initializeTwo();
                }   
            
                map.update(player);
                break;
            
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
        }

        // if flag is set at any point during gameplay, game is "won"
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

    public void changeMap(){

    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED
    }
}
