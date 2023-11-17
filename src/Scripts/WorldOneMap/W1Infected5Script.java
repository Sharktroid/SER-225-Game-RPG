package Scripts.WorldOneMap;

import Combatants.W1Combatant5;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

//infected NPC 5 script
public class W1Infected5Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDONE);
        setNPCName("NPC 5");
        showTextbox();
        entity.facePlayer(player);
        String[] selections = { "RUN VIRUS SCAN", "LEAVE" };
        String[] answers = { "##IN1TI4TING V1Ru5 S3QU3Nc3##", "I've seen salad that dresses better than you." };

        if (isFlagSet("hasTalkedToNSE")) {
            if (!isFlagSet("w1CuredNPC5") && !isFlagSet("w1Btl5")) {
                setNPCName("NPC 5");
                addTextToTextboxQueue("You really need to brush your teeth.", selections, answers);
            } else if (!isFlagSet("w1CuredNPC5") && isFlagSet("w1Btl5")) {
                setNPCName("SYSTEM");
                addTextToTextboxQueue("VIRUS DETECTED!");
                SoundPlayer.playMusic(MusicTracks.BATTLETHEME);
                map.initiateCombat(player, new W1Combatant5(entity, map));
            } else if (isFlagSet("w1CuredNPC5") && !isFlagSet("w1Btl5")) {
                setNPCName("NPC 5");
                addTextToTextboxQueue("I'm not infected anymore!");
                addTextToTextboxQueue("Thank you so much for helping me.");
            } else if (isFlagSet("w1CuredNPC5") && isFlagSet("w1Btl5")) {
                setNPCName("System");
                addTextToTextboxQueue("game error: unexpected flagstate");
                addTextToTextboxQueue("isFlagSet(\"w1CuredNPC5\") && isFlagSet(\"w1Btl5\")");
            }
        } else {
            addTextToTextboxQueue("Don't talk to me.");
        }
    }

    @Override
    protected void cleanup() {
        if (isFlagSet("hasTalkedToNSE")) {
            if (!isFlagSet("w1CuredNPC5") && !isFlagSet("w1Btl5")) {
                if (this.getChoice() == 0) {
                    setFlag("w1Btl5");
                } else {
                    hideTextbox();
                    unlockPlayer();
                }
            } else if (!isFlagSet("w1CuredNPC5") && (isFlagSet("w1Btl5"))) {
                setFlag("w1CuredNPC5");
                unsetFlag("w1Btl5");
            } else if (isFlagSet("w1CuredNPC5") && isFlagSet("w1Btl5")) {
                unsetFlag("w1Btl5");
                hideTextbox();
                unlockPlayer();
            } else if (isFlagSet("w1CuredNPC5") && !isFlagSet("w1Btl5")) {
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
            if (!isFlagSet("w1CuredNPC5")) {
                start();
                if (!isTextboxQueueEmpty()) {
                    return ScriptState.RUNNING;
                }
                end();
            }
            if (isFlagSet("w1Btl5")) {
                start();
                if (!isTextboxQueueEmpty() || map.inCombat()) {
                    return ScriptState.RUNNING;
                }
                end();
            }
            if (isFlagSet("w1CuredNPC5")) {
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


/*package Scripts.WorldOneMap;

import Combatants.W1Combatant5;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// infected npc 5 script
public class W1Infected5Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDONE);
        setNPCName("Infected NPC 5");
        showTextbox();
        entity.facePlayer(player);

        String[] selections = { "RUN VIRUS SCAN", "LEAVE" };
        String[] answers = { "VIRUS DETECTED", "Yeah, go away." };

        if (!isFlagSet("hasTalkedToNSE")) { // if not talked to nse yet
            addTextToTextboxQueue("What are you looking at?...");
        } else if (!isFlagSet("w1CuredNPC5")) { // if not cured infected npc 5 yet
            if (!isFlagSet("w1Btl5")) { // if not in battle
                addTextToTextboxQueue("Woah... Why are you getting so close to me?", selections, answers); // chose to scan or leave
            } else if (isFlagSet("w1Btl5")) { // if in battle
                addTextToTextboxQueue("Y0U'LL N3V3R D3F3AT M3");
                SoundPlayer.playMusic(MusicTracks.BATTLETHEME); // change to battle music
                map.initiateCombat(player, new W1Combatant5(entity, map)); // start battle with infected npc 5
            }
        } else if (isFlagSet("w1CuredNPC5")) { // if infected npc cured
            addTextToTextboxQueue("Woah what happened?");
            addTextToTextboxQueue("I'm not infected anymore!");
            addTextToTextboxQueue("Thank you so much for helping me.");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        if (!isFlagSet("hasTalkedToNSE")) { //if not talked to nse
            unlockPlayer();
            hideTextbox();
        } else if (!isFlagSet("w1CuredNPC5")) { //if not cured infected npc 5
            if (!isFlagSet("w1Btl5")) { //if not in battle 
                if (this.getChoice() == 0) {   // if choseto battle
                    setFlag("w1Btl5");  //set battle flag
                } else {
                    hideTextbox();
                }
            } else if (isFlagSet("w1Btl5")) { //if in battle 
                setFlag("w1CuredNPC5"); //set cured infected npc 5 flag
                unsetFlag("w1Btl5");    // unset in battle flag
                SoundPlayer.playMusic(MusicTracks.WORLDONEBGM); //set music back to normal
            }
        } else if (isFlagSet("w1CuredNPC5")) { //if infected npc cured 
            hideTextbox();
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasTalkedToNSE")) { //if not talked to nse yet
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
            return ScriptState.COMPLETED;
        } else if (!isFlagSet("w1CuredNPC5")) { //if infected npc 5 not cured yet
            start();
            if (!isTextboxQueueEmpty() || map.inCombat()) {
                return ScriptState.RUNNING; //this execution will continue into battle
            }
            end();
        } else {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
            return ScriptState.COMPLETED;
        }
        return ScriptState.RUNNING;
    }
}
*/