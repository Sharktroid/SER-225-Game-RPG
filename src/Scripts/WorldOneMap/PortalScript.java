package Scripts.WorldOneMap;

import Level.Script;
import Level.ScriptState;

// script for talking to tree with hole in it
public class PortalScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        addTextToTextboxQueue("Teleporting to World Zero");
    }

    @Override
    protected void cleanup() {
        setFlag("portalActivated");
        
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("portalActivated")) {
                return ScriptState.RUNNING;
        }
        return ScriptState.COMPLETED;
    }
}