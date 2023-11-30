



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


public class W2PB5 extends NPC {
    protected boolean isInteracting = false;
    protected SpriteFont playGame;


    public W2PB5(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("PB5.png"), 38, 80), "STAND_RIGHT");

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
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale((float) 1)
                            .withBounds(2, 0, 38, 80)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0))
                           .withScale((float) 1)
                           .withBounds(2, 0, 38, 80)
                           .build()
           });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {

        super.draw(graphicsHandler);

        if (isInteracting == true)
        {
            graphicsHandler.drawFilledRectangle(getCalibratedXLocation()-2, getCalibratedYLocation()-22,54,19, java.awt.Color.black);
            playGame = new SpriteFont("...", getCalibratedXLocation()+13, getCalibratedYLocation()-30, "Comic Sans", 20, java.awt.Color.black);


            //textbox.draw(graphicsHandler);
            graphicsHandler.drawFilledRectangle(getCalibratedXLocation()-2, getCalibratedYLocation()-20,50,15, java.awt.Color.white);
            playGame.draw(graphicsHandler);
        }

    }





}

