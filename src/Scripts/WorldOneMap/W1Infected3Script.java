package Scripts.WorldOneMap;

import Combatants.W1Combatant3;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

//infected npc 3 script
public class W1Infected3Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDONE);
        setNPCName("NPC 3");
        showTextbox();
        entity.facePlayer(player);
        String[] selections = { "RUN VIRUS SCAN", "LEAVE" };
        String[] answers = { "Y0U'R3 F1N1Sh3D!", "Bye." };

        if (isFlagSet("hasTalkedToNSE")) {
            if (!isFlagSet("w1CuredNPC3") && !isFlagSet("w1Btl3")) {
                setNPCName("NPC 3");
                addTextToTextboxQueue("Virus? What Virus?", selections, answers);
            } else if (!isFlagSet("w1CuredNPC3") && isFlagSet("w1Btl3")) {
                setNPCName("SYSTEM");
                addTextToTextboxQueue("VIRUS DETECTED!");
                SoundPlayer.playMusic(MusicTracks.BATTLE);
                map.initiateCombat(player, new W1Combatant3(entity, map));
            } else if (isFlagSet("w1CuredNPC3") && !isFlagSet("w1Btl3")) {
                setNPCName("NPC 3");
                addTextToTextboxQueue("Woah, what happened? I was infected?");
                addTextToTextboxQueue("Thanks for helping!");
            } else if (isFlagSet("w1CuredNPC3") && isFlagSet("w1Btl3")) {
                setNPCName("System");
                addTextToTextboxQueue("game error: unexpected flagstate");
                addTextToTextboxQueue("isFlagSet(\"w1CuredNPC3\") && isFlagSet(\"w1Btl3\")");
            }
        } else {
            addTextToTextboxQueue("I'm not suspicious at all...");
        }
    }

    @Override
    protected void cleanup() {
        if (isFlagSet("hasTalkedToNSE")) {
            if (!isFlagSet("w1CuredNPC3") && !isFlagSet("w1Btl3")) {
                if (this.getChoice() == 0) {
                    setFlag("w1Btl3");
                } else {
                    hideTextbox();
                    unlockPlayer();
                }
            } else if (!isFlagSet("w1CuredNPC3") && (isFlagSet("w1Btl3"))) {
                setFlag("w1CuredNPC3");
                unsetFlag("w1Btl3");
            } else if (isFlagSet("w1CuredNPC3") && isFlagSet("w1Btl3")) {
                unsetFlag("w1Btl3");
                hideTextbox();
                unlockPlayer();
            } else if (isFlagSet("w1CuredNPC3") && !isFlagSet("w1Btl3")) {
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
            if (!isFlagSet("w1CuredNPC3")) {
                start();
                if (!isTextboxQueueEmpty()) {
                    return ScriptState.RUNNING;
                }
                end();
            }
            if (isFlagSet("w1Btl3")) {
                start();
                if (!isTextboxQueueEmpty() || map.inCombat()) {
                    return ScriptState.RUNNING;
                }
                end();
            }
            if (isFlagSet("w1CuredNPC3")) {
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