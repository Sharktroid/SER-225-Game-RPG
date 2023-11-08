package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import NPCs.Beaver;
import NPCs.Catsuit;
import NPCs.Dinosaur;
import NPCs.Elder;
import NPCs.Giraffe;
import NPCs.Redpanda;
import NPCs.Sloth;
import NPCs.Walrus;
import Scripts.WorldOneMap.BeaverScript;
import Scripts.WorldOneMap.CatsuitScript;
import Scripts.WorldOneMap.DinoScript;
import Scripts.WorldOneMap.GiraffeScript;
import Scripts.WorldOneMap.OldManJenksScript;
import Scripts.WorldOneMap.RedpandaScript;
import Scripts.WorldOneMap.SlothScript;
import Scripts.WorldOneMap.WalrusScript;
import Tilesets.InternetExplorerTileset;

//fastest way to load: start program -> press up arrow -> select hub world -> press 7 key

public class ShannonTestMap extends Map {
    public ShannonTestMap() {
        super("shannon_map.txt", new InternetExplorerTileset());
        this.playerStartPosition = getMapTile(23, 13).getLocation();
    }

    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

        //old man jenkins
        Elder elder = new Elder(9, getMapTile(25, 14).getLocation());
        elder.setInteractScript(new OldManJenksScript());
        npcs.add(elder);

        //police officer
        Catsuit catsuit = new Catsuit(7, getMapTile(35, 24).getLocation());
        catsuit.setInteractScript(new CatsuitScript());
        npcs.add(catsuit);

        //librarian
        Walrus walrus = new Walrus(1, getMapTile(36, 9).getLocation());
        walrus.setInteractScript(new WalrusScript());
        npcs.add(walrus);

        //infected npcs
        Dinosaur dinosaur = new Dinosaur(2, getMapTile(8, 15).getLocation());
        dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        dinosaur.setInteractScript(new DinoScript());
        npcs.add(dinosaur);

        Beaver beaver = new Beaver(3, getMapTile(15, 7).getLocation());
        beaver.setInteractScript(new BeaverScript());
        npcs.add(beaver);

        Giraffe giraffe = new Giraffe(4, getMapTile(3, 8).getLocation());
        giraffe.setInteractScript(new GiraffeScript());
        npcs.add(giraffe);

        Redpanda redpanda = new Redpanda(5, getMapTile(38, 31).getLocation());
        redpanda.setInteractScript(new RedpandaScript());
        npcs.add(redpanda);

        Sloth sloth = new Sloth(6, getMapTile(9, 28).getLocation());
        sloth.setInteractScript(new SlothScript());
        npcs.add(sloth);

        return npcs;
    }

}