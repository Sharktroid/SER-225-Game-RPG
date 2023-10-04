package GameObject;

public class Item {
    protected String name;
    protected Boolean usable;
    protected Boolean keyItem;
    protected Boolean droppable;
    protected String description;

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
}
