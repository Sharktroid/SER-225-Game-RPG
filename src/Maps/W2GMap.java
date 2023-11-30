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
// import Scripts.WorldTwoMap.W2GreenScript;
// import Scripts.WorldTwoMap.W2OrangeScript;
// import Scripts.WorldTwoMap.W2PurpleScript;
// import Scripts.WorldTwoMap.W2RedScript;
// import Scripts.WorldTwoMap.W2YellowScript;
import Scripts.WorldTwoMap.WorldTwoClearScript;
import Level.Trigger;
import NPCs.W2PB4;
import NPCs.W2PB5;
import Screens.PlayLevelScreen;
import Scripts.WorldTwoMap.EnterBuildingScript;
import Scripts.WorldTwoMap.W2PB4Script;
import Scripts.WorldTwoMap.W2PB5Script;
import Tilesets.SafariTileset;

public class W2GMap extends Map {
    public W2GMap() {
        super("w2gmap.txt", new SafariTileset(), 0);
        if (PlayLevelScreen.getFlagManager() == null) {
            this.playerStartPosition = getMapTile(0, 0).getLocation();
        }else if (!PlayLevelScreen.getFlagManager().isFlagSet("wentOutOfBuilding1") && !PlayLevelScreen.getFlagManager().isFlagSet("wentOutOfBuilding2") && !PlayLevelScreen.getFlagManager().isFlagSet("wentOutOfBuilding3") && !PlayLevelScreen.getFlagManager().isFlagSet("wentOutOfBuilding4")) {
            this.playerStartPosition = getMapTile(1, 7).getLocation();
        } else if (PlayLevelScreen.getFlagManager().isFlagSet("wentOutOfBuilding1")) {
            this.playerStartPosition = getMapTile(8, 7).getLocation().adjustXY(-12, -12);
        } else if (PlayLevelScreen.getFlagManager().isFlagSet("wentOutOfBuilding2")) {
            this.playerStartPosition = getMapTile(26, 7).getLocation().adjustXY(-12, -12);
        } else if (PlayLevelScreen.getFlagManager().isFlagSet("wentOutOfBuilding3")) {
            this.playerStartPosition = getMapTile(44, 7).getLocation().adjustXY(-12, -12);
        } else if (PlayLevelScreen.getFlagManager().isFlagSet("wentOutOfBuilding4")) {
            this.playerStartPosition = getMapTile(55, 7).getLocation().adjustXY(-12, -12);
        }
        

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
        // green.setInteractScript(new W2GreenScript());
        npcs.add(green);

        //orange
        W2Orange orange = new W2Orange(2, getMapTile(41, 6).getLocation());
        // orange.setInteractScript(new W2OrangeScript());
        npcs.add(orange);

        //purple
        W2Purple purple = new W2Purple(3, getMapTile(34, 7).getLocation());
        // purple.setInteractScript(new W2PurpleScript());
        npcs.add(purple);

        //red
        W2Red red = new W2Red(4, getMapTile(29, 6).getLocation());
        // red.setInteractScript(new W2RedScript());
        npcs.add(red);

        //yellow
        W2Yellow yellow = new W2Yellow(5, getMapTile(11, 6).getLocation());
        // yellow.setInteractScript(new W2YellowScript());
        npcs.add(yellow);

        W2PB4 W2PB4 = new W2PB4(2, getMapTile(0, 0).getLocation().subtractY(40));
        W2PB4.setInteractScript(new W2PB4Script());
        npcs.add(W2PB4);

        W2PB5 W2PB5 = new W2PB5(3, getMapTile(32, 1).getLocation().subtractY(40));
        W2PB5.setInteractScript(new W2PB5Script());
        npcs.add(W2PB5);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(384, 288, 48, 36, new EnterBuildingScript(1)));
        triggers.add(new Trigger(1248, 288, 96, 36, new EnterBuildingScript(2)));
        triggers.add(new Trigger(2640, 288, 96, 36, new EnterBuildingScript(3)));
        triggers.add(new Trigger(2112, 288, 96, 36, new EnterBuildingScript(4)));
        triggers.add(new Trigger(0, 336, 48, 48, new WorldTwoClearScript()));

        return triggers;
    }
    
}
