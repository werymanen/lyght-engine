package org.unware.lyght.main;

import org.unware.lyght.render.Container;
import org.unware.lyght.render.Display;
import org.unware.lyght.render.Renderer;
import org.unware.lyght.render.gui.Camera;

public class Game extends PrimitiveGame {

    protected Display display;
    protected Renderer renderer;
    protected Container container;
    protected Camera camera;

    protected Game(){
        display = new Display();
        renderer = display.getRenderer();
        container = renderer.getContainer();
        camera = new Camera(0, 0, display.getWidth(), display.getHeight(), 0, 0, container);
        renderer.add(camera);
    }

    @Override
    public final void tick0() {
        renderer.tick();
        display.tick();
        camera.setWidth(display.getWidth());
        camera.setHeight(display.getHeight());
    }

    @Override
    public final void render0() {
        renderer.render();
    }

    public Display getDisplay() {
        return display;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Container getContainer() {
        return container;
    }
}
