package Scripts.WorldThreeMap;

import Combatants.W3Combatant;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// infected world 3 floor 1 npc 2 script
public class W3F3BlueTieScript extends Script<NPC> {

    private String npcName = "Employee";

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDTHREE);
        setNPCName(npcName);
        showTextbox();
        entity.facePlayer(player);
        String[] selections = { "RUN VIRUS SCAN", "LEAVE" };
        String[] answers = { "fight start msg", "leave without fighting msg" };

        if (!isFlagSet("w3f3CuredNPC1") && !isFlagSet("w3f3Btl1")) {
            setNPCName(npcName);
            addTextToTextboxQueue("battle query", selections, answers);
        } else if (!isFlagSet("w3f3CuredNPC1") && isFlagSet("w3f3Btl1")) {
            setNPCName("SYSTEM");
            addTextToTextboxQueue("VIRUS DETECTED!");
            SoundPlayer.playMusic(MusicTracks.BATTLE);
            map.initiateCombat(player, new W3Combatant(entity, map));
        } else if (isFlagSet("w3f3CuredNPC1") && isFlagSet("w3f3Btl1")) {
            SoundPlayer.playMusic(MusicTracks.WORLDTHREE);
            setNPCName(npcName);
            addTextToTextboxQueue("virus clear msg");
        } else if (isFlagSet("w3f3CuredNPC1") && !isFlagSet("w3f3Btl1")) {
            setNPCName(npcName);
            addTextToTextboxQueue("virus cleareded msg");
        }
    }

    @Override
    protected void cleanup() {

        if (!isFlagSet("w3f3CuredNPC1") && !isFlagSet("w3f3Btl1")) {
            if (this.getChoice() == 0) {
                setFlag("w3f3Btl1");
            } else {
                hideTextbox();
                unlockPlayer();
            }
        } else if (!isFlagSet("w3f3CuredNPC1") && (isFlagSet("w3f3Btl1"))) {
            setFlag("w3f3CuredNPC1");
        } else if (isFlagSet("w3f3CuredNPC1") && isFlagSet("w3f3Btl1")) {
            unsetFlag("w3f3Btl1");
            hideTextbox();
            unlockPlayer();
        } else if (isFlagSet("w3f3CuredNPC1") && !isFlagSet("w3f3Btl1")) {
            hideTextbox();
            unlockPlayer();
        } else {
            hideTextbox();
            unlockPlayer();
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("w3f3CuredNPC1")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        if (isFlagSet("w3f3Btl1")) {
            start();
            if (!isTextboxQueueEmpty() || map.inCombat()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        if (isFlagSet("w3f3CuredNPC1")) {
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