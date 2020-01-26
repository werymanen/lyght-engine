package net.lyght.util;

import net.lyght.render.texture.Texture;
import net.lyght.sound.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Loader {

    public static final Loader load = new Loader();


    private Loader(){
    }



    public BufferedImage resFileImage(String path){
        return fileImage(this.getClass().getResource(path).getFile());
    }

    public BufferedImage fileImage(String path){
        try {
            return ImageIO.read(new File(path));
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public Texture resFileTexture(String path){
        return new Texture(resFileImage(path));
    }

    public Texture fileTexture(String path){
        return new Texture(fileImage(path));
    }


    public Sound resFileSound(String path){
        return fileSound(this.getClass().getResource(path).getFile());
    }

    public Sound fileSound(String path){
        return new Sound(new File(path));
    }

    public BufferedImage colorImage(net.lyght.util.Color color){
        BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = img.createGraphics();

        graphics.setPaint (color.getColor());
        graphics.fillRect ( 0, 0, img.getWidth(), img.getHeight() );

        return img;
    }

    public Texture colorTexture(Color color){
        return new Texture(colorImage(color));
    }
}
