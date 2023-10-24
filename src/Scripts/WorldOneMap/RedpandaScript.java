package Scripts.WorldOneMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.TextboxStyle;

// script for talking to redpanda npc
public class RedpandaScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(TextboxStyle.WORLDONE);
        setNPCName("Red Panda");
        showTextbox();

        // changes what Redpanda says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToRedpanda")) {
            addTextToTextboxQueue( "Hey cat");
            addTextToTextboxQueue( "I found some glowing orb today");
            addTextToTextboxQueue( "I can show you it another time soon");
        }
        else {
            addTextToTextboxQueue( "I can show you the orb I found whenever you want");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if beaver is talked to again after the first time, what he says changes
        setFlag("hasTalkedToRedpanda");
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




