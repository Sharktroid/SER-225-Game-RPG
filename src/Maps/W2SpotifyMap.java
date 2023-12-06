package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.ItemMapObject;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Items.Fragment;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import Level.Trigger;
import NPCs.W2DJ;
import NPCs.W2PB1;
import NPCs.W2Pitbull;
import Scripts.WorldTwoMap.ExitBuildingScript;
import Scripts.WorldTwoMap.W2SpotifyFragScript;
import Scripts.WorldTwoMap.W2DJScript;
import Scripts.WorldTwoMap.W2PB1Script;
import Scripts.WorldTwoMap.W2PitbullScript;
import Tilesets.SafariTileset;

public class W2SpotifyMap extends Map {
    public W2SpotifyMap() {
        super("w2_spotify_map.txt", new SafariTileset(), 4);
        this.playerStartPosition = getMapTile(7, 10).getLocation().adjustXY(12, -12);
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
        triggers.add(new Trigger(336, 552, 96, 24, new ExitBuildingScript(4)));

        return triggers;
    }

    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

    
        W2DJ W2DJ = new W2DJ(1, getMapTile(10, 2).getLocation().subtractY(40));
        W2DJ.setInteractScript(new W2DJScript());
        npcs.add(W2DJ);

        W2Pitbull Pitbull = new W2Pitbull(2, getMapTile(9, 6).getLocation().subtractY(40));
        Pitbull.setInteractScript(new W2PitbullScript());
        npcs.add(Pitbull);

        W2PB1 W2PB1 = new W2PB1(3, getMapTile(2, 3).getLocation().subtractY(40));
        W2PB1.setInteractScript(new W2PB1Script());
        npcs.add(W2PB1);  
        return npcs;
    }
}