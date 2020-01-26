package net.lyght.render.texture;

import net.lyght.util.Color;
import net.lyght.util.Loader;

import java.awt.image.BufferedImage;

public class Texture {

    protected BufferedImage img;
    protected TextureRender textureRender = TextureRender.stretch;

    public Texture(){
        this.img = Loader.load.colorImage(Color.black);
    }
    public Texture(BufferedImage img){
        this.img = img;
    }
    public Texture(Color color){
        img = Loader.load.colorImage(color);
    }


    public BufferedImage getImage() {
        return img;
    }

    public void setImage(BufferedImage img) {
        this.img = img;
    }

    public TextureRender getTextureRender() {
        return textureRender;
    }

    public void setTextureRender(TextureRender textureRender) {
        this.textureRender = textureRender;
    }

    public int getWidth(){
        return img.getWidth();
    }

    public int getHeight(){
        return img.getHeight();
    }
}
