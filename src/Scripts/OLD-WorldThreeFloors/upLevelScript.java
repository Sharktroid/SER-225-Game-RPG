/*
package Scripts.WorldThreeFloors;

import Level.Script;
import Level.ScriptState;
import Maps.WorldThreeFloors;

//script to go up a floor in world three
public class upLevelScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        setFlag("wentUpLevel");
    }

    @Override
    public ScriptState execute() {
        start();
        int currFloor = WorldThreeFloors.getCurrentFloorNumber();
        currFloor++;
        WorldThreeFloors.setCurrentFloorNumber(currFloor);
        end();
        return ScriptState.COMPLETED;
    }
}
*/