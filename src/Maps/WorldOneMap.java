package Maps;

import EnhancedMapTiles.PushableRock;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import Tilesets.CommonTileset;

import java.util.ArrayList;


public class WorldOneMap extends Map {
    public WorldOneMap() {
        super("world_one_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(17, 20).getLocation();
    }
}
