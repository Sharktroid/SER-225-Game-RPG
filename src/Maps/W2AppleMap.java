package Maps;

import java.util.ArrayList;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.Textbox.Style;
import Level.Trigger;
import Scripts.WorldTwoMap.ExitBuildingScript;
import Tilesets.SafariTileset;

public class W2AppleMap extends Map {
    public W2AppleMap() {
        super("w2_apple_map.txt", new SafariTileset(), 2);
        this.playerStartPosition = getMapTile(8, 10).getLocation().adjustXY(12, -12);
        textbox.setStyle(Style.WORLDTWO);
    }

    @Override
    public void setupMap() {
        super.setupMap();
        SoundPlayer.playMusic(MusicTracks.SHOP);
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(384, 552, 96, 24, new ExitBuildingScript(2)));

        return triggers;
    }
}