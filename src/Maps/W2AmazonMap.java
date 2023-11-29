package Maps;

import java.util.ArrayList;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.Textbox.Style;
import Level.Trigger;
import Scripts.WorldTwoMap.ExitBuildingScript;
import Tilesets.SafariTileset;

public class W2AmazonMap extends Map {
    public W2AmazonMap() {
        super("w2_amazon_map.txt", new SafariTileset(), 1);
        this.playerStartPosition = getMapTile(19, 48).getLocation();
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
        triggers.add(new Trigger(912, 2376, 96, 24, new ExitBuildingScript(1)));

        return triggers;
    }
}