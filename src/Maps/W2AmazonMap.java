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
import NPCs.World2.W2FrontDesk;
import NPCs.World2.W2WarehouseGuy;
import Scripts.WorldTwoMap.W2FrontDeskScript;
import Scripts.WorldTwoMap.W2WarehouseGuyScript;
import Level.Trigger;
import Scripts.WorldOneMap.w1Frag3Script;
import Scripts.WorldTwoMap.ExitBuildingScript;
import Scripts.WorldTwoMap.W2AmznFragScript;
import Scripts.WorldTwoMap.W2UnlockAmznMazeScript;
import Tilesets.SafariTileset;

public class W2AmazonMap extends Map {
    public W2AmazonMap() {
        super("w2_amazon_map.txt", new SafariTileset(), 1);
        this.playerStartPosition = getMapTile(19, 48).getLocation().adjustXY(12, -12);
        textbox.setStyle(Style.WORLDTWO);
    }

    @Override
    public void setupMap() {
        super.setupMap();
        SoundPlayer.playMusic(MusicTracks.SHOP);
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        ItemMapObject amznFragment = new ItemMapObject(getMapTile(7, 36).getLocation(), new Fragment(null));
        amznFragment.setInteractScript(new W2AmznFragScript());
        enhancedMapTiles.add(amznFragment);

        return enhancedMapTiles;
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

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(912, 2376, 96, 24, new ExitBuildingScript(1)));
        triggers.add(new Trigger(1248, 1788, 96, 24, new W2UnlockAmznMazeScript()));

        return triggers;
    }
}