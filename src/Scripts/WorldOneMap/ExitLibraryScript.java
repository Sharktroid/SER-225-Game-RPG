package Scripts.WorldOneMap;

import Level.Script;
import Level.ScriptState;
import Maps.W1GMap;

//script to go up a floor in world three
public class ExitLibraryScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        setFlag("exitedLibrary");
    }

    @Override
    public ScriptState execute() {
        start();
        end();
        return ScriptState.COMPLETED;
    }
}