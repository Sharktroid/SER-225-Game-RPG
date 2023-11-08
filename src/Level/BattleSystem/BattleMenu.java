package Level.BattleSystem;

import java.awt.Color;

import Combatants.PlayerCombatant;
import Engine.Config;
import Engine.GraphicsHandler;
import Engine.Key;
import Level.Combatant;
import Level.Menu;
import Level.Panel;
import Level.Textbox;
import Menus.CombatInventoryMenu;
import SpriteFont.SpriteFont;

public class BattleMenu extends Menu {
    PlayerCombatant combatant;
    public BattleSystem battleSystem;
    private static final String[] actions = { "Bash", "Item", "Recover" };
    private String actionName;
    private CombatInventoryMenu inventoryMenu;

    public BattleMenu(BattleSystem battleSystem, PlayerCombatant combatant) {
        this.battleSystem = battleSystem;
        top = 400;
        width = Config.GAME_WINDOW_WIDTH - left * 2 - 16;
        height = Config.GAME_WINDOW_HEIGHT - top - 25 - 39;
        rows = 2;
        columns = 2;
        setText(actions);
        updatePanel();
        this.combatant = combatant;
        inventoryMenu = new CombatInventoryMenu(battleSystem.player, combatant, this);
    }

    public void draw(GraphicsHandler graphicsHandler) {
        if (inventoryMenu.isActive() == true) {
            inventoryMenu.draw(graphicsHandler);
        } else {
            super.draw(graphicsHandler);
            SpriteFont descriptionSpriteFont = new SpriteFont(
                    String.format("HP: %d/%d", combatant.getHitPoints(), combatant.getMaxHitPoints()), left + border,
                    top + border - 48, Textbox.getFont(), Color.black);
            descriptionSpriteFont.drawWithParsedNewLines(graphicsHandler, 10);
        }
    }

    @Override
    public void update() {
        if (inventoryMenu.isActive() == true) {
            inventoryMenu.update();
        }
        super.update();
    }

    @Override
    protected void optionSelected() {
        Combatant enemyCombatant = battleSystem.enemy;
        actionName = actions[currentTextItemHovered];
        if (actionName == "Item") {
            inventoryMenu.setActive(true);
            setActive(false);
        } else {
            switch (actionName) {
                case "Bash":
                    combatant.bash(enemyCombatant);
                    break;
                case "Recover":
                    combatant.recover();
                    break;
                default:
                    throw new RuntimeException(String.format("Unrecognized attack name %s", actionName));
            }
            setActive(false);
            battleSystem.playerTurn = false;
        }
    }

    @Override
    public void setActive(Boolean active) {
        if (!active) {
            battleSystem.map.getTextbox().setIsActive(true);
        }
        super.setActive(active);
    }

    protected void lock() {
        lockKey(Key.ENTER);
    }

    @Override
    protected void updatePanel() {
        panel = new Panel(left, top - 64, width, height + 64, false);
    }
}
