package Maps;

import java.util.ArrayList;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import NPCs.World2.W2SBCustomer1;
import NPCs.World2.W2StarbucksEmployee;
import Scripts.WorldTwoMap.W2SBCustomer1Script;
import Scripts.WorldTwoMap.W2SBCustomer2Script;
import Scripts.WorldTwoMap.W2SBCustomer3Script;
import Scripts.WorldTwoMap.W2StarbucksEmployeeScript;
import Tilesets.SafariTileset;

public class W2StarbucksMap extends Map {
    public W2StarbucksMap() {
        super("w2_starbucks_map.txt", new SafariTileset(), 4);
        this.playerStartPosition = getMapTile(0, 0).getLocation();
        textbox.setStyle(Style.WORLDTWO);
    }

    @Override
    public void setupMap() {
        super.setupMap();
        SoundPlayer.playMusic(MusicTracks.SHOP);
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        W2StarbucksEmployee starEm = new W2StarbucksEmployee(1, getMapTile(12, 1).getLocation());
        starEm.setInteractScript(new W2StarbucksEmployeeScript());
        npcs.add(starEm);

        W2SBCustomer1 customer1 = new W2SBCustomer1(2, getMapTile(5, 4).getLocation());
        customer1.setInteractScript(new W2SBCustomer1Script());
        npcs.add(customer1);

        W2SBCustomer1 customer2 = new W2SBCustomer1(3, getMapTile(7, 4).getLocation());
        customer2.setInteractScript(new W2SBCustomer2Script());
        npcs.add(customer2);

        W2SBCustomer1 customer3 = new W2SBCustomer1(4, getMapTile(9, 4).getLocation());
        customer3.setInteractScript(new W2SBCustomer3Script());
        npcs.add(customer3);

        return npcs;
    }
}