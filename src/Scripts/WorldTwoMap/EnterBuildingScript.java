package Scripts.WorldTwoMap;

import Level.Script;
import Level.ScriptState;

//script to go up a level in world three
public class EnterBuildingScript extends Script {

    private int enteringBuildingNum;

    public EnterBuildingScript(int enteringNum) {
        super();
        enteringBuildingNum = enteringNum;
    }

    @Override
    protected void setup() {
        lockPlayer();
        switch (enteringBuildingNum) {
            case(1):{
                addTextToTextboxQueue("enterBuilding1");
            }
            case(2):{
                addTextToTextboxQueue("enterBuilding2");
            }
            case(3):{
                addTextToTextboxQueue("enterBuilding3");
            }
            case(4):{
                addTextToTextboxQueue("enterBuilding4");
            }
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        switch (enteringBuildingNum) {
            case(1):{
                setFlag("enterBuilding1");
                setFlag("wentIntoBuilding1");

                unsetFlag("wentOutOfBuilding1");
                unsetFlag("wentOutOfBuilding2");
                unsetFlag("wentOutOfBuilding3");
                unsetFlag("wentOutOfBuilding4");
                break;
            }
            case(2):{
                setFlag("enterBuilding2");
                setFlag("wentIntoBuilding2");

                unsetFlag("wentOutOfBuilding1");
                unsetFlag("wentOutOfBuilding2");
                unsetFlag("wentOutOfBuilding3");
                unsetFlag("wentOutOfBuilding4");
                break;
            }
            case(3):{
                setFlag("enterBuilding3");
                setFlag("wentIntoBuilding3");

                unsetFlag("wentOutOfBuilding1");
                unsetFlag("wentOutOfBuilding2");
                unsetFlag("wentOutOfBuilding3");
                unsetFlag("wentOutOfBuilding4");
                break;
            }   
            case(4):{
                setFlag("enterBuilding4");
                setFlag("wentIntoBuilding4");

                unsetFlag("wentOutOfBuilding1");
                unsetFlag("wentOutOfBuilding2");
                unsetFlag("wentOutOfBuilding3");
                unsetFlag("wentOutOfBuilding4");
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