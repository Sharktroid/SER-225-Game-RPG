package Engine;

import GameObject.ImageEffect;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class GraphicsHandler {
    private Graphics2D g;

    public Graphics2D getGraphics() {
        return g;
    }

    public void setGraphics(Graphics2D g) {
        this.g = g;
    }

    public void drawImage(BufferedImage image, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    public void drawImage(BufferedImage image, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }

    public void drawImage(BufferedImage image, int x, int y, int width, int height, ImageEffect imageEffect) {
        switch (imageEffect) {
            case NONE:
                drawImage(image, x, y, width, height);
                break;
            case FLIP_HORIZONTAL:
                g.drawImage(image, x + width, y, -width, height, null);
                break;
            case FLIP_VERTICAL:
                g.drawImage(image, x, y + height, width, -height, null);
                break;
            case FLIP_H_AND_V:
                g.drawImage(image, x + width, y + height, -width, -height, null);
                break;
        }
    }

    //draw rectangle (just outline)
    public void drawRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight, Color color) {
        g.setColor(color);
        g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }
    public void drawRectangle(int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.drawRect(x, y, width, height);
    }

    //draw rectangle with border
    public void drawRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight, Color color, int borderThickness) {
        g.setStroke(new BasicStroke(borderThickness));
        g.setColor(color);
        g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }
    public void drawRectangle(int x, int y, int width, int height, Color color, int borderThickness) {
        g.setStroke(new BasicStroke(borderThickness));
        g.setColor(color);
        g.drawRect(x, y, width, height);
    }


    //draw filled rectangle
    public void drawFilledRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight, Color color) {
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    }
    public void drawFilledRectangle(int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    public void drawFilledRectangle(float x, float y, int width, int height, int arcWidth, int arcHeight, Color color) {
        g.setColor(color);
        int xRounded = Math.round(x);
        int yRounded= Math.round(y);
        g.fillRoundRect(xRounded, yRounded, width, height, arcWidth, arcHeight);
    }
    public void drawFilledRectangle(float x, float y, int width, int height, Color color) {
        g.setColor(color);
        int xRounded = Math.round(x);
        int yRounded= Math.round(y);
        g.fillRect(xRounded, yRounded, width, height);
    }

    public void drawFilledRectangleWithBorder(int x, int y, int width, int height, int arcWidth, int arcHeight, Color fillColor, Color borderColor, int borderThickness) {
        drawFilledRectangle(x, y, width, height, arcWidth, arcHeight, fillColor);
        drawRectangle(x, y, width, height, arcWidth, arcHeight, borderColor, borderThickness);
    }
    public void drawFilledRectangleWithBorder(int x, int y, int width, int height, Color fillColor, Color borderColor, int borderThickness) {
        drawFilledRectangle(x, y, width, height, fillColor);
        drawRectangle(x, y, width, height, borderColor, borderThickness);
    }


    //gradient
    public void drawRectangleGradient(int x, int y, int width, int height, int arcWidth, int arcHeight, Color color1, Color color2, Color borderColor, int borderThickness) {
        g.setStroke(new BasicStroke(borderThickness));
        g.setColor(borderColor);
        GradientPaint gradient = new GradientPaint(x, y, color1, x, y + height, color2);
        g.setPaint(gradient);
        g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }
    public void drawFilledRectangleGradient(int x, int y, int width, int height, int arcWidth, int arcHeight, Color color1, Color color2) {
        GradientPaint gradient = new GradientPaint(x, y, color1, x, y + height, color2);
        g.setPaint(gradient);
        g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    }
    public void drawFilledRectangleGradientWithBorder(int x, int y, int width, int height, int arcWidth, int arcHeight, Color fillColor1, Color fillColor2, Color borderColor, int borderThickness) {
        drawFilledRectangleGradient(x, y, width, height, arcWidth, arcHeight, fillColor1, fillColor2);
        drawRectangleGradient(x, y, width, height, arcWidth, arcHeight, fillColor1, fillColor2, borderColor, borderThickness);
    }

    public void drawString(String text, int x, int y, Font font, Color color) {
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }

    // https://stackoverflow.com/a/35222059 and https://stackoverflow.com/a/31831120
    public void drawStringWithOutline(String text, int x, int y, Font font, Color textColor, Color outlineColor, float outlineThickness) {
        // remember original settings
        Color originalColor = g.getColor();
        Stroke originalStroke = g.getStroke();
        RenderingHints originalHints = g.getRenderingHints();
        g.setStroke(new BasicStroke(outlineThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // create a glyph vector from your text
        GlyphVector glyphVector = font.createGlyphVector(g.getFontRenderContext(), text);

        // get the shape object
        Shape textShape = glyphVector.getOutline();
        AffineTransform at = new AffineTransform();
        at.setToTranslation(Math.round(x), Math.round(y));
        textShape = at.createTransformedShape(textShape);

        // activate anti aliasing for text rendering (if you want it to look nice)
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g.setColor(outlineColor);
        g.draw(textShape); // draw outline

        g.setColor(textColor);
        g.fill(textShape); // fill the shape

        // reset to original settings after painting
        g.setColor(originalColor);
        g.setStroke(originalStroke);
        g.setRenderingHints(originalHints);
    }
}
