package Maps;

import java.util.ArrayList;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Firefox;
import Scripts.HubMap.*;
import Tilesets.HubTileset;

public class HubMap extends Map {

    public HubMap() {
        super("hub_map.txt", new HubTileset(), 0);
        this.playerStartPosition = getMapTile(8, 10).getLocation();
    }

    @Override
    public void setupMap() {
        super.setupMap();
        SoundPlayer.playMusic(MusicTracks.HUBMAP);
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

        Firefox firefox = new Firefox(1, getMapTile(5, 7).getLocation());
        firefox.setInteractScript(new FirefoxScript());
        npcs.add(firefox);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(204, 108, 24, 24, new portalOneScript()));
        triggers.add(new Trigger(396, 108, 24, 24, new portalTwoScript()));
        triggers.add(new Trigger(588, 108, 24, 24, new portalThreeScript()));
        //triggers.add(new Trigger(384, 336, 48, 48, new hubMsgScript(),"sawHubMsg"));

        return triggers;
    }

}
