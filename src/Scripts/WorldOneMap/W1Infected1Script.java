package Scripts.WorldOneMap;

import Combatants.W1Combatant1;
import Combatants.W1Combatant2;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import NPCs.W1Infected1;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;
import Utils.Direction;
import GameObject.AnimatedSprite;

//infected npc 1 script
public class W1Infected1Script extends Script<NPC> {

    private String npcName = "User 1";
    public Direction facing;

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDONE);
        setNPCName(npcName);
        showTextbox();
        entity.facePlayer(player);
        String[] selections = { "RUN VIRUS SCAN", "LEAVE" };
        String[] answers = { "Y0U'll N3v3R D3F4T M3!", "Yeah, go away." };

        if (isFlagSet("hasTalkedToNSE")) {
            if (!isFlagSet("w1CuredNPC1") && !isFlagSet("w1Btl1")) {
                setNPCName(npcName);
                addTextToTextboxQueue("Woah... Why are you getting so close to me?", selections, answers);
            } else if (!isFlagSet("w1CuredNPC1") && isFlagSet("w1Btl1")) {
                setNPCName("SYSTEM");
                addTextToTextboxQueue("VIRUS DETECTED!");
                SoundPlayer.playMusic(MusicTracks.BATTLE);
                map.initiateCombat(player, new W1Combatant1(entity, map));
            } else if (isFlagSet("w1CuredNPC1") && !isFlagSet("w1Btl1")) {
                SoundPlayer.playMusic(MusicTracks.WORLDONE);
                setNPCName(npcName);
                addTextToTextboxQueue("I'm not infected anymore!");
                addTextToTextboxQueue("Thank you so much for helping me.");
            } else if (isFlagSet("w1CuredNPC1") && isFlagSet("w1Btl1")) {
                setNPCName("System");
                addTextToTextboxQueue("game error: unexpected flagstate");
                addTextToTextboxQueue("isFlagSet(\"w1CuredNPC1\") && isFlagSet(\"w1Btl1\")");
            }
        } else {
            addTextToTextboxQueue("What are you looking at?...");
        }
    }

    @Override
    protected void cleanup() {
        if (isFlagSet("hasTalkedToNSE")) {
            if (!isFlagSet("w1CuredNPC1") && !isFlagSet("w1Btl1")) {
                if (this.getChoice() == 0) {
                    setFlag("w1Btl1");
                    setFlag("changeSprite");
                } else {
                    hideTextbox();
                    unlockPlayer();
                }
            } else if (!isFlagSet("w1CuredNPC1") && (isFlagSet("w1Btl1"))) {
                setFlag("w1CuredNPC1");
                unsetFlag("w1Btl1");
                unsetFlag("changeSprite");
            } else if (isFlagSet("w1CuredNPC1") && isFlagSet("w1Btl1")) {
                unsetFlag("w1Btl1");
                hideTextbox();
                unlockPlayer();
            } else if (isFlagSet("w1CuredNPC1") && !isFlagSet("w1Btl1")) {
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
            if (!isFlagSet("w1CuredNPC1")) {
                start();
                if (!isTextboxQueueEmpty()) {
                    return ScriptState.RUNNING;
                }
                end();
            }
            if (isFlagSet("w1Btl1")) {
                start();
                if (!isTextboxQueueEmpty() || map.inCombat()) {
                    return ScriptState.RUNNING;
                }
                end();
            }
            if (isFlagSet("w1CuredNPC1")) {
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
