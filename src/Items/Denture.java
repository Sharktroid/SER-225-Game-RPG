package Items;

import Engine.ImageLoader;
import GameObject.Item;
import Level.Player;

public class Denture extends Item {

    public Denture(Player player) {
        super(player);
        name = "Dentures";
        sprite = ImageLoader.load("Denture.png");
    }
}
