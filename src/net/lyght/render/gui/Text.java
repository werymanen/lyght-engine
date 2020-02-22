package net.lyght.render.gui;

import net.lyght.render.Drawer;
import net.lyght.util.Color;

import java.awt.*;

/** Text element */
public class Text extends Element {

    /** Text to be shown */
    private String text;
    /** Color for the text to be rendered in */
    private net.lyght.util.Color color;
    /** Font for the text to be rendered in */
    private Font font;

    /** Default constructor
     * @param x X coordinate
     * @param y Y coordinate
     * @param text Text
     * @param color Color of the text
     * @param font Font of the text
     * */
    public Text(int x, int y, String text, Color color, Font font) {
        super(x, y, 0, 0);

        this.text = text;
        this.color = color;
        this.font = font;
    }

    @Override
    public void render(Drawer d) {
        d.drawString(bounds.width / 2, bounds.height / 2, true, text, color, font);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

}
