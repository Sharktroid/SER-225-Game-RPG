package Maps;

import Level.Map;
import Level.NPC;
import Level.Script;
import Level.Trigger;
import Scripts.SimpleTextScript;
import Tilesets.CommonTileset;

import java.util.ArrayList;

public class WorldTwoMap extends Map {
    public WorldTwoMap() {
        super("world_two_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(0,6).getLocation();
    }
}
