package GameObject;

public class Item {
    protected String name;
    protected String description;
    protected Boolean usable = false;
    protected Boolean keyItem = false;
    protected Boolean droppable = true;

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
