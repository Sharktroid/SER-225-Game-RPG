package Scripts.WorldTwoMap;

import Scripts.BasicItemScript;
import Game.SoundPlayer;
import Game.SoundPlayer.SoundEffects;
import Items.Fragment;
import Level.Textbox.Style;

public class W2SpotifyFragScript extends BasicItemScript {

    public W2SpotifyFragScript() {
        super(new Fragment(null));
    }

    @Override
    protected void onPickup() {
        setTextboxStyle(Style.WORLDTWO);
        setNPCName("");
        SoundPlayer.playSoundEffect(SoundEffects.FRAGMENTGET);
        addTextToTextboxQueue(String.format("You found a %s!", item.getName()));
        setFlag("w2FoundFrag3");
    }
}
