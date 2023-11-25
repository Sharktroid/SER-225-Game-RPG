package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Textbox.Style;
import Level.Trigger;
import Screens.PlayLevelScreen;
import Scripts.WorldThreeMap.DownLevelScript;
import Scripts.WorldThreeMap.UpLevelScript;
import Tilesets.ChromeTileset;

public class W31Map extends Map {

    public W31Map() {
        super("w31map.txt", new ChromeTileset(), 1);

        if (PlayLevelScreen.getFlagManager() == null){
            this.playerStartPosition = getMapTile(0, 0).getLocation();
        }else if (PlayLevelScreen.getFlagManager().getFlagState("wentUpLevel") == true) {
            this.playerStartPosition = getMapTile(8, 15).getLocation().adjustXY(-12, -12);
        } else if (PlayLevelScreen.getFlagManager().getFlagState("wentDownLevel") == true) {
            this.playerStartPosition = getMapTile(12, 14).getLocation().adjustXY(-12, -12);
        }

        textbox.setStyle(Style.WORLDTHREE);
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(336, 804, 144, 12, new DownLevelScript()));
        triggers.add(new Trigger(648, 624, 24, 144, new UpLevelScript()));

        return triggers;
    }
}