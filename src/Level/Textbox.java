package Level;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

// Represents the game's textbox
// will display the text it is given to its textQueue
// each String in the textQueue will be displayed in the textbox, and hitting the interact key will cycle between additional Strings in the queue
// use the newline character in a String in the textQueue to break the text up into a second line if needed
public class Textbox {
    protected boolean isActive;
    //big box
    protected int x = 100;
    protected int y = 0;
    protected int bottomY = 460;
    protected int topY = 47;
    protected int fontX = 115;
    protected int fontBottomY = 472;
    protected int fontTopY = 55;
    protected int width = 520;
    protected int height = 100;
    //select box
    protected int xSelect = 542;
    protected int ySelect = 0;
    protected int bottomYSelect = 308;
    protected int topYSelect = 122;
    protected int fontXSelect = 548;
    protected int fontBottomYSelect = 316;
    protected int fontTopYSelect = 132;
    protected int widthSelect = 230;
    protected int heightSelect = 150;

    protected static Font font;
    protected static Font fontSelect;
    protected static Font fontSmall;
    protected BufferedImage logo;
    protected int fontSmallX;
    protected int arcWidth;
    protected int arcHeight;
    protected int borderThickness;
    protected Color bigFillColor;
    protected Color bigBorderColor;
    protected Color bigFontColor;
    protected Color smallFillColor;
    protected Color smallBorderColor;
    protected Color smallFontColor;
    protected int currentTextItemHovered = 0;
    protected int compiledCount = 0;
    protected int choice = -1;

    private Queue<String> textQueue = new LinkedList<String>();
    private Queue<String> textQueueOnHold = new LinkedList<String>();
    private Queue<String> selectionQueue = new LinkedList<String>();
    private SpriteFont[] selectionText = new SpriteFont[3];
    private String[] responseText = new String[3];
    private int selectablesPresent;
    private SpriteFont text = null;
    private int fontY;
    private int fontYSelect;
    private KeyLocker keyLocker = new KeyLocker();
    private Map map;
    private Key interactKey = Key.ENTER;
    private int keyPressTimer;
    private SpriteFont npcName;
    
    protected TextboxStyle textboxStyle;
    protected TextboxStyle prevTextboxStyle;

    protected void handleTextboxStyle() {
        System.out.println(textboxStyle);
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

    protected void hubWorldTextbox() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/arial.ttf")).deriveFont(10f);
            ge.registerFont(font);
            fontSelect = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/arial.ttf")).deriveFont(10f);
            ge.registerFont(fontSelect);
            fontSmall = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/arial.ttf")).deriveFont(10f);
            ge.registerFont(fontSmall);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        arcWidth = 0;
        arcHeight = 0;
        borderThickness = 3;
        fontSmallX = fontX+10; 
        bigFillColor = Color.RED;
        bigBorderColor = Color.BLUE;
        smallFillColor = Color.YELLOW;
        smallBorderColor = Color.DARK_GRAY;
        bigFontColor = Color.CYAN;
        smallFontColor = Color.GREEN;
    }

    protected void worldOneTextbox() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/tahoma.ttf")).deriveFont(30f);
            ge.registerFont(font);
            fontSelect = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/tahoma.ttf")).deriveFont(30f);
            ge.registerFont(fontSelect);
            fontSmall = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/trebuchetms.ttf")).deriveFont(20f);
            ge.registerFont(fontSmall);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        logo = ImageLoader.load("windowsxplogo.png", Color.MAGENTA);
        arcWidth = 0;
        arcHeight = 0;
        borderThickness = 3;
        fontSmallX = fontX+10;
        bigFillColor = new Color(239,235,222);
        bigBorderColor = new Color(10,85,233);
        smallFillColor = new Color(10,85,233);
        smallBorderColor = new Color(10,85,233);
        bigFontColor = Color.BLACK;
        smallFontColor = Color.WHITE;
    }

    protected void worldTwoTextbox() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/sfpro.ttf")).deriveFont(20f);
            ge.registerFont(font);
            fontSelect = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/sfpro.ttf")).deriveFont(30f);
            ge.registerFont(fontSelect);
            fontSmall = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/sfpro.ttf")).deriveFont(16f);
            ge.registerFont(fontSmall);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        arcWidth = 0;
        arcHeight = 0;
        borderThickness = 1;
        fontSmallX = x+242;
        bigFillColor = new Color(27,27,27);
        bigBorderColor = new Color(27,27,27);
        smallFillColor = new Color(50,50,50);
        smallBorderColor = new Color(50,50,50);
        bigFontColor = new Color (225,225,225);
        smallFontColor = new Color(225,225,225);
    }

    protected void worldThreeTextbox() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/opensans.ttf")).deriveFont(20f);
            ge.registerFont(font);
            fontSelect = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/opensans.ttf")).deriveFont(30f);
            ge.registerFont(fontSelect);
            fontSmall = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/opensans.ttf")).deriveFont(15f);
            ge.registerFont(fontSmall);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        arcWidth = 0;
        arcHeight = 0;
        borderThickness = 1;
        fontSmallX = fontX;
        bigFillColor = new Color(255,255,255);
        bigBorderColor = new Color(255,255,255);
        smallFillColor = new Color(223,225,231);
        smallBorderColor = new Color(223,225,231);
        bigFontColor = new Color(54,68,85);
        smallFontColor = new Color(54,68,85);
    }

    public TextboxStyle getTextboxStyle() {
        return textboxStyle;
    }

    public void setTextboxStyle(TextboxStyle textboxStyle) {
        System.out.println("SET TEXTBOX STYLE");
        this.textboxStyle = textboxStyle;
        handleTextboxStyle();
    }

    public Textbox(Map map) {
        this.map = map;
        textboxStyle = TextboxStyle.HUBWORLD;
        prevTextboxStyle = textboxStyle;
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/arial.ttf")).deriveFont(30f);
            ge.registerFont(font);
            fontSelect = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/arial.ttf")).deriveFont(30f);
            ge.registerFont(fontSelect);
            fontSmall = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/arial.ttf")).deriveFont(20f);
            ge.registerFont(fontSmall);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void addText(String text) {
        if (textQueue.isEmpty()) {
            keyLocker.lockKey(interactKey);
        }
        textQueue.add(text);
    }

    public void addText(String[] text) {
        if (textQueue.isEmpty()) {
            keyLocker.lockKey(interactKey);
        }
        for (String textItem : text) {
            textQueue.add(textItem);
        }
    }

    // adds text followed by selection options underneath
    public void addSelectableText(String textChat, String[] selectionText) {
        selectionQueue.clear();
        compiledCount = 0;

        if (textQueue.isEmpty()) {
            keyLocker.lockKey(interactKey);
        }
        textQueue.add(textChat);

        for (int i = 0; i < selectionText.length; i++) {
            selectionQueue.add(selectionText[i]);
        }

        for (int i = 0; i < selectionText.length; i++) {
            this.selectionText[compiledCount] = spriteFontCompile(selectionQueue);
            compiledCount++;
        }

        for (int i = selectionText.length; i < this.selectionText.length; i++) {
            this.selectionText[i] = new SpriteFont("", fontX, fontY, fontSelect, bigFontColor);
        }
        
        selectablesPresent = 1;
    }

    // returns whether the textQueue is out of items to display or not
    // useful for scripts to know when to complete
    public boolean isTextQueueEmpty() {
        return textQueue.isEmpty();
    }

    // returns whether the selectionQueue is empty
    public boolean isSelectionQueueEmpty() {
        return selectionQueue.isEmpty();
    }

    // creates spriteFont for each string in a queue
    public SpriteFont spriteFontCompile(Queue<String> selectionQueue) {
        if (!selectionQueue.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
            String next = selectionQueue.poll();
            return new SpriteFont(next, fontX, fontY, fontSelect, bigFontColor);
        } else if (selectionQueue.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
            return new SpriteFont("", fontX, fontY, fontSelect, bigFontColor);
        }
        return null;
    }

    public void setResponses(String[] responses) {
        for (int i = 0; i < responses.length; i++) {
            this.responseText[i] = responses[i];
        }
    }

    public void update() {

        // if textQueue has more text to display and the interact key button was pressed previously, display new text
        if (!textQueue.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
            String next = textQueue.peek();

            // if camera is at bottom of screen, text is drawn at top of screen instead of the bottom like usual
            // to prevent it from covering the player
            if (!map.getCamera().isAtBottomOfMap()) {
                fontY = fontBottomY;
                fontYSelect = fontBottomYSelect;
            }
            else {
                fontY = fontTopY;
                fontYSelect = fontTopYSelect;
            }
            text = new SpriteFont(next, fontX, fontY, font, bigFontColor);

        }

        // if interact key is pressed, remove the current text from the queue to prepare for the next text item to be displayed
        if (Keyboard.isKeyDown(interactKey) && !keyLocker.isKeyLocked(interactKey)) {
            keyLocker.lockKey(interactKey);
            textQueue.poll();
            if (selectablesPresent == 1) {
                while (!textQueue.isEmpty()) {
                    textQueueOnHold.add(textQueue.poll());
                }
                setChoice(currentTextItemHovered);
                textQueue.add(responseText[choice]);
                currentTextItemHovered = 0;
                while (!textQueueOnHold.isEmpty()) {
                    textQueue.add(textQueueOnHold.poll());
                }
            }
            selectablesPresent = 0;
        } else if (Keyboard.isKeyUp(interactKey)) {
            keyLocker.unlockKey(interactKey);
        }

        if (Keyboard.isKeyDown(Key.DOWN) && (keyPressTimer == 0) && (selectablesPresent == 1)) {
            keyPressTimer = 14;
            currentTextItemHovered++;

        } else if (Keyboard.isKeyDown(Key.UP) && (keyPressTimer == 0) && (selectablesPresent == 1)) {
            keyPressTimer = 14;
            currentTextItemHovered--;

        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }

        //if down is pressed on last item or up is pressed on first item, "loop" the selection back around to the beginning/end
        if (currentTextItemHovered == compiledCount) {
            currentTextItemHovered = 0;
        } else if (currentTextItemHovered < 0) {
            currentTextItemHovered = compiledCount-1;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // if camera is at bottom of screen, textbox is drawn at top of screen instead of the bottom like usual
        // to prevent it from covering the player
        if (!map.getCamera().isAtBottomOfMap()) {
            y = bottomY;
            ySelect = bottomYSelect;
        } else {
            y = topY;
            ySelect = topYSelect;
        }

        // ----- big textbox ----- //
        //upper box
        if (textboxStyle.equals(TextboxStyle.WORLDONE)) {
            graphicsHandler.drawFilledRectangleGradientWithBorder(x, y-25, width, 25, arcWidth, arcHeight, new Color(57,147,255), new Color(10,85,234), smallBorderColor, borderThickness);
        } else if (textboxStyle.equals(TextboxStyle.WORLDTWO)) {
            graphicsHandler.drawFilledRectangleWithBorder(x, y-25, width, 25, 15, 15, smallFillColor, smallBorderColor, borderThickness);
            graphicsHandler.drawFilledRectangleWithBorder(x, y-15, width, 15, arcWidth, arcHeight, smallFillColor, smallFillColor, borderThickness);
        } else {
            graphicsHandler.drawFilledRectangleWithBorder(x, y-25, width, 25, arcWidth, arcHeight, smallFillColor, smallBorderColor, borderThickness);
        }
        //upper box details
        if (textboxStyle.equals(TextboxStyle.WORLDONE)) {
            graphicsHandler.drawFilledRectangleGradientWithBorder(x+width-22, y-22, 19, 19, 5, 5, new Color(240,162,141), new Color(194,48,8), new Color(217,210,226), 1);
            SpriteFont X = new SpriteFont("X", x+width-22, y-22, font, new Color(217,210,226));
            X.drawWithParsedNewLines(graphicsHandler, 10);
            graphicsHandler.drawImage(logo, x+2, y-22, 19, 19);
        } else if (textboxStyle.equals(TextboxStyle.WORLDTWO)) {
            graphicsHandler.drawFilledRectangleWithBorder(x+8, y-18, 11, 11, 11, 11, new Color(253,95,86), new Color(241,93,84), 1);
            graphicsHandler.drawFilledRectangleWithBorder(x+27, y-18, 11, 11, 11, 11, new Color(254,189,47), new Color(241,180,51), 1);
            graphicsHandler.drawFilledRectangleWithBorder(x+46, y-18, 11, 11, 11, 11, new Color(40,200,64), new Color(77,189,84), 1);
        }
        //lower box
        if (textboxStyle.equals(TextboxStyle.WORLDTWO)) {
            graphicsHandler.drawFilledRectangleWithBorder(x, y, width, height, 15, 15, bigFillColor, bigBorderColor, borderThickness);
            graphicsHandler.drawFilledRectangleWithBorder(x, y, width, height-10, arcWidth, arcHeight, bigFillColor, bigFillColor, borderThickness);
        } else {
            graphicsHandler.drawFilledRectangleWithBorder(x, y, width, height, arcWidth, arcHeight, bigFillColor, bigBorderColor, borderThickness);
        }
        
        // ----- select textbox ----- //
        if (selectablesPresent == 1) {
            //upper box
            if (textboxStyle.equals(TextboxStyle.WORLDONE)) {
                graphicsHandler.drawFilledRectangleGradientWithBorder(xSelect, ySelect-25, widthSelect, 25, arcWidth, arcHeight, new Color(57,147,255), new Color(10,85,234), smallBorderColor, borderThickness);
            } else {
                graphicsHandler.drawFilledRectangleWithBorder(xSelect, ySelect-25, widthSelect, 25, arcWidth, arcHeight, smallFillColor, smallBorderColor, borderThickness);
            }
            //upper box details
            if (textboxStyle.equals(TextboxStyle.WORLDONE)) {
                graphicsHandler.drawFilledRectangle(xSelect+widthSelect-23, ySelect-23, 21, 21, 5, 5, new Color(217,210,226));
                graphicsHandler.drawFilledRectangleGradient(xSelect+widthSelect-22, ySelect-22, 19, 19, 5, 5, new Color(240,162,141), new Color(194,48,8));
                SpriteFont X = new SpriteFont("X", xSelect+widthSelect-18, ySelect-22, "Arial", 17, new Color(217,210,226));
                X.drawWithParsedNewLines(graphicsHandler, 10);
                graphicsHandler.drawImage(logo, xSelect+2, ySelect-22, 19, 19);
            }
            //lower box
            graphicsHandler.drawFilledRectangleWithBorder(xSelect, ySelect, widthSelect, heightSelect, arcWidth, arcHeight, bigFillColor, bigBorderColor, borderThickness);
        }

        if (text != null) {
            text.drawWithParsedNewLines(graphicsHandler, 10);
        }
        if (npcName != null) {
            npcName.drawWithParsedNewLines(graphicsHandler, 10);
        }
        if (selectablesPresent == 1) {
            for (int i=0; i<selectionText.length; i++) {
                selectionText[i].setColor(bigFontColor);
            }
            selectionText[currentTextItemHovered].setColor(Color.red);

            selectionText[0].drawWithParsedNewLines(graphicsHandler, 10);
            int y = fontYSelect;
            int x = fontXSelect;
            for (int i = 0; i < compiledCount; i++) {
                selectionText[i].setY(y);
                selectionText[i].setX(x+10);
                y += 35;
                selectionText[i].drawWithParsedNewLines(graphicsHandler, 10);
            }
        }
    }

    public SpriteFont getNPCName() {
        return npcName;
    }

    public void setNPCName(String npcName) {
        if (!map.getCamera().isAtBottomOfMap()) {
            fontY = fontBottomY;
        } else {
            fontY = fontTopY;
        }
        this.npcName = new SpriteFont(npcName, fontSmallX, fontY - 34, fontSmall, smallFontColor);
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    // public Color getBigFillColor() {
    //     return fillColor;
    // }

    // public void setBigFillColor(Color fillColor) {
    //     this.fillColor = fillColor;
    // }

    // public Color getBigBorderColor() {
    //     return borderColor;
    // }

    // public void setBorderColor(Color borderColor) {
    //     this.borderColor = borderColor;
    // }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setInteractKey(Key interactKey) {
        this.interactKey = interactKey;
    }

}
