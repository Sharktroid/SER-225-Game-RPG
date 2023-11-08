package Scripts.HubMap;

import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class hubMsgScript extends Script {

    public static boolean setSawHubMsgFS = false;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.HUBWORLD);
        setNPCName("Tutorial");
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
        setSawHubMsgFS = isFlagSet("sawHubMsg");
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
