package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import Level.Trigger;
import NPCs.World3.W3BlueTie;
import NPCs.World3.W3Pink;
import NPCs.World3.W3RedTie;
import NPCs.World3.W3WhiteTie;
import Screens.PlayLevelScreen;
import Scripts.WorldThreeMap.DownLevelScript;
import Scripts.WorldThreeMap.UpLevelScript;
import Scripts.WorldThreeMap.W3F2RedTieScript;
import Scripts.WorldThreeMap.W3F3BlueTieScript;
import Scripts.WorldThreeMap.W3F3PinkScript;
import Scripts.WorldThreeMap.W3F3WhiteTieScript;
import Tilesets.ChromeTileset;

public class W33Map extends Map {

    public W33Map() {
        super("w33map.txt", new ChromeTileset(), 3);

        if (PlayLevelScreen.getFlagManager() == null){
            this.playerStartPosition = getMapTile(0, 0).getLocation();
        }else if (PlayLevelScreen.getFlagManager().getFlagState("wentUpLevel") == true) {
            this.playerStartPosition = getMapTile(4, 14).getLocation().adjustXY(-12, -12);
        } else if (PlayLevelScreen.getFlagManager().getFlagState("wentDownLevel") == true) {
            this.playerStartPosition = getMapTile(12, 14).getLocation().adjustXY(-12, -12);
        }

        textbox.setStyle(Style.WORLDTHREE);
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
      
        W3WhiteTie whiteTie = new W3WhiteTie(1, getMapTile(2, 3).getLocation());
        whiteTie.setInteractScript(new W3F3WhiteTieScript());
        npcs.add(whiteTie);

        W3RedTie redTie = new W3RedTie(2, getMapTile(5, 4).getLocation());
        redTie.setInteractScript(new W3F2RedTieScript());
        npcs.add(redTie);

        W3Pink pink = new W3Pink(3, getMapTile(10, 3).getLocation());
        pink.setInteractScript(new W3F3PinkScript());
        npcs.add(pink);

        W3BlueTie blueTie = new W3BlueTie(4, getMapTile(14, 6).getLocation());
        blueTie.setInteractScript(new W3F3BlueTieScript());
        npcs.add(blueTie);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(144, 624, 24, 144, new DownLevelScript()));
        triggers.add(new Trigger(648, 624, 24, 144, new UpLevelScript()));

        return triggers;
    }
}