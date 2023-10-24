package Combatants;

import GameObject.GameObject;
import Level.Combatant;
import Level.Map;

public class PsychicPsycho extends Combatant {

    public PsychicPsycho(GameObject object, Map map) {
        super(object, map);
        hitPoints = 50;
        name = "Psychic Psycho";
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
