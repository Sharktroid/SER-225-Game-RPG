package Scripts.HubMap;

import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class portalThreeScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.HUBWORLD);
        showTextbox();

        if (!isFlagSet("portalThreeUnlocked")) {
            addTextToTextboxQueue("Talk to the Firefox (**trigger*)");
        } else {
            addTextToTextboxQueue("Teleporting to world 3");
        }
    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();

        if (!isFlagSet("portalThreeUnlocked")) {
            player.moveY(10);
        } else {
            setFlag("portalThreeActivated");
        }
    }

    @Override
    public ScriptState execute() {

        if (!isFlagSet("portalThreeActivated")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }
}