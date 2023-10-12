package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.MapEntityStatus;
import Level.Player;
import Level.PlayerState;
import Level.TileType;
import Utils.Point;

public class catFood extends EnhancedMapTile {
    private int healingAmount;
    private int speedAmount;

    public catFood(Point location, int speedAmount) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("CatFood.png"), 25, 25),
                TileType.NOT_PASSABLE);
        this.speedAmount = speedAmount;
    }

    @Override
    public void update(Player cat) {
        super.update(cat);
        if (cat.overlaps(this) && cat.getPlayerState() == PlayerState.WALKING) {
            // Update the player's speed by adding the hspeedAmount
            cat.usecatFood(this, 2);
            System.out.println("catFood Grabbed");
            // Remove the catFood from the game or set its state to inactive
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    // increases speed amount by 2
    public int getSpeedAmount() {
        return 2;
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0))
                .withScale(2)// put f after number if a decimil
                .build();
        return new GameObject(x, y, frame);
    }

}