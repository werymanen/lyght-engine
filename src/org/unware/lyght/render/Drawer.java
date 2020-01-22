package org.unware.lyght.render;

import org.unware.lyght.render.texture.TextureRender;
import org.unware.lyght.util.shape.Rect;
import org.unware.lyght.render.texture.Texture;
import org.unware.lyght.util.Color;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Drawer {

    private Drawer thls = this;

    protected Drawer(){
    }

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

    public void drawRect(Rect rect, Color color){
        drawRect(rect.x, rect.y, rect.width, rect.height, color);
    }
    public void fillRect(Rect rect, Color color){
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

    public abstract void drawRect(int x, int y, int width, int height, Color color);
    public abstract void fillRect(int x, int y, int width, int height, Color color);
    public abstract void clearRect(int x, int y, int width, int height);
    public abstract void draw(int x, int y, int width, int height, Image img);
    public abstract void drawString(int x, int y, boolean centered, String text, Color color, Font font);

    public void draw(int x, int y, int width, int height, Texture texture){
        if(texture.getTextureRender() == TextureRender.stretch)
            draw(x, y, width, height, texture.getImage());

/*
        if(texture.getTextureRender() == TextureRender.multiply){
            for (int x0 = 0; x0 < width / texture.getWidth(); x0++) {
                for (int y0 = 0; y0 < height / texture.getHeight(); y0++) {

                    if(!((x + x0 * texture.getWidth()) + texture.getWidth() > width) || ((y + y0 * texture.getHeight()) + texture.getHeight() > height)){
                        draw((x + x0 * texture.getWidth()), (y + y0 * texture.getHeight()), texture.getWidth(), texture.getHeight(), texture.getImage());
                    }else{
                        draw((x + x0 * texture.getWidth()), (y + y0 * texture.getHeight()), texture.getWidth(), texture.getHeight(), texture.getImage());
                        /*
                        //stolen (or in business language credit) from https://stackoverflow.com/a/23585894 i stole it all xd
                        BufferedImage img = texture.getImage().getSubimage(0, 0, x0 * (x + x0 * texture.getWidth()), (y + y0 * texture.getHeight()));
                        //fillRect(0, 0, (int) (bounds.width / health.getMaxhealth() * health.getHealth()), bounds.height, Color.green); copied from beta game Trumpy
                        BufferedImage copyOfImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB); //according to https://stackoverflow.com/a/23585894, it's important or something I didn't read it
                        Graphics g = copyOfImage.createGraphics();
                        g.drawImage(img, 0, 0, null);
                        draw((x + (x0 + 1) * texture.getWidth()), (y + (y0 + 1) * texture.getHeight()), texture.getWidth(), texture.getHeight(), copyOfImage);
                        */
/*
                    }
                }
            }
        }*/

        if(texture.getTextureRender() == TextureRender.single)
            draw(x + (width / 2) - (texture.getWidth() / 2), y + (height / 2) - (texture.getHeight() / 2), texture.getWidth(), texture.getHeight(), texture.getImage());
    }

}
