package Scripts.WorldOneMap;

import Combatants.SoulConsumingFlame;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.NPC;
import Level.Player;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to redpanda npc
public class SoulConsumingFlameNPCScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        SoundPlayer.playMusic(MusicTracks.BATTLE);
        setTextboxStyle(Style.WORLDONE);
        map.initiateCombat(player, new SoulConsumingFlame(entity, map));
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        SoundPlayer.playMusic(MusicTracks.WORLDONE);
    }

    @Override
    public void setPlayer(Player player) {
        super.setPlayer(player);
    }

    @Override
    public ScriptState execute() {
        start();
        if (!isTextboxQueueEmpty() || map.inCombat()) {
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }
}




