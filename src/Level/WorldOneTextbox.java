package Level;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WorldOneTextbox extends Textbox {
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

    public WorldOneTextbox(Map map) {
        super(map);
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

}
