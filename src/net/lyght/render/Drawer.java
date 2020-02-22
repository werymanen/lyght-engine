package net.lyght.render;

import net.lyght.util.Color;
import net.lyght.util.shape.Rect;
import net.lyght.render.texture.TextureRender;
import net.lyght.render.texture.Texture;

import java.awt.*;

/** Responsible for making an interface to allow others to draw */
public abstract class Drawer {

    /** Needed for cropping */
    private Drawer thls = this;

    /** Default constructor */
    protected Drawer(){
    }

    /** Cropping down the drawer
     * @param x The X coordinate of the new Drawer
     * @param y The Y coordinate of the new Drawer
     * @return The cropped drawer
     * */
    public Drawer crop(int x, int y){
        return new Drawer() {
            Drawer parent = thls;
        @Override
        public void drawRect(int dx, int dy, int dwidth, int dheight, Color color) {
            parent.drawRect(x + dx, y + dy, dwidth, dheight, color);
        }

        @Override
        public void fillRect(int dx, int dy, int dwidth, int dheight, Color color) {
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
        public void drawString(int dx, int dy, boolean centered, String text, Color color, Font font) {
            parent.drawString(x + dx, y + dy, centered, text, color, font);
        }
       };
    }

    /** Draws a rect based on a color
     * @param rect Bounds of the color
     * @param color Color
     * */
    public void drawRect(Rect rect, Color color){
        drawRect(rect.x, rect.y, rect.width, rect.height, color);
    }

    /** Fills a rect based on a color
     * @param rect Bounds of the color
     * @param color Color
     * */
    public void fillRect(Rect rect, Color color){
        fillRect(rect.x, rect.y, rect.width, rect.height, color);
    }

    /** Clears a rect
     * @param rect Bounds of clearing
     * */
    public void clearRect(Rect rect){
        clearRect(rect.x, rect.y, rect.width, rect.height);
    }

    /** Draws an image
     * @param rect Bounds of the texture
     * @param img Image
     * */
    public void draw(Rect rect, Image img){
        draw(rect.x, rect.y, rect.width, rect.height, img);
    }

    /** Draws a texture
     * @param rect Bounds of the texture
     * @param texture Texture
     * */
    public void draw(Rect rect, Texture texture){
        draw(rect.x, rect.y, rect.width, rect.height, texture);
    }

    /** Draws a rect based on a color
     * @param x X coordinate of the color
     * @param y Y coordinate of the color
     * @param width Width of the color
     * @param height Height of the color
     * @param color Color
     * */
    public abstract void drawRect(int x, int y, int width, int height, Color color);

    /** Fills a rect based on a color
     * @param x X coordinate of the color
     * @param y Y coordinate of the color
     * @param width Width of the color
     * @param height Height of the color
     * @param color Color
     * */
    public abstract void fillRect(int x, int y, int width, int height, Color color);

    /** Clears a rect
     * @param x X coordinate of clearing
     * @param y Y coordinate of clearing
     * @param width Width of clearing
     * @param height Height of clearing
     * */
    public abstract void clearRect(int x, int y, int width, int height);

    /** Draws an image
     * @param x X coordinate of the image
     * @param y Y coordinate of the image
     * @param width Width of the image
     * @param height Height of the image
     * @param img Image
     * */
    public abstract void draw(int x, int y, int width, int height, Image img);

    /** Draws a text
     * @param x X coordinate of the text
     * @param y Y coordinate of the text
     * @param centered If the X and Y coordinate are centered
     * @param text The text
     * @param color Color of the text
     * @param font Font of the text
     * */
    public abstract void drawString(int x, int y, boolean centered, String text, Color color, Font font);

    /** Draws a texture
     * Needed to calculate texture render modes
     * @param x X coordinate of the object
     * @param y Y coordinate of the object
     * @param width Width of the object
     * @param height Height of the object
     * @param texture Texture of the object
     * */
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
