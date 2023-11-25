package Scripts.WorldThreeMap;

import Level.Script;
import Level.ScriptState;

//script to go down a level in world three
public class DownLevelScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        setFlag("goDownLevel");
        unsetFlag("wentUpLevel");
        setFlag("wentDownLevel");
    }

    @Override
    public ScriptState execute() {
        start();
        end();
        return ScriptState.COMPLETED;
    }
}