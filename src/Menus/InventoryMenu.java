package Menus;

import java.awt.Color;
import java.util.ArrayList;

import Engine.Config;
import Engine.GraphicsHandler;
import Engine.Key;
import GameObject.Item;
import Level.Menu;
import Level.Player;
import SpriteFont.SpriteFont;
import Utils.Point;

public class InventoryMenu extends Menu {
    private Player player;
    private final int totalHeight = Config.GAME_WINDOW_HEIGHT - spacer - (left * 2) - 39;
    private int descriptionHeight;

    private Boolean viewingKeyItems = false;
    protected InventoryMenuItemSelectionBox selectionBox;

    public InventoryMenu(Player player) {
        super();
        height = (int) (totalHeight * (0.75));
        width = Config.GAME_WINDOW_WIDTH - (left * 2) - 16;
        descriptionHeight = (int) (height * (1.0/3));
        this.player = player;
        columns = 2;
        rows = 5;
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        updateItemText();
        super.draw(graphicsHandler);
        int descriptionTop = top + height + spacer;
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

    @Override
    public void update() {
        if (selectionBox == null) {
            super.update();
            if (lockKey(Key.SHIFT)) {
                setViewingKeyItems(!viewingKeyItems);
                if (currentTextItemHovered >= getCurrentItems().size()) {
                    currentTextItemHovered = 0;
                }
            }
            unlockKey(Key.SHIFT);
        } else {
            selectionBox.update();
        }
    }

    @Override
    public void setActive(Boolean active) {
        super.setActive(active);
        setViewingKeyItems(false);
    }

    public void updateItemText() {
        String[] names = new String[getCurrentItems().size()];
        for (int i = 0; i < getCurrentItems().size(); i++) {
            names[i] = getCurrentItems().get(i).getName();
        }
        setText(names);
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
        } else {
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

    private void setViewingKeyItems(Boolean viewing) {
        viewingKeyItems = viewing;
    }

    @Override
    protected void optionSelected() {
        SpriteFont currSpriteFont = spriteFonts.get(currentTextItemHovered);
        selectionBox = new InventoryMenuItemSelectionBox(this, getCurrentItem(),
                new Point(currSpriteFont.getX(), currSpriteFont.getY() + fontSize + 5));
        if (selectionBox.choices.size() <= 1) {
            selectionBox = null;
        }
    }
}
