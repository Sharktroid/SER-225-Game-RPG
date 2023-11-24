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
        setNPCName("System");
        setTextboxStyle(Style.WORLDONE);

        if (isFlagSet("worldOneComplete")) {
            lockPlayer();
            showTextbox();
            addTextToTextboxQueue("Collected All Fragments!");
            addTextToTextboxQueue("The Firefox is summoning you.");
            SoundPlayer.stopMusic();
            SoundPlayer.playSoundEffect(SoundEffects.WORLDCLEAR);
        }
    }

    @Override
    protected void cleanup() {
      
        if (isFlagSet("worldOneComplete")){
            hideTextbox();
            unlockPlayer();
            setFlag("teleportToHub");
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