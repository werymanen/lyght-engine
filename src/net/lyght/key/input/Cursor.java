package net.lyght.key.input;

import net.lyght.render.texture.Texture;

/** Class for storing data about a cursor */
public class Cursor {

    /** The cursor's width and height */
    public int width, height;
    /** The texture rendered */
    public Texture texture;

    /** Default constructor
     * @param width The width of the cursor
     * @param height The height of the cursor
     * @param texture The texture of the cursor
     * */
    public Cursor(int width, int height, Texture texture){
        this.width = width;
        this.height = height;
        this.texture = texture;
    }
}
