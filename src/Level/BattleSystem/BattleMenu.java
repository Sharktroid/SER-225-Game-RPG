package Level.BattleSystem;

import java.util.ArrayList;

import Combatants.PlayerCombatant;
import Level.Combatant;
import Level.Menu;
import Menus.InventoryMenuItemSelectionBox;

public class BattleMenu extends Menu {
    protected InventoryMenuItemSelectionBox selectionBox;
    private State currentState = State.SELECTINGATTACK;
    public Combatant combatant;
    private BattleSystem battleSystem;
    private ArrayList<Combatant> enemyCombatants;

    public enum State {
        SELECTINGATTACK,
        SELECTINGTARGET
    }

    public BattleMenu(BattleSystem battleSystem) {
        this.battleSystem = battleSystem;
        width = 500;
        height = 500;
        rows = 1;
        columns = 1;
        reset();
    }

    public State getCurrentState() {
        return currentState;
    }

    @Override
    protected void optionSelected() {
        if (currentState == State.SELECTINGATTACK) {
            enemyCombatants = new ArrayList<Combatant>();
            ArrayList<String> enemyCombatantStrings = new ArrayList<String>();
            for (int i = 0; i < battleSystem.combatants.size(); i++) {
                if (battleSystem.combatants.get(i) != combatant) {
                    enemyCombatants.add(battleSystem.combatants.get(i));
                    enemyCombatantStrings.add(battleSystem.combatants.get(i).getName());
                }
            }
            setText(enemyCombatantStrings);
            currentState = State.SELECTINGTARGET;
        }
        else {
            ((PlayerCombatant) combatant).bash(enemyCombatants.get(currentTextItemHovered));
            setActive(false);
        }
    }

    @Override
    public void setActive(Boolean active) {
        if (active) {
            currentState = State.SELECTINGATTACK;
            reset();

        }
        super.setActive(active);
    }

    private void reset() {
        String[] items = {"Bash"};
        setText(items);
    }
}
