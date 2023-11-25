package Maps;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.Textbox.Style;
import Tilesets.SafariTileset;

public class W2SpotifyMap extends Map {
    public W2SpotifyMap() {
        super("w2_spotify_map.txt", new SafariTileset(), 3);
        this.playerStartPosition = getMapTile(0, 0).getLocation();
        textbox.setStyle(Style.WORLDTWO);
    }

    @Override
    public void setupMap() {
        super.setupMap();
        SoundPlayer.playMusic(MusicTracks.SHOP);
    }
}