package Combatants;

import GameObject.GameObject;
import Level.Combatant;
import Level.Map;
import Level.Player;

public class PlayerCombatant extends Combatant {

    public PlayerCombatant(Player player, Map map, ControlType controlType) {
        super((GameObject) player, map, controlType);
        hitPoints = (int) player.getCurrentHealth();
        name = player.getName();
    }

    @Override
    public void autoExecuteMove(Combatant target) {
        bash(target);
    }

    public void bash(Combatant target) {
        target.dealDamage(20);
        map.getTextbox().addText(String.format("The %s attacks.\n20 damage.", name));
    }

    @Override
    public String getIntroMessage() {
        return null;
    }

    @Override
    public String getDeathMessage() {
        return "You lost the battle!";
    }

}