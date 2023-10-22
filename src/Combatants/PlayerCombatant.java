package Combatants;

import GameObject.GameObject;
import Level.Combatant;
import Level.Player;

public class PlayerCombatant extends Combatant {

    public PlayerCombatant(Player player, ControlType controlType) {
        super((GameObject) player, controlType);
        hitPoints = (int) player.getCurrentHealth();
        name = "Player";
    }

    @Override
    public void autoExecuteMove(Combatant target) {
        bash(target);
    }

    public void bash(Combatant target) {
        target.dealDamage(20);
    }

}
