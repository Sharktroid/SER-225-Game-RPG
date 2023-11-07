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

        if (!isFlagSet("worldOneComplete")){
            addTextToTextboxQueue("clear world one then come back");
        }else{
            addTextToTextboxQueue("(**unlocked portal two**)");
        }

    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
        player.moveY(-10);
        
        

        if (!isFlagSet("worldOneComplete")) {
            player.moveY(-10);
        } else {
            setFlag("portalTwoUnlocked");
            setUnlockPortalTwoFlagState = isFlagSet("portalTwoUnlocked");
        }

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