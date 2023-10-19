package Maps;

import Level.Map;
import Level.NPC;
import Level.Trigger;
import Scripts.WorldThreeFloors.upLevelScript;
import Scripts.WorldThreeFloors.downLevelScript;
import Tilesets.CommonTileset;

import NPCs.Redpanda;
import Scripts.WorldThreeFloors.RedpandaScript;

import java.util.ArrayList;

//all 5 world three maps are created using this class
public class WorldThreeFloors extends Map {

    // current floor number int
    public static int currentFloorNumber = 0;
    public static boolean downFromFloor;

    public static boolean redPandaFlagState(){
        return RedpandaScript.setRedPandaFlagState;
    }

    // current floor number getter
    public static int getCurrentFloorNumber() {
        return currentFloorNumber;
    }

    // current floor number setter
    public static void setCurrentFloorNumber(int newFloorNumber) {
        currentFloorNumber = newFloorNumber;
    }

    public static void setDownFromFloorTrue(){
        downFromFloor = true;
    }


    // setup maps
    public WorldThreeFloors(int floorNum) {
        super("world_three_" + floorNum + "_map.txt", new CommonTileset());

        // player starting position floor 0
        if (floorNum == 0) {
            if (downFromFloor == false){
                //starting positon when teleported to world three
                this.playerStartPosition = getMapTile(8, 11).getLocation();
            }
            else{
                //starting position when exited building in world three
                this.playerStartPosition = getMapTile(8, 8).getLocation();
            }

                
        }

        // player starting position floors 1-4
        else {
            this.playerStartPosition = getMapTile(8, 15).getLocation();
        }
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // floor zero triggers
        if (currentFloorNumber == 0) {
            // front door entrance trigger
            triggers.add(new Trigger(328, 338, 160, 24, new upLevelScript()));
        }

        // floor one triggers
        if (currentFloorNumber == 1) {
            // front door exit trigger
            triggers.add(new Trigger(328, 796, 160, 24, new downLevelScript()));
            // stairs up trigger
            triggers.add(new Trigger(512, 670, 40, 100, new upLevelScript()));
        }

        // floor two triggers
        if (currentFloorNumber == 2) {
            // stairs up trigger
            triggers.add(new Trigger(512, 670, 40, 100, new upLevelScript()));
            // stairs down trigger
            triggers.add(new Trigger(268, 670, 40, 100, new downLevelScript()));
        }

        // floor three triggers
        if (currentFloorNumber == 3) {
            // stairs up trigger
            triggers.add(new Trigger(512, 670, 40, 100, new upLevelScript()));
            // stairs down trigger
            triggers.add(new Trigger(268, 670, 40, 100, new downLevelScript()));
        }

        // floor four triggers
        if (currentFloorNumber == 4) {
            // stairs down trigger
            triggers.add(new Trigger(268, 670, 40, 100, new downLevelScript()));
        }

        return triggers;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // floor zero npcs
        if (currentFloorNumber == 0) {
            Redpanda redpanda = new Redpanda(5, getMapTile(1, 11).getLocation().subtractY(40));
            redpanda.setInteractScript(new RedpandaScript());
            npcs.add(redpanda);
        }

        return npcs;
    }
}