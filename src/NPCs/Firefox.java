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
import Utils.Direction;
import Utils.Point;
import java.awt.Color;

import java.util.HashMap;

public class Firefox extends NPC {
    protected boolean isInteracting = false;
    protected SpriteFont playGame;
    protected Direction directionFacing;


    public Firefox(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("firefoxSheet.png"), 32, 32), "NO_ORB_RIGHT");

    }


    public void update(Player player) {
        super.update();
        directionFacing = getFacingDirection(player);

        if (!PlayLevelScreen.getFlagManager().isFlagSet("hasTalkedToFirefox1") && !PlayLevelScreen.getFlagManager().isFlagSet("hasTalkedToFirefox2") && !PlayLevelScreen.getFlagManager().isFlagSet("hasTalkedToFirefox3")) {
            if (directionFacing == Direction.RIGHT) {
                this.currentAnimationName = "NO_ORB_RIGHT";
            } else if (directionFacing == Direction.LEFT) {
                this.currentAnimationName = "NO_ORB_LEFT";
            }
        } else if (PlayLevelScreen.getFlagManager().isFlagSet("hasTalkedToFirefox1") && !PlayLevelScreen.getFlagManager().isFlagSet("hasTalkedToFirefox2") && !PlayLevelScreen.getFlagManager().isFlagSet("hasTalkedToFirefox3")) {
            if (directionFacing == Direction.RIGHT) {
                this.currentAnimationName = "LITTLE_ORB_RIGHT";
            } else if (directionFacing == Direction.LEFT) {
                this.currentAnimationName = "LITTLE_ORB_LEFT";
            }
        } else if (PlayLevelScreen.getFlagManager().isFlagSet("hasTalkedToFirefox1") && PlayLevelScreen.getFlagManager().isFlagSet("hasTalkedToFirefox2") && !PlayLevelScreen.getFlagManager().isFlagSet("hasTalkedToFirefox3")) {
            if (directionFacing == Direction.RIGHT) {
                this.currentAnimationName = "MOST_ORB_RIGHT";
            } else if (directionFacing == Direction.LEFT) {
                this.currentAnimationName = "MOST_ORB_LEFT";
            }
        } else if (PlayLevelScreen.getFlagManager().isFlagSet("hasTalkedToFirefox1") && PlayLevelScreen.getFlagManager().isFlagSet("hasTalkedToFirefox2") && PlayLevelScreen.getFlagManager().isFlagSet("hasTalkedToFirefox3")) {
            if (directionFacing == Direction.RIGHT) {
                this.currentAnimationName = "FULL_ORB_RIGHT";
            } else if (directionFacing == Direction.LEFT) {
                this.currentAnimationName = "FULL_ORB_LEFT";
            }
        }

        //if player can talk to npc, textbox pops up
        if (intersects(player.getInteractionRange()))
        {
            isInteracting = true;
        }else
        {
            isInteracting = false;
        }

    }

    // public void facePlayer(Player player) {
    //     if (map.getFlagManager().isFlagSet("hasTalkedToFirefox1")) {
    //         if (Math.round(getBoundsX2()) - (getBounds().getWidth() / 2) < Math.round(player.getBoundsX2())) {
    //             this.currentAnimationName = "LITTLE_ORB_RIGHT";
    //         }
    //         else if (Math.round(getBoundsX1()) + (getBounds().getWidth() / 2) > Math.round(player.getBoundsX1())) {
    //             this.currentAnimationName = "LITTLE_ORB_LEFT";
    //         }
    //     } else {
    //         if (Math.round(getBoundsX2()) - (getBounds().getWidth() / 2) < Math.round(player.getBoundsX2())) {
    //             this.currentAnimationName = "NO_ORB_RIGHT";
    //         }
    //         else if (Math.round(getBoundsX1()) + (getBounds().getWidth() / 2) > Math.round(player.getBoundsX1())) {
    //             this.currentAnimationName = "NO_ORB_LEFT";
    //         }
    //     }
    // }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("NO_ORB_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3)
                            .withBounds(2, 2, 29, 28)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put("NO_ORB_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0))
                           .withScale(3)
                           .withBounds(2, 2, 29, 28)
                           .build()
           });
           put("LITTLE_ORB_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 1))
                            .withScale(3)
                            .withBounds(2, 2, 29, 28)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put("LITTLE_ORB_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 1))
                           .withScale(3)
                           .withBounds(2, 2, 29, 28)
                           .build()
           });
           put("MOST_ORB_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 2))
                            .withScale(3)
                            .withBounds(2, 2, 29, 28)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put("MOST_ORB_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 2))
                           .withScale(3)
                           .withBounds(2, 2, 29, 28)
                           .build()
           });
           put("FULL_ORB_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 3))
                            .withScale(3)
                            .withBounds(2, 2, 29, 28)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put("FULL_ORB_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 3))
                           .withScale(3)
                           .withBounds(2, 2, 29, 28)
                           .build()
           });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {

        super.draw(graphicsHandler);

        if (isInteracting == true)
        {
            graphicsHandler.drawFilledRectangle(getCalibratedXLocation()+18, getCalibratedYLocation()-12,54,19, Color.black);
            playGame = new SpriteFont("...", getCalibratedXLocation()+35, getCalibratedYLocation()-20, "Comic Sans", 20, Color.black);


            //textbox.draw(graphicsHandler);
            graphicsHandler.drawFilledRectangle(getCalibratedXLocation()+20, getCalibratedYLocation()-10,50,15, Color.white);
            playGame.draw(graphicsHandler);
        }

    }





}
