package Level;

import GameObject.GameObject;

public abstract class Combatant {
    protected GameObject object;
    protected int hitPoints;
    private ControlType controlType;
    protected String name;

    public enum ControlType {
        HUMAN,
        COMPUTER
    }

    public Combatant(GameObject object) {
        this(object, ControlType.COMPUTER);
    }

    public Combatant(GameObject object, ControlType controlType) {
        this.object = object;
        this.controlType = controlType;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void dealDamage(int damage) {
        hitPoints -= damage;
    }

    public ControlType getControlType() {
        return controlType;
    }

    public String getName() {
        return name;
    }

    // For computer controlled combatants
    public abstract void autoExecuteMove(Combatant target);
}
