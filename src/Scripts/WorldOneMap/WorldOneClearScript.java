package Scripts.WorldOneMap;

import Game.SoundPlayer;
import Game.SoundPlayer.SoundEffects;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;
import Maps.W1GMap;


// trigger script at beginning of game to set that heavy emotional plot
public class WorldOneClearScript extends Script {

    @Override
    protected void setup() {
        setTextboxStyle(Style.WORLDONE);
        lockPlayer();
        showTextbox();


        if (W1GMap.FragmentCount < 3) {
            showTextbox();
            addTextToTextboxQueue( "FragmentCount is "+W1GMap.FragmentCount+".");
            
            
        }
        else if (W1GMap.FragmentCount == 3){
            showTextbox();
            addTextToTextboxQueue("Collected All Fragments!");
            addTextToTextboxQueue("The Firefox is summoning you.");
            SoundPlayer.stopMusic();
            SoundPlayer.playSoundEffect(SoundEffects.WORLDCLEAR);
        }
    }

    @Override
    protected void cleanup() {
        if(W1GMap.FragmentCount < 3){
            hideTextbox();
            unlockPlayer();
            player.moveX(10);
        }else if (W1GMap.FragmentCount == 3){
            hideTextbox();
            unlockPlayer();
            setFlag("worldOneComplete");
            setFlag("teleportToHub");
        }
        
        
        
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("worldOneComplete")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }
}
