package Level.BattleSystem;

import java.util.ArrayList;

import Engine.GraphicsHandler;
import Level.Combatant;
import Level.Map;

public class BattleSystem {
    ArrayList<Combatant> combatants;
    private int currentCombatantIndex = 0;
    private BattleMenu battleMenu;
    Map map;
    private Boolean shuttingDown = false;

    public BattleSystem(Map map, ArrayList<Combatant> combatants) {
        this.map = map;
        this.combatants = combatants;
        currentCombatantIndex = combatants.size() - 1;
        battleMenu = new BattleMenu(this);
        battleMenu.setActive(false);
        for (int i = 0; i < combatants.size(); i++) {
            if (combatants.get(i).getIntroMessage() != null) {
                map.getTextbox().addText(combatants.get(i).getIntroMessage());
            }
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        if (battleMenu != null && battleMenu.isActive()) {
            battleMenu.draw(graphicsHandler);
        }
    }

    public void update() {
        if (map.getTextbox().isTextQueueEmpty()) {
            map.getTextbox().setIsActive(false);
            if (shuttingDown) {
                map.endCombat();
            }
        }
        if (!shuttingDown) {
            if (battleMenu != null && battleMenu.isActive()) {
                battleMenu.update();
            }
            if (!battleMenu.isActive() && !map.getTextbox().isActive()) {
                advance();
            }
        }
    }

    private void advance() {
        for (int i = 0; i < combatants.size(); i++) {
            if (combatants.get(i).getHitPoints() <= 0) {
                combatants.get(i).kill();
                combatants.remove(combatants.get(i));
            }
        }
        if (combatants.size() <= 1) {
            map.getTextbox().addText("You Win!!");
            map.getTextbox().setIsActive(true);
            shuttingDown = true;
        } else {
            currentCombatantIndex++;
            if (currentCombatantIndex >= combatants.size()) {
                currentCombatantIndex = 0;
            }
            if (getCurrentCombatant().getControlType() == Combatant.ControlType.COMPUTER) {
                getCurrentCombatant().autoExecuteMove(combatants.get(0));
                map.getTextbox().setIsActive(true);
            } else {
                battleMenu.setActive(true);
                battleMenu.combatant = getCurrentCombatant();
            }
        }
    }

    private Combatant getCurrentCombatant() {
        return combatants.get(currentCombatantIndex);
    }
}
