package Scripts.WorldTwoMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;


public class W2FrontDeskScript extends Script<NPC> {

    private int sequence = 0;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTWO);
        showTextbox();

        entity.facePlayer(player);

        if (!isFlagSet("w2TalkedToFrontDesk") && !isFlagSet("w2FoundFrag2")) {
            if (sequence == 0) {
                setNPCName("Receptionist");
                addTextToTextboxQueue("Hi, can I help you with anything today?");
            } else if (sequence == 1) {
                setNPCName("T");
                addTextToTextboxQueue("Have you seen a bright purple object?");
            } else if (sequence == 2) {
                setNPCName("Receptionist");
                addTextToTextboxQueue("Oh, yes! I did!");
                addTextToTextboxQueue("It was mixed in with the pile of boxes that our delivery truck brought in.");
                addTextToTextboxQueue("We sent it to the lost and found bin since it didn't have a send or return address written anywhere.");
                addTextToTextboxQueue("...I can't really give you any more direction, since I've never been there myself.");
                addTextToTextboxQueue("Only one of our employees goes there, but he had to leave unexpectedly, so you're going to have to find it yourself unfortunately.");
                addTextToTextboxQueue("But! If you just follow the hallways, you should be able to find it... at some point...");
            } else if (sequence == 3) {
                setNPCName("T");
                addTextToTextboxQueue("Okay... Thanks...");
            }
        } else if (isFlagSet("w2TalkedToFrontDesk") && !isFlagSet("w2FoundFrag2")) {
            setNPCName("Receptionist");
            addTextToTextboxQueue("Good luck finding your purple thing!...");
        } else if (isFlagSet("w2TalkedToFrontDesk") && isFlagSet("w2FoundFrag2")) {
            setNPCName("Receptionist");
            addTextToTextboxQueue("You actually found the lost and found bin? That's amazing! You should work here, haha!...");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        if (!isFlagSet("w2TalkedToFrontDesk") && !isFlagSet("w2FoundFrag2")) {
            if (sequence < 3) {
                sequence++;
            } else if (sequence == 3) {
                setFlag("w2TalkedToFrontDesk");
                sequence++;
            }
        }
    }


    @Override
    public ScriptState execute() {
        if (!isFlagSet("w2TalkedToFrontDesk") && !isFlagSet("w2FoundFrag2")) {
            if (sequence < 3) {
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

