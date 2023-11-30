package Scripts;

import Game.SoundPlayer;
import Game.SoundPlayer.SoundEffects;
import GameObject.Item;
import Level.EnhancedMapTile;
import Level.Script;
import Level.ScriptState;



public class AdBlockerScript extends Script<EnhancedMapTile> {
    protected boolean flag;

    public AdBlockerScript(boolean flag) {
        super();
        this.flag = flag;
    }

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        if (this.flag == false){
            addTextToTextboxQueue("There is an ad blocking the way!");
        } else if (this.flag == true){
            addTextToTextboxQueue("Ad blocked!");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (this.flag == true){
            entity.setIsHidden(true);
        }
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
