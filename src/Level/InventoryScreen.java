package Level;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import GameObject.Item;
import SpriteFont.SpriteFont;
import Utils.Point;
import Engine.Config;

public class InventoryScreen {
    private Boolean active = false;
    private final int left = 20;
    private final int top = left;
    private final int spacer = left;
    private final int width = Config.GAME_WINDOW_WIDTH - (left * 2) - 16;
    private final int height = Config.GAME_WINDOW_HEIGHT - spacer - (left * 2) - 39;
    private final int inventoryHeight = (int) (height * (0.75));
    private final int descriptionHeight = (int) (height * (0.25));
    private final int border = 25;
    private final int rows = 5;
    private final int fontSize = 30;
    private final int lineGap = (((int) getInternalSize().y - fontSize) / (rows - 1));

    private SpriteFont[] inventoryText = new SpriteFont[rows * 2];
    private int keyPressTimer;
    private int currentTextItemHovered = 0;
    private ArrayList<Item> items;
    private KeyLocker keyLocker = new KeyLocker();

    public InventoryScreen(Item[] items) {
        this(new ArrayList<Item>(Arrays.asList(items)));
    }

    public InventoryScreen(ArrayList<Item> items) {
        setItems(items);
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangleWithBorder(left, top, width, inventoryHeight, Color.white, Color.black, 2);

        for (int i = 0; i < items.size(); i++) {
            inventoryText[i].setColor(Color.black);
        }
        inventoryText[currentTextItemHovered].setColor(Color.red);
        int y = top + border;
        inventoryText[0].drawWithParsedNewLines(graphicsHandler, 10);
        for (int i = 0; i < items.size(); i++) {
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
        int descriptionTop = top + inventoryHeight + spacer;
        graphicsHandler.drawFilledRectangleWithBorder(left, descriptionTop, width, descriptionHeight, Color.white, Color.black, 2);

        SpriteFont description = new SpriteFont(getCurrentItem().getDescription(), 0, 0, "Arial", fontSize, Color.black);
        description.setX(left + border);
        description.setY(descriptionTop + border);
        description.drawWithParsedNewLines(graphicsHandler, 10);
    }

    public void update() {
        if (lockKey(Key.RIGHT)) {
            currentTextItemHovered = (currentTextItemHovered + 1) % items.size();

        } else if (lockKey(Key.LEFT)) {
            currentTextItemHovered--;
            if (currentTextItemHovered < 0) {
                currentTextItemHovered = items.size() - 1;
            }

        } else if (lockKey(Key.UP)) {
            currentTextItemHovered -= 2;
            if (currentTextItemHovered < 0) {
                if (items.size() % 2 == 0) {
                    currentTextItemHovered += items.size();
                }
                else {
                    if (currentTextItemHovered == -1) {
                        currentTextItemHovered = items.size() - 2;
                    }
                    else {
                        currentTextItemHovered = items.size() - 1;
                    }
                }
            }

        } else if (lockKey(Key.DOWN)) {
            currentTextItemHovered += 2;
            if (currentTextItemHovered >= items.size()) {
                if (items.size() % 2 == 0) {
                    currentTextItemHovered -= items.size();
                }
                else {
                    if (currentTextItemHovered == items.size()) {
                        currentTextItemHovered = 1;
                    }
                    else {
                        currentTextItemHovered = 0;
                    }
                }
            }

        }
        unlockKey(Key.LEFT);
        unlockKey(Key.UP);
        unlockKey(Key.DOWN);
        unlockKey(Key.RIGHT);
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

    public void setItems(ArrayList<Item> items) {
        this.items = items;
        for (int i = 0; i < items.size(); i++) {
            inventoryText[i] = new SpriteFont(items.get(i).getName(), 0, 0, "Arial", fontSize, Color.black);
        }
    }

    private Item getCurrentItem() {
        return items.get(currentTextItemHovered);
    }

    private Boolean lockKey(Key key) {
        if (Keyboard.isKeyDown(key) && !keyLocker.isKeyLocked(key)) {
            keyLocker.lockKey(key);
            return true;
        }
        else {
            return false;
        }
    }

    private void unlockKey(Key key) {
        if (keyLocker.isKeyLocked(key) && Keyboard.isKeyUp(key)) {
            keyLocker.unlockKey(key);
        }
    }
}
