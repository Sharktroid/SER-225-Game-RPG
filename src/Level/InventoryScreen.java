package Level;

import java.awt.Color;
import java.util.ArrayList;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import GameObject.Item;
import SpriteFont.SpriteFont;
import Utils.Point;
import Engine.Config;

public class InventoryScreen {
    private Player player;
    private Boolean active = false;
    private final int left = 20;
    protected final int top = left;
    private final int spacer = left;
    private final int width = Config.GAME_WINDOW_WIDTH - (left * 2) - 16;
    private final int height = Config.GAME_WINDOW_HEIGHT - spacer - (left * 2) - 39;
    protected final int inventoryHeight = (int) (height * (0.75));
    private final int descriptionHeight = (int) (height * (0.25));
    protected final int border = 25;
    private final int rows = 5;
    protected final int fontSize = 30;
    private final int lineGap = (((int) getInternalSize().y - fontSize * 2) / (rows - 1));

    private SpriteFont[] spriteFonts = new SpriteFont[rows * 2];
    private int currentTextItemHovered = 0;
    private Boolean viewingKeyItems = false;
    private KeyLocker keyLocker = new KeyLocker();
    protected InventoryScreenItemSelectionBox selectionBox;

    public InventoryScreen(Player player) {
        this.player = player;
    }

    public void draw(GraphicsHandler graphicsHandler) {
        updateItemText();
        graphicsHandler.drawFilledRectangleWithBorder(left, top, width, inventoryHeight, Color.white, Color.black, 2);

        for (int i = 0; i < getCurrentItems().size(); i++) {
            spriteFonts[i].setColor(Color.black);
        }
        if (getCurrentItems().size() > 0) {
            spriteFonts[currentTextItemHovered].setColor(Color.red);
        }
        for (int i = 0; i < getCurrentItems().size(); i++) {
            spriteFonts[i].drawWithParsedNewLines(graphicsHandler, 10);
        }
        int descriptionTop = top + inventoryHeight + spacer;
        graphicsHandler.drawFilledRectangleWithBorder(left, descriptionTop, width, descriptionHeight, Color.white,
                Color.black, 2);
        String description = "";
        if (getCurrentItem() != null) {
            description = getCurrentItem().getDescription();
        }
        SpriteFont descriptionSpriteFont = new SpriteFont(description, 0, 0, "Arial", fontSize, Color.black);
        descriptionSpriteFont.setX(left + border);
        descriptionSpriteFont.setY(descriptionTop + border);
        descriptionSpriteFont.drawWithParsedNewLines(graphicsHandler, 10);
        if (selectionBox != null) {
            selectionBox.draw(graphicsHandler);
        }

    }

    public void update() {
        if (selectionBox == null) {
            if (lockKey(Key.RIGHT)) {
                currentTextItemHovered = (currentTextItemHovered + 1) % getCurrentItems().size();

            } else if (lockKey(Key.LEFT)) {
                currentTextItemHovered--;
                if (currentTextItemHovered < 0) {
                    currentTextItemHovered = getCurrentItems().size() - 1;
                }

            } else if (lockKey(Key.UP)) {
                currentTextItemHovered -= 2;
                if (currentTextItemHovered < 0) {
                    if (getCurrentItems().size() % 2 == 0) {
                        currentTextItemHovered += getCurrentItems().size();
                    } else {
                        if (currentTextItemHovered == -1) {
                            currentTextItemHovered = getCurrentItems().size() - 2;
                        } else {
                            currentTextItemHovered = getCurrentItems().size() - 1;
                        }
                    }
                }

            } else if (lockKey(Key.DOWN)) {
                currentTextItemHovered += 2;
                if (currentTextItemHovered >= getCurrentItems().size()) {
                    if (getCurrentItems().size() % 2 == 0) {
                        currentTextItemHovered -= getCurrentItems().size();
                    } else {
                        if (currentTextItemHovered == getCurrentItems().size()) {
                            currentTextItemHovered = 1;
                        } else {
                            currentTextItemHovered = 0;
                        }
                    }
                }

            } else if (lockKey(Key.SHIFT)) {
                setViewingKeyItems(!viewingKeyItems);
                if (currentTextItemHovered >= getCurrentItems().size()) {
                    currentTextItemHovered = 0;
                }
            } else if (lockKey(Key.ENTER)) {
                SpriteFont currSpriteFont = spriteFonts[currentTextItemHovered];
                selectionBox = new InventoryScreenItemSelectionBox(this, getCurrentItem(),
                        new Point(currSpriteFont.getX(), currSpriteFont.getY() + fontSize + 5));
                if (selectionBox.choices.size() <= 1) {
                    selectionBox = null;
                }
            }
            unlockKey(Key.LEFT);
            unlockKey(Key.UP);
            unlockKey(Key.DOWN);
            unlockKey(Key.RIGHT);
            unlockKey(Key.SHIFT);
            unlockKey(Key.ENTER);
        } else {
            selectionBox.update();
        }
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
        setViewingKeyItems(false);
    }

    public Point getInternalSize() {
        return new Point(width - border * 2, height - border * 2);
    }

    public void updateItemText() {
        int y = top + border;
        for (int i = 0; i < getCurrentItems().size(); i++) {
            spriteFonts[i] = new SpriteFont(getCurrentItems().get(i).getName(), 0, 0, "Arial", fontSize, Color.black);

            int x;
            if (i % 2 == 0) {
                x = border + left;
            } else {
                x = (int) (width / 2) + left;
            }
            spriteFonts[i].setY(y);
            spriteFonts[i].setX(x);
            if (i % 2 == 1) {
                y += lineGap;
            }
        }
    }

    public void drop(Item item) {
        getCurrentItems().remove(item);
        if (currentTextItemHovered >= getCurrentItems().size()) {
            currentTextItemHovered = getCurrentItems().size() - 1;
        }
    }

    private Item getCurrentItem() {
        if (getCurrentItems().size() > 0) {
            return getCurrentItems().get(currentTextItemHovered);
        }
        else {
            return null;
        }
    }

    private ArrayList<Item> getCurrentItems() {
        if (viewingKeyItems) {
            return player.keyItems;
        } else {
            return player.items;
        }
    }

    private Boolean lockKey(Key key) {
        if (Keyboard.isKeyDown(key) && !keyLocker.isKeyLocked(key)) {
            keyLocker.lockKey(key);
            return true;
        } else {
            return false;
        }
    }

    private void unlockKey(Key key) {
        if (keyLocker.isKeyLocked(key) && Keyboard.isKeyUp(key)) {
            keyLocker.unlockKey(key);
        }
    }

    private void setViewingKeyItems(Boolean viewing) {
            viewingKeyItems = viewing;
    }
}
