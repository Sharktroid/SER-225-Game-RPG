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

        addTextToTextboxQueue("Teleporting to world 1");

    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
        setFlag("portalOneActivated");
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