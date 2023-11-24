package Maps;

import Level.Map;
import Level.Textbox.Style;
import Tilesets.CommonTileset;

//all 5 world three maps are created using this class
public class W33Map extends Map {

    // setup maps
    public W33Map() {
        super("w33map.txt", new CommonTileset());

        this.playerStartPosition = getMapTile(8, 15).getLocation();
        textbox.setStyle(Style.WORLDTHREE);
    }
}