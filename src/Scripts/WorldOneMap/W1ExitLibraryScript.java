package Scripts.WorldOneMap;

import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class W1ExitLibraryScript extends Script {

    public static boolean exitedLib = false;

    @Override
    protected void setup() {
        lockPlayer();
        setNPCName("System");
        setTextboxStyle(Style.WORLDONE);
        showTextbox();

        if (!isFlagSet("w1FoundFrag3")) {
            addTextToTextboxQueue("collect the frag");

        } else {
            addTextToTextboxQueue("leaving library");
        }

    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
        if (!isFlagSet("w1FoundFrag3")) {
            player.moveY(-2);
        } else {
            setFlag("exitLibrary");
            exitedLib = true;
        }

    }

    @Override
    public ScriptState execute() {

        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        end();

        return ScriptState.COMPLETED;

    }
}