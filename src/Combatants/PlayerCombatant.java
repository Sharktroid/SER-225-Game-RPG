package Combatants;

import Level.Combatant;
import Level.Map;
import Level.Player;

public class PlayerCombatant extends Combatant {

    public PlayerCombatant(Player player, Map map) {
        super(null, map);
        setMaxHitPoints((int) player.getCurrentHealth());
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

    public void recover() {
        dealDamage(-50);
        map.getTextbox().addText(String.format("The %s heals themself.\n50 hit points restored.", name));
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

}