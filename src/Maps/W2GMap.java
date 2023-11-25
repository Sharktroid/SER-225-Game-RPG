package Maps;

import java.util.ArrayList;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import NPCs.Finder1;
import Scripts.WorldTwoMap.Finder1Script;
import Tilesets.SafariTileset;

public class W2GMap extends Map {
    public W2GMap() {
        super("w2gmap.txt", new SafariTileset(), 0);
        this.playerStartPosition = getMapTile(0, 6).getLocation();
        textbox.setStyle(Style.WORLDTWO);
    }

    @Override
    public void setupMap() {
        super.setupMap();
        SoundPlayer.playMusic(MusicTracks.WORLDTWO);
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Finder1 finder1 = new Finder1(5, getMapTile(4, 4).getLocation().subtractY(40));
        finder1.setInteractScript(new Finder1Script());
        npcs.add(finder1);

        return npcs;
    }
}
