package Scripts.HubMap;

import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class portalThreeScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Style.HUBWORLD);
        showTextbox();

        if (!isFlagSet("unlockedPortal3") && !isFlagSet("worldOneComplete") && !isFlagSet("worldTwoComplete")) {
            addTextToTextboxQueue("clear world one and two then talk to firefox");
        } else if (!isFlagSet("unlockedPortal3") && isFlagSet("worldOneComplete") && !isFlagSet("worldTwoComplete")) {
            addTextToTextboxQueue("world one complete. complete world two then talk to the firefox)");
        }else if (!isFlagSet("unlockedPortal3") && isFlagSet("worldOneComplete") && isFlagSet("worldTwoComplete")) {
            addTextToTextboxQueue("world one and two complete. talk to the firefox");  
        }else if (isFlagSet("unlockedPortal3") && isFlagSet("worldOneComplete") && isFlagSet("worldTwoComplete")) {
            addTextToTextboxQueue("world one and two complete and firefox talked to");
            addTextToTextboxQueue("teleporting to world 3");
        }else{
            addTextToTextboxQueue("teleport flag error: portalThreeScript");
        }
    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();

        if (!isFlagSet("unlockedPortal3") && !isFlagSet("worldOneComplete") && !isFlagSet("worldTwoComplete")) {
            player.moveY(10);
        } else if(!isFlagSet("unlockedPortal3") && isFlagSet("worldOneComplete") && !isFlagSet("worldTwoComplete")){
            player.moveY(10);
        } else if(!isFlagSet("unlockedPortal3") && isFlagSet("worldOneComplete") && isFlagSet("worldTwoComplete")){
            player.moveY(10);
        }else if(isFlagSet("unlockedPortal3") && isFlagSet("worldOneComplete") && isFlagSet("worldTwoComplete")){
            setFlag("portalThreeActivated");
        }else {
            System.out.println("huhhhhhhhh");
        }
    }

    @Override
    public ScriptState execute() {

        if (!isFlagSet("portalThreeActivated")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }
}