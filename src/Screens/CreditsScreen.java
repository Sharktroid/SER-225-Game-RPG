package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the credits screen
public class CreditsScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected SpriteFont creditsLabel;
    protected SpriteFont createdByLabel;
    protected SpriteFont returnInstructionsLabel;

    public CreditsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        // setup graphics on screen (background map, spritefont text)
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        creditsLabel = new SpriteFont("Credits", 350, 7, "Times New Roman", 30, Color.white);
        createdByLabel = new SpriteFont("Created by: Aaron, Shannon, Juliet, Calvin, Evan", 120, 151, "Times New Roman", 30, Color.white);
        createdByLabel.setOutlineColor(Color.black);
        returnInstructionsLabel = new SpriteFont("Press Enter to return to the menu", 200, 532, "Times New Roman", 30, Color.white);
        KeyLocker.lockKey(Key.ENTER);
    }

    public void update() {
        background.update(null);

        if (Keyboard.isKeyUp(Key.ENTER)) {
            KeyLocker.unlockKey(Key.ENTER);
        }

        // if space is pressed, go back to main menu
        if (!KeyLocker.isKeyLocked(Key.ENTER) && Keyboard.isKeyDown(Key.ENTER)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        creditsLabel.draw(graphicsHandler);
        createdByLabel.draw(graphicsHandler);
        returnInstructionsLabel.draw(graphicsHandler);
    }
}
