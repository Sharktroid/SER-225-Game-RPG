package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.ItemMapObject;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Items.CatFood;
import Items.Fetch;
import Items.Fragment;
import Items.Medkit;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import Level.Textbox.Style;
import NPCs.Engineer;
import NPCs.EngineerPartner;
import NPCs.Infected1;
import NPCs.Infected2;
import NPCs.Infected3;
import NPCs.Infected4;
import NPCs.Infected5;
import NPCs.Librarian;
import NPCs.Normal1;
import NPCs.Normal2;
import NPCs.OldManJenks;
import NPCs.SoulConsumingFlameNPC;
import Scripts.BasicFragmentScript;
import Scripts.WorldOneMap.EngineerScript;
import Scripts.WorldOneMap.EnterLibraryScript;
import Scripts.WorldOneMap.Infected1Script;
import Scripts.WorldOneMap.Infected2Script;
import Scripts.WorldOneMap.Infected3Script;
import Scripts.WorldOneMap.Infected4Script;
import Scripts.WorldOneMap.Infected5Script;
import Scripts.WorldOneMap.LibrarianScript;
import Scripts.WorldOneMap.Normal1Script;
import Scripts.WorldOneMap.Normal2Script;
import Scripts.WorldOneMap.OldManJenksScript;
import Scripts.WorldOneMap.SoulConsumingFlameNPCScript;
import Scripts.WorldOneMap.WorldOneClearScript;
import Tilesets.InternetExplorerTileset;

public class W1GMap extends Map {

    public static int FragmentCount = 0;

    public W1GMap() {
        super("w1gmap.txt", new InternetExplorerTileset());
        this.playerStartPosition = getMapTile(23, 13).getLocation();
        SoundPlayer.playMusic(MusicTracks.WORLDONEBGM);
        textbox.setStyle(Style.WORLDONE);
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        ItemMapObject medkit = new ItemMapObject(getMapTile(16, 14).getLocation(), new Medkit(null));
        enhancedMapTiles.add(medkit);

        ItemMapObject catFood = new ItemMapObject(getMapTile(5, 10).getLocation(), new CatFood(null));
        enhancedMapTiles.add(catFood);

        ItemMapObject fetch = new ItemMapObject(getMapTile(5, 14).getLocation(), new Fetch(null));
        enhancedMapTiles.add(fetch);

        ItemMapObject fragment = new ItemMapObject(getMapTile(6, 3).getLocation(), new Fragment(null));
        fragment.setInteractScript(new BasicFragmentScript());
        enhancedMapTiles.add(fragment);

        ItemMapObject fragment2 = new ItemMapObject(getMapTile(6, 3).getLocation(), new Fragment(null));
        fragment2.setInteractScript(new BasicFragmentScript());
        enhancedMapTiles.add(fragment2);

        ItemMapObject fragment3 = new ItemMapObject(getMapTile(6, 3).getLocation(), new Fragment(null));
        fragment3.setInteractScript(new BasicFragmentScript());
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

        //infected npcs
        //green
        Infected1 infected1 = new Infected1(5, getMapTile(20, 8).getLocation());
        infected1.setInteractScript(new Infected1Script());
        npcs.add(infected1);
        //purple
        Infected2 infected2 = new Infected2(6, getMapTile(23, 21).getLocation());
        infected2.setInteractScript(new Infected2Script());
        npcs.add(infected2);
        //red
        Infected3 infected3 = new Infected3(7, getMapTile(3, 8).getLocation());
        infected3.setInteractScript(new Infected3Script());
        npcs.add(infected3);
        //pink
        Infected4 infected4 = new Infected4(8, getMapTile(34, 32).getLocation());
        infected4.setInteractScript(new Infected4Script());
        npcs.add(infected4);
        //green
        Infected5 infected5 = new Infected5(9, getMapTile(7, 28).getLocation());
        infected5.setInteractScript(new Infected5Script());
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