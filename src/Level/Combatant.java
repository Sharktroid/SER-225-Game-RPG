package Level;

public abstract class Combatant {
    protected NPC npc;
    protected int hitPoints;
    private ControlType controlType;
    protected String name;
    protected Map map;

    public enum ControlType {
        HUMAN,
        COMPUTER
    }

    public Combatant(NPC npc, Map map) {
        this(npc, map, ControlType.COMPUTER);
    }

    public Combatant(NPC npc, Map map, ControlType controlType) {
        this.npc = npc;
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

    public void kill() {
        npc.setMapEntityStatus(MapEntityStatus.REMOVED);
        map.getTextbox().addText(getDeathMessage());
    }
}
