package Maps;

import Level.Map;
import Tilesets.CommonTileset;


public class WorldTwoMap extends Map {
    public WorldTwoMap() {
        super("world_two_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(0,6).getLocation();
    }
}
