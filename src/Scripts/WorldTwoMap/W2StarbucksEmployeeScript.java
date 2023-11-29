package Scripts.WorldTwoMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;


public class W2StarbucksEmployeeScript extends Script<NPC> {

    private int sequence = 0;
    private int responseNum = -1;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTWO);
        showTextbox();

        entity.facePlayer(player);

        if (!isFlagSet("w2CompletedSBTask") && !isFlagSet("w2TalkedToSBEmployee")) {
            if (sequence == 0) {
                setNPCName("Starbucks Employee");
                addTextToTextboxQueue("Hi! How can I help you today?");
            }
        }

        // if (!isFlagSet("hasTalkedToBeaver")) {

        //     addTextToTextboxQueue("Hi Cat!");
        //     addTextToTextboxQueue("I found some glowing orb today");
        //     addTextToTextboxQueue("I can show you it another time soon");
        // }

        // if (isFlagSet("hasTalkedToBeaver")){

        //     addTextToTextboxQueue("Hello again.");
        // }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        setFlag("hasTalkedToBeaver");
    }


    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasTalkedToBeaver")){
            start();


            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;

            }
            end();
            return ScriptState.COMPLETED;

        }else if (isFlagSet("hasTalkedToBeaver")){
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

