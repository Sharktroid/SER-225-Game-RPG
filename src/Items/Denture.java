package Items;

import Engine.ImageLoader;
import GameObject.Item;
import Level.Player;

public class Denture extends Item {

    public Denture(Player player) {
        super(player);
        name = "Dentures";
        description = "Marked 'Property Of Jenkins'...a bit slimey too";
        sprite = ImageLoader.load("Denture.png");
        spriteWidth = 60;
        spriteHeight = 41;
    }
}
