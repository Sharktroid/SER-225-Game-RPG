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

import java.util.HashMap;

public class W1Infected3 extends NPC {
    protected boolean isInteracting = false;
    protected SpriteFont playGame;

    public W1Infected3(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("w1Sprites.png"), 25, 25), "STAND_RIGHT");
    }

    public void update(Player player) {
        super.update(player);
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
                    new FrameBuilder(spriteSheet.getSprite(2, 0))
                            .withScale(3)
                            .withBounds(1, 0, 22, 20)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(2, 0))
                           .withScale(3)
                           .withBounds(1, 0, 22, 20)
                           .build()
           });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        
        if (isInteracting == true)
        {
            graphicsHandler.drawFilledRectangle(getCalibratedXLocation()+10, getCalibratedYLocation()-22,54,19, java.awt.Color.black);
            playGame = new SpriteFont("...", getCalibratedXLocation()+27, getCalibratedYLocation()-30, "Comic Sans", 20, java.awt.Color.black);


            //textbox.draw(graphicsHandler);
            graphicsHandler.drawFilledRectangle(getCalibratedXLocation()+12, getCalibratedYLocation()-20,50,15, java.awt.Color.white);
            playGame.draw(graphicsHandler);
        }
    }
}

