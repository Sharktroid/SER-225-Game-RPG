package GameObject;

import Level.Combatant;
import Level.Map;
import Level.Player;
import java.awt.image.BufferedImage;

public abstract class Item {
    protected String name;
    protected String description = "What the hey is this?";
    protected String useText = null; // If null item cannot be used

    protected Boolean battleUsable = false;
    protected Boolean keyItem = false;
    protected Boolean droppable = true;
    protected Boolean consumable = true;
    public Player player;
    protected BufferedImage sprite;

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
        return useText != null;
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
        if (canUse()) {
            throw new RuntimeException("An unusable item was used");
        } else {
            throw new RuntimeException("This item was not given a proper 'Use' command");
        }
    }

    public void use(Combatant combatant, Map map) {
        // Meant to be overridden or never used; throws when called.
        if (battleUsable) {
            throw new RuntimeException("An unusable item was used");
        } else {
            throw new RuntimeException("This item was not given a proper 'Use' command");
        }
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public String getUseText() {
        return useText;
    }
}
