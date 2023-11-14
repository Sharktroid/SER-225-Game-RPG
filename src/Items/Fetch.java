package Items;

import Engine.ImageLoader;
import GameObject.Item;
import Level.Player;
import Scripts.WorldOneMap.GarfunkleScript;

public class Fetch extends Item {
    private final int fetchCount = 1;

    public Fetch(Player player) {
        super(player);
        name = "Lasagna";
        usable = true;
        sprite = ImageLoader.load("Lasanga.png");
    }

    @Override
    public void use() {
        GarfunkleScript.setCurrentFetch(fetchCount);
        System.out.println("fetch count changed in fetch.java");
    }
}