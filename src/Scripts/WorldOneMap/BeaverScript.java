package Scripts.WorldOneMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.TextboxStyle;

// script for talking to beaver npc
public class BeaverScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(TextboxStyle.WORLDONE);
        setNPCName("Beaver");
        showTextbox();

        // changes what beaver says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToBeaver")) {
            addTextToTextboxQueue( "Hello Mr Cat!");
            addTextToTextboxQueue( "I just want to build a dam");
        }
        else {
            addTextToTextboxQueue( "I hope I can build a damn soon!");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if beaver is talked to again after the first time, what he says changes
        setFlag("hasTalkedToBeaver");
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
