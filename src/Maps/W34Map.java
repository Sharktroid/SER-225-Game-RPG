package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import Level.Trigger;
import NPCs.World3.W3CEO;
import Screens.PlayLevelScreen;
import Scripts.WorldThreeMap.DownLevelScript;
import Scripts.WorldThreeMap.WorldThreeClearScript;
import Scripts.WorldThreeMap.W3CEOScript;
import Tilesets.ChromeTileset;

public class W34Map extends Map {

    public W34Map() {
        super("w34map.txt", new ChromeTileset(), 4);

        if (PlayLevelScreen.getFlagManager() == null){
            this.playerStartPosition = getMapTile(0, 0).getLocation();
        }else if (PlayLevelScreen.getFlagManager().getFlagState("wentUpLevel") == true) {
            this.playerStartPosition = getMapTile(4, 14).getLocation().adjustXY(-12, -12);
        }

        textbox.setStyle(Style.WORLDTHREE);
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
      
        W3CEO ceo = new W3CEO(1, getMapTile(5, 4).getLocation());
        ceo.setInteractScript(new W3CEOScript());
        npcs.add(ceo);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(144, 624, 24, 144, new DownLevelScript()));
        triggers.add(new Trigger(48, 144, 48, 48, new WorldThreeClearScript()));
        
        return triggers;
    }
}