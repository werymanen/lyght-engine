package net.lyght.script;

import net.lyght.entity.Entity;
import net.lyght.util.LyghtException;

/** An interface for scripts */
public class Script {

    /** The entity it's attached to */
    protected Entity entity;

    /** Default constructor */
    public Script(){
    }

    /** Runs when the script is added to an entity */
    public void start(){}
    /** Runs on every tick */
    public void tick(){}
    /** Runs on every render */
    public void render(){}

    /** Checks if the script is able to be added to an entity
     * @param entity The entity to be added to
     * */
    public boolean add(Entity entity){
        if(this.entity != null){
            try {
                throw new LyghtException("This script is already in use.");
            }catch(LyghtException e) {
                e.printStackTrace();
                return false;
            }
        }

        this.entity = entity;
        start();

        return true;
    }

    public Entity getEntity() {
        return entity;
    }
}
