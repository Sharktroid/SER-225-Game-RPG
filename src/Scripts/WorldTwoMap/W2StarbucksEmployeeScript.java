package Scripts.WorldTwoMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;


public class W2StarbucksEmployeeScript extends Script<NPC> {

    private int sequence = 0;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTWO);
        showTextbox();

        String[] selections = { "Sure!", "Ugh, fine." };
        String[] answers = { "Great!", "Great." };

        entity.facePlayer(player);

        if (!isFlagSet("w2CompletedSBTask") && !isFlagSet("w2TalkedToSBEmployee")) {
            if (sequence == 0) {
                setNPCName("Employee");
                addTextToTextboxQueue("Hi! How can I help you today?");
            } else if (sequence == 1) {
                setNPCName("T");
                addTextToTextboxQueue("Have you seen anything purple around here?");
            } else if (sequence == 2) {
                setNPCName("Employee");
                addTextToTextboxQueue("Purple? The only thing kind of purple is the dragonfruit refresher.");
                addTextToTextboxQueue("But, since the holidays are around the corner, I really suggest the Sugar Cookie Almondmilk Latte and the Gingerbread Oatmilk Chai Latte.");
            } else if (sequence == 3) {
                setNPCName("T");
                addTextToTextboxQueue("No, no. Like, a purple orb? Purple shard?");
            } else if (sequence == 4) {
                setNPCName("Employee");
                addTextToTextboxQueue("OMG! Like, a purple rock? I thought it was, like, a new crystal for my collection!");
                addTextToTextboxQueue("I have it in my bag in the breakroom, but unfortunately, I can't really get it to you right now.");
                addTextToTextboxQueue("We're, like, a little swamped with orders right now.");
                addTextToTextboxQueue("The printer broke so we're stuck having to remember everything.");
                addTextToTextboxQueue("Say, if you help us out, I'll get you that crys- orb.", selections, answers);
            }
        } else if (!isFlagSet("w2CompletedSBTask") && isFlagSet("w2TalkedToSBEmployee")) {
            addTextToTextboxQueue("Chop chop!");
        } else if (isFlagSet("w2CompletedSBTask")) {
            //thanks for helping but also need another flag for recieving shard
            //w2FoundFrag1
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (!isFlagSet("w2CompletedSBTask") && !isFlagSet("w2TalkedToSBEmployee")) {
            if (sequence < 4) {
                sequence++;
            } else if (sequence == 4) {
                setFlag("w2TalkedToSBEmployee");
                sequence++;
            }
        }
    }


    @Override
    public ScriptState execute() {
        if (!isFlagSet("w2CompletedSBTask") && !isFlagSet("w2TalkedToSBEmployee")) {
            if (sequence < 4) {
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

