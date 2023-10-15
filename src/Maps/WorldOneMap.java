package Maps;

import EnhancedMapTiles.Medkit;
import EnhancedMapTiles.CatFood;
import EnhancedMapTiles.PushableRock;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Script;
import Level.Trigger;
import NPCs.Beaver;
import NPCs.Catsuit;
import NPCs.Dinosaur;
import NPCs.Giraffe;
import NPCs.Officeworker;
import NPCs.Redpanda;
import NPCs.Sloth;
import NPCs.Walrus;
import Scripts.SimpleTextScript;
import Scripts.WorldOneMap.BeaverScript;
import Scripts.WorldOneMap.CatsuitScript;
import Scripts.WorldOneMap.DinoScript;
import Scripts.WorldOneMap.GiraffeScript;
import Scripts.WorldOneMap.LostBallScript;
import Scripts.WorldOneMap.OfficeworkerScript;
import Scripts.WorldOneMap.RedpandaScript;
import Scripts.WorldOneMap.SlothScript;
import Scripts.WorldOneMap.TreeScript;
import Scripts.WorldOneMap.WalrusScript;
import Tilesets.CommonTileset;

import java.util.ArrayList;

public class WorldOneMap extends Map {
    public WorldOneMap() {
        super("world_one_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(0, 14).getLocation();
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        Medkit medkit = new Medkit(getMapTile(16, 14).getLocation(), 25);
        enhancedMapTiles.add(medkit);
        CatFood catFood = new CatFood(getMapTile(5, 10).getLocation(), 2);
        enhancedMapTiles.add(catFood);
        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

        Walrus walrus = new Walrus(1, getMapTile(4, 28).getLocation().subtractY(40));
        walrus.setInteractScript(new WalrusScript());
        npcs.add(walrus);

        Dinosaur dinosaur = new Dinosaur(2, getMapTile(13, 4).getLocation());
        dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        dinosaur.setInteractScript(new DinoScript());
        npcs.add(dinosaur);

        Beaver beaver = new Beaver(3, getMapTile(10, 20).getLocation().subtractY(40));
        beaver.setInteractScript(new BeaverScript());
        npcs.add(beaver);

        Giraffe giraffe = new Giraffe(4, getMapTile(11, 21).getLocation().subtractY(40));
        giraffe.setInteractScript(new GiraffeScript());
        npcs.add(giraffe);

        Redpanda redpanda = new Redpanda(5, getMapTile(17, 22).getLocation().subtractY(40));
        redpanda.setInteractScript(new RedpandaScript());
        npcs.add(redpanda);

        Sloth sloth = new Sloth(6, getMapTile(9, 18).getLocation().subtractY(40));
        sloth.setInteractScript(new SlothScript());
        npcs.add(sloth);

        Catsuit catsuit = new Catsuit(7, getMapTile(13, 20).getLocation().subtractY(40));
        catsuit.setInteractScript(new CatsuitScript());
        npcs.add(catsuit);

        Officeworker officeworker = new Officeworker(8, getMapTile(11, 16).getLocation().subtractY(40));
        officeworker.setInteractScript(new OfficeworkerScript());
        npcs.add(officeworker);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(), "hasLostBall"));
        return triggers;
    }
}