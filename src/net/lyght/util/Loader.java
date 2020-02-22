package net.lyght.util;

import net.lyght.render.texture.Texture;
import net.lyght.sound.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/** Responsible for loading stuff */
public class Loader {

    /** Instance */
    public static final Loader load = new Loader();

    /** Default constructor */
    private Loader(){
    }

    /** Loads an image from resources
     * @param path The path of the image in the resources
     * */
    public BufferedImage resFileImage(String path){
        return fileImage(this.getClass().getResource(path).getFile());
    }

    /** Loads an image
     * @param path The path of the image on the computer
     * */
    public BufferedImage fileImage(String path){
        try {
            return ImageIO.read(new File(path));
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }


    /** Loads a texture from the resources
     * @param path The path of the image in the resources
     * */
    public Texture resFileTexture(String path){
        return new Texture(resFileImage(path));
    }

    /** Loads a texture
     * @param path The path of the image on the computer
     * */
    public Texture fileTexture(String path){
        return new Texture(fileImage(path));
    }


    /** Loads a sound from resources
     * @param path The path of the audio in the resources
     * */
    public Sound resFileSound(String path){
        return fileSound(this.getClass().getResource(path).getFile());
    }

    /** Loads a sound
     * @param path The path of the audio on the computer
     * */
    public Sound fileSound(String path){
        return new Sound(new File(path));
    }


    /** @return An image, made out of a provided color
     * @param color The provided color
     * */
    public BufferedImage colorImage(Color color){
        BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = img.createGraphics();

        graphics.setPaint (color.toColor());
        graphics.fillRect ( 0, 0, img.getWidth(), img.getHeight() );

        return img;
    }

    /** @return A texture, made out of a provided color
     * @param color The provided color
     * */
    public Texture colorTexture(Color color){
        return new Texture(colorImage(color));
    }

}
