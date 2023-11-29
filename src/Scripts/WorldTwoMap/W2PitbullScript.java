package Scripts.WorldTwoMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;
import Level.Player;

// script for talking to sloth npc
public class W2PitbullScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDONE);
        setNPCName("Mr Worldwide");
        showTextbox();

        
        String[] selections = {"uhhhh Mr. Pitbull?\n Are you seriously a dog?", "\nI'm a really big fan"};
        String[] answers = {"..did he really tell you? ... one job", "...woof"};

       
         if ((isFlagSet("hasTalkedToDJ")) && (!isFlagSet("hasTalkedToPitbull"))  ){
            entity.facePlayer(player);
            addTextToTextboxQueue( "woof", selections, answers);
            addTextToTextboxQueue( "I can't believe he's still telling people about me");
            addTextToTextboxQueue( "This is almost the final straw, i've had it with him");
            addTextToTextboxQueue( "Do me a solid and act like none of this happened");

            setFlag("hasTalkedToPitbull");
        } 
        

       /*  leave as comment for now 
        else if (isFlagSet("friendOfGarfunkle")){

        }   */
       
        
        else {
            entity.facePlayer(player);
            addTextToTextboxQueue( "woof");
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



