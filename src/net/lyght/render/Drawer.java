package net.lyght.render;

import net.lyght.util.Color;
import net.lyght.util.shape.Rect;
import net.lyght.render.texture.TextureRender;
import net.lyght.render.texture.Texture;

import java.awt.*;

public abstract class Drawer {

    private Drawer thls = this;

    protected Drawer(){
    }

    public Drawer crop(int x, int y){
        return new Drawer() {
            Drawer parent = thls;
        @Override
        public void drawRect(int dx, int dy, int dwidth, int dheight, net.lyght.util.Color color) {
            parent.drawRect(x + dx, y + dy, dwidth, dheight, color);
        }

        @Override
        public void fillRect(int dx, int dy, int dwidth, int dheight, net.lyght.util.Color color) {
            parent.fillRect(x + dx, y + dy, dwidth, dheight, color);
        }

        @Override
        public void clearRect(int dx, int dy, int dwidth, int dheight) {
            parent.clearRect(x + dx, y + dy, dwidth, dheight);
        }

        @Override
        public void draw(int dx, int dy, int width, int height, Image img) {
            parent.draw(x + dx, y + dy, width, height, img);
        }

        @Override
        public void draw(int dx, int dy, int width, int height, Texture texture) {
             parent.draw(x + dx, y + dy, width, height, texture);
        }

        @Override
        public void drawString(int dx, int dy, boolean centered, String text, net.lyght.util.Color color, Font font) {
            parent.drawString(x + dx, y + dy, centered, text, color, font);
        }
       };
    }

    public void drawRect(Rect rect, net.lyght.util.Color color){
        drawRect(rect.x, rect.y, rect.width, rect.height, color);
    }
    public void fillRect(Rect rect, net.lyght.util.Color color){
        fillRect(rect.x, rect.y, rect.width, rect.height, color);
    }
    public void clearRect(Rect rect){
        clearRect(rect.x, rect.y, rect.width, rect.height);
    }
    public void draw(Rect rect, Image img){
        draw(rect.x, rect.y, rect.width, rect.height, img);
    }
    public void draw(Rect rect, Texture texture){
        draw(rect.x, rect.y, rect.width, rect.height, texture);
    }

    public abstract void drawRect(int x, int y, int width, int height, net.lyght.util.Color color);
    public abstract void fillRect(int x, int y, int width, int height, net.lyght.util.Color color);
    public abstract void clearRect(int x, int y, int width, int height);
    public abstract void draw(int x, int y, int width, int height, Image img);
    public abstract void drawString(int x, int y, boolean centered, String text, Color color, Font font);

    public void draw(int x, int y, int width, int height, Texture texture){
        if(texture.getTextureRender() == TextureRender.stretch)
            draw(x, y, width, height, texture.getImage());

        if(texture.getTextureRender() == TextureRender.multiply){
            for (int x0 = 0; x0 < width / texture.getWidth(); x0++) {
                for (int y0 = 0; y0 < height / texture.getHeight(); y0++) {
                    draw((x + x0 * texture.getWidth()), (y + y0 * texture.getHeight()), texture.getWidth(), texture.getHeight(), texture.getImage());
                    }
                }
            }

        if(texture.getTextureRender() == TextureRender.single)
            draw(x + (width / 2) - (texture.getWidth() / 2), y + (height / 2) - (texture.getHeight() / 2), texture.getWidth(), texture.getHeight(), texture.getImage());
    }

}
