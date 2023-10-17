package GameObject;

import Level.Player;

public abstract class KeyItem extends Item {
    public KeyItem(Player player) {
        super(player);
        keyItem = true;
        droppable = false;
        consumable = false;
    }
}
