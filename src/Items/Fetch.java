package Items;

import GameObject.Item;
import Level.Player;
import Scripts.WorldOneMap.GarfunkleScript;
import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import GameObject.Frame;
import Level.*;
import Utils.Point;

public class Fetch extends Item {
    private final int fetchCount = 1;

    public Fetch(Player player) {
        super(player);
        name = "Lasagna";
        usable = true;
    }

    @Override
    public void use() {
        
        GarfunkleScript.setCurrentFetch(fetchCount);
        System.out.println("fetch count changed in fetch.java");
    
            
    }

   
}

   
    
