package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;

import SpriteFont.SpriteFont;
import Utils.Point;
// import java.awt.Color;

import java.util.HashMap;

// blue shirt
public class W1EngineerPartner extends NPC {
    protected boolean isInteracting = false;
    protected SpriteFont playGame;


    public W1EngineerPartner(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("w1Sprites.png"), 25, 25), "STAND_LEFT");

    }


    public void update(Player player) {
        super.update();

        //if player can talk to npc, textbox pops up
        if (intersects(player.getInteractionRange()))
        {
            isInteracting = true;
        }else
        {
            isInteracting = false;
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(5, 1))
                            .withScale(3)
                            .withBounds(7, 0, 11, 20)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(5, 1))
                           .withScale(3)
                           .withBounds(7, 0, 11, 20)
                           .build()
           });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {

        super.draw(graphicsHandler);

 

    }





}
