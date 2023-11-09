package Level;

import javax.swing.Action;

public abstract class Combatant {
    protected NPC npc;
    private int hitPoints;
    private int maxHitPoints;
    protected String name;
    protected Map map;
    public int statusTurn;
    public Statuses currentStatus = Statuses.NONE;
    protected int maxTP;
    protected int currentTP;

    public enum Statuses {
        NONE,
        DDOSED,
        CRASHED,
        REBOOTING
    }

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
        if (currentStatus == Statuses.CRASHED && hitPoints > 0) {
            map.getTextbox().addText(String.format("The %s has resumed normal operation.", name));
            currentStatus = Statuses.NONE;
        }
    }

    public void removeTP(int deduction) {
        currentTP -= deduction;
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

    public Boolean statusCheck() {
        if (currentStatus == Statuses.DDOSED) {
            statusTurn--;
            if (statusTurn <= 0) {
                currentStatus = Statuses.NONE;
            }
        }
        if (currentStatus == Statuses.CRASHED) {
            map.getTextbox().addText(String.format("The %s has crashed, they cannot move.", name));
            return false;
        }
        else if (currentStatus == Statuses.REBOOTING) {
            currentStatus = Statuses.NONE;
            map.getTextbox().addText(String.format("The %s has finished rebooting.", name));
            return false;
        } else {
            return true;
        }
    }

    public int getCurrentTP() {
        return currentTP;
    }

    public int getMaxTP() {
        return maxTP;
    }
}
