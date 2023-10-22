package Level.BattleSystem;

import java.util.ArrayList;

import Engine.GraphicsHandler;
import Level.Combatant;
import Level.Map;

public class BattleSystem {
    protected ArrayList<Combatant> combatants;
    private int currentCombatantIndex = 0;
    private BattleMenu battleMenu;
    private Map map;

    public BattleSystem(Map map, ArrayList<Combatant> combatants) {
        this.map = map;
        this.combatants = combatants;
        battleMenu = new BattleMenu(this);
    }

    public void draw(GraphicsHandler graphicsHandler) {
        if (battleMenu != null) {
            battleMenu.draw(graphicsHandler);
        }
    }

    public void update() {
        if (battleMenu != null) {
            battleMenu.update();
        }
        if (!battleMenu.isActive()) {
            advance();
        }
    }

    private void advance() {
        for (int i = 0; i < combatants.size(); i++) {
            if (combatants.get(i).getHitPoints() <= 0) {
                combatants.remove(combatants.get(i));
            }
        }
        if (combatants.size() <= 1) {
            map.endCombat();
        }
        currentCombatantIndex ++;
        if (currentCombatantIndex >= combatants.size()) {
            currentCombatantIndex = 0;
        }
        if (getCurrentCombatant().getControlType() == Combatant.ControlType.COMPUTER) {
            getCurrentCombatant().autoExecuteMove(combatants.get(0));
            advance();
        }
        else {
            battleMenu.setActive(true);
            battleMenu.combatant = getCurrentCombatant();
        }
    }

    private Combatant getCurrentCombatant() {
        return combatants.get(currentCombatantIndex);
    }
}
