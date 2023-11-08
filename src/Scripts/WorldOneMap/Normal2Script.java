package Scripts.WorldOneMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to giraffe npc
public class Normal2Script extends Script<NPC> {

    private int responseNum = -1;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDONE);
        setNPCName("Normal NPC 2");
        showTextbox();

        String[] selections = {"RUN VIRUS SCAN", "LEAVE"};
        String[] answers = {"NO VIRUS DETECTED", "Yes! Leave me alone!"};

        entity.facePlayer(player);
        if (!isFlagSet("hasRanVirusScanNPC2")) {
            addTextToTextboxQueue( "A virus! Don't attack me!", selections, answers);
        } else if (isFlagSet("hasCuredAllNPCs")) {
            addTextToTextboxQueue("Thanks for helping out.");
        } else if (isFlagSet("hasRanVirusScanNPC2")) {
            addTextToTextboxQueue( "Sorry about mistaking you as a virus.");
        } 
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (responseNum == 0) {
            setFlag("hasRanVirusScanNPC2");
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

