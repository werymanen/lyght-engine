package net.lyght.render.gui;

import net.lyght.render.Drawer;
import net.lyght.util.shape.Rect;

/** UI element */
public class Element {

    /** The bounds of the element */
    protected Rect bounds;

    /** Default constructor
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Width
     * @param height Height
     * */
    public Element(int x, int y, int width, int height){
        bounds = new Rect(x, y, width, height);
    }

    /** Runs on every render */
    public void render(Drawer d){
    }

    /** Runs on every tick */
    public void tick(){
    }

    public int getX() {
        return bounds.x;
    }

    public void setX(int x) {
        bounds.x = x;
    }

    public int getY() {
        return bounds.y;
    }

    public void setY(int y) {
        bounds.y = y;
    }

    public int getWidth() {
        return bounds.width;
    }

    public void setWidth(int width) {
        bounds.width = width;
    }

    public int getHeight() {
        return bounds.height;
    }

    public void setHeight(int height) {
        bounds.height = height;
    }

    public Rect getBounds(){
        return bounds;
    }

    public void setBounds(Rect bounds) {
        this.bounds = bounds;
    }

}
