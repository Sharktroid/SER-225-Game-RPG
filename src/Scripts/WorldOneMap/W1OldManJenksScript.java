package Scripts.WorldOneMap;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;
import Utils.Direction;

// old man
public class W1OldManJenksScript extends Script<NPC> {

    private int sequence = 0;
    private int miniSequence = 0;
    private int responseNum = -1;
    private int amountMoved = 0;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDONE);
        setNPCName("Old Man Jenkins");
        // showTextbox();

        String[] selections = {"Where am I?", "Have you seen a purple\norb around here?"};
        String[] answersBefore = {"A can til yo if yo can fin ma den-ers","A can til yo if yo can fin ma den-ers"};
        String[] answers = {"This is Bliss.", "Purple orb?"};

        entity.facePlayer(player);
        if (!isFlagSet("hasTalkedToOMJ") && !isFlagSet("hasFoundDentures")) {
            showTextbox();
            if (sequence == 0) {
                addTextToTextboxQueue("Hewlo?", selections, answersBefore);
            } else if (sequence == 1) {
               setNPCName("T");
               addTextToTextboxQueue("Dentures? Find your dentures?");
            } else if (sequence == 2) {
                setNPCName("Old Man Jenkins");
                addTextToTextboxQueue("Yeh! Den-ers!");
            }
        }else if (isFlagSet("hasTalkedToOMJ") && !isFlagSet("hasFoundDentures")){
            showTextbox();
            addTextToTextboxQueue("Yo hav-nt fin em yet? Go!");
        } else if (isFlagSet("hasFoundDentures")) {
            if (sequence == 0) {
                showTextbox();
                addTextToTextboxQueue("You found them! Thank you!", selections, answers);
            } else if (sequence == 1) {
                SoundPlayer.playMusic(MusicTracks.DIALOGUE);
                showTextbox();
                if (responseNum == 0) {
                    if (miniSequence == 0) {
                        showTextbox();
                        addTextToTextboxQueue("Well, it was bliss before a huge earthquake hit and this\nportal opened up.");
                        addTextToTextboxQueue("What's your purpose for coming here?");
                    } else if (miniSequence == 1) {
                        showTextbox();
                        setNPCName("T");
                        addTextToTextboxQueue("I'm trying to find a purple orb. Have you seen it?");
                    } else if (miniSequence == 2) {
                        showTextbox();
                        setNPCName("Old Man Jenkins");
                        addTextToTextboxQueue(answers[1]);
                    }
                }
                addTextToTextboxQueue("It might not be an orb, but I did find this purple shard on\nthe ground.");
                addTextToTextboxQueue("It was right near the portal you came out of.");
                addTextToTextboxQueue("Since you were so kind to find me my dentures, I can\ngive it to you.");
            } else if (sequence == 2) {
                showTextbox();
                setNPCName("T");
                addTextToTextboxQueue("Thank you, sir!");
            } else if (sequence == 3) {
                showTextbox();
                setNPCName("Old Man Jenkins");
                addTextToTextboxQueue("Now... before you venture off, just beware that people\nare starting to act weird around here...");
            } else if (sequence == 4) {
                showTextbox();
                setNPCName("T");
                addTextToTextboxQueue("Strange? What do you mean?");
            } else if (sequence == 5) {
                showTextbox();
                setNPCName("Old Man Jenkins");
                addTextToTextboxQueue("I have to head inside before they get me too...");
            } else if (sequence == 6) {
                entity.stand(Direction.LEFT);
                amountMoved = 0;
            } else if (sequence == 7) {
                amountMoved = 0;
            } else if (sequence == 8) {
                amountMoved = 0;
            } else if (sequence == 9) {
                amountMoved = 0;
            } else if (sequence == 10) {
                amountMoved = 0;
            }

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
                sequence=0;
            }
        }
        else if(isFlagSet("hasTalkedToOMJ") && !isFlagSet("hasFoundDentures")){
        //nothing
        } else if (isFlagSet("hasFoundDentures")) {
            if (sequence == 0) {
                responseNum = getChoice();
                System.out.println(responseNum);
                sequence++;
            } else if (sequence == 1) {
                if (miniSequence == 0) {
                    miniSequence++;
                    System.out.println("mini 0");
                } else if (miniSequence == 1) {
                    miniSequence++;
                    System.out.println("mini 1");
                } else if (miniSequence == 2) {
                    miniSequence++;
                    System.out.println("mini 2");
                }
                sequence++;
            } else if (sequence == 2) {
                sequence++;
            } else if (sequence == 3) {
                sequence++;
            } else if (sequence == 4) {
                sequence++;
            } else if (sequence == 5) {
                hideTextbox();
                sequence++;
            } else if (sequence == 6) {
                sequence++;
            } else if (sequence == 7) {
                sequence++;
            } else if (sequence == 8) {
                sequence++;
            } else if (sequence == 9) {
                sequence++;
            } else if (sequence == 10) {
                entity.setIsHidden(true);
                setFlag("hasFinishedOMJ");
                sequence++;
                SoundPlayer.playMusic(MusicTracks.WORLDONE);
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
        }else if (isFlagSet("hasTalkedToOMJ") && !isFlagSet("hasFoundDentures")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
            return ScriptState.COMPLETED;
        } else if (isFlagSet("hasFoundDentures")) {
            if (sequence == 0) {
                start();
                if (isTextboxQueueEmpty()) {
                    end();
                }
            } else if (sequence == 1) {
                if (miniSequence == 0) {
                    start();
                    if (isTextboxQueueEmpty()) {
                        end();
                    }
                } else if (miniSequence == 1) {
                    start();
                    if (isTextboxQueueEmpty()) {
                        end();
                    }
                } else if (miniSequence == 2) {
                    start();
                    if (isTextboxQueueEmpty()) {
                        end();
                    }
                }
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
                }
            } else if (sequence == 6) {
                start();
                entity.walk(Direction.DOWN,2);
                amountMoved += 2;
                if (amountMoved == 40) {
                    end();
                }
            } else if (sequence == 7) {
                start();
                entity.walk(Direction.LEFT,2);
                amountMoved += 2;
                if (amountMoved == 140) {
                    end();
                }
            } else if (sequence == 8) {
                start();
                entity.walk(Direction.DOWN,2);
                amountMoved += 2;
                if (amountMoved == 120) {
                    end();
                }
            } else if (sequence == 9) {
                start();
                entity.walk(Direction.LEFT,2);
                amountMoved += 2;
                if (amountMoved == 206) {
                    end();
                }
            } else if (sequence == 10) {
                start();
                entity.walk(Direction.UP,2);
                amountMoved += 2;
                if (amountMoved == 100) {
                    end();
                }
            }
            return ScriptState.RUNNING;
        }
        return ScriptState.COMPLETED;
    }
}
