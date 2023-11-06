package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Firefox;
import Scripts.HubMap.FirefoxScript;
import Scripts.HubMap.hubMsgScript;
import Scripts.HubMap.portalOneScript;
import Scripts.HubMap.portalTwoScript;
import Scripts.HubMap.portalThreeScript;
import Scripts.HubMap.unlockPortalOneScript;
import Scripts.HubMap.unlockPortalTwoScript;
import Scripts.HubMap.unlockPortalThreeScript;
import Tilesets.CommonTileset;

public class HubMap extends Map {

    public HubMap() {
        super("hub_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(9, 7).getLocation();

    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

        Firefox firefox = new Firefox(1, getMapTile(6, 7).getLocation());
        firefox.setInteractScript(new FirefoxScript());
        npcs.add(firefox);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(108, 60, 24, 24, new portalOneScript()));
        triggers.add(new Trigger(396, 60, 24, 24, new portalTwoScript()));
        triggers.add(new Trigger(684, 60, 24, 24, new portalThreeScript()));
        triggers.add(new Trigger(384, 336, 48, 48, new hubMsgScript(),"sawHubMsg"));

        triggers.add(new Trigger(108,496,24,24, new unlockPortalOneScript(), "portalOneUnlocked"));//temp to unlock portal 1
        triggers.add(new Trigger(396,496,24,24, new unlockPortalTwoScript(), "portalTwoUnlocked"));//temp to unlock portal 2
        triggers.add(new Trigger(684,496,24,24, new unlockPortalThreeScript(), "portalThreeUnlocked"));//temp to unlock portal 3
        
        return triggers;
    }    
    
    public static boolean unlockPortalOneFlagState(){
        return unlockPortalOneScript.setUnlockPortalOneFlagState;
    }

    public static boolean unlockPortalTwoFlagState(){
        return unlockPortalTwoScript.setUnlockPortalTwoFlagState;
    }

    public static boolean unlockPortalThreeFlagState(){
        return unlockPortalThreeScript.setUnlockPortalThreeFlagState;
    }
    

}
