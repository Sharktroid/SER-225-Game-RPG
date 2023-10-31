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

        addTextToTextboxQueue("Teleporting to world 2");

    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
        setFlag("portalTwoActivated");
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
