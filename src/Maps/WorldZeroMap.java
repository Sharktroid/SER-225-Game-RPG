package Maps;

import Level.Map;
import Tilesets.CommonTileset;

public class WorldZeroMap extends Map {

    public WorldZeroMap() {
        super("world_zero_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();

    }

}
