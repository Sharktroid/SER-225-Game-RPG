package Combatants;

import Level.Combatant;
import Level.Map;
import Level.NPC;

public class SoulConsumingFlame extends Combatant {

    public SoulConsumingFlame(NPC npc, Map map) {
        super(npc, map);
        setMaxHitPoints(50);
        name = "Soul Consuming Flame";
    }

    @Override
    public void autoExecuteMove(Combatant target) {
        fire(target);
    }

    private void fire(Combatant target) {
        target.dealDamage(20);
        map.getTextbox().addText(String.format("The %s cast fire.\n20 damage.", name));
    }

    @Override
    public String getIntroMessage() {
        return String.format("You encountered a %s.", name);
    }

    @Override
    public String getDeathMessage() {
        return String.format("The %s went back to normal.", name);
    }

}
