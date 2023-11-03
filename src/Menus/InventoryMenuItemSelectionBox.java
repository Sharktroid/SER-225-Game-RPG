package Menus;

import java.util.ArrayList;
import Engine.Key;
import GameObject.Item;
import Level.Menu;
import Utils.Point;

public class InventoryMenuItemSelectionBox extends Menu {
    protected ArrayList<String> choices = new ArrayList<String>();
    private InventoryMenu inventoryScreen;
    private Item item;

    public InventoryMenuItemSelectionBox(InventoryMenu inventoryScreen, Item item, Point position) {
        super();
        columns = 1;
        width = 100;
        left = (int) position.x;
        top = (int) position.y;
        border = 5;
        lockKey(Key.ENTER);
        this.inventoryScreen = inventoryScreen;
        this.item = item;
        if (item.canUse()) {
            choices.add("Use");
        }
        if (item.canDrop()) {
            choices.add("Drop");
        }
        choices.add("Cancel");
        currentTextItemHovered = choices.size() - 1;
        rows = choices.size();
        height = ((choices.size()) * (fontSize + border));
        setText(choices);
        updatePanel();
    }

    @Override
    protected void optionSelected() {
        switch (choices.get(currentTextItemHovered)) {
            case "Use":
                item.use();
                if (item.isConsumable()) {
                    inventoryScreen.drop(item);
                }
                break;
            case "Drop":
                inventoryScreen.drop(item);
                break;
        }
        inventoryScreen.selectionBox = null;
    }
}
