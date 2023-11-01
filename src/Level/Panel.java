package Level;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import SpriteFont.SpriteFont;

public class Panel {
    private Textbox.Style textboxStyle;

    private Color smallFillColor;
    private Color smallBorderColor;
    private Color bigFillColor;
    private Color bigBorderColor;
    private Color bigFontColor;

    private BufferedImage logo;
    private boolean displayWindowName;

    private int x;
    private int y;
    private int width;
    private int height;
    private int arcWidth;
    private int arcHeight;
    private int borderThickness;

    public Panel(Textbox.Style textboxStyle, Color bigFontColor, int x, int y, int width, int height, boolean displayWindowName) {
        this.textboxStyle = textboxStyle;
        this.bigFontColor = bigFontColor;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this. displayWindowName = displayWindowName;
        handleTextboxStyle();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        if (textboxStyle.equals(Textbox.Style.WORLDONE)) {
                graphicsHandler.drawFilledRectangleGradientWithBorder(x, y-25, width, 25, arcWidth, arcHeight, new Color(57,147,255), new Color(10,85,234), smallBorderColor, borderThickness);
                if (displayWindowName) {
                    graphicsHandler.drawFilledRectangleWithBorder(x+30, y-23, 150, 21, arcWidth, arcHeight, new Color(26,79,188), new Color(17,69,169), 2);
                }
            } else if (textboxStyle.equals(Textbox.Style.WORLDTWO)) {
                graphicsHandler.drawFilledRectangleWithBorder(x, y-25, width, 25, arcWidth, arcHeight, smallFillColor, smallBorderColor, borderThickness);
                graphicsHandler.drawFilledRectangleWithBorder(x, y-15, width, 15, 0, 0, smallFillColor, smallFillColor, borderThickness);
                if (displayWindowName) {
                    graphicsHandler.drawFilledRectangleWithBorder((x+(width/2))-(100), y-21, 200, 17, 10, 10, smallFillColor, new Color(73,73,73), borderThickness);
                }
            } else if (textboxStyle.equals(Textbox.Style.WORLDTHREE)) {
                graphicsHandler.drawFilledRectangleWithBorder(x, y-30, width, 30, arcWidth, arcHeight, smallFillColor, smallBorderColor, borderThickness);
                if (displayWindowName) {
                    graphicsHandler.drawFilledRectangleWithBorder(x+5, y-25, 100, 35, 15, 15, bigFillColor, bigBorderColor, borderThickness);
                }
            } else { //hubworld
                graphicsHandler.drawFilledRectangleWithBorder(x, y-30, width, 30, arcWidth, arcHeight, smallFillColor, smallBorderColor, borderThickness);
                if (displayWindowName) {
                    graphicsHandler.drawFilledRectangleWithBorder(x+30, y-27, 150, 24, 5, 5, new Color(255,255,255), new Color(210,210,214), 1);
                }
            }
            //upper box details
            if (textboxStyle.equals(Textbox.Style.WORLDONE)) {
                //white background
                graphicsHandler.drawFilledRectangle(x+width-23, y-23, 21, 21, 5, 5, new Color(217,210,226));
                //red box
                graphicsHandler.drawFilledRectangleGradient(x+width-22, y-22, 19, 19, 5, 5, new Color(240,162,141), new Color(194,48,8));
                //X
                SpriteFont X = new SpriteFont("X", x+width-18, y-22, "Arial", 17, new Color(217,210,226));
                X.drawWithParsedNewLines(graphicsHandler, 10);
                //logo
                graphicsHandler.drawImage(logo, x+5, y-20, 15, 15);
            } else if (textboxStyle.equals(Textbox.Style.WORLDTWO)) {
                //red
                graphicsHandler.drawFilledRectangleWithBorder(x+8, y-18, 11, 11, 11, 11, new Color(253,95,86), new Color(241,93,84), 1);
                //yellow
                graphicsHandler.drawFilledRectangleWithBorder(x+27, y-18, 11, 11, 11, 11, new Color(254,189,47), new Color(241,180,51), 1);
                //green
                graphicsHandler.drawFilledRectangleWithBorder(x+46, y-18, 11, 11, 11, 11, new Color(40,200,64), new Color(77,189,84), 1);
            } else if (textboxStyle.equals(Textbox.Style.WORLDTHREE)) {
                SpriteFont X = new SpriteFont("X", x+width-19, y-21, "Arial", 15, new Color(54,68,85));
                X.drawWithParsedNewLines(graphicsHandler, 10);
            } else { //hubworld
                graphicsHandler.drawImage(logo, x+8, y-22, 14, 14);
                SpriteFont X = new SpriteFont("X", x+width-19, y-22, "Arial", 13, bigFontColor);
                X.drawWithParsedNewLines(graphicsHandler, 10);
            }
            //lower box
            if (textboxStyle.equals(Textbox.Style.WORLDTWO)) {
                graphicsHandler.drawFilledRectangleWithBorder(x, y, width, height, arcWidth, arcHeight, bigFillColor, bigBorderColor, borderThickness);
                graphicsHandler.drawFilledRectangleWithBorder(x, y, width, height-10, 0, 0, bigFillColor, bigFillColor, borderThickness);
            } else {
                graphicsHandler.drawFilledRectangleWithBorder(x, y, width, height, arcWidth, arcHeight, bigFillColor, bigBorderColor, borderThickness);
            }
    }

    private void handleTextboxStyle() {
        switch (textboxStyle) {
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
        logo = ImageLoader.load("firefoxlogo.png", Color.MAGENTA);
        arcWidth = 0;
        arcHeight = 0;
        borderThickness = 1;
        bigFillColor = new Color(249,249,251);
        bigBorderColor = new Color(249,249,251);
        smallFillColor = new Color(240,240,245);
        smallBorderColor = new Color(240,240,245);
        bigFontColor = new Color(29,28,34);
    }

    private void worldOneTextbox() {
        logo = ImageLoader.load("windowsxplogo.png", Color.MAGENTA);
        arcWidth = 0;
        arcHeight = 0;
        borderThickness = 3;
        bigFillColor = new Color(239,235,222);
        bigBorderColor = new Color(10,85,233);
        smallFillColor = new Color(10,85,233);
        smallBorderColor = new Color(10,85,233);
        bigFontColor = Color.BLACK;
    }

    private void worldTwoTextbox() {
        arcWidth = 15;
        arcHeight = 15;
        borderThickness = 1;
        bigFillColor = new Color(27,27,27);
        bigBorderColor = new Color(27,27,27);
        smallFillColor = new Color(50,50,50);
        smallBorderColor = new Color(50,50,50);
        bigFontColor = new Color (225,225,225);
    }

    private void worldThreeTextbox() {
        arcWidth = 0;
        arcHeight = 0;
        borderThickness = 1;
        bigFillColor = new Color(255,255,255);
        bigBorderColor = new Color(255,255,255);
        smallFillColor = new Color(223,225,231);
        smallBorderColor = new Color(223,225,231);
        bigFontColor = new Color(54,68,85);
    }
}
