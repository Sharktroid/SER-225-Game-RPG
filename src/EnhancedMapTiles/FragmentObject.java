package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Game.SoundPlayer;
import Game.SoundPlayer.SoundEffects;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Items.Fetch;
import Items.Fragment;
import Level.EnhancedMapTile;
import Level.MapEntityStatus;
import Level.Player;
import Level.PlayerState;
import Level.TileType;
import Utils.Point;

public class FragmentObject extends EnhancedMapTile {

    public FragmentObject(Point location, int fetchCountAmount) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Fragment.png"), 36, 36),
                TileType.NOT_PASSABLE);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (player.overlaps(this) && player.getPlayerState() == PlayerState.WALKING) {
            SoundPlayer.playSoundEffect(SoundEffects.ITEMGET);
            player.addItem(new Fragment(player));
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0)).withScale(1).build();
        return new GameObject(x, y, frame);
    }
}

