package Combatants;

import GameObject.GameObject;
import Level.Combatant;

public class PsychicPsycho extends Combatant {

    public PsychicPsycho(GameObject object) {
        super(object);
        hitPoints = 50;
        name = "Psychic Psycho";
    }

    @Override
    public void autoExecuteMove(Combatant target) {
        fire(target);
    }

    private void fire(Combatant target) {
        target.dealDamage(20);
    }

}
