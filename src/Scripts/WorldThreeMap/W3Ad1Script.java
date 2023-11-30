package Scripts.WorldThreeMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class W3Ad1Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTHREE);
        setNPCName("Ad");
        showTextbox();

        if (!isFlagSet("level1Complete")) {
            addTextToTextboxQueue("Clear the viruses to remove this ad.");
        }else if (isFlagSet("level1Complete")){
            addTextToTextboxQueue("Removing Ad!");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        if (!isFlagSet("level1Complete")) {
            player.moveX(-24);
        }else if (isFlagSet("level1Complete")){
            entity.setIsHidden(true);
        }
    }


    @Override
    public ScriptState execute() {
        if (!isFlagSet("level1Complete")){
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }

}

