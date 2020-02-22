package net.lyght.entity;

import net.lyght.main.Lyght;
import net.lyght.util.Color;
import net.lyght.util.Loader;
import net.lyght.util.LyghtException;
import net.lyght.util.shape.Rect;
import net.lyght.render.Container;
import net.lyght.render.Display;
import net.lyght.render.Renderer;
import net.lyght.script.Script;
import net.lyght.script.physics.Movement;
import net.lyght.render.texture.Texture;

import java.util.ArrayList;

/** Technically game object */
public class Entity {

    /** The entity's texture */
    protected Texture texture;
    /** A rectangle, the position of the entity */
    protected Rect bounds;
    /** If true, entity and its scripts won't tick or render */
    protected boolean active = true;

    /** Stores the game's display */
    protected Display display;
    /** Stores the game's container */
    protected Container container;

    /** The entity's scripts */
    protected ArrayList<Script> scripts = new ArrayList();

    /** Entity constructor, which makes a texture based on a color
     * @param x X coordinate of the entity
     * @param y Y coordinate of the entity
     * @param width Width of the entity
     * @param height Height of the entity
     * */
    public Entity(int x, int y, int width, int height, Color color){
        this(x, y, width, height, Loader.load.colorTexture(color));
    }

    /** Entity constructor, with a black texture
     * @param x X coordinate of the entity
     * @param y Y coordinate of the entity
     * @param width Width of the entity
     * @param height Height of the entity
     * */
    public Entity(int x, int y, int width, int height){
        this(x, y, width, height, Color.black);
    }

    /** Entity constructor
     * @param x X coordinate of the entity
     * @param y Y coordinate of the entity
     * @param width Width of the entity
     * @param height Height of the entity
     * @param texture Texture of the entity
     * */
    public Entity(int x, int y, int width, int height, Texture texture) {
        bounds = new Rect();
        bounds.x = x;
        bounds.y = y;
        bounds.width = width;
        bounds.height = height;

        this.texture = texture;
    }

    /** Entity constructor
     * @param bounds Bounds of an entity, takes in a Rectangle
     * @param texture Texture of an entity
     * */
    public Entity(Rect bounds, Texture texture){
        this(bounds.x, bounds.y, bounds.width, bounds.height, texture);
    }

    /** Runs given times a second, meant to do the logic
     * @see Lyght#getMaxTps()
     * */
    public final void tick(){
        for(Script script : scripts)
            script.tick();
    }

    /** Runs every time the display renders
     * @see Lyght#getMaxFps()
     * */
    public final void render(){
        for(Script script : scripts)
            script.render();
    }

    /** Runs when the entity is added to a container */
    public void init(){
    }

    /** Tells if entity is touching the display's edge */
    public boolean touchingEdge(){
        return  bounds.x >= display.getWidth()  || bounds.x + bounds.width  >= display.getWidth()  || //right
                bounds.y >= display.getHeight() || bounds.y + bounds.height >= display.getHeight() || //down
                bounds.x <= 0                   || bounds.x + bounds.width  <= 0                   || //left
                bounds.y <= 0                   || bounds.y + bounds.height <= 0                      // up
                ;
    }

    /** Declares the container and display
     * Runs when added to container
     * @param c The container
     * @param d The display
     * */
    public void attachStuff(Container c, Display d){
        this.container = c;
        this.display = d;
    }

    /** Adds script to scripts
     * @see #scripts
     * @param script The script that is being added
     * */
    public void addScript(Script script){
        if(script.add(this))
            scripts.add(script);
        else try{
            throw new LyghtException("Couldn't add script");
        }catch(LyghtException e){
            e.printStackTrace();
        }
    }

    /** Gets a script based on type
     * @param clazz The type of Script
     * @return The script (if found)
     * */
    public Script getScript(Class clazz){
        return getScript(clazz, true);
    }

    /** Gets a script based on type
     * @param clazz The type
     * @param finding If true, throws error, if didn't find
     * */
    private Script getScript(Class clazz, boolean finding){
        for (Script script : scripts) {
            if(clazz.isInstance(script))
                return script;
        }
        //continues if didn't find
        if(finding) {
            try {
                throw new LyghtException("Couldn't find script.");
            } catch (LyghtException e) {
                e.printStackTrace();
            }
        }
            return null;
    }

    /** Tells if entity has a script based on type
     * @param clazz The type
     * @return Found
     * */
    public boolean hasScript(Class clazz){
        if(getScript(clazz, false) == null)
            return false;
        else return true;
    }

    /** Removes a script
     * @param script The script to be removed
     * */
    public void removeScript(Script script){
        scripts.remove(script);
    }

    /** Removes a script based on type
     * @param clazz The type
     * */
    public void removeScript(Class clazz){
        removeScript(getScript(clazz));
    }

    /** Tells if the entity is affected by borders
     * @return The value
     * (Meant to be overrided)
     * */
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

    /** @return If has Movement script, its bounds, else entity bounds */
    public Rect getActualBounds(){
        if(this.hasScript(Movement.class) && ((Movement)(this.getScript(Movement.class))).getBounds() != null)
            return this.bounds.add(((Movement)(this.getScript(Movement.class))).getBounds());
        else return this.bounds;
    }

}
