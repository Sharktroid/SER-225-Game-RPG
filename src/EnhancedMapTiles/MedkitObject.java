package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Items.Medkit;
import Level.EnhancedMapTile;
import Level.MapEntityStatus;
import Level.Player;
import Level.PlayerState;
import Level.TileType;
import Utils.Point;

public class MedkitObject extends EnhancedMapTile {

    public MedkitObject(Point location, int healingAmount) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Apple.png"), 45, 43),
                TileType.NOT_PASSABLE);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (player.overlaps(this) && player.getPlayerState() == PlayerState.WALKING) {
            // Update the player's health by adding the healingAmount
            player.addItem(new Medkit(player));
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0)).withScale(1).build();
        return new GameObject(x, y, frame);
    }
}
