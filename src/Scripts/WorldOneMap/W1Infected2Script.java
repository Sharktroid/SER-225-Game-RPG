package Scripts.WorldOneMap;

import Combatants.W1Combatant2;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

//infected NPC 2 script
public class W1Infected2Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDONE);
        setNPCName("NPC 2");
        showTextbox();
        entity.facePlayer(player);
        String[] selections = { "RUN VIRUS SCAN", "LEAVE" };
        String[] answers = { "1'M G0ING T0 D3STR0y Y0U!", "Leave me alone." };

        if (isFlagSet("hasTalkedToNSE")) {
            if (!isFlagSet("w1CuredNPC2") && !isFlagSet("w1Btl2")) {
                setNPCName("NPC 2");
                addTextToTextboxQueue("Would you let me stand here in peace?", selections, answers);
            } else if (!isFlagSet("w1CuredNPC2") && isFlagSet("w1Btl2")) {
                setNPCName("SYSTEM");
                addTextToTextboxQueue("VIRUS DETECTED!");
                SoundPlayer.playMusic(MusicTracks.BATTLETHEME);
                map.initiateCombat(player, new W1Combatant2(entity, map));
            } else if (isFlagSet("w1CuredNPC2") && !isFlagSet("w1Btl2")) {
                setNPCName("NPC 2");
                addTextToTextboxQueue("You helped me get rid of the virus!");
                addTextToTextboxQueue("Thank you!");
            } else if (isFlagSet("w1CuredNPC2") && isFlagSet("w1Btl2")) {
                setNPCName("System");
                addTextToTextboxQueue("game error: unexpected flagstate");
                addTextToTextboxQueue("isFlagSet(\"w1CuredNPC2\") && isFlagSet(\"w1Btl2\")");
            }
        } else {
            addTextToTextboxQueue("Can you bother someone else?");
        }
    }

    @Override
    protected void cleanup() {
        if (isFlagSet("hasTalkedToNSE")) {
            if (!isFlagSet("w1CuredNPC2") && !isFlagSet("w1Btl2")) {
                if (this.getChoice() == 0) {
                    setFlag("w1Btl2");
                } else {
                    hideTextbox();
                    unlockPlayer();
                }
            } else if (!isFlagSet("w1CuredNPC2") && (isFlagSet("w1Btl2"))) {
                setFlag("w1CuredNPC2");
                unsetFlag("w1Btl2");
            } else if (isFlagSet("w1CuredNPC2") && isFlagSet("w1Btl2")) {
                unsetFlag("w1Btl2");
                hideTextbox();
                unlockPlayer();
            } else if (isFlagSet("w1CuredNPC2") && !isFlagSet("w1Btl2")) {
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
            if (!isFlagSet("w1CuredNPC2")) {
                start();
                if (!isTextboxQueueEmpty()) {
                    return ScriptState.RUNNING;
                }
                end();
            }
            if (isFlagSet("w1Btl2")) {
                start();
                if (!isTextboxQueueEmpty() || map.inCombat()) {
                    return ScriptState.RUNNING;
                }
                end();
            }
            if (isFlagSet("w1CuredNPC2")) {
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