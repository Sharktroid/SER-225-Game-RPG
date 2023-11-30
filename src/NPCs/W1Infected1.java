package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import Screens.PlayLevelScreen;
import SpriteFont.SpriteFont;
import Utils.Point;
import Utils.Direction;

import java.util.HashMap;

public class W1Infected1 extends NPC {
    protected boolean isInteracting = false;
    protected SpriteFont playGame;
    //protected Direction directionFacing;

    public W1Infected1(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("w1Sprites.png"), 25, 25), "STAND_RIGHT");
    }

    public void update(Player player) {
        super.update(player);
        /*directionFacing = getFacingDirection(player);

        if (PlayLevelScreen.getFlagManager().isFlagSet("changeSprite")){
            if (directionFacing == Direction.RIGHT) {
                this.currentAnimationName = "INFECTED_STAND_RIGHT";
            }
            else if (directionFacing == Direction.LEFT) {
                this.currentAnimationName = "INFECTED_STAND_LEFT";
            }
        }else if (!PlayLevelScreen.getFlagManager().isFlagSet("changeSprite")){
            if (directionFacing == Direction.RIGHT) {
                this.currentAnimationName = "STAND_RIGHT";
            }
            else if (directionFacing == Direction.LEFT) {
                this.currentAnimationName = "STAND_LEFT";
            }
        } */


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
           put("INFECTED_STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 1))
                            .withScale(3)
                            .withBounds(7, 0, 11, 20)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put("INFECTED_STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 1))
                           .withScale(3)
                           .withBounds(7, 0, 11, 20)
                           .build()
           });
        }};
    }

    @Override
    public void setAnimation(Direction facingDirection, int frameNum){
        if (facingDirection == Direction.RIGHT && frameNum == 1) {
            this.currentAnimationName = "INFECTED_STAND_RIGHT";
        }
        else if (facingDirection == Direction.LEFT && frameNum == 1) {
            this.currentAnimationName = "INFECTED_STAND_LEFT";
        }
        else if(facingDirection == Direction.RIGHT && frameNum == 0){
            this.currentAnimationName = "STAND_RIGHT";
        }
        else if(facingDirection == Direction.LEFT && frameNum == 0){
            this.currentAnimationName = "STAND_LEFT";
        }
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

