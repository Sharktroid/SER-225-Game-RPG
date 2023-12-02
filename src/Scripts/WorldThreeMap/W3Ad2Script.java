package Scripts.WorldThreeMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class W3Ad2Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTHREE);
        setNPCName("T");
        showTextbox();

        if (!isFlagSet("level2Complete")) {
            addTextToTextboxQueue("I think I need to clear all the infections to clear this ad...");
        }else if (isFlagSet("level2Complete")){
            addTextToTextboxQueue("Removing Ad!");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        if (!isFlagSet("level2Complete")) {
            player.moveX(-24);
        }else if (isFlagSet("level2Complete")){
            entity.setIsHidden(true);
        }
    }


    @Override
    public ScriptState execute() {
        if (!isFlagSet("level2Complete")){
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }

}

