package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
// import NPCs.Beaver;
// import NPCs.Catsuit;
// import NPCs.Dinosaur;
// import NPCs.Elder;
// import NPCs.Giraffe;
// import NPCs.Redpanda;
// import NPCs.Sloth;
// import NPCs.Walrus;
// import Scripts.WorldOneMap.BeaverScript;
// import Scripts.WorldOneMap.EngineerScript;
// import Scripts.WorldOneMap.DinoScript;
// import Scripts.WorldOneMap.GiraffeScript;
// import Scripts.WorldOneMap.OldManJenksScript;
// import Scripts.WorldOneMap.RedpandaScript;
// import Scripts.WorldOneMap.SlothScript;
// import Scripts.WorldOneMap.LibrarianScript;
import Tilesets.InternetExplorerTileset;

//fastest way to load: start program -> press up arrow -> select hub world -> press 7 key

public class ShannonTestMap extends Map {
    public ShannonTestMap() {
        super("shannon_map.txt", new InternetExplorerTileset());
        this.playerStartPosition = getMapTile(23, 13).getLocation();
    }

    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

        return npcs;
    }

}