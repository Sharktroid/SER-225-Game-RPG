package Items;

import Engine.ImageLoader;
import GameObject.Item;
import Level.Player;

public class CatFood extends Item {
    private final float speedModifier = 3;
    private final int speedModifierDuration = 600;

    public CatFood(Player player) {
        super(player);
        name = "Cat Food";
        usable = true;
        sprite = ImageLoader.load("CatFood.png");
    }

    @Override
    public void use() {
        player.modifySpeed(speedModifier, speedModifierDuration);
    }
}
