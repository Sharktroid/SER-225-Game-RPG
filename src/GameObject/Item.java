package GameObject;

import Level.Combatant;
import Level.Map;
import Level.Player;

public abstract class Item {
    protected String name;
    protected String description = "What the hey is this?";
    protected Boolean usable = false;
    protected Boolean battleUsable = false;
    protected Boolean keyItem = false;
    protected Boolean droppable = true;
    protected Boolean consumable = true;
    protected Player player;

    public Item(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean canUse() {
        return usable;
    }

    public Boolean canUseInCombat() {
        return battleUsable;
    }

    public Boolean isKeyItem() {
        return keyItem;
    }

    public Boolean canDrop() {
        return droppable;
    }

    public Boolean isConsumable() {
        return consumable;
    }

    public void use() {
        // Meant to be overridden or never used; throws when called.
        if (usable) {
            throw new RuntimeException("An unusable item was used");
        }
        else {
            throw new RuntimeException("This item was not given a proper 'Use' command");
        }
    }

    public void use(Combatant combatant, Map map) {
        // Meant to be overridden or never used; throws when called.
        if (battleUsable) {
            throw new RuntimeException("An unusable item was used");
        }
        else {
            throw new RuntimeException("This item was not given a proper 'Use' command");
        }
    }
}
