package Level;

import java.awt.Color;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.Keyboard;
import GameObject.Item;
import SpriteFont.SpriteFont;
import Utils.Point;
import Engine.Config;

public class InventoryScreen {
    // private Map map;
    // private Textbox textbox;
    private Boolean active = false;
    private final int left = 20;
    private final int top = left;
    private final int width = Config.GAME_WINDOW_WIDTH - (left * 2) - 16;
    private final int height = Config.GAME_WINDOW_HEIGHT - (top * 2) - 39;
    private final int border = 30;
    private final int rows = 5;
    private final int fontSize = 30;
    private final int lineGap = (((int) getInternalSize().y - fontSize) / (rows - 1));
    private SpriteFont[] inventoryText = new SpriteFont[rows * 2];
    private int keyPressTimer;
    private int currentTextItemHovered = 0;
    private int numItems;

        // this.map = map;
    public InventoryScreen(Item[] items) {
        setItems(items);
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // if camera is at bottom of screen, textbox is drawn at top of screen instead
        // of the bottom like usual
        // to prevent it from covering the player
        graphicsHandler.drawFilledRectangleWithBorder(left, top, width, height, Color.white, Color.black, 2);

        for (int i = 0; i < numItems; i++) {
            inventoryText[i].setColor(Color.black);
        }
        inventoryText[currentTextItemHovered].setColor(Color.red);
        int y = top + border;
        inventoryText[0].drawWithParsedNewLines(graphicsHandler, 10);
        for (int i = 0; i < numItems; i++) {
            int x;
            if (i % 2 == 0) {
                x = border + left;
            } else {
                x = (int) (width / 2) + left;
            }
            inventoryText[i].setY(y);
            inventoryText[i].setX(x);
            inventoryText[i].drawWithParsedNewLines(graphicsHandler, 10);

            if (i % 2 == 1) {
                y += lineGap;
            }
        }
    }

    public void update() {
        if (Keyboard.isKeyDown(Key.RIGHT) && (keyPressTimer == 0)) {
            keyPressTimer = 14;
            currentTextItemHovered++;

        } else if (Keyboard.isKeyDown(Key.LEFT) && (keyPressTimer == 0)) {
            keyPressTimer = 14;
            currentTextItemHovered--;

        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }

        // if down is pressed on last item or up is pressed on first item, "loop" the
        // selection back around to the beginning/end
        if (currentTextItemHovered == numItems) {
            currentTextItemHovered = 0;
        } else if (currentTextItemHovered < 0) {
            currentTextItemHovered = numItems - 1;
        }
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Point getInternalSize() {
        return new Point(width - border * 2, height - border * 2);
    }

    public void setItems(Item[] items) {
        numItems = items.length;
        for (int i = 0; i < numItems; i++) {
            inventoryText[i] = new SpriteFont(items[i].getName(), 0, 0, "Arial", fontSize, Color.black);
        }
    }
}
