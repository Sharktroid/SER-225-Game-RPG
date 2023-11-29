package Scripts;

import Game.SoundPlayer;
import Game.SoundPlayer.SoundEffects;
import Items.Fragment;



public class BasicFragmentScript extends BasicItemScript {

    public BasicFragmentScript() {
        super(new Fragment(null));
    }

    @Override
    protected void onPickup() {
        SoundPlayer.playSoundEffect(SoundEffects.FRAGMENTGET);
        addTextToTextboxQueue(String.format("You got a %s!", item.getName()));
    }
}
