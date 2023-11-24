package Maps;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.Textbox.Style;
import Tilesets.CommonTileset;

public class W2StarbucksMap extends Map {
    public W2StarbucksMap() {
        super("w2_starbucks_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();
        textbox.setStyle(Style.WORLDTWO);
    }

    @Override
    public void setupMap() {
        super.setupMap();
        SoundPlayer.playMusic(MusicTracks.SHOP);
    }
}