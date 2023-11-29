package Maps;

import java.util.ArrayList;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import NPCs.World2.W2Apple;
import NPCs.World2.W2Green;
import NPCs.World2.W2Orange;
import NPCs.World2.W2Purple;
import NPCs.World2.W2Yellow;
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
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        W2Apple appleEm = new W2Apple(1, getMapTile(8, 1).getLocation());
        npcs.add(appleEm);

        //green
        W2Green customer1 = new W2Green(1, getMapTile(2, 4).getLocation());
        npcs.add(customer1);

        //orange
        W2Orange customer2 = new W2Orange(2, getMapTile(4, 6).getLocation());
        npcs.add(customer2);

        //purple
        W2Purple customer3 = new W2Purple(3, getMapTile(10, 7).getLocation());
        npcs.add(customer3);

        //yellow
        W2Yellow customer4 = new W2Yellow(5, getMapTile(13, 6).getLocation());
        npcs.add(customer4);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(384, 552, 96, 24, new ExitBuildingScript(2)));

        return triggers;
    }
}