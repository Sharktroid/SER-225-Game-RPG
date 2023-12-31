package Scripts.WorldOneMap;

import Scripts.BasicItemScript;
import Game.SoundPlayer;
import Game.SoundPlayer.SoundEffects;
import Items.Denture;


public class W1DentureScript extends BasicItemScript {

    public W1DentureScript() {
        super(new Denture(null));
    }

    @Override
    protected void onPickup() {
        setNPCName("");
        SoundPlayer.playSoundEffect(SoundEffects.ITEMGET);
        addTextToTextboxQueue(String.format("You found a %s!", item.getName()));
        setFlag("hasFoundDentures");
    }
}
