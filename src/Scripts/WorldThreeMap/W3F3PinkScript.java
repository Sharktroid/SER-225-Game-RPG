package Scripts.WorldThreeMap;

import Combatants.W3Combatant;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// infected world 3 floor 1 npc 2 script
public class W3F3PinkScript extends Script<NPC> {

    private String npcName = "Employee";

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDTHREE);
        setNPCName(npcName);
        showTextbox();
        entity.facePlayer(player);
        String[] selections = { "RUN VIRUS SCAN", "LEAVE" };
        String[] answers = { "Get out of here!", "..." };

        if (!isFlagSet("w3f3CuredNPC2") && !isFlagSet("w3f3Btl2")) {
            setNPCName(npcName);
            addTextToTextboxQueue("Who are you?", selections, answers);
        } else if (!isFlagSet("w3f3CuredNPC2") && isFlagSet("w3f3Btl2")) {
            setNPCName("SYSTEM");
            addTextToTextboxQueue("VIRUS DETECTED!");
            SoundPlayer.playMusic(MusicTracks.BATTLE);
            map.initiateCombat(player, new W3Combatant(entity, map));
        } else if (isFlagSet("w3f3CuredNPC2") && isFlagSet("w3f3Btl2")) {
            SoundPlayer.playMusic(MusicTracks.WORLDTHREE);
            setNPCName(npcName);
            addTextToTextboxQueue("I'm cured! Thank you!");
        } else if (isFlagSet("w3f3CuredNPC2") && !isFlagSet("w3f3Btl2")) {
            setNPCName(npcName);
            addTextToTextboxQueue("I need a break now.");
        }
    }

    @Override
    protected void cleanup() {

        if (!isFlagSet("w3f3CuredNPC2") && !isFlagSet("w3f3Btl2")) {
            if (this.getChoice() == 0) {
                setFlag("w3f3Btl2");
            } else {
                hideTextbox();
                unlockPlayer();
            }
        } else if (!isFlagSet("w3f3CuredNPC2") && (isFlagSet("w3f3Btl2"))) {
            setFlag("w3f3CuredNPC2");
        } else if (isFlagSet("w3f3CuredNPC2") && isFlagSet("w3f3Btl2")) {
            unsetFlag("w3f3Btl2");
            hideTextbox();
            unlockPlayer();
        } else if (isFlagSet("w3f3CuredNPC2") && !isFlagSet("w3f3Btl2")) {
            hideTextbox();
            unlockPlayer();
        } else {
            hideTextbox();
            unlockPlayer();
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("w3f3CuredNPC2")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        if (isFlagSet("w3f3Btl2")) {
            start();
            if (!isTextboxQueueEmpty() || map.inCombat()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        if (isFlagSet("w3f3CuredNPC2")) {
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