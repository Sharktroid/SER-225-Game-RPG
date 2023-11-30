package Screens;

import Engine.*;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the win level screen
public class WinScreen extends Screen {
    protected SpriteFont winMessage;
    protected SpriteFont instructions;
    protected PlayLevelScreen playLevelScreen;

    public WinScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        winMessage = new SpriteFont("You win!", 350, 239, "Comic Sans", 30, Color.white);
        instructions = new SpriteFont("Press Escape to go back to the main menu", 225, 279,"Comic Sans", 20, Color.white);
        KeyLocker.lockKey(Key.ENTER);
        KeyLocker.lockKey(Key.ESC);
    }

    @Override
    public void update() {
        if (Keyboard.isKeyUp(Key.ENTER)) {
            KeyLocker.unlockKey(Key.ENTER);
        }
        if (Keyboard.isKeyUp(Key.ESC)) {
            KeyLocker.unlockKey(Key.ESC);
        }

        // if space is pressed, reset level. if escape is pressed, go back to main menu
        if (Keyboard.isKeyDown(Key.ENTER) && !KeyLocker.isKeyLocked(Key.ENTER)) {
            playLevelScreen.resetLevel();
        } else if (Keyboard.isKeyDown(Key.ESC) && !KeyLocker.isKeyLocked(Key.ESC)) {
            playLevelScreen.goBackToMenu();
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        winMessage.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }
}
