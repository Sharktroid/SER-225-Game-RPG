package Scripts.HubMap;

import Level.Script;
import Level.ScriptState;

public class portalThreeScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        addTextToTextboxQueue("Teleporting to world 3");

    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
        setFlag("portalThreeActivated");
        setFlag("startWorldThree");
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