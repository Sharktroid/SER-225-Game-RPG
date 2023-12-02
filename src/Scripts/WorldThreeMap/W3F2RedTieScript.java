package Scripts.WorldThreeMap;

import Combatants.W3Combatant;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// infected world 3 floor 1 npc 2 script
public class W3F2RedTieScript extends Script<NPC> {

    private String npcName = "Employee";

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDTHREE);
        setNPCName(npcName);
        showTextbox();
        entity.facePlayer(player);
        String[] selections = { "RUN VIRUS SCAN", "LEAVE" };
        String[] answers = { "I'm trying to get work done right now!", "..." };

        if (!isFlagSet("w3f2CuredNPC3") && !isFlagSet("w3f2Btl3")) {
            setNPCName(npcName);
            addTextToTextboxQueue("Don't bother me.", selections, answers);
        } else if (!isFlagSet("w3f2CuredNPC3") && isFlagSet("w3f2Btl3")) {
            setNPCName("SYSTEM");
            addTextToTextboxQueue("VIRUS DETECTED!");
            SoundPlayer.playMusic(MusicTracks.BATTLE);
            map.initiateCombat(player, new W3Combatant(entity, map));
        } else if (isFlagSet("w3f2CuredNPC3") && isFlagSet("w3f2Btl3")) {
            SoundPlayer.playMusic(MusicTracks.WORLDTHREE);
            setNPCName(npcName);
            addTextToTextboxQueue("Was I just being mind controlled?");
        } else if (isFlagSet("w3f2CuredNPC3") && !isFlagSet("w3f2Btl3")) {
            setNPCName(npcName);
            addTextToTextboxQueue("Thanks for helping.");
        }
    }

    @Override
    protected void cleanup() {

        if (!isFlagSet("w3f2CuredNPC3") && !isFlagSet("w3f2Btl3")) {
            if (this.getChoice() == 0) {
                setFlag("w3f2Btl3");
            } else {
                hideTextbox();
                unlockPlayer();
            }
        } else if (!isFlagSet("w3f2CuredNPC3") && (isFlagSet("w3f2Btl3"))) {
            setFlag("w3f2CuredNPC3");
        } else if (isFlagSet("w3f2CuredNPC3") && isFlagSet("w3f2Btl3")) {
            unsetFlag("w3f2Btl3");
            hideTextbox();
            unlockPlayer();
        } else if (isFlagSet("w3f2CuredNPC3") && !isFlagSet("w3f2Btl3")) {
            hideTextbox();
            unlockPlayer();
        } else {
            hideTextbox();
            unlockPlayer();
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("w3f2CuredNPC3")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        if (isFlagSet("w3f2Btl3")) {
            start();
            if (!isTextboxQueueEmpty() || map.inCombat()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        if (isFlagSet("w3f2CuredNPC3")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
            return ScriptState.COMPLETED;
        }

        SoundPlayer.playMusic(MusicTracks.WORLDTHREE);
        return ScriptState.COMPLETED;
    }
}