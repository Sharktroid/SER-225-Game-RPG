package Maps;

import java.util.ArrayList;

import Level.Map;
import Tilesets.CommonTileset;

//fastest way to load: start program -> press up arrow -> select hub world -> press 7 key

public class ShannonTestMap extends Map {
    public ShannonTestMap() {
        super("shannon_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();
    }

}