package Maps;

import java.util.ArrayList;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import Level.Trigger;
import NPCs.Finder1;
import Screens.PlayLevelScreen;
import Scripts.WorldTwoMap.EnterBuildingScript;
import Scripts.WorldTwoMap.Finder1Script;
import Tilesets.SafariTileset;

public class W2GMap extends Map {
    public W2GMap() {
        super("w2gmap.txt", new SafariTileset(), 0);
        if (PlayLevelScreen.getFlagManager() == null) {
            this.playerStartPosition = getMapTile(0, 0).getLocation();
        }else if (!PlayLevelScreen.getFlagManager().isFlagSet("wentOutOfBuilding1") && !PlayLevelScreen.getFlagManager().isFlagSet("wentOutOfBuilding2") && !PlayLevelScreen.getFlagManager().isFlagSet("wentOutOfBuilding3") && !PlayLevelScreen.getFlagManager().isFlagSet("wentOutOfBuilding4")) {
            this.playerStartPosition = getMapTile(0, 7).getLocation();
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

        Finder1 finder1 = new Finder1(5, getMapTile(4, 4).getLocation().subtractY(40));
        finder1.setInteractScript(new Finder1Script());
        npcs.add(finder1);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(384, 288, 48, 36, new EnterBuildingScript(1)));
        triggers.add(new Trigger(1248, 288, 96, 36, new EnterBuildingScript(2)));
        triggers.add(new Trigger(2640, 288, 96, 36, new EnterBuildingScript(3)));
        triggers.add(new Trigger(2112, 288, 96, 36, new EnterBuildingScript(4)));

        return triggers;
    }
}
