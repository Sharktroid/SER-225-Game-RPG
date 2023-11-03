package Scripts.WorldOneMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to sloth npc
public class SlothScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDONE);
        setNPCName("Sloth");
        showTextbox();

        // changes what Sloth says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToSloth")) {
            addTextToTextboxQueue( "Good morning cat");
            addTextToTextboxQueue( "I just want to take a nap");
            addTextToTextboxQueue( "I hope I can sleep soon");
        }
        else {
            addTextToTextboxQueue( "I hope I can sleep soon");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if Sloth is talked to again after the first time, what he says changes
        setFlag("hasTalkedToSloth");
    }

    @Override
    public ScriptState execute() {
        // setTextboxStyle(Textbox.Style.WORLDTWO);
        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }
}



