package net.lyght.render;

import net.lyght.entity.Entity;

import java.util.ArrayList;

/** Responsible for containing entities */
public class Container {

    /** The renderer */
    private Renderer renderer;
    /** The entities */
    private ArrayList<Entity> objects = new ArrayList<>();

    /** Default container
     * @param renderer Renderer
     * */
    public Container(Renderer renderer){
        this.renderer = renderer;
    }

    /** Adds an entity to the list
     * @param e Entity to be added
     * */
    public Entity add(Entity e){
        objects.add(e);
        e.attachStuff(this, renderer.getDisplay());
        e.init();
        return e;
    }

    public ArrayList<Entity> getObjects(){
        return objects;
    }
}
