package Level;

public abstract class Combatant {
    protected NPC npc;
    private int hitPoints;
    private int maxHitPoints;
    protected String name;
    protected Map map;

    public enum ControlType {
        HUMAN,
        COMPUTER
    }

    public Combatant(NPC npc, Map map) {
        this.npc = npc;
        this.map = map;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    protected void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
        hitPoints = maxHitPoints;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public void dealDamage(int damage) {
        hitPoints -= damage;
        if (hitPoints < 0) {
            hitPoints = 0;
        }
        else if (hitPoints > maxHitPoints) {
            hitPoints = maxHitPoints;
        }
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
