package Scripts.WorldThreeMap;

import Level.Script;
import Level.ScriptState;

//script to go up a level in world three
public class UpLevelScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        setFlag("goUpLevel");
        unsetFlag("wentDownLevel");
        setFlag("wentUpLevel");
        
    }

    @Override
    public ScriptState execute() {
        start();
        end();
        return ScriptState.COMPLETED;
    }
}