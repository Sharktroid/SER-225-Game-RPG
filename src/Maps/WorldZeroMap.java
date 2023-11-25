package Maps;

import Level.Map;
import Tilesets.HubTileset;

public class WorldZeroMap extends Map {

    public WorldZeroMap() {
        super("world_zero_map.txt", new HubTileset(), 0);
        this.playerStartPosition = getMapTile(8, 9).getLocation();

    }

}
