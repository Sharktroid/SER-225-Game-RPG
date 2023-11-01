package Maps;

import java.util.ArrayList;

import Level.Map;
import Tilesets.CommonTileset;

//fastest way to load: start program -> press up arrow -> select hub world -> press 5 key


public class EvanTestMap extends Map {
    public EvanTestMap() {
        super("evan_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();
    }

}

