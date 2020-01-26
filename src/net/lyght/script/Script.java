package net.lyght.script;

import net.lyght.entity.Entity;
import net.lyght.util.LyghtException;

public class Script {

    protected Entity entity;

    public Script(){
    }

    public void start(){}
    public void tick(){}
    public void render(){}

    public final boolean add(Entity entity){
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
