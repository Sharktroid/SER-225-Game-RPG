package Level;

import java.awt.Color;
import java.util.ArrayList;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import SpriteFont.SpriteFont;
import Utils.Point;

public abstract class Menu {
    protected int left = 20;
    protected int top = left;
    protected int spacer = left;
    protected int border = 25;
    protected int rows = 1;
    protected int columns = 1;
    protected int fontSize = 30;
    protected int width;
    protected int height;

    private Boolean active = false;
    protected ArrayList<SpriteFont> spriteFonts = new ArrayList<SpriteFont>();
    protected int currentTextItemHovered = 0;
    private KeyLocker keyLocker = new KeyLocker();

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangleWithBorder(left, top, width, height, Color.white, Color.black, 2);

        for (int i = 0; i < spriteFonts.size(); i++) {
            spriteFonts.get(i).setColor(Color.black);
        }
        if (spriteFonts.size() > 0) {
            spriteFonts.get(currentTextItemHovered).setColor(Color.red);
        }
        for (int i = 0; i < spriteFonts.size(); i++) {
            spriteFonts.get(i).drawWithParsedNewLines(graphicsHandler, 10);
        }
    }

    public void update() {
        if (lockKey(Key.RIGHT) && columns > 1) {
            currentTextItemHovered = (currentTextItemHovered + 1) % spriteFonts.size();
        }
        else if (lockKey(Key.LEFT) && columns > 1) {
            currentTextItemHovered--;
            if (currentTextItemHovered < 0) {
                currentTextItemHovered = spriteFonts.size() - 1;
            }

        }
        else if (lockKey(Key.UP) && rows > 1) {
            currentTextItemHovered -= columns;
            if (currentTextItemHovered < 0) {
                currentTextItemHovered = spriteFonts.size() - (currentTextItemHovered + 1 + columns);
            }
        }
        else if (lockKey(Key.DOWN) && rows > 1) {
            currentTextItemHovered += columns;
            if (currentTextItemHovered >= spriteFonts.size()) {
                currentTextItemHovered %= columns;
            }
        }

        else if (lockKey(Key.ENTER)) {
            optionSelected();
        }

        unlockKey(Key.LEFT);
        unlockKey(Key.UP);
        unlockKey(Key.DOWN);
        unlockKey(Key.RIGHT);
        unlockKey(Key.ENTER);
    }

    public Point getInternalSize() {
        return new Point(width - border * 2, height - border * 2);
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    protected Boolean lockKey(Key key) {
        if (Keyboard.isKeyDown(key) && !keyLocker.isKeyLocked(key)) {
            keyLocker.lockKey(key);
            return true;
        } else {
            return false;
        }
    }

    protected void unlockKey(Key key) {
        if (keyLocker.isKeyLocked(key) && Keyboard.isKeyUp(key)) {
            keyLocker.unlockKey(key);
        }
    }

    protected void setText(ArrayList<String> stringList) {
        String[] stringArray = new String[stringList.size()];
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = stringList.get(i);
        }
        setText(stringArray);
    }

    protected void setText(String[] stringArray) {
        spriteFonts.clear();
        int y = top + border;
        for (int i = 0; i < stringArray.length; i++) {
            spriteFonts.add(new SpriteFont(stringArray[i], 0, 0, "Arial", fontSize, Color.black));
            int x;
            x = (int) (i % columns * (getInternalSize().x) / columns) + left + border;
            spriteFonts.get(i).setY(y);
            spriteFonts.get(i).setX(x);
            if ((i + 1) % columns == 0) {
                y += getLineGap();
            }
        }
    }

    protected abstract void optionSelected();

    protected int getLineGap() {
        if (rows > 1) {
            return(((int) getInternalSize().y - fontSize) / (rows - 1));
        }
        else {
            return 0;
        }
    }
}
