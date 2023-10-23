package Level;

import GameObject.GameObject;

public abstract class Combatant {
    protected GameObject object;
    protected int hitPoints;
    private ControlType controlType;
    protected String name;
    protected Map map;

    public enum ControlType {
        HUMAN,
        COMPUTER
    }

    public Combatant(GameObject object, Map map) {
        this(object, map, ControlType.COMPUTER);
    }

    public Combatant(GameObject object, Map map, ControlType controlType) {
        this.object = object;
        this.controlType = controlType;
        this.map = map;
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

    public abstract String getIntroMessage();

    public abstract String getDeathMessage();
}
