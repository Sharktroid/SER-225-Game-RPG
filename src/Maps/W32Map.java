package Maps;

import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import Level.Trigger;
import Scripts.WorldThreeFloors.upLevelScript;
import Scripts.WorldOneMap.EnterLibraryScript;
import Scripts.WorldOneMap.WorldOneClearScript;
import Scripts.WorldThreeFloors.downLevelScript;
import Tilesets.CommonTileset;

import java.util.ArrayList;

//all 5 world three maps are created using this class
public class W32Map extends Map {

    // setup maps
    public W32Map() {
        super("w32map.txt", new CommonTileset());

        this.playerStartPosition = getMapTile(8, 15).getLocation();
        textbox.setStyle(Style.WORLDTHREE);
    }
}