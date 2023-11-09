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
import Menus.CombatInventoryMenu;
import SpriteFont.SpriteFont;

public class BattleMenu extends Menu {
    PlayerCombatant combatant;
    public BattleSystem battleSystem;
    private static final Actions[] actions = { Actions.BASH, Actions.ITEMS, Actions.REFRESH, Actions.STACKSMASH,
            Actions.DDOS, Actions.REBOOT, Actions.INTERUPTREQUEST };
    private String actionName;
    private CombatInventoryMenu inventoryMenu;
    private Panel descriptionPanel;
    private int descriptionTop;
    private int descriptionHeight = 100;

    private enum Actions {
        BASH,
        ITEMS,
        REFRESH,
        STACKSMASH,
        DDOS,
        REBOOT,
        INTERUPTREQUEST;

        @Override
        public String toString() {
            String name = super.toString();
            switch (name) {
                case "STACKSMASH":
                    return "Stack Smash";
                case "INTERUPTREQUEST":
                    return "Interupt Request";
                default:
                    return (name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase());
            }
        }
    }

    public BattleMenu(BattleSystem battleSystem, PlayerCombatant combatant) {
        this.battleSystem = battleSystem;
        top = 300;
        width = Config.GAME_WINDOW_WIDTH - left * 2 - 16;
        height = Config.GAME_WINDOW_HEIGHT - top - descriptionHeight - 25 - 39;
        rows = 2;
        columns = 4;
        descriptionTop = top + height + 10;
        ArrayList<String> actionNames = new ArrayList<String>();
        for (Actions action: actions) {
            actionNames.add(action.toString());
        }
        setText(actionNames);
        updatePanel();
        this.combatant = combatant;
        inventoryMenu = new CombatInventoryMenu(battleSystem.player, combatant, this);
        descriptionPanel = new Panel(left, descriptionTop, width, descriptionHeight, false);
    }

    public void draw(GraphicsHandler graphicsHandler) {
        if (inventoryMenu.isActive() == true) {
            inventoryMenu.draw(graphicsHandler);
        } else {
            super.draw(graphicsHandler);
            SpriteFont HPSpriteFont = new SpriteFont(
                    String.format("HP: %d/%d", combatant.getHitPoints(), combatant.getMaxHitPoints()), left + border,
                    top + border - 48, Textbox.getFont(), Color.black);
            HPSpriteFont.drawWithParsedNewLines(graphicsHandler, 10);
            super.draw(graphicsHandler);
            descriptionPanel.draw(graphicsHandler);
            String description = "";
            description = "TEST";
            SpriteFont descriptionSpriteFont = new SpriteFont(description, 0, 0, Textbox.getFont(), Color.black);
            descriptionSpriteFont.setX(left + border);
            descriptionSpriteFont.setY(descriptionTop + border);
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
                case "Refresh":
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
