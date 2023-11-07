package Scripts.HubMap;

import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class portalOneScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.HUBWORLD);
        showTextbox();

        if (!isFlagSet("portalOneUnlocked")) {
            addTextToTextboxQueue("Talk to the Firefox (**trigger*)");
        } else {
            addTextToTextboxQueue("Teleporting to world 1");
        }
    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();

        if (!isFlagSet("portalOneUnlocked")) {
            player.moveY(10);
        } else {
            setFlag("portalOneActivated");
        }
    }

    @Override
    public ScriptState execute() {

        if (!isFlagSet("portalOneActivated")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }
}