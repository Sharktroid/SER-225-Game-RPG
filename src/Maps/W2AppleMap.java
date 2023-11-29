package Maps;

import java.util.ArrayList;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import NPCs.World2.W2Apple;
import NPCs.World2.W2Outside1;
import NPCs.World2.W2Outside2;
import NPCs.World2.W2Outside3;
import NPCs.World2.W2Outside5;
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

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        W2Apple appleEm = new W2Apple(1, getMapTile(8, 1).getLocation());
        npcs.add(appleEm);

        //green
        W2Outside1 customer1 = new W2Outside1(1, getMapTile(2, 4).getLocation());
        npcs.add(customer1);

        //orange
        W2Outside2 customer2 = new W2Outside2(2, getMapTile(4, 6).getLocation());
        npcs.add(customer2);

        //purple
        W2Outside3 customer3 = new W2Outside3(3, getMapTile(10, 7).getLocation());
        npcs.add(customer3);

        //yellow
        W2Outside5 customer4 = new W2Outside5(5, getMapTile(13, 6).getLocation());
        npcs.add(customer4);

        return npcs;
    }
}