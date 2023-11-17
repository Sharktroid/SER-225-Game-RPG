package Menus;

import Combatants.PlayerCombatant;
import Engine.GraphicsHandler;
import Engine.Key;
import Level.Player;
import Level.BattleSystem.BattleMenu;
import SpriteFont.SpriteFont;
import Utils.Point;

public class CombatInventoryMenu extends InventoryMenu {
    protected PlayerCombatant playerCombatant;
    protected BattleMenu battleMenu;
    protected CombatInventoryMenuItemSelectionBox combatSelectionBox;

    public CombatInventoryMenu(Player player, PlayerCombatant playerCombatant, BattleMenu battleMenu) {
        super(null, player);
        this.battleMenu = battleMenu;
        this.playerCombatant = playerCombatant;
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        if (combatSelectionBox != null) {
            combatSelectionBox.draw(graphicsHandler);
        }
    }

    @Override
    public void update() {
        if (combatSelectionBox != null) {
            combatSelectionBox.update();
            lockKey(Key.ENTER);
        } else {
            if (lockKey(Key.ESC)) {
                combatSelectionBox = null;
                setActive(false);
            } else {
                super.update();
            }
        }
        unlockKey(Key.ESC);
    }

    @Override
    public void setActive(Boolean active) {
        super.setActive(active);
        if (active) {
            lockKey(Key.ENTER);
        }
    }

    @Override
    protected void optionSelected() {
        SpriteFont currSpriteFont = spriteFonts.get(currentTextItemHovered);
        combatSelectionBox = new CombatInventoryMenuItemSelectionBox(this, getCurrentItem(),
                new Point(currSpriteFont.getX(), currSpriteFont.getY() + fontSize + 5));
        if (combatSelectionBox.choices.size() <= 1) {
            combatSelectionBox = null;
        }
    }
}
