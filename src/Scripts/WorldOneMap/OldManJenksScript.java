package Scripts.WorldOneMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// old man
public class OldManJenksScript extends Script<NPC> {

    private int sequence = 0;
    private int miniSequence = 0;
    private int responseNum = -1;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDONE);
        setNPCName("Old Man Jenkins");
        showTextbox();

        String[] selections = {"Where am I?", "Have you seen a purple\norb around here?"};
        String[] answersBefore = {"A can til yo if yo can fin ma den-ers","A can til yo if yo can fin ma den-ers"};
        String[] answers = {"This is Bliss. Well, it was bliss before a huge earthquake hit and this portal opened up.", "Purple orb?"};

        entity.facePlayer(player);
        if (!isFlagSet("hasTalkedToOMJ") && !isFlagSet("hasFoundDentures")) {
            if (sequence == 0) {
                addTextToTextboxQueue("Hewlo?", selections, answersBefore);
            } else if (sequence == 1) {
               setNPCName("T");
               addTextToTextboxQueue("Dentures? Find your dentures?");
            } else if (sequence == 2) {
                setNPCName("Old Man Jenkins");
                addTextToTextboxQueue("Yeh! Den-ers!");
            }
        } else if (isFlagSet("hasFoundDentures")) {
            if (sequence == 0) {
                addTextToTextboxQueue("You found them! Thank you!", selections, answers);
            } else if (sequence == 1) {
                if (responseNum == 0) {
                    if (miniSequence == 0) {
                        addTextToTextboxQueue("Well, it was bliss before a huge earthquake hit and this portal opened up.");
                        addTextToTextboxQueue("What's your purpose for coming here?");
                    } else if (miniSequence == 1) {
                        setNPCName("T");
                        addTextToTextboxQueue("I'm trying to find a purple orb. Have you seen it?");
                    } else if (miniSequence == 2) {
                        setNPCName("Old Man Jenkins");
                        addTextToTextboxQueue(answers[1]);
                    }
                }
                addTextToTextboxQueue("It might not be an orb, but I did find this purple shard on the ground.");
                addTextToTextboxQueue("It was right near the portal you came out of.");
                addTextToTextboxQueue("Since you were so kind to find me my dentures, I can give it to you.");
            } else if (sequence == 2) {
                setNPCName("T");
                addTextToTextboxQueue("Thank you, sir!");
            } else if (sequence == 3) {
                setNPCName("Old Man Jenkins");
                addTextToTextboxQueue("Now... before you venture off, just beware that people are starting to act weird around here...");
            } else if (sequence == 4) {
                setNPCName("T");
                addTextToTextboxQueue("Strange? What do you mean?");
            } else if (sequence == 5) {
                setNPCName("Old Man Jenkins");
                addTextToTextboxQueue("I have to head inside before they get me too...");
            }
        } else {
            addTextToTextboxQueue("Yo hav-nt fin em yet? Go!");
        }
    }




    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if ((!isFlagSet("hasTalkedToOMJ") && !isFlagSet("hasFoundDentures"))) {
            if (sequence == 0) {
                sequence++;
            } else if (sequence == 1) {
                sequence++;
            } else if (sequence == 2) {
                setFlag("hasTalkedToOMJ");
                sequence++;
            }
        } else if (isFlagSet("hasFoundDentures")) {
            if (sequence == 0) {
                responseNum = getChoice();
                sequence++;
            } else if (sequence == 1) {
                if (miniSequence == 0) {
                    miniSequence++;
                } else if (miniSequence == 1) {
                    miniSequence++;
                } else if (miniSequence == 2) {
                    miniSequence++;
                }
                sequence++;
            } else if (sequence == 2) {
                sequence++;
            } else if (sequence == 3) {
                sequence++;
            } else if (sequence == 4) {
                sequence++;
            } else if (sequence == 5) {
                sequence++;
            }
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasTalkedToOMJ")) {
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
        } else if (isFlagSet("hasFoundDentures")) {
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
                }
            } else if (sequence == 5) {
                start();
                if (isTextboxQueueEmpty()) {
                    end();
                    return ScriptState.COMPLETED;
                }
            }
            return ScriptState.RUNNING;
        }
        return ScriptState.COMPLETED;
    }
}
