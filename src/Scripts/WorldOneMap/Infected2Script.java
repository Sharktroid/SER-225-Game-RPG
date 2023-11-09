package Scripts.WorldOneMap;

import Level.*;
import Level.Textbox.Style;

public class Infected2Script extends Script<NPC> {

    private boolean hasWonBattle = false;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDONE);
        setNPCName("Infected NPC 2");
        showTextbox();

        String[] selections = {"RUN VIRUS SCAN", "LEAVE"};
        String[] answers = {"VIRUS DETECTED", "Yeah, go away."};

        entity.facePlayer(player);
        if (!isFlagSet("hasCured2")) {
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
            setFlag("hasCured2");
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasCured2")) {
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

