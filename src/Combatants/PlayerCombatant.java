package Combatants;

import Level.Combatant;
import Level.Map;
import Level.Player;

public class PlayerCombatant extends Combatant {

    public PlayerCombatant(Player player, Map map) {
        super(null, map);
        setMaxHitPoints((int) player.getCurrentHealth());
        name = player.getName();
        currentTP = 0;
        maxTP = 20;
    }

    public enum Actions {
        BASH,
        ITEMS,
        REFRESH,
        STACKSMASH,
        DDOS,
        REBOOT,
        INTERRUPTREQUEST;

        @Override
        public String toString() {
            String name = super.toString();
            switch (name) {
                case "STACKSMASH":
                    return "Stack Smash";
                case "INTERRUPTREQUEST":
                    return "Interupt Request";
                case "DDOS":
                    return "DDoS";
                default:
                    return (name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase());
            }
        }

        public int getTPCost() {
            switch (this) {
                case REFRESH:
                    return 2;
                case STACKSMASH:
                    return 3;
                case DDOS:
                    return 5;
                case REBOOT:
                    return 4;
                case INTERRUPTREQUEST:
                    return 6;
                default:
                    return 0;
            }
        }
    }

    @Override
    public void autoExecuteMove(Combatant target) {
        bash(target);
    }

    public void bash(Combatant target) {
        map.getTextbox().addText(String.format("The %s attacks.\n20 damage.", name));
        target.dealDamage(20);
    }

    public void refresh() {
        map.getTextbox().addText(String.format("The %s heals themself.\n50 hit points restored.", name));
        dealDamage(-50);
    }

    public void stackSmash(Combatant enemyCombatant) {
        map.getTextbox().addText(String.format("The %s smashed the foe's stack.\n50 damage.", name));
        enemyCombatant.dealDamage(50);
    }

    public void dDoS(Combatant enemyCombatant) {
        map.getTextbox().addText(String.format("The %s sent the foe offline.\nThe enemy can not use TP for five turns.", name));
        enemyCombatant.currentStatus = Statuses.DDOSED;
        enemyCombatant.statusTurn = 5;
    }

    public void reboot() {
        currentStatus = Statuses.REBOOTING;
        map.getTextbox().addText(String.format("The %s is rebooting.\nHealth restored.", name));
        dealDamage(-1000);
    }

    public void interruptRequest(Combatant enemyCombatant) {
        map.getTextbox().addText(String.format("The %s crashed the foe.\nThe enemy is unable to act.", name));
        enemyCombatant.currentStatus = Statuses.CRASHED;
    }

    @Override
    public String getIntroMessage() {
        return null;
    }

    @Override
    public String getDeathMessage() {
        return "You lost the battle!";
    }

    public void kill() {
        map.getTextbox().addText(getDeathMessage());
        map.getTextbox().addText("Game Over");
    }

    public Map getMap() {
        return map;
    }


    public Boolean tPCheck(Actions action) {
        if (currentStatus != Statuses.DDOSED && currentTP >= action.getTPCost()) {
            return true;
        }
        else {
            return false;
        }
    }

}