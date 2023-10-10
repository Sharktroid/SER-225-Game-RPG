package Level;

import java.awt.Color;
import java.util.ArrayList;
import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import GameObject.Item;
import Scripts.HubMap.portalOneScript;
import SpriteFont.SpriteFont;
import Utils.Point;

public class InventoryScreenItemSelectionBox {
    private Boolean active = false;
    private final int width = 100;
    private int height;
    private final int border = 5;
    private int fontSize;
    private final int textGap = 5;

    private ArrayList<SpriteFont> spriteFonts = new ArrayList<SpriteFont>();
    protected ArrayList<String> choices = new ArrayList<String>();
    private int currentTextItemHovered = 0;
    private KeyLocker keyLocker = new KeyLocker();
    private InventoryScreen inventoryScreen;
    private Item item;
    private Point position;

    public InventoryScreenItemSelectionBox(InventoryScreen inventoryScreen, Item item, Point position) {
        lockKey(Key.ENTER);
        this.inventoryScreen = inventoryScreen;
        this.item = item;
        fontSize = inventoryScreen.fontSize;
        if (item.canUse()) {
            choices.add("Use");
        }
        if (item.canDrop()) {
            choices.add("Drop");
        }
        choices.add("Cancel");
        currentTextItemHovered = choices.size() - 1;
        height = (choices.size()) * (fontSize + textGap) + border * 2;
        float newY = position.y;
        if (newY > (inventoryScreen.inventoryHeight - inventoryScreen.border + inventoryScreen.top - inventoryScreen.fontSize)) {
            newY -= inventoryScreen.fontSize + 5 + height;
        }
        this.position = new Point(position.x, newY);
        int textY = (int) (border + this.position.y);
        for (int i = 0; i < choices.size(); i++) {
            spriteFonts.add(new SpriteFont(choices.get(i), border + this.position.x, textY, "Arial", fontSize, Color.black));
            textY += fontSize + textGap;
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangleWithBorder((int) position.x, (int) position.y, width, height, Color.white, Color.black, 2);

        for (int i = 0; i < spriteFonts.size(); i++) {
            spriteFonts.get(i).setColor(Color.black);
        }
        spriteFonts.get(currentTextItemHovered).setColor(Color.red);
        for (int i = 0; i < choices.size(); i++) {
            spriteFonts.get(i).drawWithParsedNewLines(graphicsHandler, 10);
        }
    }

    public void update() {
        if (lockKey(Key.UP)) {
            currentTextItemHovered --;
            if (currentTextItemHovered < 0) {
                currentTextItemHovered = spriteFonts.size();
            }

        } else if (lockKey(Key.DOWN)) {
            currentTextItemHovered ++;
            currentTextItemHovered %= spriteFonts.size();

        } else if (lockKey(Key.ENTER)) {
            if (choices.get(currentTextItemHovered) == "Use") {
                item.use();
                if (item.isConsumable()) {
                    inventoryScreen.drop(item);
                }
            }
            else if (choices.get(currentTextItemHovered) == "Drop") {
                inventoryScreen.drop(item);
            }
            inventoryScreen.selectionBox = null;
        }
        unlockKey(Key.LEFT);
        unlockKey(Key.UP);
        unlockKey(Key.DOWN);
        unlockKey(Key.RIGHT);
        unlockKey(Key.SHIFT);
        unlockKey(Key.ENTER);
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
}
