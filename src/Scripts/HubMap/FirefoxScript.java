package Scripts.HubMap;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to sloth npc
public class FirefoxScript extends Script<NPC> {

    public int sequence = 0;

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.HUBWORLD);
        setNPCName("Firefox");
        showTextbox();
        SoundPlayer.playMusic(MusicTracks.FIREFOXDIALOGUE);

        if (!isFlagSet("hasTalkedToFirefox0")) {
            if (sequence == 0) {
                setNPCName("Firefox");
                addTextToTextboxQueue("My orb! Where did it go?");
                addTextToTextboxQueue("You! Do you know where my orb went?");
            } else if (sequence == 1) {
            }
        } else if (!isFlagSet("hasTalkedToFirefox1")) {
            if (!isFlagSet("worldOneComplete")) {
                addTextToTextboxQueue("Go through the portal, go look for my orb!");
            } else {
                addTextToTextboxQueue("Is that... my orb?");
                addTextToTextboxQueue("But it's only part of it... What have you done the rest of my orb?");
                addTextToTextboxQueue("Find the rest of it!");
            }
        } else if (!isFlagSet("hasTalkedToFirefox2")) {
            if (!isFlagSet("worldTwoComplete")) {
                addTextToTextboxQueue("Keep looking, see if you can find more pieces.");
            } else {
                addTextToTextboxQueue("You've found more of my orb! Great job!");
                addTextToTextboxQueue("There seems to only be one third left of it.");
            }

        } else if (!isFlagSet("hasTalkedToFirefox3")) {
            if (!isFlagSet("worldThreeComplete")) {
                addTextToTextboxQueue("Keep looking, theres only one more piece left.");
            }else{
                addTextToTextboxQueue("You've found the last piece of my orb! Thank you so much!");
                addTextToTextboxQueue("I will send you back to the real world since you've helped me so much!");
            }
        } else {
            addTextToTextboxQueue("Please help me find my orb!");
        }
        // entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        SoundPlayer.playMusic(MusicTracks.HUBMAP);

        if (!isFlagSet("hasTalkedToFirefox0")) {
            if (sequence == 0) {
                sequence++;
            }else if(sequence == 1){
                sequence++;
            }else if (sequence == 2) {
                setFlag("hasTalkedToFirefox0");
                setFlag("unlockedPortal1");
                sequence++;
            }

        } else if (!isFlagSet("hasTalkedToFirefox1")) {
            if (isFlagSet("worldOneComplete")) {
                setFlag("hasTalkedToFirefox1");
                setFlag("unlockedPortal2");
            }
        } else if (!isFlagSet("hasTalkedToFirefox2")) {
            if(isFlagSet("worldTwoComplete")){
                setFlag("hasTalkedToFirefox2");
                setFlag("unlockedPortal3");
            }

        } else if (!isFlagSet("hasTalkedToFirefox3")) {
            if (isFlagSet("worldThreeComplete")){
                setFlag("hasTalkedToFirefox3");
                setFlag("gameComplete");
            }
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasTalkedToFirefox0")) {
            if (sequence == 0) {
                start();
                if (isTextboxQueueEmpty()) {
                    setNPCName("T");
                    addTextToTextboxQueue("Is this... the Firefox?");
                    addTextToTextboxQueue("I thought it was only a myth. What is it doing here?");
                    addTextToTextboxQueue("No, I have no idea where your orb went.");
                    sequence++;
                }
            } else if (sequence == 1) {
                if (isTextboxQueueEmpty()) {
                    end();
                }
            } else if (sequence == 2) {
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
            return ScriptState.COMPLETED;

        }

        // return ScriptState.COMPLETED;
    }
}