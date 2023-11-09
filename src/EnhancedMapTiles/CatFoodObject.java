package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Game.SoundPlayer;
import Game.SoundPlayer.SoundEffects;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Items.CatFood;
import Level.EnhancedMapTile;
import Level.MapEntityStatus;
import Level.Player;
import Level.PlayerState;
import Level.TileType;
import Utils.Point;

public class CatFoodObject extends EnhancedMapTile {
    // private int healingAmount;
    // private int speedAmount;

    public CatFoodObject(Point location, int speedAmount) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("CatFood.png"), 25, 25),
                TileType.NOT_PASSABLE);
        // this.speedAmount = speedAmount;
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (player.overlaps(this) && player.getPlayerState() == PlayerState.WALKING) {
            SoundPlayer.playSoundEffect(SoundEffects.ITEMGET);
            player.addItem(new CatFood(player));
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    // increases speed amount by 2
    // public int getSpeedAmount() {
    //     return 2;
    // }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0))
                .withScale(2)// put f after number if a decimil
                .build();
        return new GameObject(x, y, frame);
    }

}