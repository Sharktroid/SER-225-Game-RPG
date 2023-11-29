package Maps;

import java.util.ArrayList;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import NPCs.World2.W2Outside1;
import NPCs.World2.W2Outside2;
import NPCs.World2.W2Outside3;
import NPCs.World2.W2Outside4;
import NPCs.World2.W2Outside5;
import Scripts.WorldTwoMap.W2Outside1Script;
import Scripts.WorldTwoMap.W2Outside2Script;
import Scripts.WorldTwoMap.W2Outside3Script;
import Scripts.WorldTwoMap.W2Outside4Script;
import Scripts.WorldTwoMap.W2Outside5Script;
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

        //green
        W2Outside1 outside1 = new W2Outside1(1, getMapTile(20, 7).getLocation());
        outside1.setInteractScript(new W2Outside1Script());
        npcs.add(outside1);

        //orange
        W2Outside2 outside2 = new W2Outside2(2, getMapTile(41, 6).getLocation());
        outside2.setInteractScript(new W2Outside2Script());
        npcs.add(outside2);

        //purple
        W2Outside3 outside3 = new W2Outside3(3, getMapTile(34, 7).getLocation());
        outside3.setInteractScript(new W2Outside3Script());
        npcs.add(outside3);

        //red
        W2Outside4 outside4 = new W2Outside4(4, getMapTile(29, 6).getLocation());
        outside4.setInteractScript(new W2Outside4Script());
        npcs.add(outside4);

        //yellow
        W2Outside5 outside5 = new W2Outside5(5, getMapTile(11, 6).getLocation());
        outside5.setInteractScript(new W2Outside5Script());
        npcs.add(outside5);

        return npcs;
    }
}
