package Maps;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.Textbox.Style;
import Tilesets.CommonTileset;
import Tilesets.SafariTileset;

public class W2AmazonMap extends Map {
    public W2AmazonMap() {
        super("w2_amazon_map.txt", new SafariTileset(), 1);
        this.playerStartPosition = getMapTile(0, 0).getLocation();
        textbox.setStyle(Style.WORLDTWO);
    }

    @Override
    public void setupMap() {
        super.setupMap();
        SoundPlayer.playMusic(MusicTracks.SHOP);
    }
}