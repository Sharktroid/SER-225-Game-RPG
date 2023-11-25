package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Game.SoundPlayer;
import Game.SoundPlayer.MusicTracks;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Slideshow extends Screen
{
    protected GameState gameState;
	protected GameState previousGameState;

    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected SpriteFont playGame;
    protected SpriteFont credits;
    protected Map background;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;

    protected Rectangle rect;
    protected int counter = 1;
    protected int x,y;
    protected BufferedImage image = ImageLoader.load("SlideshowOne.png"); //first image played in slideshow
    protected SpriteFont spaceToContinue;


    //plays slideshow after pressing play
    public Slideshow(ScreenCoordinator screenCoordinator)
    {

        this.screenCoordinator = screenCoordinator;
        screenCoordinator.setGameState(GameState.MENU);
        gameState = GameState.MENU;


    }

    @Override
    public void initialize() {
        //System.out.println("slideshow");

        background = new TitleScreenMap();
        background.setAdjustCamera(false);

        spaceToContinue = new SpriteFont("PRESS SPACE TO CONTINUE", 200, 519, "Comic Sans", 30, new Color(49, 207, 240));
        SoundPlayer.playMusic(MusicTracks.INTRO);

    }

    //GOES THROUGH SLIDESHOW
    @Override
    public void update() {

    // if space key is released, unlock space key
    if (Keyboard.isKeyUp(Key.SPACE)) {
        KeyLocker.unlockKey(Key.SPACE);
    }

    // if space is pressed and space is not locked
    if (Keyboard.isKeyDown(Key.SPACE) && !KeyLocker.isKeyLocked(Key.SPACE)) {

        if(counter == 1)
        {
            x=300;
            y=300;

            image = ImageLoader.load("SlideshowTwo.png"); //second image in slideshow
            counter ++;
        }else if(counter == 2)
        {
            x=500;
            y=500;
            image = ImageLoader.load("SlideshowThree.png"); //third image in slideshow
            counter ++;
        }else if (counter == 3)
        {
            x=100;
            y=100;
            image = ImageLoader.load("SlideshowFour.png"); //fourth image in slideshow
            counter ++;

        }else if (counter == 4)
        {
            image = ImageLoader.load("SlideshowFive.png"); //fifth image in slideshow
            counter ++;

        }else if (counter == 5)
        {
            screenCoordinator.setGameState(GameState.LEVEL);
        }




        // lock space key
        KeyLocker.lockKey(Key.SPACE);
    }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        //background.draw(graphicsHandler);
        graphicsHandler.drawImage(image,0,0,800,605);
        spaceToContinue.draw(graphicsHandler);

    }
}





