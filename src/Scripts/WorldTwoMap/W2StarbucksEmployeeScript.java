package Scripts.WorldTwoMap;


import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;
import Utils.Direction;


public class W2StarbucksEmployeeScript extends Script<NPC> {

    private int sequence = 0;
    private int amountMoved = 0;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTWO);

        entity.facePlayer(player);

        if (!isFlagSet("w2CompletedSBTask") && !isFlagSet("w2TalkedToSBEmployee")) {
            if (sequence == 0) {
                showTextbox();
                setNPCName("Employee");
                addTextToTextboxQueue("Hi! How can I help you today?");
            } else if (sequence == 1) {
                showTextbox();
                setNPCName("T");
                addTextToTextboxQueue("Have you seen anything purple around here?");
            } else if (sequence == 2) {
                showTextbox();
                setNPCName("Employee");
                addTextToTextboxQueue("Purple? The only thing kind of purple is the dragonfruit refresher.");
                addTextToTextboxQueue("But, since the holidays are around the corner, I really suggest the Sugar Cookie\nAlmondmilk Latte and the Gingerbread Oatmilk Chai Latte.");
            } else if (sequence == 3) {
                showTextbox();
                setNPCName("T");
                addTextToTextboxQueue("No, no. Like, a purple orb? Purple shard?");
            } else if (sequence == 4) {
                showTextbox();
                setNPCName("Employee");
                addTextToTextboxQueue("OMG! Like, a purple rock? I thought it was, like, a new crystal for my\ncollection!");
                addTextToTextboxQueue("I have it in my bag in the breakroom.");
                addTextToTextboxQueue("Let me go get that for you!");
            } else if (sequence == 5) {
                entity.stand(Direction.LEFT);
                amountMoved = 0;
            } else if (sequence == 6) {
                //walk hide
                amountMoved = 0;
            } else if (sequence == 7) {
                //wait
                setWaitTime(100);
            } else if (sequence == 8) {
                //show
                amountMoved = 0;
            } else if (sequence == 9) {
                //walk
                amountMoved = 0;
            } else if (sequence == 10) {
                showTextbox();
                setNPCName("Employee");
                addTextToTextboxQueue("Here you go! Have a nice day!");
            } 
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (!isFlagSet("w2TalkedToSBEmployee")) {
            if (sequence < 4) {
                sequence++;
            } else if (sequence == 4) {
                hideTextbox();
                sequence++;
            } else if (sequence == 5) {
                sequence++;
            } else if (sequence == 6) {
                //walk hide
                entity.setIsHidden(true);
                sequence++;
            } else if (sequence == 7) {
                //wait
                sequence++;
            } else if (sequence == 8) {
                //show
                entity.setIsHidden(false);
                sequence++;
            } else if (sequence == 9) {
                //walk
                sequence++;
            } else if (sequence == 10) {
                //talk
                setFlag("w2TalkedToSBEmployee");
                setFlag("w2FoundFrag1");
                sequence++;
            }
        }
    }


    @Override
    public ScriptState execute() {
        if (!isFlagSet("w2TalkedToSBEmployee")) {
            if (sequence < 6) {
                start();
                if (isTextboxQueueEmpty()) {
                    end();
                }
            } else if (sequence == 6) {
                //walk hide
                start();
                entity.walk(Direction.LEFT, 3);
                amountMoved += 3;
                if (amountMoved == 603) {
                    end();
                }
            } else if (sequence == 7) {
                //wait
                start();
                if (isWaitTimeUp()) {
                    end();
                }
            } else if (sequence == 8) {
                //show
                start();
                if (isTextboxQueueEmpty()) {
                    end();
                }
            }
            else if (sequence == 9) {
                //walk
                start();
                entity.walk(Direction.RIGHT, 3);
                amountMoved += 3;
                if (amountMoved == 603) {
                    end();
                }
            }
            else if (sequence == 10) {
                start();
                if (isTextboxQueueEmpty()) {
                    end();
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

