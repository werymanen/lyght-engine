package net.lyght.render;

import net.lyght.util.Color;

import java.awt.*;

public class AwtDrawer extends Drawer {

    public Graphics g;

    public AwtDrawer(Graphics g){
        this.g = g;
    }

    @Override
    public void drawRect(int x, int y, int width, int height, net.lyght.util.Color color) {
        g.setColor(color.getColor());
        g.drawRect(x, y, width, height);
    }

    @Override
    public void fillRect(int x, int y, int width, int height, net.lyght.util.Color color) {
        g.setColor(color.getColor());
        g.fillRect(x, y, width, height);
    }

    @Override
    public void clearRect(int x, int y, int width, int height) {
        g.clearRect(x, y, width, height);
    }

    @Override
    public void draw(int x, int y, int width, int height, Image img) {
        g.drawImage(img, x, y, width, height, null);
    }

    @Override
    public void drawString(int dx, int dy, boolean centered, String text, Color c, Font font) {
        g.setColor(c.getColor());
        g.setFont(font);
        int x = dx;
        int y = dy;
        if(centered) {
            FontMetrics fm = g.getFontMetrics(font);
            x = dx - fm.stringWidth(text) / 2;
            y = dy - fm.getHeight() / 2 + fm.getAscent();
        }

        g.drawString(text, x, y);

    }

    public void dispose(){
        g.dispose();
    }
}
