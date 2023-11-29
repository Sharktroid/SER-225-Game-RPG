package Scripts.WorldTwoMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to sloth npc
public class W2PB1Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTWO);
        setNPCName("Dustin");
        showTextbox();

        
       String[] selections = {"Yea it was hand \npicked by Pitbull himself\n", "Yea this is yours"};
       String[] answers = {"Are you being serious?? NO WAY!", "it's about time this came"};

        
        if (isFlagSet("hasTalkedToDJ") && (!isFlagSet("gaveToPB1"))){
            entity.facePlayer(player);
            addTextToTextboxQueue( "is that mine!?!");
            addTextToTextboxQueue( "Thank you so much for finding this \n i dont know what I'd do without it");
            W2DJScript.removeSamples(1);
            setFlag("gaveToBP1");
            

        }

       else if ((!isFlagSet("hasTalkedToPB1")) && (!isFlagSet("hasTalkedToDJ")))  {
            entity.facePlayer(player);
            addTextToTextboxQueue( "I can't believe they even threw in a shirt...");

            setFlag("hasTalkedToBP1");
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




