package Maps;

import Level.Map;
import Tilesets.CommonTileset;

//fastest way to load: start program -> press up arrow -> select hub world -> press 9 key

public class AaronTestMap extends Map {
    public AaronTestMap() {
        super("aaron_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();
    }

}