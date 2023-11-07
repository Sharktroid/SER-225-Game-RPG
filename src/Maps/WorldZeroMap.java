package Maps;

import Level.Map;
import Tilesets.InternetExplorerTileset;
import Tilesets.HubTileset;

public class WorldZeroMap extends Map {

    public WorldZeroMap() {
        super("world_zero_map.txt", new HubTileset());
        this.playerStartPosition = getMapTile(8, 9).getLocation();

    }

}
