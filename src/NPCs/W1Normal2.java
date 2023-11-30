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
import java.awt.Color;

import java.util.HashMap;

// pink
public class W1Normal2 extends NPC {
    protected boolean isInteracting = false;
    protected SpriteFont playGame;


    public W1Normal2(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("w1Sprites.png"), 25, 25), "STAND_RIGHT");

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
                    new FrameBuilder(spriteSheet.getSprite(4, 0))
                            .withScale(3)
                            .withBounds(1, 0, 22, 20)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(4, 0))
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
            graphicsHandler.drawFilledRectangle(getCalibratedXLocation()+10, getCalibratedYLocation()-22,54,19, Color.black);
            playGame = new SpriteFont("...", getCalibratedXLocation()+27, getCalibratedYLocation()-30, "Comic Sans", 20, Color.black);


            //textbox.draw(graphicsHandler);
            graphicsHandler.drawFilledRectangle(getCalibratedXLocation()+12, getCalibratedYLocation()-20,50,15, java.awt.Color.white);
            playGame.draw(graphicsHandler);
        }


    }





}
