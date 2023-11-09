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

// blue shirt
public class Engineer extends NPC {
    protected boolean isInteracting = false;
    protected SpriteFont playGame;


    public Engineer(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Noface1.png"), 25, 25), "STAND_LEFT");

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
                            .withScale(3)
                            .withBounds(7, 0, 11, 20)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0))
                           .withScale(3)
                           .withBounds(7, 0, 11, 20)
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


            //textbox.draw(graphicsHandler);
            graphicsHandler.drawFilledRectangle(getCalibratedXLocation(), getCalibratedYLocation()-10,50,15, java.awt.Color.white);
            playGame.draw(graphicsHandler);
        }

    }





}
