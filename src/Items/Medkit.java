package Items;

import GameObject.Item;
import Level.Player;

public class Medkit extends Item {
    private final int healingAmount = 25;

    public Medkit(Player player) {
        super(player);
        name = "Medkit";
        usable = true;
    }
    

    @Override
    public void use() {
        player.addCurrentHealth(healingAmount);
        
    }
}
