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
        String[] answers = {"VIRUS DETECTED", "Leave me alone."};

        entity.facePlayer(player);
        if (!isFlagSet("hasTalkedToNSE")) {
            addTextToTextboxQueue( "Don't bother me.");
        } else if (!isFlagSet("hasCured2")) {
            addTextToTextboxQueue( "Would you let me stand here in peace???", selections, answers);
            if (hasWonBattle) {
                addTextToTextboxQueue("You helped me! Thank you!");
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

