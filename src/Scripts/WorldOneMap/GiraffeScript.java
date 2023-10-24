package Scripts.WorldOneMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.TextboxStyle;

// script for talking to giraffe npc
public class GiraffeScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(TextboxStyle.WORLDONE);
        setNPCName("Giraffe");
        showTextbox();

        // changes what Giraffe says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToGiraffe")) {
            addTextToTextboxQueue( "Hows it going little cat");
            addTextToTextboxQueue( "Do you need need me to grab anything for you?");
        }
        else {
            addTextToTextboxQueue( "If you need me to grab anything for you let me know");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if Giraffe is talked to again after the first time, what he says changes
        setFlag("hasTalkedToGiraffe");
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

