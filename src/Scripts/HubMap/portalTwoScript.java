package Scripts.HubMap;

import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class portalTwoScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.HUBWORLD);
        showTextbox();

        if (!isFlagSet("unlockedPortal2") && !isFlagSet("worldOneComplete")) {
            addTextToTextboxQueue("clear world one then talk to the firefox");
        } else if (!isFlagSet("unlockedPortal2") && isFlagSet("worldOneComplete")){
            addTextToTextboxQueue("world one cleared. talk to the firefox)");
        }else if (isFlagSet("unlockedPortal2") && isFlagSet("worldOneComplete")){
            addTextToTextboxQueue("world one cleared and firefox talked to");
            addTextToTextboxQueue("teleporting to world 2");
        }else if(isFlagSet("unlockedPortal2") && !isFlagSet("worldOneComplete")){
            addTextToTextboxQueue("teleport flag error: portalTwoScript");
        }
    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();

        if (!isFlagSet("unlockedPortal2") && !isFlagSet("worldOneComplete")) {
            player.moveY(10);
            
        } else if(!isFlagSet("unlockedPortal2") && isFlagSet("worldOneComplete")){
            player.moveY(10);
        }
        else if(isFlagSet("unlockedPortal2") && isFlagSet("worldOneComplete")){
            setFlag("portalTwoActivated");
        }else {
            System.out.println("huhhhhhhhh");
        }
    }

    @Override
    public ScriptState execute() {

        if (!isFlagSet("portalTwoActivated")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }
}