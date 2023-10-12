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
import Utils.Direction;
import Utils.Point;

public class Medkit extends EnhancedMapTile {
    private int healingAmount;

    public Medkit(Point location, int healingAmount) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Apple.png"), 45, 43),
                TileType.NOT_PASSABLE);
        this.healingAmount = healingAmount;
    }

    @Override
    public void update(Player cat) {
        super.update(cat);
        if (cat.overlaps(this) && cat.getPlayerState() == PlayerState.WALKING) {
            // Update the player's health by adding the healingAmount
            cat.useMedkit(this, 31);
            System.out.println("Apple Grabbed");
            // Remove the medkit from the game or set its state to inactive
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    // sets healing amount of apple/medkit to 25
    public int getHealingAmount() {
        return 25;
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0)).withScale(1).build();
        return new GameObject(x, y, frame);
    }
}
