package Maps;

import EnhancedMapTiles.ItemMapObject;
import EnhancedMapTiles.PushableRock;
import Items.Fragment;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import Scripts.WorldOneMap.W1ExitLibraryScript;
//import Scripts.WorldOneMap.W1Frag3Script;
import Scripts.WorldOneMap.w1Frag3Script;
import Tilesets.InternetExplorerTileset;
import java.util.ArrayList;


public class W1LibraryMap extends Map {

    public static int currentArea = 1;
    public static boolean entered = true;

    public W1LibraryMap() {
        super("w1_library_map.txt", new InternetExplorerTileset());
        this.playerStartPosition = getMapTile(6, 1).getLocation().addX(20);
    }

    @Override
    public void setupMap() {
        super.setupMap();
        SoundPlayer.playMusic(MusicTracks.LIBRARY);
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        PushableRock pushableRock = new PushableRock(getMapTile(5, 2).getLocation());
        enhancedMapTiles.add(pushableRock);

        PushableRock pushableRock2 = new PushableRock(getMapTile(2, 5).getLocation());
        enhancedMapTiles.add(pushableRock2);

        PushableRock pushableRock3 = new PushableRock(getMapTile(3, 8).getLocation());
        enhancedMapTiles.add(pushableRock3);

        PushableRock pushableRock4 = new PushableRock(getMapTile(2, 11).getLocation());
        enhancedMapTiles.add(pushableRock4);

        PushableRock pushableRock5 = new PushableRock(getMapTile(2, 12).getLocation());
        enhancedMapTiles.add(pushableRock5);

        PushableRock pushableRock6 = new PushableRock(getMapTile(10, 12).getLocation());
        enhancedMapTiles.add(pushableRock6);

        PushableRock pushableRock7 = new PushableRock(getMapTile(11, 2).getLocation());
        enhancedMapTiles.add(pushableRock7);

        PushableRock pushableRock8 = new PushableRock(getMapTile(12, 2).getLocation());
        enhancedMapTiles.add(pushableRock8);

        ItemMapObject fragment3 = new ItemMapObject(getMapTile(13, 8).getLocation(), new Fragment(null));
        fragment3.setInteractScript(new w1Frag3Script());
        enhancedMapTiles.add(fragment3);

        return enhancedMapTiles;
    }
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        return npcs;
    }
    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        triggers.add(new Trigger(384, 672, 96,48, new W1ExitLibraryScript()));

        return triggers;
    }

}



