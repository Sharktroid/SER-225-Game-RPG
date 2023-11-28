package Maps;

import java.util.ArrayList;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import Tilesets.SafariTileset;

public class W2StarbucksMap extends Map {
    public W2StarbucksMap() {
        super("w2_starbucks_map.txt", new SafariTileset(), 4);
        this.playerStartPosition = getMapTile(0, 0).getLocation();
        textbox.setStyle(Style.WORLDTWO);
    }

    @Override
    public void setupMap() {
        super.setupMap();
        SoundPlayer.playMusic(MusicTracks.SHOP);
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

        W1OldManJenks oldManJenks = new W1OldManJenks(1, getMapTile(25, 14).getLocation());
        oldManJenks.setExistenceFlag("hasFinishedOMJ");
        oldManJenks.setInteractScript(new W1OldManJenksScript());
        npcs.add(oldManJenks);



        return npcs;
    }
}