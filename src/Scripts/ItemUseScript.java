package Scripts;

import GameObject.Item;
import Level.Map;
import Level.Player;
import Level.Script;
import Level.ScriptState;

// script for talking to redpanda npc

public class ItemUseScript extends Script {
    protected Item item;

    public ItemUseScript(Item item, Player player, Map map) {
        super();
        this.item = item;
        this.player = player;
        this.map = map;
    }

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        addTextToTextboxQueue(item.getUseText());
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
    }

    @Override
    public ScriptState execute() {
        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }
}
