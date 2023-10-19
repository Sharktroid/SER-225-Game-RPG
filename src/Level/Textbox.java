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
    //small box
    protected int xSmall = 542;
    protected int ySmall = 0;
    protected int bottomYSmall = 308;
    protected int topYSmall = 122;
    protected int fontXSmall = 548;
    protected int fontBottomYSmall = 316;
    protected int fontTopYSmall = 132;
    protected int widthSmall = 230;
    protected int heightSmall = 150;

    protected static Font font;
    protected static Font fontSmall;
    // protected static Font tahoma;
    // protected static Font tahomaSmall;
    protected BufferedImage logo;
    // protected BufferedImage logo = ImageLoader.load("src/Level/windowsxplogo.png");
    protected int arcWidth;
    protected int arcHeight;
    protected int borderThickness;
    protected Color fillColor;
    protected Color borderColor;
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
    private int fontYSmall;
    private KeyLocker keyLocker = new KeyLocker();
    private Map map;
    private Key interactKey = Key.ENTER;
    private int keyPressTimer;
    private SpriteFont npcName;
    
    protected TextboxStyle textboxStyle;

    protected void handleTextboxStyle() {
        System.out.println(getTextboxStyle());
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
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/Level/arial.ttf")).deriveFont(30f);
            ge.registerFont(font);
            fontSmall = Font.createFont(Font.TRUETYPE_FONT, new File("src/Level/arial.ttf")).deriveFont(30f);
            ge.registerFont(fontSmall);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        arcWidth = 0;
        arcHeight = 0;
        borderThickness = 3;
        fillColor = new Color(242,244,248);
        //238,240,243
        borderColor = new Color(242,244,248);
    }

    protected void worldOneTextbox() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/Level/tahoma.ttf")).deriveFont(30f);
            ge.registerFont(font);
            fontSmall = Font.createFont(Font.TRUETYPE_FONT, new File("src/Level/tahoma.ttf")).deriveFont(30f);
            ge.registerFont(fontSmall);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        logo = ImageLoader.load("windowsxplogo2.png");
        arcWidth = 0;
        arcHeight = 0;
        borderThickness = 3;
        fillColor = new Color(239,235,222);
        borderColor = new Color(10,85,233);
    }

    protected void worldTwoTextbox() {

    }

    protected void worldThreeTextbox() {

    }

    public TextboxStyle getTextboxStyle() {
        return textboxStyle;
    }

    public void setTextboxStyle(TextboxStyle textboxStyle) {
        handleTextboxStyle();
        this.textboxStyle = textboxStyle;
    }

    public Textbox(Map map) {
        this.map = map;
        textboxStyle = TextboxStyle.HUBWORLD;
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/Level/arial.ttf")).deriveFont(30f);
            ge.registerFont(font);
            fontSmall = Font.createFont(Font.TRUETYPE_FONT, new File("src/Level/arial.ttf")).deriveFont(30f);
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
            this.selectionText[i] = new SpriteFont("", fontX, fontY, font, Color.black);
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
            return new SpriteFont(next, fontX, fontY, fontSmall, Color.black);
        } else if (selectionQueue.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
            return new SpriteFont("", fontX, fontY, fontSmall, Color.black);
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
                fontYSmall = fontBottomYSmall;
            }
            else {
                fontY = fontTopY;
                fontYSmall = fontTopYSmall;
            }
            text = new SpriteFont(next, fontX, fontY, fontSmall, Color.black);

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
            ySmall = bottomYSmall;
        } else {
            y = topY;
            ySmall = topYSmall;
        }

        // ----- big textbox ----- //
        //upper box
        graphicsHandler.drawFilledRectangleWithBorder(x, y-25, width, 25, arcWidth, arcHeight, borderColor, borderColor, borderThickness);
        //upper box details
        if (textboxStyle.equals(TextboxStyle.WORLDONE)) {
            graphicsHandler.drawFilledRectangleWithBorder(x+width-22, y-22, 19, 19, 5, 5, new Color(218,74,34), new Color(217,210,226), 1);
            // SpriteFont X = new SpriteFont("X", x+width-22, y-22, font, new Color(217,210,226));
            // X.drawWithParsedNewLines(graphicsHandler, 10);
            graphicsHandler.drawImage(logo, x+2, y-22, 19, 19);
        }
        //bottom box
        graphicsHandler.drawFilledRectangleWithBorder(x, y, width, height, arcWidth, arcHeight, fillColor, borderColor, borderThickness);
        
        // ----- small textbox ----- //
        if (selectablesPresent == 1) {
            //upper box
            graphicsHandler.drawFilledRectangleWithBorder(xSmall, ySmall-25, widthSmall, 25, arcWidth, arcHeight, borderColor, borderColor, borderThickness);
            //upper box details
            if (textboxStyle.equals(TextboxStyle.WORLDONE)) {
                graphicsHandler.drawFilledRectangleWithBorder(xSmall+widthSmall-22, ySmall-22, 19, 19, 5, 5, new Color(218,74,34), new Color(217,210,226), 1);
                graphicsHandler.drawImage(logo, xSmall+2, ySmall-22, 19, 19);
            }
            //lower box
            graphicsHandler.drawFilledRectangleWithBorder(xSmall, ySmall, widthSmall, heightSmall, arcWidth, arcHeight, fillColor, borderColor, borderThickness);
        }

        if (text != null) {
            text.drawWithParsedNewLines(graphicsHandler, 10);
        }
        if (npcName != null) {
            npcName.drawWithParsedNewLines(graphicsHandler, 10);
        }
        if (selectablesPresent == 1) {
            for (int i=0; i<selectionText.length; i++) {
                selectionText[i].setColor(Color.black);
            }
            selectionText[currentTextItemHovered].setColor(Color.red);

            selectionText[0].drawWithParsedNewLines(graphicsHandler, 10);
            int y = fontYSmall;
            int x = fontXSmall;
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
        this.npcName = new SpriteFont(npcName, fontX+10, fontY - 34, "Trebuchet MS", 18, Color.WHITE);
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

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
