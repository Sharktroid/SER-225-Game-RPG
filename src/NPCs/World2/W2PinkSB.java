package NPCs.World2;

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

import java.util.HashMap;

public class W2PinkSB extends NPC {
    protected boolean isInteracting = false;
    protected SpriteFont playGame;

    public W2PinkSB(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("safariNPC/Pink.png"), 25, 25), "STAND_LEFT");
    }


    public void update(Player player) {
        super.update(player);

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
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3)
                            .withBounds(4, 0, 17, 20)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0))
                           .withScale(3)
                           .withBounds(4, 0, 17, 20)
                           .build()
           });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);

        // if (isInteracting == true)
        // {
        //     graphicsHandler.drawFilledRectangle(getCalibratedXLocation()+10, getCalibratedYLocation()-22,54,19, java.awt.Color.black);
        //     playGame = new SpriteFont("...", getCalibratedXLocation()+27, getCalibratedYLocation()-30, "Comic Sans", 20, java.awt.Color.black);


        //     //textbox.draw(graphicsHandler);
        //     graphicsHandler.drawFilledRectangle(getCalibratedXLocation()+12, getCalibratedYLocation()-20,50,15, java.awt.Color.white);
        //     playGame.draw(graphicsHandler);
        // }
    }
}
