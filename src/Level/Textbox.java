package Level;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import SpriteFont.SpriteFont;

import java.awt.*;
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
    protected int bottomY = 460;
    protected int topY = 47;
    protected int fontX = 115;
    protected int fontBottomY = 472;
    protected int fontTopY = 55;
    protected int width = 520;
    protected int height = 100;
    //small box
    protected final int xSmall = 542;
    protected final int bottomYSmall = 258;
    protected final int topYSmall = 122;
    protected final int fontXSmall = 548;
    protected final int fontBottomYSmall = 266;
    protected final int fontTopYSmall = 132;
    protected final int widthSmall = 230;
    protected final int heightSmall = 200;

    protected String fontTahoma = "Tahoma";
    protected static Font tahoma;
    protected static Font tahomaSmall;
    protected int arcWidth = 0;
    protected int arcHeight = 0;
    protected int borderThickness = 3;
    protected Color fillColor = new Color(239,235,222);
    protected Color borderColor = new Color(10,85,233);
    protected int currentTextItemHovered = 0;
    protected int compiledCount = 0;
    protected int choice = -1;

    private Queue<String> textQueue = new LinkedList<String>();
    private Queue<String> textQueueOnHold = new LinkedList<String>();
    private Queue<String> selectionQueue = new LinkedList<String>();
    private SpriteFont[] selectionText = new SpriteFont[10];
    private String[] responseText = new String[10];
    private int selectablesPresent;
    private SpriteFont text = null;
    private int fontY;
    private int fontYSmall;
    private KeyLocker keyLocker = new KeyLocker();
    private Map map;
    private Key interactKey = Key.ENTER;
    private int keyPressTimer;
    private SpriteFont npcName;

    public Textbox(Map map) {
        this.map = map;
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            tahoma = Font.createFont(Font.TRUETYPE_FONT, new File("src/Level/tahoma.ttf")).deriveFont(30f);
            ge.registerFont(tahoma);
            tahomaSmall = Font.createFont(Font.TRUETYPE_FONT, new File("src/Level/tahoma.ttf")).deriveFont(30f);
            ge.registerFont(tahomaSmall);
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
            this.selectionText[i] = new SpriteFont("", fontX, fontY, tahomaSmall, Color.black);
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
            return new SpriteFont(next, fontX, fontY, tahomaSmall, Color.black);
        } else if (selectionQueue.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
            return new SpriteFont("", fontX, fontY, tahomaSmall, Color.black);
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
            text = new SpriteFont(next, fontX, fontY, tahoma, Color.black);

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

        // setChoice(choice);

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
            //upper box
            graphicsHandler.drawFilledRectangleWithBorder(x, bottomY-25, width, 25, arcWidth, arcHeight, borderColor, borderColor, borderThickness);
            //lower box
            graphicsHandler.drawFilledRectangleWithBorder(x, bottomY, width, height, arcWidth, arcHeight, fillColor, borderColor, borderThickness);
            if (selectablesPresent == 1) {
                //upper box
                graphicsHandler.drawFilledRectangleWithBorder(xSmall, bottomYSmall-25, widthSmall, 25, arcWidth, arcHeight, borderColor, borderColor, borderThickness);
                //lower box
                graphicsHandler.drawFilledRectangleWithBorder(xSmall, bottomYSmall, widthSmall, heightSmall, arcWidth, arcHeight, fillColor, borderColor, borderThickness);
            }
        } else {
            //upper box
            graphicsHandler.drawFilledRectangleWithBorder(x, topY-25, width, 25, arcWidth, arcHeight, borderColor, borderColor, borderThickness);
            //lower box
            graphicsHandler.drawFilledRectangleWithBorder(x, topY, width, height, arcWidth, arcHeight, fillColor, borderColor, borderThickness);
            if (selectablesPresent == 1) {
                //upper box
                graphicsHandler.drawFilledRectangleWithBorder(xSmall, topYSmall-25, widthSmall, 25, arcWidth, arcHeight, borderColor, borderColor, borderThickness);
                //lower box
                graphicsHandler.drawFilledRectangleWithBorder(xSmall, topYSmall, widthSmall, heightSmall, arcWidth, arcHeight, fillColor, borderColor, borderThickness);
            }
        }

        if (text != null) {
            text.drawWithParsedNewLines(graphicsHandler, 10);
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
                y += 30;
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
        this.npcName = new SpriteFont(npcName, fontX, topY - 22, "Trebuchet MS", 18, Color.WHITE);
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
