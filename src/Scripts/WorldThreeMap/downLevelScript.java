package Scripts.WorldThreeMap;

import Level.Script;
import Level.ScriptState;
import Maps.WorldThreeMap;

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
        int currFloor = WorldThreeMap.getCurrentFloorNumber();
        currFloor--;
        WorldThreeMap.setCurrentFloorNumber(currFloor);
        end();
        return ScriptState.COMPLETED;
    }
}