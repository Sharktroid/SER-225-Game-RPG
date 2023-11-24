package Maps;

import Level.Map;
import Level.Textbox.Style;
import Level.Trigger;
import Tilesets.ChromeTileset;
import Tilesets.CommonTileset;

//all 5 world three maps are created using this class
public class W32Map extends Map {

    // setup maps
    public W32Map() {
        super("w32map.txt", new ChromeTileset());

        this.playerStartPosition = getMapTile(8, 15).getLocation();
        textbox.setStyle(Style.WORLDTHREE);
    }
}