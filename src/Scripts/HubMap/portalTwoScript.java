package Scripts.HubMap;

import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class portalTwoScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.HUBWORLD);
        showTextbox();

        if (!isFlagSet("portalTwoUnlocked")) {
            addTextToTextboxQueue("Talk to the Firefox (**trigger*)");
        } else {
            addTextToTextboxQueue("Teleporting to world 2");
        }
    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();

        if (!isFlagSet("portalTwoUnlocked")) {
            player.moveY(10);
        } else {
            setFlag("portalTwoActivated");
        }
    }

    @Override
    public ScriptState execute() {

        if (!isFlagSet("portalTwoActivated")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }
}