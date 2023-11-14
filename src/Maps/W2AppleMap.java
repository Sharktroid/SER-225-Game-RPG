package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import Tilesets.CommonTileset;

public class W2AppleMap extends Map {
    public W2AppleMap() {
        super("w2_apple_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();
        textbox.setStyle(Style.WORLDTWO);
    }
}