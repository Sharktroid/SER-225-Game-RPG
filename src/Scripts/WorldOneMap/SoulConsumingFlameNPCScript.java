package Scripts.WorldOneMap;

import java.util.ArrayList;

import Combatants.PlayerCombatant;
import Combatants.SoulConsumingFlame;
import Level.Combatant;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to redpanda npc
public class SoulConsumingFlameNPCScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.WORLDONE);
        ArrayList<Combatant> combatants = new ArrayList<Combatant>();
        map.initiateCombat(player, new SoulConsumingFlame(entity, map));
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
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




