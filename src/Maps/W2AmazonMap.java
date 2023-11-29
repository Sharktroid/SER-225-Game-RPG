package Maps;

import java.util.ArrayList;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import NPCs.World2.W2FrontDesk;
import NPCs.World2.W2WarehouseGuy;
import Scripts.WorldTwoMap.W2FrontDeskScript;
import Scripts.WorldTwoMap.W2WarehouseGuyScript;
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

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        W2FrontDesk frontDesk = new W2FrontDesk(1, getMapTile(11, 42).getLocation());
        frontDesk.setInteractScript(new W2FrontDeskScript());
        npcs.add(frontDesk);

        W2WarehouseGuy warehouseGuy = new W2WarehouseGuy(2, getMapTile(36, 18).getLocation());
        warehouseGuy.setInteractScript(new W2WarehouseGuyScript());
        npcs.add(warehouseGuy);

        return npcs;
    }
}