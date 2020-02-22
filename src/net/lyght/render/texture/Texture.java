package net.lyght.render.texture;

import net.lyght.util.Color;
import net.lyght.util.Loader;

import java.awt.image.BufferedImage;

/** Texture that can be rendered */
public class Texture {

    /** Image of the texture */
    protected BufferedImage img;
    /** Render mode */
    protected TextureRender textureRender = TextureRender.stretch;

    /** Default constructor
     * @param img The image
     * */
    public Texture(BufferedImage img){
        this.img = img;
    }

    /** Secondary constructor
     * @param color The color to be converted to an image
     * */
    public Texture(Color color){
        img = Loader.load.colorImage(color);
    }

    /** Tertiary constructor
     * The texture will have a black texture
     * */
    public Texture(){
        this.img = Loader.load.colorImage(Color.black);
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
