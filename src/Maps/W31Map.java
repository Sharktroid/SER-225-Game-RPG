package Maps;

import java.util.ArrayList;

import Game.Game;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import NPCs.World3.W3BlueTie;
import NPCs.World3.W3DarkBlue;
import NPCs.World3.W3Ad1;
import Level.Trigger;
import Screens.PlayLevelScreen;
import Scripts.WorldThreeMap.DownLevelScript;
import Scripts.WorldThreeMap.UpLevelScript;
import Scripts.WorldThreeMap.W3F1BlueTieScript;
import Scripts.WorldThreeMap.W3F1DarkBlueScript;
import Scripts.WorldThreeMap.W3Ad1Script;
import Tilesets.ChromeTileset;

public class W31Map extends Map {

    public W31Map() {
        super("w31map.txt", new ChromeTileset(), 1);

        if (PlayLevelScreen.getFlagManager() == null){
            this.playerStartPosition = getMapTile(0, 0).getLocation();
        }else if (PlayLevelScreen.getFlagManager().getFlagState("wentUpLevel") == true) {
            this.playerStartPosition = getMapTile(8, 15).getLocation().adjustXY(-12, -12);
        } else if (PlayLevelScreen.getFlagManager().getFlagState("wentDownLevel") == true) {
            this.playerStartPosition = getMapTile(12, 14).getLocation().adjustXY(-12, -12);
        }

        textbox.setStyle(Style.WORLDTHREE);
    }


    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
      
        W3BlueTie blueTie = new W3BlueTie(1, getMapTile(2, 10).getLocation());
        blueTie.setInteractScript(new W3F1BlueTieScript());
        npcs.add(blueTie);

        W3DarkBlue darkBlue = new W3DarkBlue(2, getMapTile(10, 5).getLocation());
        darkBlue.setInteractScript(new W3F1DarkBlueScript());
        npcs.add(darkBlue);

        W3Ad1 ad1 = new W3Ad1(3, getMapTile(12, 14).getLocation());
        ad1.setInteractScript(new W3Ad1Script());
        ad1.setExistenceFlag("level1Complete");
        npcs.add(ad1);
        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(336, 804, 144, 12, new DownLevelScript()));
        triggers.add(new Trigger(648, 624, 24, 144, new UpLevelScript()));

        return triggers;
    }

}