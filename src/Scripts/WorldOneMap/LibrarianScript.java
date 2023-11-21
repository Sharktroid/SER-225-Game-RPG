package Scripts.WorldOneMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to walrus npc
public class LibrarianScript extends Script<NPC> {

    private int sequence = 0;

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDONE);
        showTextbox();

        String npcName = "Librarian";
        String playerName = "T";

        String[] selections = { "RUN VIRUS SCAN", "LEAVE" };
        String[] answers = { "NO VIRUS DETECTED", "Please come again once the library is back in top shape!" };

        entity.facePlayer(player);

        if (!isFlagSet("hasTalkedToNSE")) {
            setNPCName(npcName);
            addTextToTextboxQueue("Sorry, the library is currently closed right now due to\ntraumatic events.");

        } else if (!isFlagSet("scannedLibrarian")) {
            setNPCName(npcName);
            addTextToTextboxQueue("Sorry, the library is currently closed right now due to\ntraumatic events.",
                    selections, answers);
            System.out.println(this.getChoice());
        } else if (!isFlagSet("hasFinishedNSE")) {
            setNPCName(npcName);
            addTextToTextboxQueue("Thanks for checking to see if I was infected.");
            addTextToTextboxQueue("Please come again once this virus is taken care of.");
        } else if (!isFlagSet("hasTalkedToLibrarian")) {
            if (sequence == 0) {
                setNPCName(npcName);
                addTextToTextboxQueue("Hello!");
            } else if (sequence == 1) {
                setNPCName(playerName);
                addTextToTextboxQueue("I heard something about an object going through the\nroof?");
            } else if (sequence == 2) {
                setNPCName(npcName);
                addTextToTextboxQueue("Yes! An earthquake struck so I ran out of the building.");
                addTextToTextboxQueue("Then, a purple object flew through the second floor\nwindows!");
                addTextToTextboxQueue("I haven't gone back inside out of fear...");
            } else if (sequence == 3) {
                setNPCName(playerName);
                addTextToTextboxQueue("I need that object. Do you mind if I go inside?");
            } else if (sequence == 4) {
                setNPCName(npcName);
                addTextToTextboxQueue("Go right ahead!");
            }
        } else if (!isFlagSet("w1FoundFrag3")) {
            setNPCName(npcName);
            addTextToTextboxQueue("How are we going to afford these repairs...");
        } else if (isFlagSet("w1FoundFrag3")) {
            setNPCName(npcName);
            addTextToTextboxQueue("You were able to find it and safely remove it? Good job!");
        } else if (isFlagSet("hasFinishedLib")) {
            setNPCName(npcName);
            addTextToTextboxQueue("Please come again once the library is more presentable.");
        }

    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();

        if (isFlagSet("hasTalkedToNSE") && !isFlagSet("scannedLibrarian")) {
            if (this.getChoice() == 0) {
                setFlag("scannedLibrarian");
            }

        }
        if (isFlagSet("scannedLibrarian") && isFlagSet("hasFinishedNSE") && !isFlagSet("hasTalkedToLibrarian")) {
            if (sequence < 4) {
                sequence++;
            } else if (sequence == 4) {
                setFlag("hasTalkedToLibrarian");
                sequence++;
            }
        }else if (isFlagSet("w1FoundFrag3")){
            setFlag("hasFinishedLib");
        }

    }

    @Override
    public ScriptState execute() {
        if (isFlagSet("scannedLibrarian") && isFlagSet("hasFinishedNSE") && !isFlagSet("hasTalkedToLibrarian")) {
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