package Scripts.WorldOneMap;

import Combatants.W1Combatant4;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

//infected NPC 4 script
public class W1Infected4Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDONE);
        setNPCName("NPC 4");
        showTextbox();
        entity.facePlayer(player);
        String[] selections = { "RUN VIRUS SCAN", "LEAVE" };
        String[] answers = { "11111KILL111111111111111111", "Get out of here!" };

        if (isFlagSet("hasTalkedToNSE")) {
            if (!isFlagSet("w1CuredNPC4") && !isFlagSet("w1Btl4")) {
                setNPCName("NPC 4");
                addTextToTextboxQueue("I'm a little busy right now...", selections, answers);
            } else if (!isFlagSet("w1CuredNPC4") && isFlagSet("w1Btl4")) {
                setNPCName("SYSTEM");
                addTextToTextboxQueue("VIRUS DETECTED!");
                SoundPlayer.playMusic(MusicTracks.BATTLETHEME);
                map.initiateCombat(player, new W1Combatant4(entity, map));
            } else if (isFlagSet("w1CuredNPC4") && !isFlagSet("w1Btl4")) {
                setNPCName("NPC 4");
                addTextToTextboxQueue("The virus is gone!");
                addTextToTextboxQueue("You're my hero!");
            } else if (isFlagSet("w1CuredNPC4") && isFlagSet("w1Btl4")) {
                setNPCName("System");
                addTextToTextboxQueue("game error: unexpected flagstate");
                addTextToTextboxQueue("isFlagSet(\"w1CuredNPC4\") && isFlagSet(\"w1Btl4\")");
            }
        } else {
            addTextToTextboxQueue("What are you looking at?...");
        }
    }

    @Override
    protected void cleanup() {
        if (isFlagSet("hasTalkedToNSE")) {
            if (!isFlagSet("w1CuredNPC4") && !isFlagSet("w1Btl4")) {
                if (this.getChoice() == 0) {
                    setFlag("w1Btl4");
                } else {
                    hideTextbox();
                    unlockPlayer();
                }
            } else if (!isFlagSet("w1CuredNPC4") && (isFlagSet("w1Btl4"))) {
                setFlag("w1CuredNPC4");
                unsetFlag("w1Btl4");
            } else if (isFlagSet("w1CuredNPC4") && isFlagSet("w1Btl4")) {
                unsetFlag("w1Btl4");
                hideTextbox();
                unlockPlayer();
            } else if (isFlagSet("w1CuredNPC4") && !isFlagSet("w1Btl4")) {
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
            if (!isFlagSet("w1CuredNPC4")) {
                start();
                if (!isTextboxQueueEmpty()) {
                    return ScriptState.RUNNING;
                }
                end();
            }
            if (isFlagSet("w1Btl4")) {
                start();
                if (!isTextboxQueueEmpty() || map.inCombat()) {
                    return ScriptState.RUNNING;
                }
                end();
            }
            if (isFlagSet("w1CuredNPC4")) {
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
        return ScriptState.COMPLETED;
    }
}