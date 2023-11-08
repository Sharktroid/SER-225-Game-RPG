package Items;

import Screens.PlayLevelScreen;
import GameObject.Item;
import Level.Player;
import Scripts.WorldOneMap.TestfoxScript;

public class Fragment extends Item {
    private final int fragmentCount = 1;

    public Fragment(Player player) {
        super(player);
        name = "Fragment";
        usable = true;
    }

    @Override
    public void use() {
    TestfoxScript.addFragmentCount(fragmentCount);
    
        
    }
}
