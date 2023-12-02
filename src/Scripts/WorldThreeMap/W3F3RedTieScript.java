package Scripts.WorldThreeMap;

import Combatants.W3Combatant;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// infected world 3 floor 1 npc 2 script
public class W3F3RedTieScript extends Script<NPC> {

    private String npcName = "Employee";

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDTHREE);
        setNPCName(npcName);
        showTextbox();
        entity.facePlayer(player);
        String[] selections = { "RUN VIRUS SCAN", "LEAVE" };
        String[] answers = { "Why are you not moving?", "..." };

        if (!isFlagSet("w3f3CuredNPC3") && !isFlagSet("w3f3Btl3")) {
            setNPCName(npcName);
            addTextToTextboxQueue("Are you the new intern? Go get me a coffee.", selections, answers);
        } else if (!isFlagSet("w3f3CuredNPC3") && isFlagSet("w3f3Btl3")) {
            setNPCName("SYSTEM");
            addTextToTextboxQueue("VIRUS DETECTED!");
            SoundPlayer.playMusic(MusicTracks.BATTLE);
            map.initiateCombat(player, new W3Combatant(entity, map));
        } else if (isFlagSet("w3f3CuredNPC3") && isFlagSet("w3f3Btl3")) {
            SoundPlayer.playMusic(MusicTracks.WORLDTHREE);
            setNPCName(npcName);
            addTextToTextboxQueue("You helped me. Thanks...");
        } else if (isFlagSet("w3f3CuredNPC3") && !isFlagSet("w3f3Btl3")) {
            setNPCName(npcName);
            addTextToTextboxQueue("I need coffee...");
        }
    }

    @Override
    protected void cleanup() {

        if (!isFlagSet("w3f3CuredNPC3") && !isFlagSet("w3f3Btl3")) {
            if (this.getChoice() == 0) {
                setFlag("w3f3Btl3");
            } else {
                hideTextbox();
                unlockPlayer();
            }
        } else if (!isFlagSet("w3f3CuredNPC3") && (isFlagSet("w3f3Btl3"))) {
            setFlag("w3f3CuredNPC3");
        } else if (isFlagSet("w3f3CuredNPC3") && isFlagSet("w3f3Btl3")) {
            unsetFlag("w3f3Btl3");
            hideTextbox();
            unlockPlayer();
        } else if (isFlagSet("w3f3CuredNPC3") && !isFlagSet("w3f3Btl3")) {
            hideTextbox();
            unlockPlayer();
        } else {
            hideTextbox();
            unlockPlayer();
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("w3f3CuredNPC3")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        if (isFlagSet("w3f3Btl3")) {
            start();
            if (!isTextboxQueueEmpty() || map.inCombat()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        if (isFlagSet("w3f3CuredNPC3")) {
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