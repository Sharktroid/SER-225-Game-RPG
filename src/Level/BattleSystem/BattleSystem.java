package Level.BattleSystem;

import Combatants.PlayerCombatant;
import Engine.GraphicsHandler;
import Items.Medkit;
import Level.Combatant;
import Level.Map;
import Level.Player;

public class BattleSystem {
    Player player;
    PlayerCombatant playerCombatant;
    Combatant enemy;
    private BattleMenu battleMenu;
    Map map;
    private Boolean shuttingDown = false;
    public Boolean playerTurn;

    public BattleSystem(Map map, Player player, Combatant enemy) {
        this.map = map;
        this.player = player;
        this.playerCombatant = new PlayerCombatant(player, map);
        this.enemy = enemy;
        battleMenu = new BattleMenu(this, playerCombatant);
        battleMenu.setActive(false);
        map.getTextbox().addText(enemy.getIntroMessage());
        map.getTextbox().setIsActive(true);
        playerTurn = true;
        player.items.add(new Medkit(player));
    }

    public void draw(GraphicsHandler graphicsHandler) {
        if (battleMenu != null && battleMenu.isActive()) {
            battleMenu.draw(graphicsHandler);
        }
    }

    public void update() {
        if (map.getTextbox().isTextQueueEmpty() && map.getTextbox().isActive()) {
            map.getTextbox().setIsActive(false);
            if (shuttingDown) {
                map.endCombat();
            }
            if (!battleMenu.isActive()) {
                battleMenu.lock();
            }
        }
        if (!shuttingDown && (!map.getTextbox().isActive())) {
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
            map.getTextbox().addText("CLOSING NETWORK SECURITY TOOL...");//5
            map.getTextbox().setIsActive(true);
            shuttingDown = true;
        } else {
            if (playerTurn) {
                if (playerCombatant.statusCheck()) {
                    battleMenu.setActive(true);
                }
                else {
                    playerTurn = false;
                    map.getTextbox().setIsActive(true);
                }
            } else {
                if (enemy.statusCheck()) {
                    enemy.autoExecuteMove(playerCombatant);
                }
                map.getTextbox().setIsActive(true);
                playerTurn = true;
            }
        }
    }
}
