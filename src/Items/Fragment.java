package Items;

import GameObject.Item;
import Level.Player;
import Maps.WorldOneMap;

public class Fragment extends Item {
    private final int fragmentCount = 1;

    public Fragment(Player player) {
        super(player);
        name = "Fragment";
        usable = true;
    }

    @Override
    public void use() {
    WorldOneMap.addFragmentCount(fragmentCount);


    }
}
