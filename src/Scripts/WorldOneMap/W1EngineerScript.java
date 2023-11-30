package Scripts.WorldOneMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to catsuit npc
public class W1EngineerScript extends Script<NPC> {

    private int sequence = 0;


    @Override
    protected void setup() {
        if (isFlagSet("w1CuredNPC1") && isFlagSet("w1CuredNPC2") && isFlagSet("w1CuredNPC3") && isFlagSet("w1CuredNPC4")
                && isFlagSet("w1CuredNPC5")) {
            setFlag("w1CuredAllNPCs");

        }

        lockPlayer();

        setTextboxStyle(Style.WORLDONE);
        String npcName = "Network Security Engineer";
        String playerName = "T";
        // setNPCName("Network Security Engineer");
        showTextbox();

        String[] selections = { "Sure!", "No way!" };
        String[] answers = { "Thank you so much. I would do it but I have to tend to\nmy partner here...","Guess you aren't getting your orb piece..." };

        if (!isFlagSet("hasFinishedOMJ")) {
            setNPCName(npcName);
            addTextToTextboxQueue("Hang in there partner! ");
        } else if (!isFlagSet("hasTalkedToNSE")) {
            if (sequence == 0) {
                setNPCName(npcName);
                addTextToTextboxQueue("Who are you? How did you get here?");
            } else if (sequence == 1) {
                setNPCName(playerName);
                addTextToTextboxQueue("I'm an IT worker from Firefox. I came through a portal.");
                addTextToTextboxQueue("I'm looking for a purple orb");
            } else if (sequence == 2) {
                setNPCName(npcName);
                addTextToTextboxQueue("Purple orb? I think I found a part of that.");
                addTextToTextboxQueue("I could give it to you right now but I'm a little occupied...");
                addTextToTextboxQueue("There seems to be an infection that got in through our\nnetwork. They got my partner...");
            } else if (sequence == 3) {
                addTextToTextboxQueue("I know you're not network security, but could you help\nus out?",selections,answers);
            } else if (sequence == 4) {
                if (this.getChoice() == 1) {
                    setNPCName(playerName);
                    addTextToTextboxQueue("Okay fine! I'll do it!");
                }
                setNPCName(npcName);
                addTextToTextboxQueue("Here's the file I've made on the security breach so far.");
                addTextToTextboxQueue("The viruses aren't apparent at first, so you'll have to\nuse this file to check if someone is infected.");
                addTextToTextboxQueue("Also, here's some security tools to fight against the\nvirus.");
                addTextToTextboxQueue("Come back to me once you've saved everyone.");
            }
        } else if (!isFlagSet("w1CuredAllNPCs")) {
            setNPCName(npcName);
            addTextToTextboxQueue("Hurry. You can still save everyone if you act quickly.");
        } else if (!isFlagSet("hasFinishedNSE")) {
            setNPCName(npcName);
            addTextToTextboxQueue("You removed the virus from everyone? You saved our world.");
            addTextToTextboxQueue("Here is the shard you needed.");
            addTextToTextboxQueue("I've heard around that another shard crashed through the\nroof of the library when that earthquake happened.");
            addTextToTextboxQueue("Maybe go speak to the librarian about it.");
        } else {
            addTextToTextboxQueue("I've heard around that another shard crashed through the\nroof of the library when that earthquake happened.");
            addTextToTextboxQueue("Maybe go speak to the librarian about it.");
            addTextToTextboxQueue("Thank you again for saving our world.");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (isFlagSet("hasFinishedOMJ") && !isFlagSet("hasTalkedToNSE")) {
            if (sequence < 4) {
                sequence++;
            } else if (sequence == 4) {
                setFlag("hasTalkedToNSE");
                sequence++;
            }
        }

        if (isFlagSet("w1CuredAllNPCs") && !isFlagSet("hasFinishedNSE")) {
            setFlag("hasFinishedNSE");
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasFinishedOMJ")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
            return ScriptState.COMPLETED;
        }
        if (!isFlagSet("hasTalkedToNSE")) {
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
