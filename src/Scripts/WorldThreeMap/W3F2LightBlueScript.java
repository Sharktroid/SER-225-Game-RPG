package Scripts.WorldThreeMap;

import Combatants.W3Combatant;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// infected world 3 floor 1 npc 2 script
public class W3F2LightBlueScript extends Script<NPC> {

    private String npcName = "Employee";

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDTHREE);
        setNPCName(npcName);
        showTextbox();
        entity.facePlayer(player);
        String[] selections = { "RUN VIRUS SCAN", "LEAVE" };
        String[] answers = {"What are you doing?", "..." };

        if (!isFlagSet("w3f2CuredNPC1") && !isFlagSet("w3f2Btl1")) {
            setNPCName(npcName);
            addTextToTextboxQueue("I'm too tired for this...", selections, answers);
        } else if (!isFlagSet("w3f2CuredNPC1") && isFlagSet("w3f2Btl1")) {
            setNPCName("SYSTEM");
            addTextToTextboxQueue("VIRUS DETECTED!");
            SoundPlayer.playMusic(MusicTracks.BATTLE);
            map.initiateCombat(player, new W3Combatant(entity, map));
        } else if (isFlagSet("w3f2CuredNPC1") && isFlagSet("w3f2Btl1")) {
            SoundPlayer.playMusic(MusicTracks.WORLDTHREE);
            setNPCName(npcName);
            addTextToTextboxQueue("I was infected? Oh...");
        } else if (isFlagSet("w3f2CuredNPC1") && !isFlagSet("w3f2Btl1")) {
            setNPCName(npcName);
            addTextToTextboxQueue("I need to go home...");
        }
    }

    @Override
    protected void cleanup() {

        if (!isFlagSet("w3f2CuredNPC1") && !isFlagSet("w3f2Btl1")) {
            if (this.getChoice() == 0) {
                setFlag("w3f2Btl1");
            } else {
                hideTextbox();
                unlockPlayer();
            }
        } else if (!isFlagSet("w3f2CuredNPC1") && (isFlagSet("w3f2Btl1"))) {
            setFlag("w3f2CuredNPC1");
        } else if (isFlagSet("w3f2CuredNPC1") && isFlagSet("w3f2Btl1")) {
            unsetFlag("w3f2Btl1");
            hideTextbox();
            unlockPlayer();
        } else if (isFlagSet("w3f2CuredNPC1") && !isFlagSet("w3f2Btl1")) {
            hideTextbox();
            unlockPlayer();
        } else {
            hideTextbox();
            unlockPlayer();
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("w3f2CuredNPC1")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        if (isFlagSet("w3f2Btl1")) {
            start();
            if (!isTextboxQueueEmpty() || map.inCombat()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        if (isFlagSet("w3f2CuredNPC1")) {
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