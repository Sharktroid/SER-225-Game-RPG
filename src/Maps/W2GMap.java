package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import NPCs.Beaver;
import Scripts.WorldTwoMap.BeaverScript;
import Tilesets.CommonTileset;

public class W2GMap extends Map {
    public W2GMap() {
        super("w2gmap.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(0, 6).getLocation();
        textbox.setStyle(Style.WORLDTWO);
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Beaver beaver = new Beaver(5, getMapTile(4, 4).getLocation().subtractY(40));
        beaver.setInteractScript(new BeaverScript());
        npcs.add(beaver);

        return npcs;
    }
}
