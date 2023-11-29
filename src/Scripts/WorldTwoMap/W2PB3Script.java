package Scripts.WorldTwoMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to sloth npc
public class W2PB3Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDONE);
        setNPCName("Sir Gallard IV");
        showTextbox();

        
       String[] selections = {"uh, Dale?", "Did you order this from Spotify?"};
       String[] answers = {"DALE! You must be a man of taste", "Oh yea I was just there today"};

        
        if (isFlagSet("hasTalkedToDJ") && (!isFlagSet("gaveToPB3"))){
            entity.facePlayer(player);
            addTextToTextboxQueue( "oh sweet it's the vinly i ordered?");
            addTextToTextboxQueue( "Pitbull saved my life, \n buying this was the least I could do");
            W2DJScript.removeSamples(1);
            setFlag("gaveToPB3");
            

        }

       else if ((!isFlagSet("hasTalkedToPB3")) && (!isFlagSet("hasTalkedToDJ")))  {
            entity.facePlayer(player);
            addTextToTextboxQueue( "Live, Laugh, Pitbull");

            setFlag("hasTalkedToPB3");
        }
       
        else {
            entity.facePlayer(player);
            addTextToTextboxQueue( "I hope he talks to me next time I'm there");

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




