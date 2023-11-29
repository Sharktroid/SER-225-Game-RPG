package Scripts.WorldTwoMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;


public class W2Outside5Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTWO);
        setNPCName("Finder");
        showTextbox();

        //entity.facePlayer(player);

        if (!isFlagSet("hasTalkedToBeaver")) {

            addTextToTextboxQueue("Hi Cat!");
            addTextToTextboxQueue("I found some glowing orb today");
            addTextToTextboxQueue("I can show you it another time soon");
        }

        if (isFlagSet("hasTalkedToBeaver")){

            addTextToTextboxQueue("Hello again.");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        setFlag("hasTalkedToBeaver");
    }


    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasTalkedToBeaver")){
            start();


            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;

            }
            end();
            return ScriptState.COMPLETED;

        }else if (isFlagSet("hasTalkedToBeaver")){
            start();
            if (!isTextboxQueueEmpty()){
                return ScriptState.RUNNING;
            }
            end();
            return ScriptState.COMPLETED;
        }
        return ScriptState.COMPLETED;
    }

}

