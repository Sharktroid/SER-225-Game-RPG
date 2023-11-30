package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.ItemMapObject;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Items.CatFood;
import Items.Denture;
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
import NPCs.W1Engineer;
import NPCs.W1EngineerPartner;
import NPCs.W1Librarian;
import NPCs.W1Normal1;
import NPCs.W1Normal2;
import NPCs.W1OldManJenks;
import Screens.PlayLevelScreen;
import Scripts.BasicFragmentScript;
import Scripts.WorldOneMap.*;
import Tilesets.InternetExplorerTileset;

public class W1GMap extends Map {

    public static int FragmentCount = 0;
    public static boolean exitedLib = false;


    public W1GMap() {
        super("w1gmap.txt", new InternetExplorerTileset(), 0);

        SoundPlayer.playMusic(MusicTracks.WORLDONE);

        if(PlayLevelScreen.getFlagManager() == null){
            this.playerStartPosition = getMapTile(0, 0).getLocation();
        }else if (!PlayLevelScreen.getFlagManager().isFlagSet("wentOutOfLibrary")) {
            this.playerStartPosition = getMapTile(23, 13).getLocation();
        } else if (PlayLevelScreen.getFlagManager().isFlagSet("wentOutOfLibrary")) {
            this.playerStartPosition = getMapTile(38, 6).getLocation().adjustXY(-12, -12);
        }

        textbox.setStyle(Style.WORLDONE);
    }

    @Override
    public void setupMap() {
        super.setupMap();
        SoundPlayer.playMusic(MusicTracks.WORLDONE);
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

        ItemMapObject denture = new ItemMapObject(getMapTile(6, 33).getLocation(), new Denture(null));
        denture.setInteractScript(new W1DentureScript());
        enhancedMapTiles.add(denture);
        

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

        W1OldManJenks oldManJenks = new W1OldManJenks(1, getMapTile(25, 14).getLocation());
        oldManJenks.setExistenceFlag("hasFinishedOMJ");
        oldManJenks.setInteractScript(new W1OldManJenksScript());
        npcs.add(oldManJenks);

        W1Engineer engineer = new W1Engineer(2, getMapTile(35, 24).getLocation());
        engineer.setInteractScript(new W1EngineerScript());
        npcs.add(engineer);

        W1EngineerPartner engineerPartner = new W1EngineerPartner(3, getMapTile(36, 24).getLocation());
        npcs.add(engineerPartner);

        W1Librarian librarian = new W1Librarian(4, getMapTile(36, 9).getLocation());
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
        W1Normal1 normal1 = new W1Normal1(10, getMapTile(14, 30).getLocation());
        normal1.setInteractScript(new W1Normal1Script());
        npcs.add(normal1);
        // pink
        W1Normal2 normal2 = new W1Normal2(11, getMapTile(1, 17).getLocation());
        normal2.setInteractScript(new W1Normal2Script());
        npcs.add(normal2);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(1130, 624, 24, 24, new WorldOneClearScript()));
        triggers.add(new Trigger(1752, 72, 24, 24, new W1EnterLibraryScript()));

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