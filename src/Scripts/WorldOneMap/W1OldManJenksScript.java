package Scripts.WorldOneMap;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Game.SoundPlayer.SoundEffects;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;
import Utils.Direction;

// old man
public class W1OldManJenksScript extends Script<NPC> {

    private int sequence = 0;
    private int sequence2 = 0;
    private int miniSequence = 0;
    private int amountMoved = 0;
    private int responseNum = -1;

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDONE);
        setNPCName("Old Man Jenkins");
        // showTextbox();

        String[] selections = { "Where am I?", "Have you seen a purple\norb around here?" };
        String[] answersBefore = { "A can til yo if yo can fin ma den-ers", "A can til yo if yo can fin ma den-ers" };
        String[] answers = { "This is the town of Bliss.", "Purple orb?" };

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
        } else if (!isFlagSet("hasTalkedToOMJ") && isFlagSet("hasFoundDentures")) {
            showTextbox();
            if (sequence == 0) {
                addTextToTextboxQueue("Hewlo?", selections, answersBefore);
            } else if (sequence == 1) {
                setNPCName("T");
                addTextToTextboxQueue("Dentures? Find your dentures? You mean these?");
            }
        } else if (isFlagSet("hasTalkedToOMJ") && !isFlagSet("hasFoundDentures")) {
            showTextbox();
            addTextToTextboxQueue("Yo hav-nt fin em yet? Go!");
        } else if (isFlagSet("hasTalkedToOMJ") && isFlagSet("hasFoundDentures")) {
            if (sequence2 == 0) {
                showTextbox();
                addTextToTextboxQueue("Yuh fand dem!");
            } else if (sequence2 == 1) {
                showTextbox();
                addTextToTextboxQueue("Ahh thats better. Thank you!", selections, answers);
            } else if (sequence2 == 2) {
                showTextbox();
                System.out.println("responseNum: " + responseNum);
                SoundPlayer.playMusic(MusicTracks.DIALOGUE);
                addTextToTextboxQueue("Well, it was bliss before a huge earthquake hit and this\nportal opened up.");
                addTextToTextboxQueue("What's your purpose for coming here?");
            } else if (sequence2 == 3) {
                showTextbox();
                setNPCName("T");
                addTextToTextboxQueue("I'm trying to find a purple orb. Have you seen it?");
            } else if (sequence2 == 4) {
                showTextbox();
                setNPCName("Old Man Jenkins");
                addTextToTextboxQueue(answers[1]);
            } else if (sequence2 == 5) {
                showTextbox();
                setNPCName("Old Man Jenkins");
                addTextToTextboxQueue("It might not be an orb, but I did find this purple shard on\nthe ground.");
                addTextToTextboxQueue("It was right near the portal you came out of.");
                addTextToTextboxQueue("Since you were so kind to find me my dentures, I can\ngive it to you.");
            } else if (sequence2 == 6) {
                showTextbox();
                setNPCName("System");
                SoundPlayer.playSoundEffect(SoundEffects.ITEMGET);
                addTextToTextboxQueue("You got a Shard Fragment!");
            } else if (sequence2 == 7) {
                showTextbox();
                setNPCName("Old Man Jenkins");
                addTextToTextboxQueue(
                        "Now... before you venture off, just beware that people\nare starting to act weird around here...");
            } else if (sequence2 == 8) {
                showTextbox();
                setNPCName("T");
                addTextToTextboxQueue("Strange? What do you mean?");
            } else if (sequence2 == 9) {
                showTextbox();
                setNPCName("Old Man Jenkins");
                addTextToTextboxQueue("I have to head inside before they get me too...");
            } else if (sequence2 == 10) {
                entity.stand(Direction.LEFT);
                amountMoved = 0;
            } else if (sequence2 == 11) {
                amountMoved = 0;
            } else if (sequence2 == 12) {
                amountMoved = 0;
            } else if (sequence2 == 13) {
                amountMoved = 0;
            } else if (sequence2 == 14) {
                amountMoved = 0;
            }

        }

    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
        if (!isFlagSet("hasTalkedToOMJ") && !isFlagSet("hasFoundDentures")) {
            if (sequence == 2) {
                setFlag("hasTalkedToOMJ");
            }
            sequence++;

        } else if (!isFlagSet("hasTalkedToOMJ") && isFlagSet("hasFoundDentures")) {
            if (sequence == 1) {
                setFlag("hasTalkedToOMJ");
            }
            sequence++;
            
        } else if (isFlagSet("hasTalkedToOMJ") && isFlagSet("hasFoundDentures")) {
        
            if(sequence2 == 0){
                sequence2++;
            }else if (sequence2 == 1) {
                if (getChoice() == 0 ){
                    sequence2++;
                }else{
                    sequence2=5;
                }
            }
            else if (sequence2 < 14) {
                sequence2++;
            } else if (sequence2 == 14) {
                entity.setIsHidden(true);
                setFlag("hasFinishedOMJ");
                sequence2++;
                SoundPlayer.playMusic(MusicTracks.WORLDONE);
            }
        }

    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasTalkedToOMJ") && !isFlagSet("hasFoundDentures")) {
            start();
            System.out.println("sequence: " + sequence);
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
            if (sequence > 2) {
                return ScriptState.COMPLETED;
            } else {
                return ScriptState.RUNNING;
            }
        } else if (isFlagSet("hasTalkedToOMJ") && !isFlagSet("hasFoundDentures")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        } else if (!isFlagSet("hasTalkedToOMJ") && isFlagSet("hasFoundDentures")) {
            start();
            System.out.println("sequence: " + sequence);
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
                return ScriptState.RUNNING;
            
        } else if (isFlagSet("hasTalkedToOMJ") && isFlagSet("hasFoundDentures")) {
            if (sequence2 < 10) {
                start();
                if (!isTextboxQueueEmpty()) {
                    return ScriptState.RUNNING;
                }
                end();
            } else if (sequence2 == 10) {
                start();
                entity.walk(Direction.DOWN, 2);
                amountMoved += 2;
                if (amountMoved == 40) {
                    end();
                }
            } else if (sequence2 == 11) {
                start();
                entity.walk(Direction.LEFT, 2);
                amountMoved += 2;
                if (amountMoved == 140) {
                    end();
                }
            } else if (sequence2 == 12) {
                start();
                entity.walk(Direction.DOWN, 2);
                amountMoved += 2;
                if (amountMoved == 120) {
                    end();
                }
            } else if (sequence2 == 13) {
                start();
                entity.walk(Direction.LEFT, 2);
                amountMoved += 2;
                if (amountMoved == 206) {
                    end();
                }
            } else if (sequence2 == 14) {
                start();
                entity.walk(Direction.UP, 2);
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
