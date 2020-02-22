package net.lyght.main;

import net.lyght.render.Container;
import net.lyght.render.Display;
import net.lyght.render.Renderer;
import net.lyght.render.gui.Camera;

/** Lyght Game */
public class Game extends PrimitiveGame {

    /** Frame that the game is rendered in */
    protected Display display;
    /** Renderer that renders to the display */
    protected Renderer renderer;
    /** Container that contains entities */
    protected Container container;
    /** Camera that the entities are rendered with */
    protected Camera camera;

    /** Default constructor */
    protected Game(){
        display = new Display();
        renderer = display.getRenderer();
        container = renderer.getContainer();
        camera = new Camera(0, 0, display.getWidth(), display.getHeight(), 0, 0, container);
        renderer.add(camera);
    }

    /** Event, called a given times a second */
    @Override
    public final void tick0() {
        renderer.tick();
        display.tick();
        camera.setWidth(display.getWidth());
        camera.setHeight(display.getHeight());
    }

    /** Event, called when rendering */
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
