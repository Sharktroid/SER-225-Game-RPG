package Scripts.WorldThreeFloors;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

// script for talking to redpanda npc

public class DinoScript extends Script<NPC> {

    public static boolean setDinoFlagState = false;
    
    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        //entity.facePlayer(player);

        if (!isFlagSet("hasTalkedToDino")) {

            addTextToTextboxQueue("Hey!");
            addTextToTextboxQueue("Welcome to the top floor!");
            addTextToTextboxQueue("We're up pretty high here...");
        }

        if (isFlagSet("hasTalkedToDino")){
            
            addTextToTextboxQueue("Heading back down?");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        setFlag("hasTalkedToDino");
        setDinoFlagState = isFlagSet("hasTalkedToDino");
    }

    
    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasTalkedToDino")){
            start();


            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;

            } 
            end();
            return ScriptState.COMPLETED;

        }else if (isFlagSet("hasTalkedToDino")){
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

