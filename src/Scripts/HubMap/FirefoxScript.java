package Scripts.HubMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to sloth npc
public class FirefoxScript extends Script<NPC> {

    private int sequence = 0;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.HUBWORLD);
        setNPCName("Firefox");
        showTextbox();



        if (!isFlagSet("hasTalkedToFirefox")) {
            if (sequence == 0) {
                setNPCName("Firefox");
                addTextToTextboxQueue( "My orb! Where did it go?");
                addTextToTextboxQueue( "You! Do you know where my orb went?");
            } else if (sequence == 1) {
                setNPCName("T");
                addTextToTextboxQueue( "Is this... the Firefox? I thought it was only a myth.\nWhat is it doing here?");
                addTextToTextboxQueue( "No, I have no idea where your orb went.");
            }
        } else if (isFlagSet("beenToWorldOne")) {
            addTextToTextboxQueue("Is that... my orb?");
            addTextToTextboxQueue("But it's only part of it... What have you done the rest of my orb?");
            addTextToTextboxQueue("Find the rest of it!");
        } else if (isFlagSet("beenToWorldTwo")) {
            addTextToTextboxQueue("You've found more of my orb! Great job!");
            addTextToTextboxQueue("There seems to only be one third left of it.");
        } else if (isFlagSet("beenToWorldThree")) {
            addTextToTextboxQueue("You've found the last of it! Thank you so much!");
        } else {
            addTextToTextboxQueue( "Please help me find my orb!");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (sequence == 0) {
            sequence++;
        } else if (sequence == 1) {
            setFlag("hasTalkedToFirefox");
            sequence++;
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasTalkedToFirefox")) {
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
        } else if (isFlagSet("hasTalkedToFirefox")) {
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