package Scripts.WorldOneMap;

import java.util.ArrayList;

import Combatants.PlayerCombatant;
import Combatants.SoulConsumingFlame;
import Level.Combatant;
import Level.NPC;
import Level.Script;
import Level.ScriptState;

// script for talking to redpanda npc
public class SoulConsumingFlameScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        ArrayList<Combatant> combatants = new ArrayList<Combatant>();
        combatants.add(new PlayerCombatant(player, map, Combatant.ControlType.HUMAN));
        combatants.add(new SoulConsumingFlame(entity, map));
        map.initiateCombat(player, new ArrayList<Combatant>(combatants));
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




