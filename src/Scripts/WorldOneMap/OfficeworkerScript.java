package Scripts.WorldOneMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

// script for talking to officeworker npc
public class OfficeworkerScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        // changes what Officeworker says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToOfficeworker")) {
            addTextToTextboxQueue( "Where am I??!!");
            addTextToTextboxQueue( "At least I'm still on the clock");
            
        }
        else {
            addTextToTextboxQueue( "At least I'm still on the clock");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if Officeworker is talked to again after the first time, what he says changes
        setFlag("hasTalkedToOfficeworker");
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


