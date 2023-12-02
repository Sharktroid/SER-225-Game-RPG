package Scripts.WorldThreeMap;

import Combatants.W3Combatant;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// infected world 3 floor 1 npc 2 script
public class W3F3WhiteTieScript extends Script<NPC> {

    private String npcName = "Employee";

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDTHREE);
        setNPCName(npcName);
        showTextbox();
        entity.facePlayer(player);
        String[] selections = { "RUN VIRUS SCAN", "LEAVE" };
        String[] answers = { "GET AWAY FROM ME!", "..." };

        if (!isFlagSet("w3f3CuredNPC4") && !isFlagSet("w3f3Btl4")) {
            setNPCName(npcName);
            addTextToTextboxQueue("Please go away.", selections, answers);
        } else if (!isFlagSet("w3f3CuredNPC4") && isFlagSet("w3f3Btl4")) {
            setNPCName("SYSTEM");
            addTextToTextboxQueue("VIRUS DETECTED!");
            SoundPlayer.playMusic(MusicTracks.BATTLE);
            map.initiateCombat(player, new W3Combatant(entity, map));
        } else if (isFlagSet("w3f3CuredNPC4") && isFlagSet("w3f3Btl4")) {
            SoundPlayer.playMusic(MusicTracks.WORLDTHREE);
            setNPCName(npcName);
            addTextToTextboxQueue("I feel better...");
        } else if (isFlagSet("w3f3CuredNPC4") && !isFlagSet("w3f3Btl4")) {
            setNPCName(npcName);
            addTextToTextboxQueue("I need to get back to work.");
        }
    }

    @Override
    protected void cleanup() {

        if (!isFlagSet("w3f3CuredNPC4") && !isFlagSet("w3f3Btl4")) {
            if (this.getChoice() == 0) {
                setFlag("w3f3Btl4");
            } else {
                hideTextbox();
                unlockPlayer();
            }
        } else if (!isFlagSet("w3f3CuredNPC4") && (isFlagSet("w3f3Btl4"))) {
            setFlag("w3f3CuredNPC4");
        } else if (isFlagSet("w3f3CuredNPC4") && isFlagSet("w3f3Btl4")) {
            unsetFlag("w3f3Btl4");
            hideTextbox();
            unlockPlayer();
        } else if (isFlagSet("w3f3CuredNPC4") && !isFlagSet("w3f3Btl4")) {
            hideTextbox();
            unlockPlayer();
        } else {
            hideTextbox();
            unlockPlayer();
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("w3f3CuredNPC4")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        if (isFlagSet("w3f3Btl4")) {
            start();
            if (!isTextboxQueueEmpty() || map.inCombat()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        if (isFlagSet("w3f3CuredNPC4")) {
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