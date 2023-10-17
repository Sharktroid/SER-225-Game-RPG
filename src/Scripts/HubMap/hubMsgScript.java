package Scripts.HubMap;

import Level.Script;
import Level.ScriptState;
import Maps.HubMap;

// trigger script at beginning of game to set that heavy emotional plot
public class hubMsgScript extends Script {
    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        addTextToTextboxQueue("portal hub world");
        addTextToTextboxQueue("walk to the left square for world one");
        addTextToTextboxQueue("middle square for world two");
        addTextToTextboxQueue("or the right square for world three");

    }

    @Override
    protected void cleanup() {
        setFlag("sawHubMsg");
        hideTextbox();
        unlockPlayer();
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("sawHubMsg")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }
}