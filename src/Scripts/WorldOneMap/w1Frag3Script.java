package Scripts.WorldOneMap;

import Scripts.BasicItemScript;
import Game.SoundPlayer;
import Game.SoundPlayer.SoundEffects;
import Items.Fragment;

public class W1Frag3Script extends BasicItemScript {

    public W1Frag3Script() {
        super(new Fragment(null));
    }

    @Override
    protected void onPickup() {
        SoundPlayer.playSoundEffect(SoundEffects.FRAGMENTGET);
        addTextToTextboxQueue(String.format("You found a %s!", item.getName()));
        setFlag("w1FoundFrag3");
    }
}
