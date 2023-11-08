package Maps;

import EnhancedMapTiles.FragmentObject;
import EnhancedMapTiles.MedkitObject;
import EnhancedMapTiles.PushableRock;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Testfox;
import Scripts.WorldOneMap.TestfoxScript;
import Tilesets.CommonTileset;
import Tilesets.InternetExplorerTileset;
import java.util.ArrayList;
//To load: start program -> press 5 key


public class EvanTestMap extends Map {
    public EvanTestMap() {
        super("evan_map.txt", new InternetExplorerTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        FragmentObject fragment = new FragmentObject(getMapTile(6, 3).getLocation(), 1);
        enhancedMapTiles.add(fragment);

        FragmentObject fragment2 = new FragmentObject(getMapTile(6, 3).getLocation(), 1);
        enhancedMapTiles.add(fragment2);

        PushableRock pushableRock = new PushableRock(getMapTile(4, 3).getLocation());
        enhancedMapTiles.add(pushableRock);

        PushableRock pushableRock2 = new PushableRock(getMapTile(5, 2).getLocation());
        enhancedMapTiles.add(pushableRock2);

        PushableRock pushableRock3 = new PushableRock(getMapTile(12, 1).getLocation());
        enhancedMapTiles.add(pushableRock3);

        PushableRock pushableRock4 = new PushableRock(getMapTile(7, 1).getLocation());
        enhancedMapTiles.add(pushableRock4);

        PushableRock pushableRock5 = new PushableRock(getMapTile(10, 1).getLocation());
        enhancedMapTiles.add(pushableRock5);

        PushableRock pushableRock6 = new PushableRock(getMapTile(10, 1).getLocation());
        enhancedMapTiles.add(pushableRock6);

        PushableRock pushableRock7 = new PushableRock(getMapTile(10, 1).getLocation());
        enhancedMapTiles.add(pushableRock7);
        
        PushableRock pushableRock8 = new PushableRock(getMapTile(10, 1).getLocation());
        enhancedMapTiles.add(pushableRock8);


        MedkitObject medkit = new MedkitObject(getMapTile(7, 3).getLocation(), 25);
        enhancedMapTiles.add(medkit);

        

        return enhancedMapTiles;
    }
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

        Testfox testfox = new Testfox(1, getMapTile(11, 6).getLocation().subtractY(40));
        testfox.setInteractScript(new TestfoxScript());
        npcs.add(testfox);

        return npcs;
    }
    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        //triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(), "hasLostBall"));
        return triggers;
    }
    
}



