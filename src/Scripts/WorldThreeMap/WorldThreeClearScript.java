package Scripts.WorldThreeMap;

import Game.SoundPlayer;
import Game.SoundPlayer.SoundEffects;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;


// trigger script at beginning of game to set that heavy emotional plot
public class WorldThreeClearScript extends Script {

    @Override
    protected void setup() {
        setNPCName("System");
        setTextboxStyle(Style.WORLDTHREE);
        lockPlayer();
        showTextbox();
        if (!isFlagSet("worldThreeComplete")){
            addTextToTextboxQueue("talk to the CEO to get the last fragment.");
        }
        if (isFlagSet("worldThreeComplete")) {
            
            addTextToTextboxQueue("Collected All Fragments!");
            addTextToTextboxQueue("The Firefox is summoning you.");
            SoundPlayer.stopMusic();
            SoundPlayer.playSoundEffect(SoundEffects.WORLDCLEAR);
        }
    }

    @Override
    protected void cleanup() {

        if (isFlagSet("worldThreeComplete")){
            hideTextbox();
            unlockPlayer();
            setFlag("teleportToHub");
        }else{
            hideTextbox();
            unlockPlayer();
            player.moveDown(48);
            player.moveRight(48);
        }



    }

    @Override
    public ScriptState execute() {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        return ScriptState.COMPLETED;
    }
}