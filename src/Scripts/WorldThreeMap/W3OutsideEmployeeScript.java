package Scripts.WorldThreeMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class W3OutsideEmployeeScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTHREE);
        setNPCName("Employee");
        showTextbox();

        entity.facePlayer(player);

        if (!isFlagSet("hasTalkedToOutsideGuy")) {

            addTextToTextboxQueue("welcome msg");
        }

        if (isFlagSet("hasTalkedToOutsideGuy")) {

            addTextToTextboxQueue("go inside");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        setFlag("hasTalkedToOutsideGuy");
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
