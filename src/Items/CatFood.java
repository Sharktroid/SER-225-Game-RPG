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
        description = String.format("Increases movement speed by %.0f%% for %.0f seconds", speedModifier * 100,
                (Float) ((float) speedModifierDuration) / 60);
        useText = String.format("Speed increased by %.0f%% for %.0f seconds", speedModifier * 100,
                (Float) ((float) speedModifierDuration) / 60);
        sprite = ImageLoader.load("CatFood.png");
    }

    @Override
    public void use() {
        player.modifySpeed(speedModifier, speedModifierDuration);
    }
}
