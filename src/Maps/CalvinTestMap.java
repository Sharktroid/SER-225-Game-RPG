package Maps;

import Level.Map;
import Tilesets.CommonTileset;

//fastest way to load: start program -> press up arrow -> select hub world -> press 6 key

public class CalvinTestMap extends Map {
    public CalvinTestMap() {
        super("calvin_map.txt", new CommonTileset(), 0);
        this.playerStartPosition = getMapTile(0, 0).getLocation();
    }

}
