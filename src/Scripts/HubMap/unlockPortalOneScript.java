package Scripts.HubMap;

import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class unlockPortalOneScript extends Script {

    public static boolean setUnlockPortalOneFlagState = false;

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.HUBWORLD);
        showTextbox();

        addTextToTextboxQueue("(**unlocked portal one**)");
    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
        player.moveY(-10);
        setFlag("portalOneUnlocked");
        setUnlockPortalOneFlagState = isFlagSet("portalOneUnlocked");
        

    }

    @Override
    public ScriptState execute() {

        if (!isFlagSet("portalOneUnlocked")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }
}