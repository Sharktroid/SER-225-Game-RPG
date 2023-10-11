package GameObject;

import java.lang.IllegalStateException;

public abstract class Item {
    protected String name;
    protected String description;
    protected Boolean usable = false;
    protected Boolean keyItem = false;
    protected Boolean droppable = true;
    protected Boolean consumable = true;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean canUse() {
        return usable;
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
            throw new IllegalStateException("An unusable item was used");
        }
        else {
            throw new AbstractMethodError("This item was not given a proper 'Use' command");
        }
    }
}
