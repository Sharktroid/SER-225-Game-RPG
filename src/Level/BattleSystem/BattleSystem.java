package Level.BattleSystem;

import Combatants.PlayerCombatant;
import Engine.GraphicsHandler;
import Level.Combatant;
import Level.Map;

public class BattleSystem {
    PlayerCombatant player;
    Combatant enemy;
    private BattleMenu battleMenu;
    Map map;
    private Boolean shuttingDown = false;
    Boolean playerTurn;

    public BattleSystem(Map map, PlayerCombatant player, Combatant enemy) {
        this.map = map;
        this.player = player;
        this.enemy = enemy;
        battleMenu = new BattleMenu(this);
        battleMenu.setActive(true);
        map.getTextbox().addText(enemy.getIntroMessage());
        map.getTextbox().setIsActive(true);
        battleMenu.combatant = player;
        playerTurn = true;
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
            if (!battleMenu.isActive()) {
                battleMenu.lock();
            }
        }
        if (!shuttingDown && !map.getTextbox().isActive()) {
            if (battleMenu != null && battleMenu.isActive()) {
                battleMenu.update();
            }
            if (!battleMenu.isActive()) {
                advance();
            }
        }
    }

    private void advance() {
        if (enemy.getHitPoints() <= 0) {
            enemy.kill();
            map.getTextbox().addText("You Win!!");
            map.getTextbox().setIsActive(true);
            shuttingDown = true;
        } else {
            if (playerTurn) {
                battleMenu.setActive(true);
            }
            else {
                enemy.autoExecuteMove(player);
                playerTurn = true;
            }
        }
    }
}
