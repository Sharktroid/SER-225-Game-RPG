package Maps;

import Level.Map;
import Tilesets.ChromeTileset;

//fastest way to load: start program -> press up arrow -> select hub world -> press 8 key

public class JulietTestMap extends Map {
    public JulietTestMap() {
        super("juliet_map.txt", new ChromeTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();
    }

}

