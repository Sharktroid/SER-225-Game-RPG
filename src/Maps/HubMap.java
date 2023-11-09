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
import Tilesets.CommonTileset;
import Tilesets.HubTileset;


public class HubMap extends Map {

    public static boolean[] unlockPortalFS = new boolean[] { false, false, false };
    public static boolean[] talkedToFirefoxFS = new boolean[] { false, false, false, false };

    public HubMap() {
        super("hub_map.txt", new HubTileset());
        this.playerStartPosition = getMapTile(1, 1).getLocation();
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

        Firefox firefox = new Firefox(1, getMapTile(5, 7).getLocation());
        firefox.setInteractScript(new FirefoxScript());
        npcs.add(firefox);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(204, 108, 24, 24, new portalOneScript()));
        triggers.add(new Trigger(396, 108, 24, 24, new portalTwoScript()));
        triggers.add(new Trigger(588, 108, 24, 24, new portalThreeScript()));
        //triggers.add(new Trigger(384, 336, 48, 48, new hubMsgScript(),"sawHubMsg"));        

        return triggers;
    }    

    public static boolean getHubMsgFS(){
        return hubMsgScript.setSawHubMsgFS;
    }

    public static boolean getTalkedFS(int talkedIndex){
        boolean talkedFS = talkedToFirefoxFS[talkedIndex];
        return talkedFS;
    }

    public static void setTalkedFS(int talkedIndex, boolean newFS){
        talkedToFirefoxFS[talkedIndex] = newFS;
    }

    public static boolean getUnlockedPortalFS(int portalNum){
        boolean portalFS = unlockPortalFS[portalNum-1];
        return portalFS;
    }
    
    public static void setUnlockPortalFS(int portalNum, boolean newFS){
        unlockPortalFS[portalNum-1] = newFS;
    }

}
