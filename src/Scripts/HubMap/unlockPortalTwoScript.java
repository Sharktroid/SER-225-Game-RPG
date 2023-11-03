package Scripts.HubMap;

import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class unlockPortalTwoScript extends Script {

    public static boolean setUnlockPortalTwoFlagState = false;

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.HUBWORLD);
        showTextbox();

        addTextToTextboxQueue("(**unlocked portal two**)");
       

    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
        player.moveY(-10);
        setFlag("portalTwoUnlocked");
        setUnlockPortalTwoFlagState = isFlagSet("portalTwoUnlocked");

    }

    @Override
    public ScriptState execute() {

        if (!isFlagSet("portalTwoUnlocked")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }
}