package net.lyght.key.input;

import net.lyght.render.texture.Texture;

public class Cursor {

    public int width, height;
    public Texture texture;

    public Cursor(int width, int height, Texture texture){
        this.width = width;
        this.height = height;
        this.texture = texture;
    }
}
