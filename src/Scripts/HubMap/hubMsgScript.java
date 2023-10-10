package Scripts.HubMap;

import Level.Script;
import Level.ScriptState;

// trigger script at beginning of game to set that heavy emotional plot
public class hubMsgScript extends Script {
    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        addTextToTextboxQueue("portal hub world");
        addTextToTextboxQueue("walk to the left square for world one,"); 
        addTextToTextboxQueue("middle square for world two[not implemented]");
        addTextToTextboxQueue("or the left square for world three[not implemented]");

    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
    }

    @Override
    public ScriptState execute() {

        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }
}
