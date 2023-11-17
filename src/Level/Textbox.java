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
    protected int y;
    protected int bottomY = 460;
    protected int topY = 47;
    protected int fontX = 115;
    protected int fontBottomY = 472;
    protected int fontTopY = 55;
    protected int width = 520;
    protected int height = 125;
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
    protected static Font fontSmall;
    protected int fontSmallX;
    protected Color smallFontColor;
    protected int currentTextItemHovered = 0;
    protected int compiledCount = 0;
    protected int choice = -1;
    public static int chosenOption;

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
    private SpriteFont playerName;
    private Panel panel;
    private Panel selectPanel;

    static private Style style;
    protected Style prevTextboxStyle;

    public enum Style {
        HUBWORLD, WORLDONE, WORLDTWO, WORLDTHREE
    }

    public Textbox(Map map) {
        this.map = map;
        style = Style.HUBWORLD;
        prevTextboxStyle = style;
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/arial.ttf")).deriveFont(30f);
            ge.registerFont(font);
            fontSmall = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/arial.ttf")).deriveFont(20f);
            ge.registerFont(fontSmall);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        panel = new Panel(x, y, width, height, true);
        selectPanel = new Panel(xSelect, ySelect, widthSelect, heightSelect, false);
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
            this.selectionText[i] = new SpriteFont("", fontX, fontY, font, getFontColor());
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
            return new SpriteFont(next, fontX, fontY, font, getFontColor());
        } else if (selectionQueue.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
            return new SpriteFont("", fontX, fontY, font, getFontColor());
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
            text = new SpriteFont(next, fontX, fontY, font, getFontColor());

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
                chosenOption = currentTextItemHovered;
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
            panel.setY(bottomY);
            selectPanel.setY(bottomYSelect);
        } else {
            panel.setY(topY);
            selectPanel.setY(topYSelect);
        }

        // ----- big textbox ----- //
        //upper box
        panel.draw(graphicsHandler);

        // ----- select textbox ----- //
        if (selectablesPresent == 1) {
            selectPanel.draw(graphicsHandler);
        }

        if (text != null) {
            text.drawWithParsedNewLines(graphicsHandler, 10);
        }

        if (npcName != null) {
            npcName.drawWithParsedNewLines(graphicsHandler, 10);
        }

        if (selectablesPresent == 1) {
            for (int i=0; i<selectionText.length; i++) {
                selectionText[i].setColor(getFontColor());
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

    private void handleTextboxStyle() {
        switch (style) {
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
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/inter.ttf")).deriveFont(20f);
            ge.registerFont(font);
            fontSmall = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/inter.ttf")).deriveFont(14f);
            ge.registerFont(fontSmall);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        fontSmallX = fontX+25;
        smallFontColor = new Color(29,28,34);
    }

    private void worldOneTextbox() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/tahoma.ttf")).deriveFont(30f);
            ge.registerFont(font);
            fontSmall = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/trebuchetms.ttf")).deriveFont(17f);
            ge.registerFont(fontSmall);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        fontSmallX = fontX+20;
        smallFontColor = Color.WHITE;
    }

    private void worldTwoTextbox() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/sfpro.ttf")).deriveFont(18f);
            ge.registerFont(font);
            fontSmall = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/sfpro.ttf")).deriveFont(15f);
            ge.registerFont(fontSmall);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        fontSmallX = x+242;
        smallFontColor = new Color(225,225,225);
    }

    private void worldThreeTextbox() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/opensans.ttf")).deriveFont(20f);
            ge.registerFont(font);
            fontSmall = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/fonts/opensans.ttf")).deriveFont(15f);
            ge.registerFont(fontSmall);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        fontSmallX = fontX;
        smallFontColor = new Color(54,68,85);
    }

    public static Style getStyle() {
        return style;
    }

    public void setStyle(Style textboxStyle) {
        Textbox.style = textboxStyle;
        handleTextboxStyle();
    }

    public static Font getFont() {
        return font;
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

    public SpriteFont getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        if (!map.getCamera().isAtBottomOfMap()) {
            fontY = fontBottomY;
        } else {
            fontY = fontTopY;
        }
        this.playerName = new SpriteFont(playerName, fontXSelect, fontYSelect - 34, fontSmall, smallFontColor);
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getChosen(){
        return chosenOption;
    }



    public static void resetChosen(){
        chosenOption = -1;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setInteractKey(Key interactKey) {
        this.interactKey = interactKey;
    }

    static public Color getFontColor() {
        switch (style) {
            case HUBWORLD:
                return new Color(29,28,34);
            case WORLDTWO:
                return Color.WHITE;
            case WORLDTHREE:
                new Color(54,68,85);
            default:
                return Color.BLACK;
        }
    }

}