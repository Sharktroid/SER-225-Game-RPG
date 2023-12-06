package Scripts.WorldTwoMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;

// script for talking to sloth npc
public class W2PB4Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDTWO);
        setNPCName("Vanessa");
        showTextbox();

        
       String[] selections = {"Hey you look like a Pitbull fan", "I think this is yours"};
       String[] answers = {"You have a good eye", "what is this?"};

        
        if (isFlagSet("hasTalkedToDJ") && (!isFlagSet("gaveToPB4"))){
            entity.facePlayer(player);
            addTextToTextboxQueue( "oh you have my Spotify order?");
            addTextToTextboxQueue( "I've been waiting for this, thanks");
            addTextToTextboxQueue( "I hope my life-sized cutout comes soon too");
            //W2DJScript.removeSamples(1);
            setFlag("gaveToPB4");
            

        }

       else if ((!isFlagSet("hasTalkedToPB4")) && (!isFlagSet("hasTalkedToDJ")))  {
            entity.facePlayer(player);
            addTextToTextboxQueue( "I hope I can see him in concert soon...");

            setFlag("hasTalkedToPB4");
        }
       
        else {
            entity.facePlayer(player);
            addTextToTextboxQueue( "I can't believe he touched my hand");

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




