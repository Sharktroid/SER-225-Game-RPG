package Scripts.WorldThreeFloors;

import Level.Script;
import Level.ScriptState;
import Maps.WorldThreeFloors;

public class downLevelScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        addTextToTextboxQueue("going to floor " + WorldThreeFloors.getCurrentFloorNumber());

    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
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