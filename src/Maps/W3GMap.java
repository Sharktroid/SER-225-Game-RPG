package Maps;

import java.util.ArrayList;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import NPCs.World3.W3OutsideEmployee;
import Level.Trigger;
import Scripts.WorldThreeMap.UpLevelScript;
import Scripts.WorldThreeMap.W3OutsideEmployeeScript;
import Tilesets.ChromeTileset;
import Screens.PlayLevelScreen;

public class W3GMap extends Map {

    public static int currFloor = 0;

    // setup maps
    public W3GMap() {
        super("w3gmap.txt", new ChromeTileset(), 0);

        
        if (PlayLevelScreen.getFlagManager() == null){
            this.playerStartPosition = getMapTile(0, 0).getLocation();
        } else if (PlayLevelScreen.getFlagManager().getFlagState("wentDownLevel") == false) {
            this.playerStartPosition = getMapTile(8, 11).getLocation().adjustXY(-12, -12);
        } else if (PlayLevelScreen.getFlagManager().getFlagState("wentDownLevel") == true) {
            this.playerStartPosition = getMapTile(8, 8).getLocation().adjustXY(-12, -12);
        }
        
        textbox.setStyle(Style.WORLDTHREE);
    }

    @Override
    public void setupMap() {
        super.setupMap();
        // SoundPlayer.playMusic(MusicTracks.WORLDTHREE);
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
      
        W3OutsideEmployee outside = new W3OutsideEmployee(1, getMapTile(7, 8).getLocation());
        outside.setInteractScript(new W3OutsideEmployeeScript());
        npcs.add(outside);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(336, 384, 144, 12, new UpLevelScript()));

        return triggers;
    }

}