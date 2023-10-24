package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.PushableRock;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Script;
import Level.Trigger;
import Scripts.SimpleTextScript;
import Tilesets.CommonTileset;
import Tilesets.InternetExplorerTileset;

public class WorldZeroMap extends Map {

    public WorldZeroMap() {
        super("world_zero_map.txt", new InternetExplorerTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();
        
    }

}
