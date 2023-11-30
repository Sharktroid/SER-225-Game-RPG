package Scripts.WorldOneMap;

import Scripts.BasicItemScript;
import Items.Fragment;

public class w1Frag3Script extends BasicItemScript {

    public w1Frag3Script() {
        super(new Fragment(null));
    }

    @Override
    protected void onPickup() {
        super.onPickup();
        setFlag("w1FoundFrag3");
    }
}
