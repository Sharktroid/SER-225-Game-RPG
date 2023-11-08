package Maps;

import EnhancedMapTiles.MedkitObject;
import EnhancedMapTiles.CatFoodObject;
import EnhancedMapTiles.FetchObject;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Beaver;
import NPCs.Catsuit;
import NPCs.Dinosaur;
import NPCs.Elder;
import NPCs.Giraffe;
import NPCs.SoulConsumingFlameNPC;
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
import Scripts.WorldOneMap.SoulConsumingFlameNPCScript;
import Scripts.WorldOneMap.WalrusScript;
import Scripts.WorldOneMap.WorldOneClearScript;
import Tilesets.InternetExplorerTileset;

import java.util.ArrayList;

public class WorldOneMap extends Map {

    public static boolean w1ClearedFS = false;

    public WorldOneMap() {
        super("world_one_map.txt", new InternetExplorerTileset());
        this.playerStartPosition = getMapTile(23, 13).getLocation();
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        MedkitObject medkit = new MedkitObject(getMapTile(16, 14).getLocation(), 25);
        enhancedMapTiles.add(medkit);

        CatFoodObject catFood = new CatFoodObject(getMapTile(5, 10).getLocation(), 2);
        enhancedMapTiles.add(catFood);

        FetchObject fetch = new FetchObject(getMapTile(5, 14).getLocation(), 1);
        enhancedMapTiles.add(fetch);



        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

        //old man jenkins
        Elder elder = new Elder(9, getMapTile(25, 14).getLocation());
        elder.setInteractScript(new OldManJenksScript());
        npcs.add(elder);

        //engineer
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

        SoulConsumingFlameNPC soulConsumingFlame = new SoulConsumingFlameNPC(8, getMapTile(11, 16).getLocation().subtractY(40));
        soulConsumingFlame.setInteractScript(new SoulConsumingFlameNPCScript());
        npcs.add(soulConsumingFlame);

        return npcs;
    }


    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(790,20, 24, 24, new WorldOneClearScript()));

        return triggers;
    }

    public static void SW1ClearedFS(){
        
    }

    public static boolean getW1ClearedFS(){
        return w1ClearedFS;
    }

}