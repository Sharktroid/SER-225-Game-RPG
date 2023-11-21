package Scripts.WorldOneMap;

import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class EnterLibraryScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
        
        setTextboxStyle(Style.WORLDONE);
        showTextbox();

        if (!isFlagSet("hasTalkedToLibrarian")){
            addTextToTextboxQueue("The window is shattered...");

        }else{
            addTextToTextboxQueue("entering library");
        }
        
    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
        if (!isFlagSet("hasTalkedToLibrarian")){
            player.moveX(-2);
            player.moveY(-2);
        }else{
            setFlag("enterLibrary");
        }
        
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("enterLibrary")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }
}