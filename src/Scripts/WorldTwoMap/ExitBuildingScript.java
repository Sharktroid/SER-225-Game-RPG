package Scripts.WorldTwoMap;

import Level.Script;
import Level.ScriptState;

//script to go up a level in world three
public class ExitBuildingScript extends Script {

    private int exitingBuildingNum;

    public ExitBuildingScript(int exitingNum) {
        super();
        exitingBuildingNum = exitingNum;
    }

    @Override
    protected void setup() {
        lockPlayer();
        switch (exitingBuildingNum) {
            case(1):{
                addTextToTextboxQueue("exitingBuilding1");
            }
            case(2):{
                addTextToTextboxQueue("exitingBuilding2");
            }
            case(3):{
                addTextToTextboxQueue("exitingBuilding3");
            }
            case(4):{
                addTextToTextboxQueue("exitingBuilding4");
            }
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        switch (exitingBuildingNum) {
            case(1):{
                setFlag("exitBuilding1");
                setFlag("wentOutOfBuilding1");
                unsetFlag("wentIntoBuilding1");
                break;
            }
            case(2):{
                setFlag("exitBuilding2");
                setFlag("wentOutOfBuilding2");
                unsetFlag("wentIntoBuilding2");
                break;
            }
            case(3):{
                setFlag("exitBuilding3");
                setFlag("wentOutOfBuilding3");
                unsetFlag("wentIntoBuilding3");
                break;
            }
            case(4):{
                setFlag("exitBuilding4");
                setFlag("wentOutOfBuilding4");
                unsetFlag("wentIntoBuilding4");
                break;
            }
            
        }
    }

    @Override
    public ScriptState execute() {
        start();
        end();
        return ScriptState.COMPLETED;
    }
}