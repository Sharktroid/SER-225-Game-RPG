package Maps;

import EnhancedMapTiles.PushableRock;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Testfox;
import Scripts.WorldOneMap.TestfoxScript;
import Tilesets.InternetExplorerTileset;
import java.util.ArrayList;


public class LibraryMap extends Map {

    public static int currentArea = 1;
    public static boolean entered = true;

    public LibraryMap() {
        super("library_map.txt", new InternetExplorerTileset(), 0);
        this.playerStartPosition = getMapTile(6, 1).getLocation().addX(20);
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

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

        return triggers;
    }

}



