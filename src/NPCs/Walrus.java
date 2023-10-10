package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.Rectangle;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import Level.Textbox;
import Utils.Point;

import java.util.HashMap;

// This class is for the walrus NPC
public class Walrus extends NPC {
    protected Textbox textbox; 
    protected GraphicsHandler graphicsHandler;

    public Walrus(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Walrus.png"), 24, 24), "STAND_LEFT");
    }

 
    public void update(Player player) {
        super.update();
        graphicsHandler = new GraphicsHandler();

    
        //if player can talk to npc, textbox pops up
        if (intersects(player.getInteractionRange()))
        {
            System.out.println("hello");
            //graphicsHandler.drawFilledRectangle(100,100,100,100,java.awt.Color.green);


        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3)
                            .withBounds(7, 13, 11, 7)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0))
                           .withScale(3)
                           .withBounds(7, 13, 11, 7)
                           .build()
           });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        //graphicsHandler.drawFilledRectangle(100,100,100,100,java.awt.Color.green);
    }





}
