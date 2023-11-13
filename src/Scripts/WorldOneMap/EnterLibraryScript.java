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

        addTextToTextboxQueue("entering library");
    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();


        setFlag("enterLibrary");
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