package Combatants;

import Level.Combatant;
import Level.Map;
import Level.NPC;

public class W1Combatant2 extends Combatant {

    public W1Combatant2(NPC npc, Map map) {
        super(npc, map);
        setMaxHitPoints(50);
        name = "virus";
    }

    @Override
    public void autoExecuteMove(Combatant target) {
        fire(target);
    }

    private void fire(Combatant target) {
        target.dealDamage(20);
        map.getTextbox().addText(String.format("The %s attacked.\nDealt 20 damage.", name));
    }

    @Override
    public String getIntroMessage() {
        return String.format("INITIATING VIRUS REMOVAL.");
    }

    @Override
    public String getDeathMessage() {
        return String.format("VIRUS REMOVAL: SUCCESSFUL!");
    }

}
