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

        addTextToTextboxQueue("entring library");
    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();


        setFlag("enteredLibrary");
    }

    @Override
    public ScriptState execute() {

        if (!isFlagSet("enteredLibrary")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }
}