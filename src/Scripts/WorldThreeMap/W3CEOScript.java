package Scripts.WorldThreeMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class W3CEOScript extends Script<NPC> {

    private int sequence = 0;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTHREE);
        showTextbox();

        // entity.facePlayer(player);

        if (!isFlagSet("hasTalkedToCEO")) {
            if (sequence == 0) {
                setNPCName("CEO");
                addTextToTextboxQueue("You defeated the infection? Who are you?");
            } else if (sequence == 1) {
                setNPCName("T");
                addTextToTextboxQueue("I'm just an IT worker from Firefox.");
            } else if (sequence == 2) {
                setNPCName("CEO");
                addTextToTextboxQueue("That's incredible. You saved my whole company.");
                addTextToTextboxQueue("How can I repay you for this?");
            } else if (sequence == 3) {
                setNPCName("T");
                addTextToTextboxQueue("Have you seen a piece of a purple orb?");
            } else if (sequence == 4) {
                setNPCName("CEO");
                addTextToTextboxQueue("Do you mean this? It was on the ground outside.");
                addTextToTextboxQueue("I brought it inside with me to investigate");
                addTextToTextboxQueue("That's when I found out all of my employees were infected.");
                addTextToTextboxQueue("Here you go, IT worker from Firefox. Thank you, again.");
            }
        } else if (isFlagSet("hasTalkedToCEO")) {
            setNPCName("CEO");
            addTextToTextboxQueue("Have a safe trip back home to Firefox.");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        if (!isFlagSet("hasTalkedToCEO")) {
            if (sequence < 4) {
                sequence++;
            } else if (sequence == 4) {
                setFlag("hasTalkedToCEO");
                setFlag("worldThreeComplete");
                sequence++;
            }
        }

    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasTalkedToCEO")) {
            if (sequence < 4) {
                start();
                if (isTextboxQueueEmpty()) {
                    end();
                }
            } else {
                start();
                if (isTextboxQueueEmpty()) {
                    end();
                    return ScriptState.COMPLETED;
                }
            }
            return ScriptState.RUNNING;

        } else {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;

    }

}
