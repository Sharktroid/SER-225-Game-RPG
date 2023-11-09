package Scripts.WorldOneMap;

import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;
import Maps.WorldOneMap;


// trigger script at beginning of game to set that heavy emotional plot
public class WorldOneClearScript extends Script {

    @Override
    protected void setup() {
        setTextboxStyle(Style.WORLDONE);
        lockPlayer();
        showTextbox();

        //if 
        ///addTextToTextboxQueue("World One Cleared!");
        //addTextToTextboxQueue("The Firefox is summoning you.");



        if (WorldOneMap.FragmentCount < 3) {
            showTextbox();
            addTextToTextboxQueue( "FragmentCount is "+WorldOneMap.FragmentCount+".");
            
            
        }
        else if (WorldOneMap.FragmentCount == 3){
            showTextbox();
            addTextToTextboxQueue("Collected All Fragments!");
            addTextToTextboxQueue("The Firefox is summoning you.");
        }
    }

    @Override
    protected void cleanup() {
        if(WorldOneMap.FragmentCount < 3){
            hideTextbox();
            unlockPlayer();
            player.moveX(10);
        }else if (WorldOneMap.FragmentCount == 3){
            hideTextbox();
            unlockPlayer();
            setFlag("worldOneCleared");
        }
        
        
        WorldOneMap.w1ClearedFS = true;
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("worldOneCleared")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }
}
