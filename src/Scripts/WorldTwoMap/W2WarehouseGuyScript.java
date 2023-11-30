package Scripts.WorldTwoMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;


public class W2WarehouseGuyScript extends Script<NPC> {

    private int sequence = 0;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTWO);
        showTextbox();

        entity.facePlayer(player);

        if (!isFlagSet("w2TalkedToWarehouse")) {
            if (sequence == 0) {
                setNPCName("T");
                addTextToTextboxQueue("Do you know which way the lost and found bin is?");
            } else if (sequence == 1) {
                setNPCName("Employee");
                addTextToTextboxQueue("I don't know... I've been stuck here for 10 days...");
                addTextToTextboxQueue("I got lost when trying to find the bathroom...");
                addTextToTextboxQueue("I've been searching nonstop ever since... I can't help you... Good luck...");
            }
        } else {
            setNPCName("Employee");
            addTextToTextboxQueue("Don't worry about me... I'll find my way out soon...");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        if (!isFlagSet("w2TalkedToWarehouse")) {
            if (sequence == 0) {
                sequence++;
            } else if (sequence == 1) {
                setFlag("w2TalkedToWarehouse");
                sequence++;
            }
        } 
    }


    @Override
    public ScriptState execute() {
        if (!isFlagSet("w2TalkedToWarehouse")) {
            if (sequence == 0) {
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

