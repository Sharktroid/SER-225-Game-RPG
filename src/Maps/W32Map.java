package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import NPCs.World3.W3LightBlue;
import NPCs.World3.W3Pink;
import NPCs.World3.W3RedTie;
import Level.Trigger;
import Screens.PlayLevelScreen;
import Scripts.WorldThreeMap.DownLevelScript;
import Scripts.WorldThreeMap.UpLevelScript;
import Scripts.WorldThreeMap.W3F2LightBlueScript;
import Scripts.WorldThreeMap.W3F2PinkScript;
import Scripts.WorldThreeMap.W3F2RedTieScript;
import Tilesets.ChromeTileset;

public class W32Map extends Map {

    public W32Map() {
        super("w32map.txt", new ChromeTileset(), 2);

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
      
        W3LightBlue lightBlue = new W3LightBlue(1, getMapTile(2, 6).getLocation());
        lightBlue.setInteractScript(new W3F2LightBlueScript());
        npcs.add(lightBlue);

        W3Pink pink = new W3Pink(2, getMapTile(8, 4).getLocation());
        pink.setInteractScript(new W3F2PinkScript());
        npcs.add(pink);

        W3RedTie redTie = new W3RedTie(3, getMapTile(14, 5).getLocation());
        redTie.setInteractScript(new W3F2RedTieScript());
        npcs.add(redTie);

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