package Scripts.WorldOneMap;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;
// import Builders.FrameBuilder;
// import Builders.MapTileBuilder;
// import GameObject.Frame;
import Utils.Direction;
// import Utils.Point;

// old man
public class OldManJenksScript extends Script<NPC> {

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

        String[] selections = { "Where am I?", "Have you seen a purple\norb around here?" };
        String[] answersBefore = { "A can til yo if yo can fin ma den-ers", "A can til yo if yo can fin ma den-ers" };
        String[] answers = { "This is Bliss.", "Purple orb?" };

        entity.facePlayer(player);

        if (!isFlagSet("hasTalkedToOMJ") && !isFlagSet("hasFoundDentures")) {
            addTextToTextboxQueue("Hewlo?", selections, answersBefore);
        } else if (isFlagSet("hasFoundDentures")) {
            addTextToTextboxQueue("You found them! Thank you!", selections, answers);
        } else {
            addTextToTextboxQueue("Yo hav-nt fin em yet? Go!");
        }

    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        if (!isFlagSet("hasTalkedToOMJ") && !isFlagSet("hasFoundDentures")) {
            setFlag("hasTalkedToOMJ");
            setFlag("hasFoundDentures"); // get rid later
            sequence = 0;
        } else if (isFlagSet("hasFoundDentures")) {
            entity.setIsHidden(true);
            setFlag("hasFinishedOMJ");
            SoundPlayer.playMusic(MusicTracks.WORLDONE);
        }

    }

    @Override
    public ScriptState execute() {
        start();
        if (!isFlagSet("hasTalkedToOMJ")) {
            if (sequence == 0) {
                if (isTextboxQueueEmpty()) {
                    setNPCName("T");
                    addTextToTextboxQueue("Dentures? Find your dentures?");
                    sequence++;
                }
            } else if (sequence == 1) {
                if (isTextboxQueueEmpty()) {
                    setNPCName("Old Man Jenkins");
                    addTextToTextboxQueue("Yeh! Den-ers!");
                    sequence++;
                }
            } else if (sequence == 2) {
                if (isTextboxQueueEmpty()) {
                    end();
                    return ScriptState.COMPLETED;
                }
            }
        } else if (isFlagSet("hasFoundDentures")) {
            if (sequence == 0) {
                if (isTextboxQueueEmpty()) {
                    miniSequence = getChoice();
                    sequence++;
                    SoundPlayer.playMusic(MusicTracks.DIALOGUE);
                    if (miniSequence == 0) {
                        setNPCName("Old Man Jenkins");
                        addTextToTextboxQueue(
                                "Well, it was bliss before a huge earthquake hit and this\nportal opened up.");
                        addTextToTextboxQueue("What's your purpose for coming here?");
                        miniSequence++;
                    }
                }
            } else if (sequence == 1) {
                if (isTextboxQueueEmpty()) {
                    if (miniSequence == 1) {
                        setNPCName("T");
                        addTextToTextboxQueue("I'm trying to find a purple orb. Have you seen it?");
                        miniSequence++;
                    } else if (miniSequence == 2) {
                        setNPCName("Old Man Jenkins");
                        addTextToTextboxQueue(
                                "It might not be an orb, but I did find this purple shard on\nthe ground.");
                        addTextToTextboxQueue("It was right near the portal you came out of.");
                        addTextToTextboxQueue("Since you were so kind to find me my dentures, I can\ngive it to you.");
                        miniSequence++;
                    } else {
                        setNPCName("T");
                        addTextToTextboxQueue("Thank you, sir!");
                        sequence++;
                    }
                }
            } else if (sequence == 2) {
                start();
                if (isTextboxQueueEmpty()) {
                    setNPCName("T");
                    addTextToTextboxQueue("Strange? What do you mean?");
                    sequence++;
                }
            } else if (sequence == 3) {
                start();
                if (isTextboxQueueEmpty()) {
                    setNPCName("Old Man Jenkins");
                    addTextToTextboxQueue(
                            "Now... before you venture off, just beware that people\nare starting to act weird around here...");
                    sequence++;
                }
            } else if (sequence == 4) {
                start();
                if (isTextboxQueueEmpty()) {
                    setNPCName("Old Man Jenkins");
                    addTextToTextboxQueue("I have to head inside before they get me too...");
                    sequence++;
                }
            } else if (sequence == 5) {
                start();
                if (isTextboxQueueEmpty()) {
                    hideTextbox();
                    sequence++;
                    entity.stand(Direction.LEFT);
                }
            } else if (sequence == 6) {
                entity.walk(Direction.DOWN, 2);
                amountMoved += 2;
                if (amountMoved == 40) {
                    amountMoved = 0;
                    sequence++;
                }
            } else if (sequence == 7) {
                entity.walk(Direction.LEFT, 2);
                amountMoved += 2;
                if (amountMoved == 140) {
                    amountMoved = 0;
                    sequence++;
                }
            } else if (sequence == 8) {
                entity.walk(Direction.DOWN, 2);
                amountMoved += 2;
                if (amountMoved == 120) {
                    amountMoved = 0;
                    sequence++;
                }
            } else if (sequence == 9) {
                entity.walk(Direction.LEFT, 2);
                amountMoved += 2;
                if (amountMoved == 206) {
                    amountMoved = 0;
                    sequence++;
                }
            } else if (sequence == 10) {
                entity.walk(Direction.UP, 2);
                amountMoved += 2;
                if (amountMoved == 100) {
                    end();
                    return ScriptState.COMPLETED;
                }
            }
        }
        return ScriptState.RUNNING;
    }
}
