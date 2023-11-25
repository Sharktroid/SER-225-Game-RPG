package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Textbox.Style;
import Level.Trigger;
import Screens.PlayLevelScreen;
import Scripts.WorldThreeMap.DownLevelScript;
import Scripts.WorldThreeMap.UpLevelScript;
import Tilesets.ChromeTileset;

public class W33Map extends Map {

    public W33Map() {
        super("w33map.txt", new ChromeTileset(), 3);

        if (PlayLevelScreen.getFlagManager() == null){
            this.playerStartPosition = getMapTile(0, 0).getLocation();
        }else if (PlayLevelScreen.getFlagManager().getFlagState("wentUpLevel") == true) {
            this.playerStartPosition = getMapTile(4, 14).getLocation().adjustXY(-12, -12);
        } else if (PlayLevelScreen.getFlagManager().getFlagState("wentDownLevel") == true) {
            this.playerStartPosition = getMapTile(12, 14).getLocation().adjustXY(-12, -12);
        }

        textbox.setStyle(Style.WORLDTHREE);
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(144, 624, 24, 144, new DownLevelScript()));
        triggers.add(new Trigger(648, 624, 24, 144, new UpLevelScript()));

        return triggers;
    }
}