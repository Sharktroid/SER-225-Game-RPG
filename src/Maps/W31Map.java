package Maps;

import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import Level.Trigger;
import Tilesets.CommonTileset;

import java.util.ArrayList;

//all 5 world three maps are created using this class
public class W31Map extends Map {

    // setup maps
    public W31Map() {
        super("w31map.txt", new CommonTileset());

        this.playerStartPosition = getMapTile(8, 15).getLocation();
        textbox.setStyle(Style.WORLDTHREE);
    }
}