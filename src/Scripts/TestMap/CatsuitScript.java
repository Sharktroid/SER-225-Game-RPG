package Scripts.TestMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

// script for talking to catsuit npc
public class CatsuitScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        // changes what catsuit says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToCatsuit")) {
            addTextToTextboxQueue( "Hello fellow cat");
            addTextToTextboxQueue( "looks like not everyone likes to be presentable");
            addTextToTextboxQueue( "talk to me when you get some clothes first");
        }
        else {
            addTextToTextboxQueue( "talk to me when you get some clothes first");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if Sloth is talked to again after the first time, what he says changes
        setFlag("hasTalkedToCatsuit");
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




