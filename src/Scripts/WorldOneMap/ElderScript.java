package Scripts.WorldOneMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.TextboxStyle;

// script for talking to elder npc
public class ElderScript extends Script<NPC> {

    private int sequence = 0;
    private int responseNum = -1;

    @Override
    protected void setup() {
        lockPlayer();
        
        setTextboxStyle(TextboxStyle.WORLDONE);
        setNPCName("Granny");
        showTextbox();

        String[] selections = {"Whats wrong granny", "Who can't you find?"};
        String[] answers = {"Oh i lost my cat Garfunkle ", "My cat, his name is Garfunkle"};

        String[] selections2 = {"I found your cat \n Garfunkle"};
        String[] answers2 = {"Did you actually??"};

        entity.facePlayer(player);
        if (!isFlagSet("hasTalkedToElder") && !isFlagSet("foundCat")) {
            if (sequence == 0) {
                addTextToTextboxQueue( "Oh I hope he comes back...", selections, answers);
                responseNum = getChoice();
            } else if (sequence == 1) {
                if (responseNum == 0) {
                    
                    addTextToTextboxQueue( "He hates being alone on mondays");
                    addTextToTextboxQueue( "If you can find him for me \n I can help you with whatever you need");
                    setFlag("hasTalkedToElder");
                } else if (responseNum == 1) {
                    addTextToTextboxQueue( "He left because I wouldn't feed him lasagna again");
                    addTextToTextboxQueue( "Please help me find him");
                    addTextToTextboxQueue( "If you do ill be forever in your debt");
                    setFlag("hasTalkedToElder");
                } 
            }
        } 
        if (isFlagSet("foundCat") && !isFlagSet("foundCat2")){
            showTextbox();
            addTextToTextboxQueue("Please tell me you found him...", selections2, answers2);
            addTextToTextboxQueue("Thank you so much");
            addTextToTextboxQueue("If you need anything at all \n please do not hesitate to let me know");
            setFlag("foundCat2");


        }
        else if(isFlagSet("foundCat2")) {
            showTextbox();
            addTextToTextboxQueue("If you need anything at all \n please do not hesitate to let me know");

        }
        else {
            addTextToTextboxQueue( "I hope I can find him soon");
        }
    }
    

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (sequence == 0) {
            responseNum = getChoice();
            sequence++;
        } else if (sequence == 1) {
            setFlag("hasTalkedToElder");
            sequence++;
        }
    }

    @Override
    public ScriptState execute() {
        // setTextboxStyle(TextboxStyle.WORLDONE);
        if (!isFlagSet("hasTalkedToElder")) {
            if (sequence == 0) {
                start();
                if (isTextboxQueueEmpty()) {
                    end();
                }  
            } else if (sequence == 1) {
                start();
                if (isTextboxQueueEmpty()) {
                    end();
                    return ScriptState.COMPLETED;
                }
            }
            return ScriptState.RUNNING;
        } else if (isFlagSet("hasTalkedToElder")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
            return ScriptState.COMPLETED;
        }
        return ScriptState.COMPLETED;
    }   
}
