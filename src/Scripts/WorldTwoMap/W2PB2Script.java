package Scripts.WorldTwoMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to sloth npc
public class W2PB2Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTWO);
        setNPCName("Felipe");
        showTextbox();

        
       String[] selections = {"I think this is yours", "Did you buy a Pitbull Album"};
       String[] answers = {"MY GLORIOUS VINLY", "Oh no way its finally here"};

        
        if (isFlagSet("hasTalkedToDJ") && (!isFlagSet("gaveToPB2"))){
            entity.facePlayer(player);
            addTextToTextboxQueue( "I cant wait to make my kids listen to this");
            addTextToTextboxQueue( "Thank you for this \n dale");
            W2DJScript.removeSamples(1);
            setFlag("gaveToPB2");
            

        }

       else if ((!isFlagSet("hasTalkedToPB2")) && (!isFlagSet("hasTalkedToDJ")))  {
            entity.facePlayer(player);
            addTextToTextboxQueue( "His music just resonates with me");

            setFlag("hasTalkedToPB2");
        }
       
        else {
            entity.facePlayer(player);
            addTextToTextboxQueue( "I wish my head was that shiny...");

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




