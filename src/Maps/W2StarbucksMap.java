package Maps;

import java.util.ArrayList;

import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Level.NPC;
import Level.Textbox.Style;
import NPCs.W2PB3;
import NPCs.World2.W2GreenSB;
import NPCs.World2.W2RedSB;
import NPCs.World2.W2PinkSB;
import NPCs.World2.W2StarbucksEmployee;
// import Scripts.WorldTwoMap.W2SBCustomer1Script;
// import Scripts.WorldTwoMap.W2SBCustomer2Script;
// import Scripts.WorldTwoMap.W2SBCustomer3Script;
import Scripts.WorldTwoMap.W2StarbucksEmployeeScript;
import Level.Trigger;
import Scripts.WorldTwoMap.ExitBuildingScript;
import Scripts.WorldTwoMap.W2PB3Script;
import Tilesets.SafariTileset;

public class W2StarbucksMap extends Map {
    public W2StarbucksMap() {
        super("w2_starbucks_map.txt", new SafariTileset(), 3);
        this.playerStartPosition = getMapTile(7, 10).getLocation().adjustXY(12, -12);
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

        W2StarbucksEmployee starEm = new W2StarbucksEmployee(1, getMapTile(13, 1).getLocation());
        starEm.setInteractScript(new W2StarbucksEmployeeScript());
        npcs.add(starEm);

        W2GreenSB customer1 = new W2GreenSB(2, getMapTile(5, 4).getLocation());
        // customer1.setInteractScript(new W2SBCustomer1Script());
        npcs.add(customer1);

        W2RedSB customer2 = new W2RedSB(3, getMapTile(7, 7).getLocation());
        // customer2.setInteractScript(new W2SBCustomer2Script());
        npcs.add(customer2);

        W2PinkSB customer3 = new W2PinkSB(4, getMapTile(3, 8).getLocation());
        // customer3.setInteractScript(new W2SBCustomer3Script());
        npcs.add(customer3);

        W2PB3 W2PB3 = new W2PB3(3, getMapTile(10, 4).getLocation().subtractY(40));
        W2PB3.setInteractScript(new W2PB3Script());
        npcs.add(W2PB3); 

        return npcs;

    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(336, 552, 96, 24, new ExitBuildingScript(3)));

        return triggers;
    }
}