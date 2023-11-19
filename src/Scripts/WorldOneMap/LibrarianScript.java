package Scripts.WorldOneMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to walrus npc
public class LibrarianScript extends Script<NPC> {

    private int sequence = 0;
    // private int responseNum = -1;

    @Override
    protected void setup() {
        setFlag("hasCuredAllNPCs");
        lockPlayer();

        setTextboxStyle(Style.WORLDONE);
        showTextbox();

        String npcName = "Librarian";
        String playerName = "T";

        String[] selections = {"RUN VIRUS SCAN", "LEAVE"};
        String[] answers = {"NO VIRUS DETECTED", "Please come again once the library is back in top shape!"};

        entity.facePlayer(player);
        if (isFlagSet("hasRanVirusScanLibrarian")) {
            addTextToTextboxQueue( "Please come back once everything is fixed.");
        } else if (!isFlagSet("hasCuredAllNPCs")) {
            setNPCName(npcName);
            addTextToTextboxQueue("Sorry, the library is currently closed right now due to\ntraumatic events.", selections, answers);
        } else if (!isFlagSet("hasTalkedToLibrarian") && isFlagSet("hasCuredAllNPCs")) {
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
            //set flag
        } else if (isFlagSet("hasTalkedToLibrarian")) {
            setNPCName(npcName);
            addTextToTextboxQueue("How are we going to afford these repairs...");
        } else if (isFlagSet("hasFoundLibraryShard")) {
            setNPCName(npcName);
            addTextToTextboxQueue("You were able to find it and safely remove it? Good job!");
        } else {
            addTextToTextboxQueue("Please come again once the library is more presentable.");
        }
    }


    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (!isFlagSet("hasCuredAllNPCs")) {
            setFlag("hasRanVirusScanLibrarian");
        }
        if (!isFlagSet("hasTalkedToLibrarian")) {
            if (sequence == 0) {
                sequence++;
            } else if (sequence == 1) {
                sequence++;
            } else if (sequence == 2) {
                sequence++;
            } else if (sequence == 3) {
                sequence++;
            } else if (sequence == 4) {
                setFlag("hasTalkedToLibrarian");
                sequence++;
            }
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasCuredAllNPCs")) {
            start();
            if (isTextboxQueueEmpty()) {
                end();
                return ScriptState.COMPLETED;
            }
            return ScriptState.RUNNING;
        } else if (isFlagSet("hasRanVirusScanLibrarian")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
            return ScriptState.COMPLETED;
        } else if (!isFlagSet("hasTalkedToLibrarian") && isFlagSet("hasCuredAllNPCs")) {
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
                }
            } else if (sequence == 3) {
                start();
                if (isTextboxQueueEmpty()) {
                    end();
                }
            } else if (sequence == 4) {
                start();
                if (isTextboxQueueEmpty()) {
                    end();
                    return ScriptState.COMPLETED;
                }
            }
            return ScriptState.RUNNING;
        } else if (isFlagSet("hasTalkedToLibrarian")) {
            start();
            if (isTextboxQueueEmpty()) {
                end();
                return ScriptState.COMPLETED;
            }
            return ScriptState.RUNNING;
        } else if (isFlagSet("hasFoundLibraryShard")) {
            start();
            if (isTextboxQueueEmpty()) {
                end();
                return ScriptState.COMPLETED;
            }
            return ScriptState.RUNNING;
        } else {
            start();
            if (isTextboxQueueEmpty()) {
                end();
                return ScriptState.COMPLETED;
            }
            return ScriptState.RUNNING;
        }
        // return ScriptState.COMPLETED;
    }
}
