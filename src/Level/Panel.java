package Level;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import SpriteFont.SpriteFont;

public class Panel {

    private Color smallFillColor;
    private Color smallBorderColor;
    private Color bigFillColor;
    private Color bigBorderColor;

    private BufferedImage logo;
    private boolean displayWindowName;

    private int x;
    private int y;
    private int width;
    private int height;
    private int arcWidth;
    private int arcHeight;
    private int borderThickness;

    private final BufferedImage xpLogo = ImageLoader.load("windowsxplogo.png", Color.MAGENTA);
    private final BufferedImage fireFoxLogo = ImageLoader.load("firefoxlogo.png", Color.MAGENTA);

    public Panel(int x, int y, int width, int height, boolean displayWindowName) {
        this.x = x;
        this.y = y + 25;
        this.width = width;
        this.height = height - 25;
        this. displayWindowName = displayWindowName;
    }

    public void draw(GraphicsHandler graphicsHandler) {
        handleTextboxStyle();
        switch (Textbox.getStyle()) {
            case WORLDONE:
                graphicsHandler.drawFilledRectangleGradientWithBorder(x, y-25, width, 25, arcWidth, arcHeight, new Color(57,147,255), new Color(10,85,234), smallBorderColor, borderThickness);
                if (displayWindowName) {
                    graphicsHandler.drawFilledRectangleWithBorder(x+30, y-23, 150, 21, arcWidth, arcHeight, new Color(26,79,188), new Color(17,69,169), 2);
                }
                //white background
                graphicsHandler.drawFilledRectangle(x+width-23, y-23, 21, 21, 5, 5, new Color(217,210,226));
                //red box
                graphicsHandler.drawFilledRectangleGradient(x+width-22, y-22, 19, 19, 5, 5, new Color(240,162,141), new Color(194,48,8));
                //X
                SpriteFont X = new SpriteFont("X", x+width-18, y-22, "Arial", 17, new Color(217,210,226));
                X.drawWithParsedNewLines(graphicsHandler, 10);
                //logo
                graphicsHandler.drawImage(logo, x+5, y-20, 15, 15);
                break;
            case WORLDTWO:
                graphicsHandler.drawFilledRectangleWithBorder(x, y-25, width, 25, arcWidth, arcHeight, smallFillColor, smallBorderColor, borderThickness);
                graphicsHandler.drawFilledRectangleWithBorder(x, y-15, width, 15, 0, 0, smallFillColor, smallFillColor, borderThickness);
                if (displayWindowName) {
                    graphicsHandler.drawFilledRectangleWithBorder((x+(width/2))-(100), y-21, 200, 17, 10, 10, smallFillColor, new Color(73,73,73), borderThickness);
                //red
                graphicsHandler.drawFilledRectangleWithBorder(x+8, y-18, 11, 11, 11, 11, new Color(253,95,86), new Color(241,93,84), 1);
                //yellow
                graphicsHandler.drawFilledRectangleWithBorder(x+27, y-18, 11, 11, 11, 11, new Color(254,189,47), new Color(241,180,51), 1);
                //green
                graphicsHandler.drawFilledRectangleWithBorder(x+46, y-18, 11, 11, 11, 11, new Color(40,200,64), new Color(77,189,84), 1);
                }
                break;
            case WORLDTHREE:
                graphicsHandler.drawFilledRectangleWithBorder(x, y-30, width, 30, arcWidth, arcHeight, smallFillColor, smallBorderColor, borderThickness);
                if (displayWindowName) {
                    graphicsHandler.drawFilledRectangleWithBorder(x+5, y-25, 100, 35, 15, 15, bigFillColor, bigBorderColor, borderThickness);
                }
                X = new SpriteFont("X", x+width-19, y-21, "Arial", 15, new Color(54,68,85));
                X.drawWithParsedNewLines(graphicsHandler, 10);
                break;
            default: //hubworld
                graphicsHandler.drawFilledRectangleWithBorder(x, y-30, width, 30, arcWidth, arcHeight, smallFillColor, smallBorderColor, borderThickness);
                if (displayWindowName) {
                    graphicsHandler.drawFilledRectangleWithBorder(x+30, y-27, 150, 24, 5, 5, new Color(255,255,255), new Color(210,210,214), 1);
                }
                graphicsHandler.drawImage(logo, x+8, y-22, 14, 14);
                X = new SpriteFont("X", x+width-19, y-22, "Arial", 13, Textbox.getFontColor());
                X.drawWithParsedNewLines(graphicsHandler, 10);
                break;
        }
        //lower box
        if (Textbox.getStyle().equals(Textbox.Style.WORLDTWO)) {
            graphicsHandler.drawFilledRectangleWithBorder(x, y, width, height, arcWidth, arcHeight, bigFillColor, bigBorderColor, borderThickness);
            graphicsHandler.drawFilledRectangleWithBorder(x, y, width, height-10, 0, 0, bigFillColor, bigFillColor, borderThickness);
        } else {
            graphicsHandler.drawFilledRectangleWithBorder(x, y, width, height, arcWidth, arcHeight, bigFillColor, bigBorderColor, borderThickness);
        }
    }

    private void handleTextboxStyle() {
        switch (Textbox.getStyle()) {
            case HUBWORLD:
                hubWorldTextbox();
                break;
            case WORLDONE:
                worldOneTextbox();
                break;
            case WORLDTWO:
                worldTwoTextbox();
                break;
            case WORLDTHREE:
                worldThreeTextbox();
                break;
        }
    }

    private void hubWorldTextbox() {
        logo = fireFoxLogo;
        arcWidth = 0;
        arcHeight = 0;
        borderThickness = 1;
        bigFillColor = new Color(249,249,251);
        bigBorderColor = new Color(249,249,251);
        smallFillColor = new Color(240,240,245);
        smallBorderColor = new Color(240,240,245);
    }

    private void worldOneTextbox() {
        logo = xpLogo;
        arcWidth = 0;
        arcHeight = 0;
        borderThickness = 3;
        bigFillColor = new Color(239,235,222);
        bigBorderColor = new Color(10,85,233);
        smallFillColor = new Color(10,85,233);
        smallBorderColor = new Color(10,85,233);
    }

    private void worldTwoTextbox() {
        arcWidth = 15;
        arcHeight = 15;
        borderThickness = 1;
        bigFillColor = new Color(27,27,27);
        bigBorderColor = new Color(27,27,27);
        smallFillColor = new Color(50,50,50);
        smallBorderColor = new Color(50,50,50);
    }

    private void worldThreeTextbox() {
        arcWidth = 0;
        arcHeight = 0;
        borderThickness = 1;
        bigFillColor = new Color(255,255,255);
        bigBorderColor = new Color(255,255,255);
        smallFillColor = new Color(223,225,231);
        smallBorderColor = new Color(223,225,231);
    }

    public void setY(int newY) {
        y = newY;
    }
}