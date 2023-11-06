package Scripts.WorldOneMap;

import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// trigger script at beginning of game to set that heavy emotional plot
public class WorldOneClearScript extends Script {

    public static boolean setWorldOneClearFlagState = false;

    @Override
    protected void setup() {
        setTextboxStyle(Style.WORLDONE);
        lockPlayer();
        showTextbox();
        addTextToTextboxQueue("World One Cleared");
    }

    @Override
    protected void cleanup() {
        setFlag("worldOneCleared");
        hideTextbox();
        unlockPlayer();
        setWorldOneClearFlagState = isFlagSet("worldOneCleared");
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
