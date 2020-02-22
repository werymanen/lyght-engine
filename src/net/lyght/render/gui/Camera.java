package net.lyght.render.gui;

import net.lyght.entity.Entity;
import net.lyght.render.Container;
import net.lyght.render.Drawer;

/** Camera, that renders entities to the display */
public class Camera extends Element {

    /** Container that contains the entities */
    private Container container;
    /** The position of the camera */
    private int gx, gy;
    /** The entity to follow */
    private Entity center;

    /** Default constructor
     * @param x X coordinate of the frame
     * @param y Y coordinate of the frame
     * @param width Width
     * @param height Height
     * @param gx X coordinate on the scene
     * @param gy Y coordinate on the scene
     * @param container Container
     * */
    public Camera(int x, int y, int width, int height, int gx, int gy, Container container){
        super(x, y, width, height);
        this.gx = gx;
        this.gy = gy;
        this.container = container;
    }

    @Override
    public void render(Drawer d) {
        if(center != null) {
            gx = (center.getX() - bounds.width / 2 + center.getWidth() / 2);
            gy = (center.getY() - bounds.height / 2 + center.getHeight() / 2);
        }
        for(Entity e : container.getObjects()) {
            if(!e.isActive())
                continue;
            e.render();
            d.draw(e.getX() - gx, e.getY() - gy, e.getWidth(), e.getHeight(), e.getTexture());
        }
    }

    public void setCenter(Entity e){
        center = e;
    }

    public Entity getCenter(){
        return center;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public int getGx() {
        return gx;
    }

    public void setGx(int gx) {
        this.gx = gx;
    }

    public int getGy() {
        return gy;
    }

    public void setGy(int gy) {
        this.gy = gy;
    }
}
