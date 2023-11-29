



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


public class W2PB1 extends NPC {
    protected boolean isInteracting = false;
    protected SpriteFont playGame;


    public W2PB1(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("PB1.png"), 30, 60), "STAND_RIGHT");

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
                            .withBounds(2, 0, 20, 20)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0))
                           .withScale((float) 1)
                           .withBounds(2, 0, 20, 20)
                           .build()
           });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {

        super.draw(graphicsHandler);

        if (isInteracting == true)
        {
            playGame = new SpriteFont("ENTER", getCalibratedXLocation()+2, getCalibratedYLocation()-12, "Comic Sans", 15, Color.black);
            graphicsHandler.drawFilledRectangle(getCalibratedXLocation(), getCalibratedYLocation()-10,50,15, java.awt.Color.white);
            playGame.draw(graphicsHandler);
        }

    }





}

