package Scripts.HubMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;
import Maps.HubMap;
import Maps.WorldOneMap;
import Scripts.WorldOneMap.WorldOneClearScript;

// script for talking to sloth npc
public class FirefoxScript extends Script<NPC> {

    public int sequence = 0;

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.HUBWORLD);
        setNPCName("Firefox");
        showTextbox();
        

        System.out.println("hasTalkedToFirefox0: " + isFlagSet("hasTalkedToFirefox0"));
        System.out.println("HubMap.getTalkedFS(0): " + HubMap.getTalkedFS(0));
        System.out.println("\nhasTalkedToFirefox1: " + isFlagSet("hasTalkedToFirefox1"));
        System.out.println("HubMap.getTalkedFS(0): " + HubMap.getTalkedFS(1));
        System.out.println("\nhasTalkedToFirefox2: " + isFlagSet("hasTalkedToFirefox2"));
        System.out.println("HubMap.getTalkedFS(0): " + HubMap.getTalkedFS(2));

        System.out.println("\nunlockedPortal1: "+ isFlagSet("unlockedPortal1"));
        System.out.println("HubMap.getUnlockedPortalFS(1): " + HubMap.getUnlockedPortalFS(1));
        System.out.println("\nunlockedPortal2: "+ isFlagSet("unlockedPortal2"));
        System.out.println("HubMap.getUnlockedPortalFS(1): " + HubMap.getUnlockedPortalFS(2));


        System.out.println("\nworldOneComplete: " + isFlagSet("worldOneComplete"));
        System.out.println("WorldOneMap.getW1ClearedFS(): "+ WorldOneMap.getW1ClearedFS());
        System.out.println("------------------------------------------------");

        if (!isFlagSet("hasTalkedToFirefox0")) {
            if (sequence == 0) {
                setNPCName("Firefox");
                addTextToTextboxQueue("My orb! Where did it go?");
                addTextToTextboxQueue("You! Do you know where my orb went?");
            } else if (sequence == 1) {
                setNPCName("T");
                addTextToTextboxQueue("Is this... the Firefox?");
                addTextToTextboxQueue("I thought it was only a myth. What is it doing here?");
                addTextToTextboxQueue("No, I have no idea where your orb went.");
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
                addTextToTextboxQueue("You've found the last of it! Thank you so much!");
            }else{
                addTextToTextboxQueue("kjfnksjfd");
            }
        } else {
            addTextToTextboxQueue("Please help me find my orb!");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (!isFlagSet("hasTalkedToFirefox0")) {
            if (sequence == 0) {
                sequence++;
            }else if(sequence == 1){
                sequence++;
            }else if (sequence == 2) {
                setFlag("hasTalkedToFirefox0");
                setFlag("unlockedPortal1");
                
                HubMap.setTalkedFS(0, true);
                ;
                HubMap.setUnlockPortalFS(1, true);
                ;
                sequence++;
            }

        } else if (!isFlagSet("hasTalkedToFirefox1")) {
            if (isFlagSet("worldOneComplete")) {
                setFlag("hasTalkedToFirefox1");
                setFlag("unlockedPortal2");
                HubMap.setTalkedFS(1, true);
                HubMap.setUnlockPortalFS(2, true);
            }
        } else if (!isFlagSet("hasTalkedToFirefox2")) {
            if(isFlagSet("worldTwoComplete")){
                setFlag("hasTalkedToFirefox2");
                setFlag("portalThreeUnlocked");
                HubMap.setTalkedFS(2, true);
                HubMap.setUnlockPortalFS(3, true);
            }
            
        } else if (!isFlagSet("hasTalkedToFirefox3") && isFlagSet("worldOneComplete")
                && isFlagSet("worldTwoComplete") && isFlagSet("worldThreeComplete")) {
            setFlag("hasTalkedToFirefox3");
            // WIN FLAG HERE
            HubMap.talkedToFirefoxFS[3] = true;

        }
    }

    @Override
    public ScriptState execute() {
        // System.out.println(isFlagSet("hasTalkedToFirefox0"));
        if (!isFlagSet("hasTalkedToFirefox0")) {
            if (sequence == 0) {
                start();
                if (isTextboxQueueEmpty()) {
                    end();
                }
            } else if (sequence == 1) {
                start();
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