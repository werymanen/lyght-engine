package org.unware.lyght.render.gui;

import org.unware.lyght.render.Drawer;
import org.unware.lyght.util.shape.Rect;

public class Element {

    protected Rect bounds;

    public Element(int x, int y, int width, int height){
        bounds = new Rect(x, y, width, height);
    }

    public void render(Drawer d){
    }

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
