package Scripts.WorldThreeMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class W3Ad3Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTHREE);
        setNPCName("Ad");
        showTextbox();

        if (!isFlagSet("level3Complete")) {
            addTextToTextboxQueue("Clear the viruses to remove this ad.");
        }else if (isFlagSet("level3Complete")){
            addTextToTextboxQueue("Removing Ad!");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        if (!isFlagSet("level3Complete")) {
            player.moveX(-24);
        }else if (isFlagSet("level3Complete")){
            entity.setIsHidden(true);
        }
    }


    @Override
    public ScriptState execute() {
        if (!isFlagSet("level3Complete")){
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }

}

