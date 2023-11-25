package Maps;

import Level.Map;
import Tilesets.ChromeTileset;
import Tilesets.CommonTileset;
import Tilesets.SafariTileset;

//fastest way to load: start program -> press up arrow -> select hub world -> press 8 key

public class JulietTestMap extends Map {
    public JulietTestMap() {
        super("juliet_map.txt", new SafariTileset(), 0);
        this.playerStartPosition = getMapTile(0, 0).getLocation();
    }

}

