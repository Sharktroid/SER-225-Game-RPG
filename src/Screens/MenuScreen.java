package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;

// This is the class for the main menu screen
public class MenuScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected SpriteFont playGame;
    protected SpriteFont credits;
    protected SpriteFont hubWorld;
    protected SpriteFont worldOne;
    protected SpriteFont worldTwo;
    protected SpriteFont worldThree;
    protected SpriteFont worldZero;
    protected Map background;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker();

    public static int worldNumber;

    public MenuScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {

        playGame = new SpriteFont("PLAY GAME", 200, 119, "Comic Sans", 30, new Color(49, 207, 240));
        playGame.setOutlineColor(Color.black);
        playGame.setOutlineThickness(3);
        credits = new SpriteFont("CREDITS", 200, 219, "Comic Sans", 30, new Color(49, 207, 240));
        credits.setOutlineColor(Color.black);
        credits.setOutlineThickness(3);
        worldZero = new SpriteFont("WORLD ZERO", 200, 319, "Comic Sans", 30, new Color(49, 207, 240));
        worldZero.setOutlineColor(Color.black);
        worldZero.setOutlineThickness(3);
        worldOne = new SpriteFont("WORLD ONE", 200, 369, "Comic Sans", 30, new Color(49, 207, 240));
        worldOne.setOutlineColor(Color.black);
        worldOne.setOutlineThickness(3);
        worldTwo = new SpriteFont("WORLD TWO", 200, 419, "Comic Sans", 30, new Color(49, 207, 240));
        worldTwo.setOutlineColor(Color.black);
        worldTwo.setOutlineThickness(3);
        worldThree = new SpriteFont("WORLD THREE", 200, 469, "Comic Sans", 30, new Color(49, 207, 240));
        worldThree.setOutlineColor(Color.black);
        worldThree.setOutlineThickness(3);
        hubWorld = new SpriteFont("HUB WORLD", 200, 519, "Comic Sans", 30, new Color(49, 207, 240));
        hubWorld.setOutlineColor(Color.black);
        hubWorld.setOutlineThickness(3);
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.ENTER);
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);

        // if down or up is pressed, change menu item "hovered" over (blue square in
        // front of text will move along with currentMenuItemHovered changing)
        if (Keyboard.isKeyDown(Key.DOWN) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered++;
        } else if (Keyboard.isKeyDown(Key.UP) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered--;
        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }

        // if down is pressed on last menu item or up is pressed on first menu item,
        // "loop" the selection back around to the beginning/end
        if (currentMenuItemHovered > 6) {
            currentMenuItemHovered = 0;
        } else if (currentMenuItemHovered < 0) {
            currentMenuItemHovered = 6;
        }

        // sets location for blue square in front of text (pointerLocation) and also
        // sets color of spritefont text based on which menu item is being hovered
        if (currentMenuItemHovered == 0) {
            playGame.setColor(new Color(255, 215, 0));
            credits.setColor(new Color(49, 207, 240));
            worldZero.setColor(new Color(49, 207, 240));
            worldOne.setColor(new Color(49, 207, 240));
            worldTwo.setColor(new Color(49, 207, 240));
            worldThree.setColor(new Color(49, 207, 240));
            hubWorld.setColor(new Color(49, 207, 240));
            pointerLocationX = 170;
            pointerLocationY = 130;
        } else if (currentMenuItemHovered == 1) {
            playGame.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(255, 215, 0));
            worldZero.setColor(new Color(49, 207, 240));
            worldOne.setColor(new Color(49, 207, 240));
            worldTwo.setColor(new Color(49, 207, 240));
            worldThree.setColor(new Color(49, 207, 240));
            hubWorld.setColor(new Color(49, 207, 240));
            pointerLocationX = 170;
            pointerLocationY = 230;
        } else if (currentMenuItemHovered == 2) {
            playGame.setColor(new Color(49,207,240));
            credits.setColor(new Color(49,207,240));
            worldZero.setColor(new Color(255,215,0));
            worldOne.setColor(new Color(49,207,240));
            worldTwo.setColor(new Color(49,207,240));
            worldThree.setColor(new Color(49,207,240));
            hubWorld.setColor(new Color(49, 207, 240));
            pointerLocationX = 170;
            pointerLocationY = 330;
        } else if (currentMenuItemHovered == 3) {
            playGame.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(49, 207, 240));
            worldZero.setColor(new Color(49, 207, 240));
            worldOne.setColor(new Color(255, 215, 0));
            worldTwo.setColor(new Color(49, 207, 240));
            worldThree.setColor(new Color(49, 207, 240));
            hubWorld.setColor(new Color(49, 207, 240));
            pointerLocationX = 170;
            pointerLocationY = 380;
        } else if (currentMenuItemHovered == 4) {
            playGame.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(49, 207, 240));
            worldZero.setColor(new Color(49, 207, 240));
            worldOne.setColor(new Color(49, 207, 240));
            worldTwo.setColor(new Color(255, 215, 0));
            worldThree.setColor(new Color(49, 207, 240));
            hubWorld.setColor(new Color(49, 207, 240));
            pointerLocationX = 170;
            pointerLocationY = 430;
        } else if (currentMenuItemHovered == 5) {
            playGame.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(49, 207, 240));
            worldZero.setColor(new Color(49, 207, 240));
            worldOne.setColor(new Color(49, 207, 240));
            worldTwo.setColor(new Color(49, 207, 240));
            worldThree.setColor(new Color(255, 215, 0));
            hubWorld.setColor(new Color(49, 207, 240));
            pointerLocationX = 170;
            pointerLocationY = 480;
        } else if (currentMenuItemHovered == 6) {
            playGame.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(49, 207, 240));
            worldZero.setColor(new Color(49, 207, 240));
            worldOne.setColor(new Color(49, 207, 240));
            worldTwo.setColor(new Color(49, 207, 240));
            worldThree.setColor(new Color(49, 207, 240));
            hubWorld.setColor(new Color(255, 215, 0));
            pointerLocationX = 170;
            pointerLocationY = 530;
        }

        // if space is pressed on menu item, change to appropriate screen based on which
        // menu item was chosen
        if (Keyboard.isKeyUp(Key.ENTER)) {
            keyLocker.unlockKey(Key.ENTER);
        }
        if (!keyLocker.isKeyLocked(Key.ENTER) && Keyboard.isKeyDown(Key.ENTER)) {
            menuItemSelected = currentMenuItemHovered;
            if (menuItemSelected == 0) {
                worldNumber = 4;
                screenCoordinator.setGameState(GameState.SLIDESHOW);
            } else if (menuItemSelected == 1) {
                screenCoordinator.setGameState(GameState.CREDITS);
            } else if (menuItemSelected == 2) {
                worldNumber = 0;
                screenCoordinator.setGameState(GameState.LEVEL);
            } else if (menuItemSelected == 3) {
                worldNumber = 1;
                screenCoordinator.setGameState(GameState.LEVEL);
            } else if (menuItemSelected == 4){
                worldNumber = 2;
                screenCoordinator.setGameState(GameState.LEVEL);
            } else if (menuItemSelected == 5){
                worldNumber = 3;
                screenCoordinator.setGameState(GameState.LEVEL);
            }
            else if (menuItemSelected == 6){
                worldNumber = 4;
                screenCoordinator.setGameState(GameState.LEVEL);
            }
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        playGame.draw(graphicsHandler);
        credits.draw(graphicsHandler);
        worldZero.draw(graphicsHandler);
        worldOne.draw(graphicsHandler);
        worldTwo.draw(graphicsHandler);
        worldThree.draw(graphicsHandler);
        hubWorld.draw(graphicsHandler);
        graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20,
                new Color(49, 207, 240), Color.black, 2);
    }
}