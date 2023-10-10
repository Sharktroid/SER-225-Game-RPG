package GameObject;

public abstract class KeyItem extends Item {
    public KeyItem() {
        keyItem = true;
        droppable = false;
        consumable = false;
    }
}
