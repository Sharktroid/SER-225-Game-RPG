package Scripts.WorldThreeMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

public class W3OutsideEmployeeScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTHREE);
        showTextbox();

        entity.facePlayer(player);

        if (!isFlagSet("hasTalkedToOutsideGuy")) {
            setNPCName("Employee");
            addTextToTextboxQueue("PLEASE HELP! EVERYONE'S ACTING WEIRD INSIDE!!");
            addTextToTextboxQueue("LIKE THEY'RE INFECTED OR SOMETHING!!");
        } else if (isFlagSet("hasTalkedToOutsideGuy") ) {
            setNPCName("Employee");
            addTextToTextboxQueue("I'M SO SCARED!!!");
        } else if (isFlagSet("hasTalkedToOutsideGuy") && isFlagSet("hasTalkedToCEO")) {
            setNPCName("Employee");
            addTextToTextboxQueue("You cured everyone? You're amazing!");
            addTextToTextboxQueue("Thank you, stranger.");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        if (!isFlagSet("hasTalkedToOutsideGuy")) {
            setFlag("hasTalkedToOutsideGuy");
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
