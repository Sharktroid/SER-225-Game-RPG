package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import NPCs.Beaver;
import Scripts.WorldTwoMap.BeaverScript;
import Tilesets.CommonTileset;

public class WorldTwoMap extends Map {
    public WorldTwoMap() {
        super("world_two_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(0, 6).getLocation();
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Beaver beaver = new Beaver(5, getMapTile(4, 4).getLocation().subtractY(40));
        beaver.setInteractScript(new BeaverScript());
        npcs.add(beaver);

        return npcs;
    }
}
