package org.unware.lyght.entity;

import org.unware.lyght.render.Container;
import org.unware.lyght.render.Display;
import org.unware.lyght.render.Renderer;
import org.unware.lyght.script.Script;
import org.unware.lyght.script.physics.Movement;
import org.unware.lyght.util.shape.Rect;
import org.unware.lyght.util.Color;
import org.unware.lyght.util.Loader;
import org.unware.lyght.util.LyghtException;
import org.unware.lyght.render.texture.Texture;

import java.util.ArrayList;

public class Entity {

    protected Texture texture;
    protected Rect bounds;
    protected boolean active = true;

    protected Display display;
    protected Container container;

    protected ArrayList<Script> scripts = new ArrayList();

    public Entity(int x, int y, int width, int height, Color color){
        this(x, y, width, height, Loader.load.colorTexture(color));
    }

    public Entity(int x, int y, int width, int height){
        this(x, y, width, height, Color.black);
    }

    public Entity(int x, int y, int width, int height, Texture texture) {
        bounds = new Rect();
        bounds.x = x;
        bounds.y = y;
        bounds.width = width;
        bounds.height = height;

        this.texture = texture;
    }

    public Entity(Rect bounds, Texture texture){
        this(bounds.x, bounds.y, bounds.width, bounds.height, texture);
    }

    public final void tick(){
        for(Script script : scripts)
            script.tick();
    }

    public final void render(){
        for(Script script : scripts)
            script.render();
    }

    public void init(){
    }

    public boolean touchingEdge(){
        return  bounds.x >= display.getWidth()  || bounds.x + bounds.width  >= display.getWidth()  || //right
                bounds.y >= display.getHeight() || bounds.y + bounds.height >= display.getHeight() || //down
                bounds.x <= 0                   || bounds.x + bounds.width  <= 0                   || //left
                bounds.y <= 0                   || bounds.y + bounds.height <= 0                      // up
                ;
    }

    public void attachStuff(Container c, Display d){
        this.container = c;
        this.display = d;
    }


    public void addScript(Script script){
        if(script.add(this))
            scripts.add(script);
        else try{
            throw new LyghtException("Couldn't add script");
        }catch(LyghtException e){
            e.printStackTrace();
        }
    }

    public Script getScript(Class clazz){
        return getScript(clazz, false);
    }

    private Script getScript(Class clazz, boolean finding){
        for (Script script : scripts) {
            if(clazz.isInstance(script))
                return script;
        }
        if(finding) {
            try {
                throw new LyghtException("Couldn't find script.");
            } catch (LyghtException e) {
                e.printStackTrace();
            }
        }
            return null;
    }

    public boolean hasScript(Class clazz){
        if(getScript(clazz, false) == null)
            return false;
        else return true;
    }

    public void removeScript(Script script){
        scripts.remove(script);
    }

    public void removeScript(Class clazz){
        removeScript(getScript(clazz));
    }

    public boolean hasCollisionBorder(){
        return true;
    }

    public int getX(){
        return bounds.x;
    }

    public int getY(){
        return bounds.y;
    }

    public int getWidth(){
        return bounds.width;
    }

    public int getHeight(){
        return bounds.height;
    }

    public boolean isActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public Container getContainer() {
        return container;
    }

    public Display getDisplay() {
        return display;
    }

    public Renderer getRenderer(){
        return display.getRenderer();
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Rect getBounds(){
        return bounds;
    }

    public void setBounds(Rect bounds) {
        this.bounds = bounds;
    }

    public Rect getActualBounds(){
        if(this.hasScript(Movement.class) && ((Movement)(this.getScript(Movement.class))).getBounds() != null)
            return this.bounds.add(((Movement)(this.getScript(Movement.class))).getBounds());
        else return this.bounds;
    }

}
