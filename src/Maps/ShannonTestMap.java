package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
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