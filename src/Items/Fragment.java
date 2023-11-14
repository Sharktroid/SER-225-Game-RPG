package Items;

import Engine.ImageLoader;
import GameObject.Item;
import Level.Player;

public class Fragment extends Item {

    public Fragment(Player player) {
        super(player);
        name = "Fragment";
        usable = true;
        sprite = ImageLoader.load("Fragment.png");
    }
}
