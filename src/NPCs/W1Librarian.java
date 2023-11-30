



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

// This class is for the walrus NPC
public class W1Librarian extends NPC {
    protected boolean isInteracting = false;
    protected SpriteFont playGame;


    public W1Librarian(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Noface5.png"), 25, 25), "STAND_LEFT");

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
                            .withBounds(1, 0, 22, 20)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0))
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
