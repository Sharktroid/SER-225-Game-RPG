package Maps;

import EnhancedMapTiles.ItemMapObject;
import Items.Denture;
import Items.Fragment;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.W2Pitbull;
import NPCs.Testfox;
import NPCs.W2DJ;
import NPCs.W2PB1;
import NPCs.W2PB2;
import NPCs.W2PB3;
import NPCs.W2PB4;
import NPCs.W2PB5;
import Scripts.BasicFragmentScript;
import Scripts.WorldOneMap.TestfoxScript;
import Scripts.WorldOneMap.W1DentureScript;
import Scripts.WorldOneMap.w1Frag3Script;
import Scripts.WorldTwoMap.W2DJScript;
import Scripts.WorldTwoMap.W2PB1Script;
import Scripts.WorldTwoMap.W2PB2Script;
import Scripts.WorldTwoMap.W2PB3Script;
import Scripts.WorldTwoMap.W2PB4Script;
import Scripts.WorldTwoMap.W2PB5Script;
import Scripts.WorldTwoMap.W2PitbullScript;
import Tilesets.SafariTileset;

import java.util.ArrayList;
//To load: start program -> press 5 key


public class EvanTestMap extends Map {
    public EvanTestMap() {
        super("evan_map.txt", new SafariTileset(), 0);
        this.playerStartPosition = getMapTile(0, 0).getLocation();
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        ItemMapObject fragment = new ItemMapObject(getMapTile(6, 3).getLocation(), new Fragment(null));
        fragment.setInteractScript(new BasicFragmentScript());
        enhancedMapTiles.add(fragment);

        ItemMapObject denture = new ItemMapObject(getMapTile(13, 8).getLocation(), new Denture(null));
        denture.setInteractScript(new W1DentureScript());
        enhancedMapTiles.add(denture);

        return enhancedMapTiles;
    }
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

        Testfox testfox = new Testfox(1, getMapTile(11, 6).getLocation().subtractY(40));
        testfox.setInteractScript(new TestfoxScript());
        npcs.add(testfox);

        W2DJ W2DJ = new W2DJ(2, getMapTile(9, 3).getLocation().subtractY(40));
        W2DJ.setInteractScript(new W2DJScript());
        npcs.add(W2DJ);

        W2Pitbull Pitbull = new W2Pitbull(3, getMapTile(9, 6).getLocation().subtractY(40));
        Pitbull.setInteractScript(new W2PitbullScript());
        npcs.add(Pitbull);

        W2PB1 W2PB1 = new W2PB1(4, getMapTile(6, 3).getLocation().subtractY(40));
        W2PB1.setInteractScript(new W2PB1Script());
        npcs.add(W2PB1);

        W2PB2 W2PB2 = new W2PB2(5, getMapTile(8, 3).getLocation().subtractY(40));
        W2PB2.setInteractScript(new W2PB2Script());
        npcs.add(W2PB2);

        W2PB3 W2PB3 = new W2PB3(6, getMapTile(6, 5).getLocation().subtractY(40));
        W2PB3.setInteractScript(new W2PB3Script());
        npcs.add(W2PB3);

        W2PB4 W2PB4 = new W2PB4(7, getMapTile(3, 5).getLocation().subtractY(40));
        W2PB4.setInteractScript(new W2PB4Script());
        npcs.add(W2PB4);

        W2PB5 W2PB5 = new W2PB5(5, getMapTile(10, 3).getLocation().subtractY(40));
        W2PB5.setInteractScript(new W2PB5Script());
        npcs.add(W2PB5);





        return npcs;
    }
    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        //triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(), "hasLostBall"));
        return triggers;
    }

}



