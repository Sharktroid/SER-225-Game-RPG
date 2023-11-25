package Maps;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.Textbox.Style;
import Tilesets.CommonTileset;
import Tilesets.SafariTileset;

public class W2AppleMap extends Map {
    public W2AppleMap() {
        super("w2_apple_map.txt", new SafariTileset(), 2);
        this.playerStartPosition = getMapTile(0, 0).getLocation();
        textbox.setStyle(Style.WORLDTWO);
    }

    @Override
    public void setupMap() {
        super.setupMap();
        SoundPlayer.playMusic(MusicTracks.SHOP);
    }
}