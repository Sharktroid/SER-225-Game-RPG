package Maps;

import Level.Map;
import Tilesets.InternetExplorerTileset;

public class WorldZeroMap extends Map {

    public WorldZeroMap() {
        super("world_zero_map.txt", new InternetExplorerTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();

    }

}
