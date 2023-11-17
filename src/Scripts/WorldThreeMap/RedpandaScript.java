package Scripts.WorldThreeMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to redpanda npc

public class RedpandaScript extends Script<NPC> {

    public static boolean setRedPandaFlagState = false;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTHREE);
        setNPCName("Red Panda");
        showTextbox();

        //entity.facePlayer(player);

        if (!isFlagSet("hasTalkedToRedPanda")) {

            addTextToTextboxQueue("Hi Cat!");
            addTextToTextboxQueue("I found some glowing orb today");
            addTextToTextboxQueue("I can show you it another time soon");
        }

        if (isFlagSet("hasTalkedToRedPanda")){

            addTextToTextboxQueue("mmmmmmmmmmmmmmmmmmm");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        setFlag("hasTalkedToRedPanda");
        setRedPandaFlagState = isFlagSet("hasTalkedToRedPanda");
    }


    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasTalkedToRedPanda")){
            start();


            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;

            }
            end();
            return ScriptState.COMPLETED;

        }else if (isFlagSet("hasTalkedToRedPanda")){
            start();
            if (!isTextboxQueueEmpty()){
                return ScriptState.RUNNING;
            }
            end();
            return ScriptState.COMPLETED;
        }
        return ScriptState.COMPLETED;
    }

}

