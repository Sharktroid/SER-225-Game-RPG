package Maps;

import Level.Map;
import Level.NPC;
import Level.Script;
import Level.Trigger;
import NPCs.Walrus;
import Scripts.SimpleTextScript;
import Scripts.WorldOneMap.WalrusScript;
import Scripts.WorldThreeFloors.upLevelScript;
import Scripts.WorldThreeFloors.downLevelScript;
import Tilesets.CommonTileset;

import java.util.ArrayList;

public class WorldThreeFloors extends Map {
    public WorldThreeFloors(int floorNum) {
        super("world_three_" + floorNum + "_map.txt", new CommonTileset());
        if (floorNum == 0){
            this.playerStartPosition = getMapTile(8,11).getLocation();
        }
        else{
            this.playerStartPosition = getMapTile(8, 15).getLocation();
        }
        

    }

    public static int currentFloorNumber = 0;

    public static int getCurrentFloorNumber(){
        return currentFloorNumber;
    }

    public static void setCurrentFloorNumber(int newFloorNumber){
        currentFloorNumber = newFloorNumber;
    }


    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        
        if (currentFloorNumber == 0){
            triggers.add(new Trigger(328, 338, 160, 24, new upLevelScript()));
        }
        

        //front door leave trigger
        triggers.add(new Trigger(328, 796, 160, 24, new downLevelScript()));
        
        
        //up level trigger
        triggers.add(new Trigger(512, 670, 40, 100, new upLevelScript()));
        
        //down level trigger
        triggers.add(new Trigger(268, 670, 40, 100, new downLevelScript()));
    
        return triggers;
    }

    /*@Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        // remember to import NPC and Scripts for new NPC

        if (floorNumber == 0) {
            Walrus walrus = new Walrus(1, getMapTile(4, 28).getLocation().subtractY(40));
            walrus.setInteractScript(new WalrusScript());
            npcs.add(walrus);
        }

        return npcs;
    }*/
}