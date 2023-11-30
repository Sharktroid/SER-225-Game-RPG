package Scripts.WorldTwoMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class W2UnlockAmznMazeScript extends Script<NPC> {

    @Override
    protected void setup() {

        if (!isFlagSet("w2TalkedToFrontDesk")) {
            setTextboxStyle(Style.WORLDTWO);
            setNPCName("System");
            lockPlayer();
            showTextbox();
            addTextToTextboxQueue("talk to the receptionist");
        }

    }

    @Override
    protected void cleanup() {
        if (!isFlagSet("w2TalkedToFrontDesk")) {
            unlockPlayer();
            hideTextbox();
            player.moveDown(24);
        }

    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("w2TalkedToFrontDesk")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;

            }
            end();
        }
        return ScriptState.COMPLETED;
    }

}
