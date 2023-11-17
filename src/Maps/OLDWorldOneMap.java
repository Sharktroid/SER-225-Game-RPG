package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.CatFoodObject;
import EnhancedMapTiles.FetchObject;
import EnhancedMapTiles.FragmentObject;
import EnhancedMapTiles.MedkitObject;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import Level.Textbox.Style;
import NPCs.Engineer;
import NPCs.EngineerPartner;
import NPCs.Librarian;
import NPCs.OldManJenks;
import Scripts.WorldOneMap.EngineerScript;
import Scripts.WorldOneMap.EnterLibraryScript;
import Scripts.WorldOneMap.LibrarianScript;
import Scripts.WorldOneMap.OldManJenksScript;

import Scripts.WorldOneMap.WorldOneClearScript;
import Tilesets.InternetExplorerTileset;

public class OLDWorldOneMap extends Map {

    public static int FragmentCount = 0;

    public OLDWorldOneMap() {
        super("world_one_map.txt", new InternetExplorerTileset());
        this.playerStartPosition = getMapTile(23, 13).getLocation();
        SoundPlayer.playMusic(MusicTracks.WORLDONEBGM);
        textbox.setStyle(Style.WORLDONE);
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

        FragmentObject fragment = new FragmentObject(getMapTile(6, 3).getLocation(), 1);
        enhancedMapTiles.add(fragment);

        FragmentObject fragment2 = new FragmentObject(getMapTile(6, 3).getLocation(), 1);
        enhancedMapTiles.add(fragment2);

        FragmentObject fragment3 = new FragmentObject(getMapTile(6, 3).getLocation(), 1);
        enhancedMapTiles.add(fragment3);




        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

        OldManJenks oldManJenks = new OldManJenks(1, getMapTile(25, 14).getLocation());
        oldManJenks.setExistenceFlag("hasFinishedOMJ");
        oldManJenks.setInteractScript(new OldManJenksScript());
        npcs.add(oldManJenks);

        Engineer engineer = new Engineer(2, getMapTile(35, 24).getLocation());
        engineer.setInteractScript(new EngineerScript());
        npcs.add(engineer);

        EngineerPartner engineerPartner = new EngineerPartner(3, getMapTile(36, 24).getLocation());
        engineerPartner.setInteractScript(new EngineerScript());
        npcs.add(engineerPartner);

        Librarian librarian = new Librarian(4, getMapTile(36, 9).getLocation());
        librarian.setInteractScript(new LibrarianScript());
        npcs.add(librarian);

        /*//infected npcs
        //green
        Infected1 infected1 = new Infected1(5, getMapTile(20, 8).getLocation());
        infected1.setInteractScript(new W1Infected1Script());
        npcs.add(infected1);
        //purple
        Infected2 infected2 = new Infected2(6, getMapTile(23, 21).getLocation());
        infected2.setInteractScript(new W1Infected2Script());
        npcs.add(infected2);
        //red
        Infected3 infected3 = new Infected3(7, getMapTile(3, 8).getLocation());
        infected3.setInteractScript(new W1Infected3Script());
        npcs.add(infected3);
        //pink
        Infected4 infected4 = new Infected4(8, getMapTile(34, 32).getLocation());
        infected4.setInteractScript(new W1Infected4Script());
        npcs.add(infected4);
        //green
        Infected5 infected5 = new Infected5(9, getMapTile(7, 28).getLocation());
        infected5.setInteractScript(new W1Infected5Script());
        npcs.add(infected5);

        //normal npcs
        //red
        Normal1 normal1 = new Normal1(10, getMapTile(14, 30).getLocation());
        normal1.setInteractScript(new Normal1Script());
        npcs.add(normal1);
        //pink
        Normal2 normal2 = new Normal2(11, getMapTile(1, 17).getLocation());
        normal2.setInteractScript(new Normal2Script());
        npcs.add(normal2);


        SoulConsumingFlameNPC soulConsumingFlame = new SoulConsumingFlameNPC(12, getMapTile(11, 16).getLocation().subtractY(40));
        soulConsumingFlame.setInteractScript(new SoulConsumingFlameNPCScript());
        npcs.add(soulConsumingFlame);


        /*Testfox testfox = new Testfox(1, getMapTile(20, 13).getLocation().subtractY(40));
        testfox.setInteractScript(new TestfoxScript());
        npcs.add(testfox);*/

        return npcs;

    }


    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(1130,624, 24, 24, new WorldOneClearScript()));

        triggers.add(new Trigger(1820, 300, 100, 20, new EnterLibraryScript()));

        return triggers;
    }

    public static void addFragmentCount(int fragmentCount) {
        FragmentCount = FragmentCount + fragmentCount ;
        System.out.println("Added Fragment count");
    }

     public int getFragmentCount() {
        return FragmentCount;
    }
}