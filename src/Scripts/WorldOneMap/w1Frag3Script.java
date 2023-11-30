package Scripts.WorldOneMap;

import Scripts.BasicItemScript;
import Game.SoundPlayer;
import Game.SoundPlayer.SoundEffects;
import Items.Fragment;
import Level.Textbox.Style;

public class w1Frag3Script extends BasicItemScript {

    public w1Frag3Script() {
        super(new Fragment(null));
    }

    @Override
    protected void onPickup() {
        SoundPlayer.playSoundEffect(SoundEffects.FRAGMENTGET);
        addTextToTextboxQueue(String.format("You found a %s!", item.getName()));
        setFlag("w1FoundFrag3");
    }
}
