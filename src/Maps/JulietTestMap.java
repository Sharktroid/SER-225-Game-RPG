package Maps;

import java.util.ArrayList;

import Level.Map;
import Tilesets.CommonTileset;

//fastest way to load: start program -> press up arrow -> select hub world -> press 8 key

public class JulietTestMap extends Map {
    public JulietTestMap() {
        super("juliet_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();
    }

}

