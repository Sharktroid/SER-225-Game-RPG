package Level.BattleSystem;

import java.awt.Color;
import java.util.ArrayList;

import Combatants.PlayerCombatant;
import Engine.Config;
import Engine.GraphicsHandler;
import Engine.Key;
import Level.Combatant;
import Level.Menu;
import Level.Panel;
import Level.Textbox;
import SpriteFont.SpriteFont;

public class BattleMenu extends Menu {
    private State currentState = State.SELECTINGATTACK;
    PlayerCombatant combatant;
    private BattleSystem battleSystem;
    private ArrayList<Combatant> enemyCombatants;
    private static final String[] actions = {"Bash", "Recover"};
    private String actionName;

    public enum State {
        SELECTINGATTACK,
        SELECTINGTARGET
    }

    public BattleMenu(BattleSystem battleSystem) {
        this.battleSystem = battleSystem;
        top = 400;
        width = Config.GAME_WINDOW_WIDTH - left * 2 - 16;
        height = Config.GAME_WINDOW_HEIGHT - top - 25 - 39;
        rows = 2;
        columns = 2;
        reset();
        updatePanel();
    }

    public State getCurrentState() {
        return currentState;
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        Combatant currentCombatant;
        if (currentState == State.SELECTINGATTACK) {
            currentCombatant = combatant;
        }
        else {
            currentCombatant = enemyCombatants.get(currentTextItemHovered);
        }
        SpriteFont descriptionSpriteFont = new SpriteFont(
                String.format("HP: %d/%d", currentCombatant.getHitPoints(), currentCombatant.getMaxHitPoints()), left + border,
                top + border - 48, Textbox.getFont(), Color.black);
        descriptionSpriteFont.drawWithParsedNewLines(graphicsHandler, 10);
    }

    @Override
    protected void optionSelected() {
        if (currentState == State.SELECTINGATTACK) {
            actionName = actions[currentTextItemHovered];
            if (actionName == "Recover") {
                combatant.recover();
                setActive(false);
            }
            else    {
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
        }
        else {
            Combatant enemyCombatant = enemyCombatants.get(currentTextItemHovered);
            switch (actionName) {
                case "Bash":
                    combatant.bash(enemyCombatant);
                    break;
                default:
                    throw new RuntimeException("Unrecognized attack name");
            }
            setActive(false);
        }
    }

    @Override
    public void setActive(Boolean active) {
        if (active) {
            currentState = State.SELECTINGATTACK;
            reset();

        } else {
            battleSystem.map.getTextbox().setIsActive(true);
        }
        super.setActive(active);
        lockKey(Key.ENTER);
    }

    private void reset() {
        setText(actions);
    }

    @Override
    protected void updatePanel() {
        panel = new Panel(left, top - 64, width, height + 64, false);
    }
}
