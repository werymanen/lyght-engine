package net.lyght.render;

import net.lyght.entity.Entity;

import java.util.ArrayList;

public class Container {

    private Renderer renderer;
    private ArrayList<Entity> objects = new ArrayList<>();

    public Container(Renderer renderer){
        this.renderer = renderer;
    }

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
