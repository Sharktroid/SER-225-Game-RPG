package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.ItemMapObject;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Items.CatFood;
import Items.Fragment;
import Items.Medkit;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import Level.Textbox.Style;
import NPCs.W1Infected1;
import NPCs.W1Infected2;
import NPCs.W1Infected3;
import NPCs.W1Infected4;
import NPCs.W1Infected5;
import NPCs.Engineer;
import NPCs.EngineerPartner;
import NPCs.Librarian;
import NPCs.Normal1;
import NPCs.Normal2;
import NPCs.OldManJenks;
import Scripts.WorldOneMap.*;
import NPCs.SoulConsumingFlameNPC;
import Scripts.BasicFragmentScript;
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

        // green
        W1Infected1 btlInfected1 = new W1Infected1(5, getMapTile(20, 8).getLocation());
        btlInfected1.setInteractScript(new W1Infected1Script());
        npcs.add(btlInfected1);

        // purple
        W1Infected2 btlInfected2 = new W1Infected2(6, getMapTile(23, 21).getLocation());
        btlInfected2.setInteractScript(new W1Infected2Script());
        npcs.add(btlInfected2);

        // red
        W1Infected3 btlInfected3 = new W1Infected3(7, getMapTile(3, 8).getLocation());
        btlInfected3.setInteractScript(new W1Infected3Script());
        npcs.add(btlInfected3);        


        // pink
        W1Infected4 btlInfected4 = new W1Infected4(8, getMapTile(34, 32).getLocation());
        btlInfected4.setInteractScript(new W1Infected4Script());
        npcs.add(btlInfected4);


        // green
        W1Infected5 btlInfected5 = new W1Infected5(9, getMapTile(7, 28).getLocation());
        btlInfected5.setInteractScript(new W1Infected5Script());
        npcs.add(btlInfected5);

        // normal npcs

        // red
        Normal1 normal1 = new Normal1(10, getMapTile(14, 30).getLocation());
        normal1.setInteractScript(new Normal1Script());
        npcs.add(normal1);
        // pink
        Normal2 normal2 = new Normal2(11, getMapTile(1, 17).getLocation());
        normal2.setInteractScript(new Normal2Script());
        npcs.add(normal2);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(1130, 624, 24, 24, new WorldOneClearScript()));

        triggers.add(new Trigger(1820, 300, 100, 20, new EnterLibraryScript()));

        return triggers;
    }

    public static void addFragmentCount(int fragmentCount) {
        FragmentCount = FragmentCount + fragmentCount;
        System.out.println("Added Fragment count");
    }

    public int getFragmentCount() {
        return FragmentCount;
    }
}