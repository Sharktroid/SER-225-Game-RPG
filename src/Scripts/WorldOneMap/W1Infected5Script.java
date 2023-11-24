package Scripts.WorldOneMap;

import Combatants.W1Combatant5;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

//infected NPC 5 script
public class W1Infected5Script extends Script<NPC> {

    private String npcName = "User 5";

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDONE);
        setNPCName(npcName);
        showTextbox();
        entity.facePlayer(player);
        String[] selections = { "RUN VIRUS SCAN", "LEAVE" };
        String[] answers = { "##IN1TI4TING V1Ru5 S3QU3Nc3##", "I've seen salad that dresses better than you." };

        if (isFlagSet("hasTalkedToNSE")) {
            if (!isFlagSet("w1CuredNPC5") && !isFlagSet("w1Btl5")) {
                setNPCName(npcName);
                addTextToTextboxQueue("You really need to brush your teeth.", selections, answers);
            } else if (!isFlagSet("w1CuredNPC5") && isFlagSet("w1Btl5")) {
                setNPCName("SYSTEM");
                addTextToTextboxQueue("VIRUS DETECTED!");
                SoundPlayer.playMusic(MusicTracks.BATTLE);
                map.initiateCombat(player, new W1Combatant5(entity, map));
            } else if (isFlagSet("w1CuredNPC5") && !isFlagSet("w1Btl5")) {
                SoundPlayer.playMusic(MusicTracks.WORLDONE);
                setNPCName(npcName);
                addTextToTextboxQueue("I'm not infected anymore!");
                addTextToTextboxQueue("Thank you so much for helping me.");
            } else if (isFlagSet("w1CuredNPC5") && isFlagSet("w1Btl5")) {
                setNPCName("System");
                addTextToTextboxQueue("game error: unexpected flagstate");
                addTextToTextboxQueue("isFlagSet(\"w1CuredNPC5\") && isFlagSet(\"w1Btl5\")");
            }
        } else {
            addTextToTextboxQueue("Don't talk to me.");
        }
    }

    @Override
    protected void cleanup() {
        if (isFlagSet("hasTalkedToNSE")) {
            if (!isFlagSet("w1CuredNPC5") && !isFlagSet("w1Btl5")) {
                if (this.getChoice() == 0) {
                    setFlag("w1Btl5");
                } else {
                    hideTextbox();
                    unlockPlayer();
                }
            } else if (!isFlagSet("w1CuredNPC5") && (isFlagSet("w1Btl5"))) {
                setFlag("w1CuredNPC5");
                unsetFlag("w1Btl5");
            } else if (isFlagSet("w1CuredNPC5") && isFlagSet("w1Btl5")) {
                unsetFlag("w1Btl5");
                hideTextbox();
                unlockPlayer();
            } else if (isFlagSet("w1CuredNPC5") && !isFlagSet("w1Btl5")) {
                hideTextbox();
                unlockPlayer();
            }
        } else {
            hideTextbox();
            unlockPlayer();
        }
    }

    @Override
    public ScriptState execute() {
        if (isFlagSet("hasTalkedToNSE")) {
            if (!isFlagSet("w1CuredNPC5")) {
                start();
                if (!isTextboxQueueEmpty()) {
                    return ScriptState.RUNNING;
                }
                end();
            }
            if (isFlagSet("w1Btl5")) {
                start();
                if (!isTextboxQueueEmpty() || map.inCombat()) {
                    return ScriptState.RUNNING;
                }
                end();
            }
            if (isFlagSet("w1CuredNPC5")) {
                start();
                if (!isTextboxQueueEmpty()) {
                    return ScriptState.RUNNING;
                }
                end();
                return ScriptState.COMPLETED;
            }
        } else {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        SoundPlayer.playMusic(MusicTracks.WORLDONE);
        return ScriptState.COMPLETED;
    }
}