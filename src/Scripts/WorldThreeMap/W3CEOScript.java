package Scripts.WorldThreeMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class W3CEOScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTHREE);
        setNPCName("Employee");
        showTextbox();

        // entity.facePlayer(player);

        if (!isFlagSet("hasTalkedToCEO")) {

            addTextToTextboxQueue("You Win!");
        }

        if (isFlagSet("hasTalkedToCEO")) {

            addTextToTextboxQueue("Go back through the portal");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        setFlag("hasTalkedToCEO");
        setFlag("worldThreeComplete");

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
