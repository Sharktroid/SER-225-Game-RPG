package Scripts.HubMap;

import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class unlockPortalThreeScript extends Script {

    public static boolean setUnlockPortalThreeFlagState = false;

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.HUBWORLD);
        showTextbox();

        addTextToTextboxQueue("(**unlocked portal three**)");
       

    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
        player.moveY(-10);
        setFlag("portalThreeUnlocked");
        setUnlockPortalThreeFlagState = isFlagSet("portalThreeUnlocked");

    }

    @Override
    public ScriptState execute() {

        if (!isFlagSet("portalThreeUnlocked")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }
}