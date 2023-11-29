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
    }

    @Override
    protected void cleanup() {
        unlockPlayer();

        switch (enteringBuildingNum) {
            case(1):{
                setFlag("enterBuilding1");
                setFlag("wentIntoBuilding1");
                break;
            }
            case(2):{
                setFlag("enterBuilding2");
                setFlag("wentIntoBuilding2");
                break;
            }
            case(3):{
                setFlag("enterBuilding3");
                setFlag("wentIntoBuilding3");
                break;
            }   
            case(4):{
                setFlag("enterBuilding4");
                setFlag("wentIntoBuilding4");
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