package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Level.Textbox;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.awt.image.BufferedImage;

import org.w3c.dom.css.Rect;

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
    protected KeyLocker keyLocker = new KeyLocker();

    protected Rectangle rect;
    protected int counter = 1;
    protected int x,y;
    protected BufferedImage image = ImageLoader.load("RedPanda.png"); //first image played in slideshow
    

    //plays slideshow after pressing play
    public Slideshow(ScreenCoordinator screenCoordinator)
    {

        this.screenCoordinator = screenCoordinator;
        screenCoordinator.setGameState(GameState.MENU);
        gameState = GameState.MENU;
        

    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'initialize'");

        background = new TitleScreenMap();
        background.setAdjustCamera(false);

    }

    //GOES THROUGH SLIDESHOW
    @Override
    public void update() {

    // if space key is released, unlock space key
    if (Keyboard.isKeyUp(Key.SPACE)) {
        keyLocker.unlockKey(Key.SPACE);
    }
    
    // if space is pressed and space is not locked
    if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
        
        if(counter == 1)
        {
            x=300;
            y=300;

            image = ImageLoader.load("Walrus.png"); //second image in slideshow
            counter ++;
        }else if(counter == 2)
        {
            x=500;
            y=500;
            image = ImageLoader.load("Beaver.png"); //third image in slideshow
            counter ++;
        }else if (counter == 3)
        {
            x=100;
            y=100;
            image = ImageLoader.load("Catsuit.png"); //fourth image in slideshow
            counter ++;

        }else if (counter == 4)
        {
            screenCoordinator.setGameState(GameState.LEVEL);
        }

        
       
        
        // lock space key
        keyLocker.lockKey(Key.SPACE);
    }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        //background.draw(graphicsHandler);
        graphicsHandler.drawImage(image,0,0,300,300);

    }
}



    

