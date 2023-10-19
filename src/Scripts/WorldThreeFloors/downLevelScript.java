package Scripts.WorldThreeFloors;

import Level.Script;
import Level.ScriptState;
import Maps.WorldThreeFloors;

//script to go down a floor in world three
public class downLevelScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        setFlag("wentDownLevel");
    }

    @Override
    public ScriptState execute() {
        start();
        int currFloor = WorldThreeFloors.getCurrentFloorNumber();
        currFloor--;
        WorldThreeFloors.setCurrentFloorNumber(currFloor);
        end();
        return ScriptState.COMPLETED;
    }
}