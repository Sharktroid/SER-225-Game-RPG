package Items;

import GameObject.Item;
import Level.Combatant;
import Level.Map;
import Level.Player;

public class Medkit extends Item {
    private final int healingAmount = 25;

    public Medkit(Player player) {
        super(player);
        name = "Medkit";
        usable = true;
        battleUsable = true;
    }


    @Override
    public void use() {
        player.addCurrentHealth(healingAmount);
    }

    @Override
    public void use(Combatant combatant, Map map) {
        combatant.dealDamage(-healingAmount);
        map.getTextbox().addText(String.format("The player used the %s.\nRecovered %d health.", name, healingAmount));
    }
}
