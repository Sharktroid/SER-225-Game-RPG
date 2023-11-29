package Scripts;

import Game.SoundPlayer;
import Game.SoundPlayer.SoundEffects;
import GameObject.Item;
import Level.EnhancedMapTile;
import Level.Script;
import Level.ScriptState;



public class BasicItemScript extends Script<EnhancedMapTile> {
    protected Item item;

    public BasicItemScript(Item item) {
        super();
        this.item = item;
    }

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        onPickup();
        item.player = player;
        player.addItem(item);
    }

    protected void onPickup() {
        SoundPlayer.playSoundEffect(SoundEffects.ITEMGET);
        addTextToTextboxQueue(String.format("You got a %s!", item.getName()));
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
