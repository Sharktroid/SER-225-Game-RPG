package Scripts.WorldOneMap;

import Combatants.W1Combatant1;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

//infected npc 1 script
public class W1Infected1Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDONE);
        setNPCName("NPC 1");
        showTextbox();
        entity.facePlayer(player);
        String[] selections = { "RUN VIRUS SCAN", "LEAVE" };
        String[] answers = { "Y0U'll N3v3R D3F4T M3!", "Yeah, go away." };

        if (isFlagSet("hasTalkedToNSE")) {
            if (!isFlagSet("w1CuredNPC1") && !isFlagSet("w1Btl1")) {
                setNPCName("NPC 1");
                addTextToTextboxQueue("Woah... Why are you getting so close to me?", selections, answers);
            } else if (!isFlagSet("w1CuredNPC1") && isFlagSet("w1Btl1")) {
                setNPCName("SYSTEM");
                addTextToTextboxQueue("VIRUS DETECTED!");
                SoundPlayer.playMusic(MusicTracks.BATTLE);
                map.initiateCombat(player, new W1Combatant1(entity, map));
            } else if (isFlagSet("w1CuredNPC1") && !isFlagSet("w1Btl1")) {
                setNPCName("NPC 1");
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
                } else {
                    hideTextbox();
                    unlockPlayer();
                }
            } else if (!isFlagSet("w1CuredNPC1") && (isFlagSet("w1Btl1"))) {
                setFlag("w1CuredNPC1");
                unsetFlag("w1Btl1");
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
