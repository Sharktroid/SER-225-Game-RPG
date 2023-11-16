package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import Tilesets.CommonTileset;

public class W2AmazonMap extends Map {
    public W2AmazonMap() {
        super("w2_amazon_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();
        textbox.setStyle(Style.WORLDTWO);
    }
}