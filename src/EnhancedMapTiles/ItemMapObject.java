package EnhancedMapTiles;

import Builders.FrameBuilder;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.Item;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.MapEntityStatus;
import Level.Player;
import Level.PlayerState;
import Level.TileType;
import Scripts.BasicItemScript;
import Utils.Point;

public class ItemMapObject extends EnhancedMapTile {

    public ItemMapObject(Point location, Item item) {
        super(location.x, location.y, new SpriteSheet(item.getSprite(), item.getSpriteWidth(), item.getSpriteHeight()),
                TileType.NOT_PASSABLE);
        this.interactScript = new BasicItemScript(item);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (player.overlaps(this) && player.getPlayerState() == PlayerState.WALKING) {
            interactScript.setIsActive(true);
            map.setActiveInteractScript(interactScript);
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0))
                .withScale(1)
                .build();
        return new GameObject(x, y, frame);
    }

}