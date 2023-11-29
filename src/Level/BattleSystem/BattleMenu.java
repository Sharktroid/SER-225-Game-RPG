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
    private static final PlayerCombatant.Actions[] actions = { PlayerCombatant.Actions.BASH,
            PlayerCombatant.Actions.ITEMS, PlayerCombatant.Actions.REFRESH, PlayerCombatant.Actions.STACKSMASH,
            PlayerCombatant.Actions.DDOS, PlayerCombatant.Actions.REBOOT, PlayerCombatant.Actions.INTERRUPTREQUEST };
    private CombatInventoryMenu inventoryMenu;
    private Panel descriptionPanel;
    private int descriptionTop;
    private int descriptionHeight = 110;

    public BattleMenu(BattleSystem battleSystem, PlayerCombatant combatant) {
        this.battleSystem = battleSystem;
        top = 290;
        width = Config.GAME_WINDOW_WIDTH - left * 2 - 16;
        height = Config.GAME_WINDOW_HEIGHT - top - descriptionHeight - 35 - 39;
        rows = 2;
        columns = 4;
        descriptionTop = top + height + 15;
        ArrayList<String> actionNames = new ArrayList<String>();
        for (PlayerCombatant.Actions action : actions) {
            actionNames.add(action.toString());
        }
        setText(actionNames);
        name = "Battle";
        updatePanel();
        this.combatant = combatant;
        inventoryMenu = new CombatInventoryMenu(battleSystem.player, combatant, this);
        descriptionPanel = new Panel(left, descriptionTop, width, descriptionHeight, "Description");
    }

    public void draw(GraphicsHandler graphicsHandler) {
        if (inventoryMenu.isActive() == true) {
            inventoryMenu.draw(graphicsHandler);
        } else {
            super.draw(graphicsHandler);
            SpriteFont PlayerHPSpriteFont = new SpriteFont(
                    String.format("Player HP: %d/%d", combatant.getHitPoints(), combatant.getMaxHitPoints()),
                    left + border, top + border - 48, Textbox.getFont(), Color.black);
            PlayerHPSpriteFont.drawWithParsedNewLines(graphicsHandler, 10);
            SpriteFont TPSpriteFont = new SpriteFont(
                    String.format("TP: %d/%d", combatant.getCurrentTP(), combatant.getMaxTP()), left + border + 200,
                    top + border - 48, Textbox.getFont(), Color.black);
            TPSpriteFont.drawWithParsedNewLines(graphicsHandler, 10);
            SpriteFont EnemyHPSpriteFont = new SpriteFont(
                    String.format("Enemy HP: %d/%d", battleSystem.enemy.getHitPoints(),
                            battleSystem.enemy.getMaxHitPoints()),
                    left + border + 400, top + border - 48, Textbox.getFont(), Color.black);
            EnemyHPSpriteFont.drawWithParsedNewLines(graphicsHandler, 10);
            descriptionPanel.draw(graphicsHandler);
            String description = "";
            PlayerCombatant.Actions currentAction = actions[currentTextItemHovered];
            switch (currentAction) {
                case BASH:
                    description = String.format("Standard, low might attack.\n Cost: %d TP", currentAction.getTPCost());
                    break;
                case ITEMS:
                    description = "Opens inventory and allows use of items.";
                    break;
                case REFRESH:
                    description = String.format("Heals some health.\n Cost: %d TP", currentAction.getTPCost());
                    break;
                case STACKSMASH:
                    description = String.format("Deals significant damage.\n Cost: %d TP", currentAction.getTPCost());
                    break;
                case DDOS:
                    description = String.format("Depletes enemy's TP.\n Cost: %d TP", currentAction.getTPCost());
                    break;
                case REBOOT:
                    description = String.format("Restores health fully, but wastes a turn.\n Cost: %d TP",
                            currentAction.getTPCost());
                    break;
                case INTERRUPTREQUEST:
                    description = String.format("Disables the enemy until they attack again.\n Cost: %d TP",
                            currentAction.getTPCost());
                    break;

            }
            SpriteFont descriptionSpriteFont = new SpriteFont(description, 0, 0, Textbox.getFont(), Color.black);
            descriptionSpriteFont.setX(left + border);
            descriptionSpriteFont.setY(descriptionTop + border + 5);
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
        PlayerCombatant.Actions action = actions[currentTextItemHovered];
        if (combatant.tPCheck(action)) {
            switch (action) {
                case BASH:
                    combatant.bash(enemyCombatant);
                    battleSystem.playerTurn = false;
                    break;
                case ITEMS:
                    inventoryMenu.setActive(true);
                case REFRESH:
                    combatant.refresh();
                    battleSystem.playerTurn = false;
                    break;
                case STACKSMASH:
                    combatant.stackSmash(enemyCombatant);
                    battleSystem.playerTurn = false;
                    break;
                case DDOS:
                    combatant.dDoS(enemyCombatant);
                    battleSystem.playerTurn = false;
                    break;
                case REBOOT:
                    combatant.reboot();
                    battleSystem.playerTurn = false;
                    break;
                case INTERRUPTREQUEST:
                    combatant.interruptRequest(enemyCombatant);
                    battleSystem.playerTurn = false;
                    break;
                default:
                    break;
            }
            combatant.removeTP(action.getTPCost());
        } else {
            battleSystem.map.getTextbox().addText("Not Enough TP");
        }
        setActive(false);
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
        panel = new Panel(left, top - 64, width, height + 64, name);
    }
}
