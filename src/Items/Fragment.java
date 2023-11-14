package Items;

import GameObject.Item;
import Level.Player;
import Maps.W1GMap;

public class Fragment extends Item {
    private final int fragmentCount = 1;

    public Fragment(Player player) {
        super(player);
        name = "Fragment";
        usable = true;
    }

    @Override
    public void use() {
    W1GMap.addFragmentCount(fragmentCount);


    }
}
