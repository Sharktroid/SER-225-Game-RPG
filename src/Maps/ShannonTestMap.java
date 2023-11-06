package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import NPCs.Beaver;
import NPCs.Catsuit;
import NPCs.Dinosaur;
import NPCs.Elder;
import NPCs.Garfunkle;
import NPCs.Giraffe;
import NPCs.Officeworker;
import NPCs.Redpanda;
import NPCs.Sloth;
import NPCs.SoulConsumingFlameNPC;
import NPCs.Walrus;
import Scripts.WorldOneMap.BeaverScript;
import Scripts.WorldOneMap.CatsuitScript;
import Scripts.WorldOneMap.DinoScript;
import Scripts.WorldOneMap.ElderScript;
import Scripts.WorldOneMap.GarfunkleScript;
import Scripts.WorldOneMap.GiraffeScript;
import Scripts.WorldOneMap.OfficeworkerScript;
import Scripts.WorldOneMap.RedpandaScript;
import Scripts.WorldOneMap.SlothScript;
import Scripts.WorldOneMap.SoulConsumingFlameNPCScript;
import Scripts.WorldOneMap.WalrusScript;
import Tilesets.InternetExplorerTileset;

//fastest way to load: start program -> press up arrow -> select hub world -> press 7 key

public class ShannonTestMap extends Map {
    public ShannonTestMap() {
        super("shannon_map.txt", new InternetExplorerTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();
    }

    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

        Walrus walrus = new Walrus(1, getMapTile(4, 20).getLocation());
        walrus.setInteractScript(new WalrusScript());
        npcs.add(walrus);

        Dinosaur dinosaur = new Dinosaur(2, getMapTile(13, 4).getLocation());
        dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        dinosaur.setInteractScript(new DinoScript());
        npcs.add(dinosaur);

        Beaver beaver = new Beaver(3, getMapTile(10, 20).getLocation());
        beaver.setInteractScript(new BeaverScript());
        npcs.add(beaver);

        Giraffe giraffe = new Giraffe(4, getMapTile(11, 21).getLocation());
        giraffe.setInteractScript(new GiraffeScript());
        npcs.add(giraffe);

        Redpanda redpanda = new Redpanda(5, getMapTile(17, 22).getLocation());
        redpanda.setInteractScript(new RedpandaScript());
        npcs.add(redpanda);

        Sloth sloth = new Sloth(6, getMapTile(9, 18).getLocation());
        sloth.setInteractScript(new SlothScript());
        npcs.add(sloth);

        Catsuit catsuit = new Catsuit(7, getMapTile(13, 20).getLocation());
        catsuit.setInteractScript(new CatsuitScript());
        npcs.add(catsuit);

        Officeworker officeworker = new Officeworker(8, getMapTile(11, 16).getLocation());
        officeworker.setInteractScript(new OfficeworkerScript());
        npcs.add(officeworker);

        Elder elder = new Elder(9, getMapTile(14, 20).getLocation());
        elder.setInteractScript(new ElderScript());
        npcs.add(elder);

        Garfunkle garfunkle = new Garfunkle(10, getMapTile(13, 20).getLocation());
        garfunkle.setInteractScript(new GarfunkleScript());
        npcs.add(garfunkle);

        SoulConsumingFlameNPC soulConsumingFlame = new SoulConsumingFlameNPC(8, getMapTile(11, 16).getLocation());
        soulConsumingFlame.setInteractScript(new SoulConsumingFlameNPCScript());
        npcs.add(soulConsumingFlame);

        return npcs;
    }

}