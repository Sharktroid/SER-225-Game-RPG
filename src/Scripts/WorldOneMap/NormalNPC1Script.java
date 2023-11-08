package Scripts.WorldOneMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to giraffe npc
public class NormalNPC1Script extends Script<NPC> {

    private int responseNum = -1;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDONE);
        setNPCName("Normal NPC 1");
        showTextbox();

        String[] selections = {"RUN VIRUS SCAN", "LEAVE"};
        String[] answers = {"NO VIRUS DETECTED", "Please fix all of this..."};

        entity.facePlayer(player);
        if (!isFlagSet("hasRanVirusScanNPC1")) {
            addTextToTextboxQueue( "Are you here to help? Everyone's acting so weird...", selections, answers);
        } else if (isFlagSet("hasCuredAllNPCs")) {
            addTextToTextboxQueue("Thanks for helping out and saving our world!");
        } else if (isFlagSet("hasRanVirusScanNPC1")) {
            addTextToTextboxQueue( "I hope this can all be fixed soon.");
        } 
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (responseNum == 0) {
            setFlag("hasRanVirusScanNPC1");
        }
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

