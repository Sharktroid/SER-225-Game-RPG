package Menus;

import GameObject.Item;
import Utils.Point;

public class CombatInventoryMenuItemSelectionBox extends InventoryMenuItemSelectionBox {
    private CombatInventoryMenu combatInventoryMenu;

    public CombatInventoryMenuItemSelectionBox(CombatInventoryMenu inventoryScreen, Item item, Point position) {
        super(new InventoryMenu(null), item, position);
        this.combatInventoryMenu = inventoryScreen;
            choices.remove("Use");
        if (item.canUseInCombat()) {
            choices.add(0, "Use");
        }
        setText(choices);
        currentTextItemHovered = choices.size() - 1;
    }

    @Override
    protected void optionSelected() {
        String choice = choices.get(currentTextItemHovered);
        switch (choice) {
            case "Use":
                item.use(combatInventoryMenu.playerCombatant, combatInventoryMenu.playerCombatant.getMap());
                if (item.isConsumable()) {
                    combatInventoryMenu.drop(item);
                }
                break;
            case "Drop":
                combatInventoryMenu.drop(item);
                break;
        }
        combatInventoryMenu.combatSelectionBox = null;
        setActive(false);
        if (choice != "Cancel") {
            combatInventoryMenu.setActive(false);
            combatInventoryMenu.battleMenu.setActive(false);
            combatInventoryMenu.battleMenu.battleSystem.playerTurn = false;
        }
    }
}
