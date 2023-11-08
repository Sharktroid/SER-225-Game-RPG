package Scripts.WorldOneMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to beaver npc
public class BeaverScript extends Script<NPC> {
    
    private boolean hasWonBattle = false;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDONE);
        setNPCName("Infected NPC 1");
        showTextbox();

        String[] selections = {"RUN VIRUS SCAN", "LEAVE"};
        String[] answers = {"VIRUS DETECTED", "Yeah, go away."};

        entity.facePlayer(player);
        if (!isFlagSet("hasCured1")) {
            addTextToTextboxQueue( "I'm saying something rude!", selections, answers);

            if (hasWonBattle) {
                addTextToTextboxQueue("Woah, what happened? I was infected?");
                addTextToTextboxQueue("Thanks for helping me!");
            } else {
                addTextToTextboxQueue("Y0U'LL N3V3R D3F3AT M3");
            }
        } else {
            addTextToTextboxQueue( "Thank you again.");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (hasWonBattle) {
            setFlag("hasCured1");
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasCured1")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
            return ScriptState.COMPLETED;
        } else {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
            return ScriptState.COMPLETED;
        }
    }
}
