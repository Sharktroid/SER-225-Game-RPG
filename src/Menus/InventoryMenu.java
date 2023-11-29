package Menus;

import java.awt.Color;
import java.util.ArrayList;

import Engine.Config;
import Engine.GraphicsHandler;
import Engine.Key;
import GameObject.Item;
import Level.Map;
import Level.Menu;
import Level.Player;
import Level.Textbox;
import SpriteFont.SpriteFont;
import Utils.Point;
import Level.Panel;

public class InventoryMenu extends Menu {
    protected Player player;
    private final int totalHeight = Config.GAME_WINDOW_HEIGHT - spacer - (top * 2 ) - 39;
    private int descriptionHeight;
    private int descriptionTop;

    private Boolean viewingKeyItems = false;
    protected InventoryMenuItemSelectionBox selectionBox;
    private Panel descriptionPanel;
    protected Map map;

    public InventoryMenu(Map map, Player player) {
        super();
        this.map = map;
        height = (int) (totalHeight * (0.75));
        width = Config.GAME_WINDOW_WIDTH - (left * 2) - 16;
        descriptionHeight = (int) (height * (1.0/3));
        this.player = player;
        columns = 2;
        rows = 5;
        descriptionTop = top + height + spacer;
        name = "Inventory";
        updatePanel();
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        updateItemText();
        super.draw(graphicsHandler);
        descriptionPanel.draw(graphicsHandler);
        String description = "";
        if (getCurrentItem() != null) {
            description = getCurrentItem().getDescription();
        }
        SpriteFont descriptionSpriteFont = new SpriteFont(description, 0, 0, Textbox.getFont(), Color.black);
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
            currentTextItemHovered = Math.max(getCurrentItems().size() - 1, 0);
        }
    }

    protected Item getCurrentItem() {
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

    @Override
    protected void updatePanel() {
        super.updatePanel();
        descriptionPanel = new Panel(left, descriptionTop, width, descriptionHeight, "Description");
    }
}
