package org.unware.lyght.key.input;

import org.unware.lyght.render.texture.Texture;

public class Cursor {

    public int width, height;
    public Texture texture;

    public Cursor(int width, int height, Texture texture){
        this.width = width;
        this.height = height;
        this.texture = texture;
    }
}
