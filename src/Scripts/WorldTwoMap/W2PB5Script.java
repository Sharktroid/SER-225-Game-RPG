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

        setTextboxStyle(Style.WORLDTWO);
        setNPCName("PSY");
        showTextbox();

        
       String[] selections = {"uhh do I know you from somewhere", "PSY you're a fan of Pitbull"};
       String[] answers = {"You must have good taste in music, \n it's me, PSY!", "\nof course I am who isn't?"};

        
        if (isFlagSet("hasTalkedToDJ") && (!isFlagSet("gaveToPB5"))){
            entity.facePlayer(player);
            addTextToTextboxQueue( "Oh that must be my vinly you have there ");
            addTextToTextboxQueue( "I can't wait to listen to this");
            addTextToTextboxQueue( "Pitbull made me the man I am today ");
            //W2DJScript.removeSamples(1);
            setFlag("gaveToPB5");
            

        }

       else if ((!isFlagSet("hasTalkedToPB5")) && (!isFlagSet("hasTalkedToDJ")))  {
            entity.facePlayer(player);
            addTextToTextboxQueue( "I hope he likes me...");

            setFlag("hasTalkedToPB5");
        }
       
        else {
            entity.facePlayer(player);
            addTextToTextboxQueue( "Whoop whoop...");

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




