package Scripts.WorldOneMap;

import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;
import Maps.WorldOneMap;


// trigger script at beginning of game to set that heavy emotional plot
public class WorldOneClearScript extends Script {

    @Override
    protected void setup() {
        setTextboxStyle(Style.WORLDONE);
        lockPlayer();
        showTextbox();
        addTextToTextboxQueue("World One Cleared!");
        addTextToTextboxQueue("The Firefox is summoning you.");
    }

    @Override
    protected void cleanup() {
        setFlag("worldOneCleared");
        hideTextbox();
        unlockPlayer();
        WorldOneMap.w1ClearedFS = true;
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("worldOneCleared")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }
}
