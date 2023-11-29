package Maps;

import java.util.ArrayList;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import NPCs.World2.W2Green;
import NPCs.World2.W2Orange;
import NPCs.World2.W2Purple;
import NPCs.World2.W2Red;
import NPCs.World2.W2Yellow;
import Scripts.WorldTwoMap.W2GreenScript;
import Scripts.WorldTwoMap.W2OrangeScript;
import Scripts.WorldTwoMap.W2PurpleScript;
import Scripts.WorldTwoMap.W2RedScript;
import Scripts.WorldTwoMap.W2YellowScript;
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
        W2Green green = new W2Green(1, getMapTile(20, 7).getLocation());
        green.setInteractScript(new W2GreenScript());
        npcs.add(green);

        //orange
        W2Orange orange = new W2Orange(2, getMapTile(41, 6).getLocation());
        orange.setInteractScript(new W2OrangeScript());
        npcs.add(orange);

        //purple
        W2Purple purple = new W2Purple(3, getMapTile(34, 7).getLocation());
        purple.setInteractScript(new W2PurpleScript());
        npcs.add(purple);

        //red
        W2Red red = new W2Red(4, getMapTile(29, 6).getLocation());
        red.setInteractScript(new W2RedScript());
        npcs.add(red);

        //yellow
        W2Yellow yellow = new W2Yellow(5, getMapTile(11, 6).getLocation());
        yellow.setInteractScript(new W2YellowScript());
        npcs.add(yellow);

        return npcs;
    }
}
