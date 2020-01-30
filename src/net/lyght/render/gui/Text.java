package net.lyght.render.gui;

import net.lyght.render.Drawer;
import net.lyght.util.Color;

import java.awt.*;

public class Text extends Element {

    private String text;
    private net.lyght.util.Color color;
    private Font font;

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
