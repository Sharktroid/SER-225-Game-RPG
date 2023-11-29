package Scripts.WorldTwoMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to sloth npc
public class W2PB5Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDONE);
        setNPCName("");
        showTextbox();

        
       String[] selections = {"", ""};
       String[] answers = {"", ""};

        
        if (isFlagSet("hasTalkedToDJ") && (!isFlagSet("gaveToPB"))){
            entity.facePlayer(player);
            addTextToTextboxQueue( "is that mine!?!");
            addTextToTextboxQueue( "Thank you so much for finding this \n i dont know what I'd do without it");
            W2DJScript.removeSamples(1);
            setFlag("gaveToPB1");
            

        }

       else if ((!isFlagSet("hasTalkedToPB")) && (!isFlagSet("hasTalkedToDJ")))  {
            entity.facePlayer(player);
            addTextToTextboxQueue( "I can't believe they even threw in a shirt...");

            setFlag("hasTalkedToPB");
        }
       
        else {
            entity.facePlayer(player);
            addTextToTextboxQueue( "He's really been there and done that with me");

        }
        
    }

    

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

      
        
    }

    @Override
    public ScriptState execute() {
        // setTextboxStyle(Textbox.Style.WORLDTWO);
        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }
}




