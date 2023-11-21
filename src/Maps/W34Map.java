package Maps;

import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import Level.Trigger;
import Tilesets.ChromeTileset;
import Tilesets.CommonTileset;

import java.util.ArrayList;

//all 5 world three maps are created using this class
public class W34Map extends Map {

    // setup maps
    public W34Map() {
        super("w34map.txt", new ChromeTileset());

        this.playerStartPosition = getMapTile(8, 15).getLocation();
        textbox.setStyle(Style.WORLDTHREE);
    }
}