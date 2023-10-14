package Maps;

import Level.Map;
import Level.NPC;
import Level.Script;
import Level.Trigger;
import Scripts.SimpleTextScript;
import Tilesets.CommonTileset;

import java.util.ArrayList;

public class WorldThreeMap extends Map {
    public WorldThreeMap() {
        super("world_three_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(5, 19).getLocation();
    }
}
