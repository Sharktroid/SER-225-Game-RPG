package Maps;

import java.util.ArrayList;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import Level.Trigger;
import NPCs.W2PB2;
import NPCs.W2PB3;
import NPCs.W2PB4;
import NPCs.W2PB5;
import Scripts.WorldTwoMap.ExitBuildingScript;
import Scripts.WorldTwoMap.W2PB2Script;
import Scripts.WorldTwoMap.W2PB3Script;
import Scripts.WorldTwoMap.W2PB4Script;
import Scripts.WorldTwoMap.W2PB5Script;
import Tilesets.SafariTileset;

public class W2StarbucksMap extends Map {
    public W2StarbucksMap() {
        super("w2_starbucks_map.txt", new SafariTileset(), 3);
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
        triggers.add(new Trigger(336, 552, 96, 24, new ExitBuildingScript(3)));

        return triggers;
    }
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

        W2PB3 W2PB3 = new W2PB3(1, getMapTile(6, 5).getLocation().subtractY(40));
        W2PB3.setInteractScript(new W2PB3Script());
        npcs.add(W2PB3);

        return npcs;
    }
}